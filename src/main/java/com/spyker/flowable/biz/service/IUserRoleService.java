package com.spyker.flowable.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.flowable.biz.entity.UserRole;
import com.spyker.flowable.common.dto.R;

import java.util.List;

/**
 * 用户-角色 服务类
 *
 * @author Vincent
 * @since 2023-06-08
 */
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 根据用户id获取角色key
     *
     * @param userId
     * @return
     */
    R<List<UserRole>> queryListByUserId(String userId);
}