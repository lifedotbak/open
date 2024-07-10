package com.flyflow.core.expression.condition.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;

import com.flyflow.common.constants.FormTypeEnum;
import com.flyflow.common.dto.flow.Condition;
import com.flyflow.core.expression.ExpressionHandler;
import com.flyflow.core.expression.condition.NodeConditionStrategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/** 字符类型处理器 */
@Component
public class DateTimeNodeConditionStrategy implements NodeConditionStrategy, InitializingBean {
    /** 抽象方法 处理表达式 */
    @Override
    public String handleExpression(Condition condition) {

        String compare = condition.getExpression();
        String id = condition.getKey();
        Object value = condition.getValue();

        return StrUtil.format(
                "(expressionHandler.dateTimeHandler(\"{}\",\"{}\",\"{}\",execution,\"yyyy-MM-dd "
                        + "HH:mm:ss\"))",
                id,
                compare,
                value);
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

        return bean.dateTimeHandler(id, compare, value, paramMap.get(id), "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(FormTypeEnum.DATE_TIME.getType());
    }
}