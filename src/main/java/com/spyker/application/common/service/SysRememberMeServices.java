package com.spyker.application.common.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 记住我功能
 */
@Component
public class SysRememberMeServices extends PersistentTokenBasedRememberMeServices {

    public static final String REMEMBER_ME_KEY = "rememberMe";
    public static final List<String> TRUE_VALUES = new ArrayList();

    public SysRememberMeServices(UserDetailsService userDetailsService, InMemoryTokenRepositoryImpl tokenRepository) {
        super(UUID.randomUUID().toString(), userDetailsService, tokenRepository);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {

        TRUE_VALUES.add("true");
        TRUE_VALUES.add("yes");
        TRUE_VALUES.add("on");
        TRUE_VALUES.add("1");

        final String rememberMe = (String) request.getAttribute(REMEMBER_ME_KEY);
        if (rememberMe != null) {
            for (String trueValue : TRUE_VALUES) {
                if (trueValue.equalsIgnoreCase(rememberMe)) {
                    return true;
                }
            }
        }
        return super.rememberMeRequested(request, parameter);
    }

}