package cc.flyflow.biz.service;

import cc.flyflow.biz.vo.UserBizVO;
import cc.flyflow.common.dto.R;

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
     * 钉钉登录
     *
     * @param authCode
     * @return
     */
    R loginAtDingTalk(String authCode);

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