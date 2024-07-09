package cc.flyflow.biz.api.impl;

import cc.flyflow.biz.api.ApiStrategy;
import cc.flyflow.biz.utils.DingTalkHttpUtil;
import cc.flyflow.common.dto.LoginUrlDto;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.third.*;
import cc.flyflow.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DingTalkApi implements ApiStrategy, InitializingBean {

    /**
     * 账号密码登录
     *
     * @param password 密码
     * @param account 账号
     * @return 用户id
     */
    @Override
    public String loginWeb(String password, String account) {
        return null;
    }

    /**
     * 根据部门id集合和角色id集合查询人员id集合
     *
     * @param userQueryDto 查询参数
     * @return
     */
    @Override
    public List<String> queryUserIdListByRoleIdListAndDeptIdList(UserQueryDto userQueryDto) {
        String post =
                DingTalkHttpUtil.post(
                        userQueryDto, "/user/loadUserIdListByRoleIdListAndDeptIdList");
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<List<String>>>() {})
                .getData();
    }

    /**
     * 查询用户列表
     *
     * @param userQueryDto
     * @return
     */
    @Override
    public PageResultDto<UserDto> queryUserList(UserQueryDto userQueryDto) {
        String post = DingTalkHttpUtil.post(userQueryDto, "/user/queryUserList");
        PageResultDto<UserDto> data =
                JsonUtil.parseObject(
                                post, new JsonUtil.TypeReference<R<PageResultDto<UserDto>>>() {})
                        .getData();
        return data;
    }

    /**
     * 根据用户id查询角色id集合
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> loadRoleIdListByUserId(String userId) {
        String post = DingTalkHttpUtil.get("/user/loadRoleIdListByUserId?userId=" + userId);
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<List<String>>>() {})
                .getData();
    }

    /**
     * 获取所有的角色
     *
     * @return
     */
    @Override
    public List<RoleDto> loadAllRole() {
        String post = DingTalkHttpUtil.get("/role/loadAllRole");
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<List<RoleDto>>>() {})
                .getData();
    }

    /**
     * 获取所有的部门
     *
     * @return
     */
    @Override
    public List<DeptDto> loadAllDept(String parentDeptId) {
        String post =
                DingTalkHttpUtil.get(
                        "/dept/loadAllDept?deptId=" + (parentDeptId == null ? "" : parentDeptId));
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<List<DeptDto>>>() {})
                .getData();
    }

    /**
     * 根据部门获取部门下的用户集合
     *
     * @param deptId 部门id
     * @return
     */
    @Override
    public List<UserDto> loadUserByDept(String deptId) {
        String post = DingTalkHttpUtil.get("/user/loadUserByDept?deptId=" + deptId);
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<List<UserDto>>>() {})
                .getData();
    }

    /**
     * 批量获取部门集合
     *
     * @param deptIdList 部门id集合
     * @return
     */
    @Override
    public List<DeptDto> getDeptList(List<String> deptIdList) {
        String post = DingTalkHttpUtil.post(deptIdList, "/dept/batchGet");
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<List<DeptDto>>>() {})
                .getData();
    }

    /**
     * 根据用户id获取用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserDto getUser(String userId) {
        String post = DingTalkHttpUtil.get("/user/getUser?userId=" + userId);
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<UserDto>>() {}).getData();
    }

    @Override
    public List<UserDto> searchUser(String name) {
        String post = DingTalkHttpUtil.get("/user/searchUser?name=" + name);
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<List<UserDto>>>() {})
                .getData();
    }

    /** 重新拉取数据 */
    @Override
    public void loadRemoteData() {
        DingTalkHttpUtil.post(new HashMap<>(), "/base/loadRemoteData");
    }

    /**
     * 获取登录地址
     *
     * @return
     */
    @Override
    public LoginUrlDto getLoginUrl() {
        String s = DingTalkHttpUtil.get("/base/getLoginUrl");
        R<LoginUrlDto> r = JsonUtil.parseObject(s, new JsonUtil.TypeReference<R<LoginUrlDto>>() {});
        return r.getData();
    }

    /**
     * 获取登录参数
     *
     * @return
     */
    @Override
    public Object getLoginParam(Map<String, Object> paramMap) {
        String s = DingTalkHttpUtil.post(paramMap, "/base/getLoginParam");
        R r = JsonUtil.parseObject(s, new JsonUtil.TypeReference<R>() {});
        return r.getData();
    }

    /**
     * 发送消息
     *
     * @param messageDto
     */
    @Override
    public void sendMsg(MessageDto messageDto) {
        DingTalkHttpUtil.post(messageDto, "/message/send");
    }

    /**
     * 根据token获取用户id 处理登录接口请求的
     *
     * @param token
     * @return
     */
    @Override
    public String getUserIdByToken(String token) {

        String s = DingTalkHttpUtil.get("/user/getUserIdByCode?authCode=" + token);
        R<String> r = JsonUtil.parseObject(s, new JsonUtil.TypeReference<R<String>>() {});

        return r.getData();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet("dingtalk");
    }
}