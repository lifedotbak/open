package com.spyker.application.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.spyker.application.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        //TODO
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {

        String userId = String.valueOf(loginId);

        return sysUserMapper.queryRolesById(userId);
    }
}