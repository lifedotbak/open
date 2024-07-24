package com.spyker.flowable.biz.service;

import com.spyker.flowable.biz.vo.UserBizVO;
import com.spyker.flowable.common.dto.R;

import java.util.Map;

public interface ILoginService {

    /**
     * 登录
     *
     * @param userBizVO
     * @return
     */
    R login(UserBizVO userBizVO);

    /**
     * h5登录
     *
     * @param userBizVO
     * @return
     */
    R loginAtH5(UserBizVO userBizVO);

    /**
     * token登录
     *
     * @param token
     * @return
     */
    R loginByToken(String token);

    /**
     * 退出登录
     *
     * @return
     */
    R logout();

    /**
     * 获取登录地址
     *
     * @return
     */
    R getLoginUrl();

    /**
     * 获取登录参数
     *
     * @return
     */
    R getLoginParam(Map<String, Object> paramMap);
}