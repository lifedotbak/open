package com.spyker.commons.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;

import com.google.code.kaptcha.Producer;
import com.spyker.framework.config.PlatformConfigProperties;
import com.spyker.framework.constants.CacheConstants;
import com.spyker.framework.constants.CommonsConstants;
import com.spyker.framework.redis.RedisService;
import com.spyker.framework.util.sign.Base64Utils;
import com.spyker.framework.util.uuid.ExUuidUtils;
import com.spyker.framework.web.response.RestMapResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

/**
 * 验证码操作处理
 *
 * @author spyker
 */
@Tag(name = "验证码生成", description = "验证码生成")
@RestController
@RequestMapping("/common/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final RedisService redisService;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /** 生成验证码 */
    @SaIgnore
    @PostMapping("/captchaImage")
    public RestMapResponse createCode() {

        RestMapResponse ajax = RestMapResponse.success();
        ajax.put("captchaEnabled", PlatformConfigProperties.getCaptchaEnabled());
        if (!"open".equalsIgnoreCase(PlatformConfigProperties.getCaptchaEnabled())) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = ExUuidUtils.simpleStringUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null;
        String code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = PlatformConfigProperties.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisService.setCacheObject(
                verifyKey, code, CommonsConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return RestMapResponse.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64Utils.encode(os.toByteArray()));
        return ajax;
    }
}