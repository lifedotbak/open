package com.flyflow.biz.service.impl;

import com.flyflow.biz.entity.UserRole;
import com.flyflow.biz.mapper.UserRoleMapper;
import com.flyflow.biz.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyflow.common.dto.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户-角色 服务实现类
 *
 * @author Vincent
 * @since 2023-06-08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
        implements IUserRoleService {

    /**
     * 根据用户id获取角色key
     *
     * @param userId
     * @return
     */
    @Override
    public R<List<UserRole>> queryListByUserId(String userId) {
        List<UserRole> userRoleList = this.lambdaQuery().eq(UserRole::getUserId, userId).list();
        return R.success(userRoleList);
    }
}