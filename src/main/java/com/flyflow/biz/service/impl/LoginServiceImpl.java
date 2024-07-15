package com.flyflow.biz.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;

import com.flyflow.biz.api.ApiStrategyFactory;
import com.flyflow.biz.constants.SecurityConstants;
import com.flyflow.biz.service.ILoginService;
import com.flyflow.biz.utils.DingTalkHttpUtil;
import com.flyflow.biz.vo.UserBizVO;
import com.flyflow.common.constants.LoginPlatEnum;
import com.flyflow.common.dto.R;
import com.flyflow.common.utils.ThreadLocalUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;

@Component
@Slf4j
public class LoginServiceImpl implements ILoginService {

    @Value("${login.captcha}")
    private Boolean captcha;

    @Resource private RedisTemplate redisTemplate;

    /**
     * 登录
     *
     * @param userBizVO
     * @return
     */
    @Override
    public R login(UserBizVO userBizVO) {

        return loginAtWeb(userBizVO, LoginPlatEnum.ADMIN);
    }

    /**
     * h5登录
     *
     * @param userBizVO
     * @return
     */
    @Override
    public R loginAtH5(UserBizVO userBizVO) {

        return loginAtWeb(userBizVO, LoginPlatEnum.H5);
    }

    /**
     * 网页登录
     *
     * @param userBizVO
     * @param platform
     * @return
     */
    private R loginAtWeb(UserBizVO userBizVO, LoginPlatEnum platform) {

        if (captcha) {
            // 是否开启了验证码
            Object cacheVerifyCode =
                    redisTemplate
                            .opsForValue()
                            .get(
                                    SecurityConstants.VERIFY_CODE_CACHE_PREFIX
                                            + userBizVO.getVerifyCodeKey());
            if (cacheVerifyCode == null) {
                return R.fail("验证码错误");
            } else {
                // 验证码比对
                if (!StrUtil.equals(userBizVO.getVerifyCode(), Convert.toStr(cacheVerifyCode))) {
                    return R.fail("验证码错误");
                }
            }
        }

        String phone = userBizVO.getPhone();
        String password = userBizVO.getPassword();

        // 对接登录
        String userId = ApiStrategyFactory.getStrategy().loginWeb(password, phone);

        if (StrUtil.isBlank(userId)) {
            return R.fail("账号或者密码错误");
        }
        StpUtil.login(userId, platform.getType());

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return R.success(tokenInfo);
    }

    /**
     * token登录
     *
     * @param token
     * @return
     */
    @Override
    public R loginByToken(String token) {
        String userId = ApiStrategyFactory.getStrategy().getUserIdByToken(token);
        if (StrUtil.isBlank(userId)) {
            return R.fail("获取用户失败，请重试");
        }

        UserAgent userAgent = ThreadLocalUtil.getUserAgent();
        if (userAgent.isMobile()) {
            StpUtil.login(userId, LoginPlatEnum.H5.getType());
        } else {
            StpUtil.login(userId, LoginPlatEnum.ADMIN.getType());
        }

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return R.success(tokenInfo);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public R logout() {
        boolean login = StpUtil.isLogin();
        if (login) {
            StpUtil.logout(StpUtil.getLoginId());
        }

        return R.success();
    }

    /**
     * 钉钉登录
     *
     * @param authCode
     * @return
     */
    @Override
    public R loginAtDingTalk(String authCode) {

        String userId = DingTalkHttpUtil.getUserIdByCodeAtMiniApp(authCode).getData();

        StpUtil.login(userId, LoginPlatEnum.DING_TALK.getType());

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return R.success(tokenInfo);
    }

    /**
     * 获取登录地址
     *
     * @return
     */
    @Override
    public R getLoginUrl() {
        return R.success(ApiStrategyFactory.getStrategy().getLoginUrl());
    }

    /**
     * 获取登录参数
     *
     * @return
     */
    @Override
    public R getLoginParam(Map<String, Object> paramMap) {
        return R.success(ApiStrategyFactory.getStrategy().getLoginParam(paramMap));
    }
}