package com.flyflow.biz.service;

import com.flyflow.common.dto.R;
import com.flyflow.common.dto.flow.NodeUser;

import java.util.List;

/**
 * 角色 服务类
 *
 * @author Vincent
 * @since 2023-06-08
 */
public interface IRoleBizService {

    /**
     * 查询所有角色
     *
     * @return
     */
    R queryAll();

    /**
     * 保存角色用户
     *
     * @param nodeUserDtoList
     * @param id
     * @return
     */
    R saveUserList(List<NodeUser> nodeUserDtoList, String id);
}