package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.api.ApiStrategyFactory;
import cc.flyflow.biz.entity.UserRole;
import cc.flyflow.biz.service.IRoleBizService;
import cc.flyflow.biz.service.IUserRoleService;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.flow.NodeUser;
import cc.flyflow.common.dto.third.RoleDto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

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