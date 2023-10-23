package com.spyker.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.base.BaseTest;
import com.spyker.base.entity.SysUser;
import com.spyker.base.search.SysUserSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysUserServiceTest extends BaseTest {

    @Autowired
    private SysUserService service;

    @Test
    public void get() {

        SysUser result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysUser add = new SysUser();

        add.setDeptId("deptId");

        add.setUserName("userName");

        add.setNickName("nickName");

        add.setUserType("userType");

        add.setEmail("email");

        add.setPhonenumber("phonenumber");

        add.setSex("sex");

        add.setAvatar("avatar");

        add.setPassword("password");

        add.setStatus("status");

        add.setDelFlag("delFlag");

        add.setLoginIp("loginIp");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        service.insert(add);
    }

    @Test
    public void update() {
        SysUser update = new SysUser();

        update.setUserId("userId");

        update.setDeptId("deptId");

        update.setUserName("userName");

        update.setNickName("nickName");

        update.setUserType("userType");

        update.setEmail("email");

        update.setPhonenumber("phonenumber");

        update.setSex("sex");

        update.setAvatar("avatar");

        update.setPassword("password");

        update.setStatus("status");

        update.setDelFlag("delFlag");

        update.setLoginIp("loginIp");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        service.update(update);
    }

    @Test
    public void query() {
        SysUserSearch search = new SysUserSearch();

        search.setDeptId("deptId");
        search.setUserName("userName");
        search.setNickName("nickName");
        search.setUserType("userType");
        search.setEmail("email");
        search.setPhonenumber("phonenumber");
        search.setSex("sex");
        search.setAvatar("avatar");
        search.setPassword("password");
        search.setStatus("status");
        search.setDelFlag("delFlag");
        search.setLoginIp("loginIp");
        search.setCreateBy("createBy");
        search.setUpdateBy("updateBy");
        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysUser> page = new Page<>(1, 10);

        SysUserSearch search = new SysUserSearch();

        search.setDeptId("deptId");

        search.setUserName("userName");

        search.setNickName("nickName");

        search.setUserType("userType");

        search.setEmail("email");

        search.setPhonenumber("phonenumber");

        search.setSex("sex");

        search.setAvatar("avatar");

        search.setPassword("password");

        search.setStatus("status");

        search.setDelFlag("delFlag");

        search.setLoginIp("loginIp");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }

}