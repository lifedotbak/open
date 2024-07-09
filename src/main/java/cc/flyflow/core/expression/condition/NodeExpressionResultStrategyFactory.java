package cc.flyflow.core.expression.condition;

import cc.flyflow.common.dto.flow.Condition;
import cc.flyflow.common.dto.flow.GroupCondition;
import cc.flyflow.common.dto.flow.node.EmptyNode;
import cc.flyflow.common.utils.JsonUtil;
import cc.flyflow.core.utils.DataUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NodeExpressionResultStrategyFactory {

    private static final Map<String, NodeConditionStrategy> STRATEGY_CONCURRENT_HASH_MAP =
            new ConcurrentHashMap<>();

    /**
     * 提供获取策略的方法
     *
     * @param key
     * @return
     */
    public static NodeConditionStrategy getStrategy(String key) {
        NodeConditionStrategy sendService = STRATEGY_CONCURRENT_HASH_MAP.get(key);
        return sendService;
    }

    /**
     * 在Bean属性初始化后执行该方法
     *
     * @param key 批次类型枚举
     * @param nodeConditionHandler 表达式处理接口
     */
    public static void register(String key, NodeConditionStrategy nodeConditionHandler) {
        STRATEGY_CONCURRENT_HASH_MAP.put(key, nodeConditionHandler);
    }

    /**
     * 表达式结果处理
     *
     * @param condition 表达式对象
     * @param paramMap 所有的参数
     * @param tenantId
     * @return
     */
    public static boolean handleSingleConditionResult(
            Condition condition, Map<String, Object> paramMap, String tenantId) {

        NodeConditionStrategy nodeConditionHandler = getStrategy(condition.getKeyType());

        return nodeConditionHandler.handleResult(condition, paramMap, tenantId);
    }

    /**
     * 组内处理表达式结果
     *
     * @param groupDto
     * @param tenantId
     * @return
     */
    public static boolean handleGroupConditionResult(
            GroupCondition groupDto, Map<String, Object> paramMap, String tenantId) {

        List<Boolean> exps = new ArrayList<>();

        for (Condition condition : groupDto.getConditionList()) {
            boolean b = handleSingleConditionResult(condition, paramMap, tenantId);
            exps.add(b);
        }
        Boolean mode = groupDto.getMode();

        if (!mode) {
            // 或
            String join = CollUtil.join(exps, "||");

            return DataUtil.expression("${(" + join + ")}", Dict.create());
        }

        String join = CollUtil.join(exps, "&&");

        return DataUtil.expression("${(" + join + ")}", Dict.create());
    }

    public static boolean handle(
            EmptyNode emptyNode, Map<String, Object> paramMap, String tenantId) {
        List<Boolean> exps = new ArrayList<>();

        List<GroupCondition> groups = emptyNode.getConditionList();
        if (CollUtil.isEmpty(groups)) {
            return true;
        }
        for (GroupCondition group : groups) {
            boolean s = handleGroupConditionResult(group, paramMap, tenantId);
            exps.add(s);
        }

        if (emptyNode.getGroupRelationMode()) {

            if (!emptyNode.getMode()) {
                String join = CollUtil.join(exps, "||");

                return DataUtil.expression("${(" + join + ")}", Dict.create());
            }

            String join = CollUtil.join(exps, "&&");

            return DataUtil.expression("${(" + join + ")}", Dict.create());
        }
        Object groupRelation = emptyNode.getGroupRelation();

        List<Map> mapList = JsonUtil.toList(Map.class, groupRelation);

        StringBuilder expStr = new StringBuilder();
        for (Map map : mapList) {
            String str = MapUtil.getStr(map, "exp");

            expStr.append(str);
        }

        String expStrString = expStr.toString();

        int index = 0;
        for (boolean exp : exps) {
            expStrString = StrUtil.replace(expStrString, "c" + (index + 1), Convert.toStr(exp));

            index++;
        }

        return DataUtil.expression("${(" + expStrString + ")}", Dict.create());
    }
}