package com.spyker.flowable.biz.service;

import com.spyker.flowable.biz.vo.UserListQueryVO;
import com.spyker.flowable.common.dto.R;

/**
 * 用户表 服务类
 *
 * @author xiaoge
 * @since 2023-05-05
 */
public interface IUserBizService {

    /**
     * 获取当前用户详细信息
     *
     * @return
     */
    R getCurrentUserDetail();

    /**
     * 查询用户列表
     *
     * @param userListQueryVO
     * @return
     */
    R queryList(UserListQueryVO userListQueryVO);
}