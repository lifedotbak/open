package com.spyker.application.common.service;

import com.spyker.application.service.SysConfigService;
import com.spyker.application.service.SysUserService;
import com.spyker.framework.domain.model.LoginUser;
import com.spyker.framework.redis.RedisService;
import com.spyker.framework.security.context.AuthenticationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 登录校验方法
 *
 * @author platform
 */
@Service
@Slf4j
public class SysLoginService {

    @Autowired
    private SysTokenService sysTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {

        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);

        } catch (Exception e) {
            throw e;
        } finally {
            AuthenticationContextHolder.clearContext();
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return sysTokenService.createToken(loginUser);
    }

}