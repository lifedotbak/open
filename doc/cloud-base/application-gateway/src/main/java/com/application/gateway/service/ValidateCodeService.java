package com.application.gateway.service;

import com.application.common.core.exception.CaptchaException;
import com.application.common.core.web.domain.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author ruoyi
 */
public interface ValidateCodeService {
    /** 生成验证码 */
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    /** 校验验证码 */
    public void checkCaptcha(String key, String value) throws CaptchaException;
}