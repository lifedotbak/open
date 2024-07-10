package com.spyker.framework.mybatisplus;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataChangeRecorderInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.spyker.framework.constant.Constants;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

@EnableTransactionManagement
@Configuration
@Slf4j
public class MybatisPlusConfig implements MetaObjectHandler {

    /**
     * 设置分页组件
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        /** 分页插件 PaginationInnerInterceptor */
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        /** 非法SQL拦截插件 IllegalSQLInnerIntercepto */
        //		interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());

        /** 防全表更新与删除插件 BlockAttackInnerInterceptor */
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        /** 数据变动记录插件 #DataChangeRecorderInnerInterceptor */
        interceptor.addInnerInterceptor(new DataChangeRecorderInnerInterceptor());

        return interceptor;
    }

    /**
     * insert 默认操作
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        Date date = new Date();

        this.strictInsertFill(metaObject, "createTime", Date.class, date); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", Date.class, date); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "delFlag", Boolean.class, false); // 起始版本 3.3.0(推荐使用)

        String loginUserId = "";

        try {

            SaSession saSession = StpUtil.getSession();

            if (null != saSession) {
                Object userKey = saSession.get(Constants.LOGIN_USER_KEY);
                if (null != userKey) {
                    loginUserId = (String) userKey;
                }
            }

        } catch (Exception e) {
            log.error("error-->{}", e);
        }

        this.setFieldValByName("createBy", loginUserId, metaObject);
        this.setFieldValByName("updateBy", loginUserId, metaObject);

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("modifyTime", new Date(), metaObject);

        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * update默认操作
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();

        this.strictUpdateFill(metaObject, "updateTime", Date.class, date); // 起始版本 3.3.0(推荐)

        String loginUserId = "";

        try {
            loginUserId = (String) StpUtil.getSession().get(Constants.LOGIN_USER_KEY);
        } catch (Exception e) {
            log.error("error-->{}", e);
        }

        this.setFieldValByName("updateBy", loginUserId, metaObject);

        this.setFieldValByName("modifyTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}