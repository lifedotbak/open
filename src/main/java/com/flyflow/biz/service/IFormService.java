package com.flyflow.biz.service;

import com.flyflow.biz.vo.FormRemoteSelectOptionParamVo;
import com.flyflow.biz.vo.QueryFormListParamVo;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.flow.FormItemVO;

import java.util.List;

/** 表单服务 */
public interface IFormService {
    /**
     * 远程请求下拉选项
     *
     * @param formRemoteSelectOptionParamVo
     * @return
     */
    R selectOptions(FormRemoteSelectOptionParamVo formRemoteSelectOptionParamVo);

    /**
     * 获取表单数据
     *
     * @param taskDto
     * @return
     */
    R<List<FormItemVO>> getFormList(QueryFormListParamVo taskDto);

    /**
     * 表单详情
     *
     * @param taskDto
     * @return
     */
    R getFormDetail(QueryFormListParamVo taskDto);

    /**
     * 动态表单
     *
     * @param taskDto
     * @return
     */
    R<List<FormItemVO>> dynamicFormList(QueryFormListParamVo taskDto);
}