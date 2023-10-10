package com.spyker.application.common.service;

import com.spyker.application.entity.SysUser;
import com.spyker.application.mapper.SysUserMapper;
import com.spyker.framework.domain.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名查询用户的认证授权信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserMapper.getUserByName(username);

        if (null != sysUser) {

            LoginUser loginUser = new LoginUser();

            loginUser.setUserId(sysUser.getUserId());
            loginUser.setUserName(sysUser.getUserName());
            loginUser.setPassword(sysUser.getPassword());

            return loginUser;

        }

        throw new UsernameNotFoundException("User " + username + " not found");
    }

}