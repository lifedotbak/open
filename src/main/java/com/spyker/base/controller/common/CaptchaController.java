package com.spyker.base.controller.common;

import com.google.code.kaptcha.Producer;
import com.spyker.framework.config.PlatformConfig;
import com.spyker.framework.constant.CacheConstants;
import com.spyker.framework.constant.Constants;
import com.spyker.framework.redis.RedisService;
import com.spyker.framework.response.RestMapResponse;
import com.spyker.framework.util.sign.Base64Utils;
import com.spyker.framework.util.uuid.IdUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisService redisService;

    /**
     * 生成验证码
     */
    @PostMapping("/captchaImage")
    public RestMapResponse createCode() throws IOException {

        RestMapResponse ajax = RestMapResponse.success();
        ajax.put("captchaEnabled", PlatformConfig.getCaptchaEnabled());
        if (!"open".equalsIgnoreCase(PlatformConfig.getCaptchaEnabled())) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = PlatformConfig.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisService.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
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