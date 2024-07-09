package cc.flyflow.biz.form;

import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.flow.FormItemVO;

import java.util.Map;

/** 表单接口 */
public interface FormStrategy {

    /**
     * 策略注册方法
     *
     * @param key
     */
    default void afterPropertiesSet(String key) {
        FormStrategyFactory.register(key, this);
    }

    /**
     * 判断两个值是否一致
     *
     * @param v1 第一个值
     * @param v2 第二个值
     * @return true表示一致
     */
    boolean isSameValue(Object v1, Object v2);

    /**
     * 处理空值
     *
     * @param value
     * @return
     */
    default Object handleBlankValue(Object value) {
        return value;
    }

    /**
     * 检查字段格式
     *
     * @param value
     * @return
     */
    default R checkValue(Object value) {
        return R.success();
    }

    /**
     * 数据的长度
     *
     * @param s
     * @return
     */
    int length(String s);

    /**
     * 判断是否为空
     *
     * @param value
     * @param formItemVO
     * @param paramMap
     * @return
     */
    default boolean isBlank(Object value, FormItemVO formItemVO, Map<String, Object> paramMap) {
        return value == null;
    }
}