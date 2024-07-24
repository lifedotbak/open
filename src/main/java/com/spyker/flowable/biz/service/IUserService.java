package com.spyker.flowable.biz.service;

import com.github.yulichang.base.MPJBaseService;
import com.spyker.flowable.biz.entity.User;
import com.spyker.flowable.biz.vo.UserBizVO;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.third.PageResultDto;
import com.spyker.flowable.common.dto.third.UserDto;
import com.spyker.flowable.common.dto.third.UserQueryDto;

/**
 * 用户表 服务类
 *
 * @author xiaoge
 * @since 2023-05-05
 */
public interface IUserService extends MPJBaseService<User> {

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    R password(User user);

    /**
     * 修改用户状态
     *
     * @param user
     * @return
     */
    R status(User user);

    /**
     * 创建用户
     *
     * @param userBizVO
     * @return
     */
    R createUser(UserBizVO userBizVO);

    /**
     * 修改用户
     *
     * @param userBizVO
     * @return
     */
    R editUser(UserBizVO userBizVO);

    /**
     * 查询本地数据库用户列表
     *
     * @param userListQueryVO 查询参数
     * @return
     */
    PageResultDto<UserDto> queryLocalList(UserQueryDto userListQueryVO);
}