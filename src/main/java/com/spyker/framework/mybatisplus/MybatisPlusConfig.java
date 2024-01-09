package com.spyker.framework.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataChangeRecorderInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

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
}