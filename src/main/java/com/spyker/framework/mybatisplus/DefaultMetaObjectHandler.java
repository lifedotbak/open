package com.spyker.framework.mybatisplus;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.spyker.framework.constant.Constants;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充默认元对象处理器
 */
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {


        String loginUserId =(String) StpUtil.getSession().get(Constants.LOGIN_USER_KEY);

        this.setFieldValByName("createBy", loginUserId, metaObject);
        this.setFieldValByName("updateBy", loginUserId, metaObject);

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("modifyTime", new Date(), metaObject);

        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        String loginUserId =(String) StpUtil.getSession().get(Constants.LOGIN_USER_KEY);

        this.setFieldValByName("updateBy", loginUserId, metaObject);

        this.setFieldValByName("modifyTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

}