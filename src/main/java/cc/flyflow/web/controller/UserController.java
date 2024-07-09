package cc.flyflow.web.controller;

import cc.flyflow.biz.entity.User;
import cc.flyflow.biz.service.IOrgService;
import cc.flyflow.biz.service.IUserBizService;
import cc.flyflow.biz.service.IUserService;
import cc.flyflow.biz.vo.UserBizVO;
import cc.flyflow.biz.vo.UserListQueryVO;
import cc.flyflow.common.dto.R;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.SneakyThrows;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/** 用户接口 */
@Tag(name = "用户接口", description = "用户接口")
@RestController
@RequestMapping(value = {"user"})
public class UserController {

    @Resource private IUserService userService;
    @Resource private IUserBizService userBizService;
    @Resource private IOrgService orgService;

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @Operation(summary = "创建用户", description = "创建用户")
    @PostMapping("create")
    public R create(@RequestBody UserBizVO user) {
        return userService.createUser(user);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Operation(summary = "修改用户", description = "修改用户")
    @PutMapping("edit")
    public R editUser(@RequestBody UserBizVO user) {
        return userService.editUser(user);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @Operation(summary = "修改密码", description = "修改密码")
    @PostMapping("password")
    public R password(@RequestBody User user) {
        return userService.password(user);
    }

    /**
     * 修改状态
     *
     * @param user
     * @return
     */
    @Operation(summary = "修改状态", description = "修改状态")
    @PostMapping("status")
    public R status(@RequestBody User user) {
        return userService.status(user);
    }

    /**
     * 获取当前用户详细信息
     *
     * @return
     */
    @Operation(summary = "获取当前用户详细信息", description = "获取当前用户详细信息")
    @SneakyThrows
    @GetMapping("getCurrentUserDetail")
    public R getCurrentUserDetail() {

        return userBizService.getCurrentUserDetail();
    }

    /**
     * 获取用户详细信息
     *
     * @param user
     * @return
     */
    @Operation(summary = "获取用户详细信息", description = "获取用户详细信息")
    @GetMapping("getUserDetail")
    public R getUserDetail(String userId) {
        return orgService.getUserDetail(userId);
    }

    /**
     * 查询用户列表
     *
     * @param userListQueryVO
     * @return
     */
    @Operation(summary = "查询用户列表", description = "查询用户列表")
    @PostMapping("queryList")
    public R queryList(@RequestBody UserListQueryVO userListQueryVO) {
        return userBizService.queryList(userListQueryVO);
    }

    /**
     * 删除用户
     *
     * @param userListQueryVO
     * @return
     */
    @Operation(summary = "删除用户", description = "删除用户")
    @DeleteMapping("delete")
    public R delete(@RequestBody User userListQueryVO) {
        return orgService.delete(userListQueryVO);
    }
}