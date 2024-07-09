package cc.flyflow.biz.service;

import cc.flyflow.biz.vo.UserListQueryVO;
import cc.flyflow.common.dto.R;

/**
 * 用户表 服务类
 *
 * @author xiaoge
 * @since 2023-05-05
 */
public interface IUserBizService {

    /**
     * 获取当前用户详细信息
     *
     * @return
     */
    R getCurrentUserDetail();

    /**
     * 查询用户列表
     *
     * @param userListQueryVO
     * @return
     */
    R queryList(UserListQueryVO userListQueryVO);
}