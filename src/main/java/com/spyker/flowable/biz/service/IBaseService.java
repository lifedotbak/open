package com.spyker.flowable.biz.service;

import com.spyker.flowable.biz.vo.*;
import com.spyker.flowable.common.dto.IndexPageStatistics;
import com.spyker.flowable.common.dto.R;

public interface IBaseService {

    /**
     * 修改前端版本号
     *
     * @param webVersionVO
     * @return
     */
    R setWebVersion(WebVersionVO webVersionVO);

    /**
     * 获取当前系统前端版本号
     *
     * @return
     */
    R getWebVersion();

    /**
     * 首页数据
     *
     * @return
     */
    R<IndexPageStatistics> index();

    /**
     * 获取所有地区数据
     *
     * @return
     */
    R areaList();

    /**
     * 同步数据
     *
     * @return
     */
    R loadRemoteData();

    /**
     * 格式化流程显示
     *
     * @param nodeFormatParamVo
     * @return
     */
    R<NodeFormatResultVo> formatStartNodeShow(NodeFormatParamVo nodeFormatParamVo);

    /**
     * 查询头部显示数据
     *
     * @param nodeFormatParamVo
     * @return
     */
    R<TaskHeaderShowResultVO> queryHeaderShow(QueryFormListParamVo nodeFormatParamVo);

    /**
     * 获取任务操作列表
     *
     * @param taskId
     * @return
     */
    R<TaskOperDataResultVO> queryTaskOperData(String taskId);
}