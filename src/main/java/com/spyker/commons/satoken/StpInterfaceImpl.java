package com.spyker.commons.satoken;

import cn.dev33.satoken.stp.StpInterface;

import com.spyker.commons.mapper.SysUserMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;

/** 定义权限加载接口实现类 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final SysUserMapper sysUserMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String s) {

        String loginUserId = String.valueOf(loginId);
        return sysUserMapper.queryPermsById(loginUserId);
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {

        String loginUserId = String.valueOf(loginId);
        return sysUserMapper.queryRolesById(loginUserId);
    }
}