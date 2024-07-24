package com.spyker.flowable.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.spyker.flowable.biz.api.ApiStrategyFactory;
import com.spyker.flowable.biz.entity.UserRole;
import com.spyker.flowable.biz.service.IRoleBizService;
import com.spyker.flowable.biz.service.IUserRoleService;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.flow.NodeUser;
import com.spyker.flowable.common.dto.third.RoleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * 角色 服务实现类
 *
 * @author Vincent
 * @since 2023-06-08
 */
@Service
public class RoleBizServiceImpl implements IRoleBizService {
    @Resource private IUserRoleService userRoleService;

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public R queryAll() {
        List<RoleDto> roleDtoList = ApiStrategyFactory.getStrategy().loadAllRole();
        return R.success(roleDtoList);
    }

    /**
     * 保存角色用户
     *
     * @param nodeUserDtoList
     * @param id
     * @return
     */
    @Transactional
    @Override
    public R saveUserList(List<NodeUser> nodeUserDtoList, String id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getRoleId, id);
        userRoleService.remove(queryWrapper);

        for (NodeUser nodeUserDto : nodeUserDtoList) {
            UserRole userRole = new UserRole();

            userRole.setUserId(nodeUserDto.getId());
            userRole.setRoleId(id);
            userRoleService.save(userRole);
        }

        return R.success();
    }
}