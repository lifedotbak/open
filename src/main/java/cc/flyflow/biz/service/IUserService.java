package cc.flyflow.biz.service;

import cc.flyflow.biz.entity.User;
import cc.flyflow.biz.vo.UserBizVO;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.third.PageResultDto;
import cc.flyflow.common.dto.third.UserDto;
import cc.flyflow.common.dto.third.UserQueryDto;

import com.github.yulichang.base.MPJBaseService;

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