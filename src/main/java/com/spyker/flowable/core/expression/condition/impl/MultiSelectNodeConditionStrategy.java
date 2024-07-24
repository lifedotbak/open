package com.spyker.flowable.core.expression.condition.impl;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;

import com.spyker.flowable.common.constants.FormTypeEnum;
import com.spyker.flowable.common.dto.flow.Condition;
import com.spyker.flowable.core.expression.ExpressionHandler;
import com.spyker.flowable.core.expression.condition.NodeConditionStrategy;
import com.spyker.framework.util.JsonUtil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/** 字符类型处理器 */
@Component
public class MultiSelectNodeConditionStrategy implements NodeConditionStrategy, InitializingBean {
    /** 抽象方法 处理表达式 */
    @Override
    public String handleExpression(Condition condition) {

        String compare = condition.getExpression();
        String id = condition.getKey();
        Object value = condition.getValue();

        return StrUtil.format(
                "(expressionHandler.selectHandler(\"{}\", execution,\"{}\",\"{}\"))",
                id,
                EscapeUtil.escape(JsonUtil.toJSONString(value)),
                compare);
    }

    /**
     * 处理数据
     *
     * @param condition
     * @param paramMap
     * @param tenantId
     * @return
     */
    @Override
    public boolean handleResult(
            Condition condition, Map<String, Object> paramMap, String tenantId) {

        String compare = condition.getExpression();
        String id = condition.getKey();
        Object value = condition.getValue();

        ExpressionHandler bean = SpringUtil.getBean(ExpressionHandler.class);
        return bean.selectHandler(
                id, paramMap, EscapeUtil.escape(JsonUtil.toJSONString(value)), compare);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(FormTypeEnum.MULTI_SELECT.getType());
    }
}