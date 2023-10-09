package com.spyker.application.common.service;

import com.spyker.application.entity.SysUser;
import com.spyker.application.mapper.SysUserMapper;
import com.spyker.framework.domain.model.LoginUser;
import com.spyker.framework.security.context.AuthenticationContextHolder;
import com.spyker.framework.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserMapper.getUserByName(username);

        if (null != sysUser) {

            LoginUser loginUser = new LoginUser();

            loginUser.setUserId(sysUser.getUserId());
            loginUser.setUserName(sysUser.getUserName());

            if (validate(sysUser.getPassword())) {
                loginUser.setLoginSuccess(true);
            }

            return loginUser;

        }

        throw new UsernameNotFoundException("User " + username + " not found");
    }

    public boolean validate(String rawPassword) throws UsernameNotFoundException {

        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();

        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();

        return SecurityUtils.matchesPassword(rawPassword, password);

    }
}