package com.flyflow.biz.form.impl;

import com.flyflow.biz.form.FormStrategy;
import com.flyflow.common.constants.FormTypeEnum;
import com.flyflow.common.dto.flow.FormItemVO;
import com.flyflow.common.dto.flow.UploadValue;
import com.flyflow.common.utils.JsonUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UploadImageFormStrategyImpl implements InitializingBean, FormStrategy {
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(FormTypeEnum.UPLOAD_IMAGE.getType());
    }

    /**
     * 判断两个值是否一致
     *
     * @param v1 第一个值
     * @param v2 第二个值
     * @return true表示一致
     */
    @Override
    public boolean isSameValue(Object v1, Object v2) {
        String jsonString1 = JsonUtil.toJSONString(v1);
        String jsonString2 = JsonUtil.toJSONString(v2);

        // 都为空
        if (StrUtil.isAllBlank(jsonString1, jsonString2)) {
            return true;
        }
        // 至少有一个不为空
        // 如果有一个空的 肯定不一致
        if (StrUtil.isBlank(jsonString1) || StrUtil.isBlank(jsonString2)) {
            return false;
        }
        List<UploadValue> list1 = JsonUtil.parseArray(jsonString1, UploadValue.class);
        List<UploadValue> list2 = JsonUtil.parseArray(jsonString2, UploadValue.class);
        if (list1.size() != list2.size()) {
            // 数量不一致 肯定不匹配
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            // 挨个判断每个值
            UploadValue relatedProcessValue1 = list1.get(i);
            UploadValue relatedProcessValue2 = list2.get(i);
            if (!relatedProcessValue1.getUrl().equals(relatedProcessValue2.getUrl())) {
                return false;
            }
        }

        return true;
    }

    /**
     * 数据的长度
     *
     * @param s
     * @return
     */
    @Override
    public int length(String s) {
        List<UploadValue> list = JsonUtil.parseArray(s, UploadValue.class);

        return list.size();
    }

    /**
     * 判断是否为空
     *
     * @param value
     * @param formItemVO
     * @param paramMap
     * @return
     */
    @Override
    public boolean isBlank(Object value, FormItemVO formItemVO, Map<String, Object> paramMap) {
        if (value == null) {
            return true;
        }
        List<UploadValue> nodeUserList =
                JsonUtil.parseArray(JsonUtil.toJSONString(value), UploadValue.class);

        return CollUtil.isEmpty(nodeUserList);
    }
}