package cc.flyflow.biz.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

import static cc.flyflow.common.constants.ProcessInstanceConstant.PAGE_MAX_COUNT;

@Slf4j
@Component
public class MyBatisPlusConfig implements MetaObjectHandler {
    /**
     * 配置mybatis plus 拦截器
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        innerInterceptor.setMaxLimit(Long.valueOf(PAGE_MAX_COUNT));
        interceptor.addInnerInterceptor(innerInterceptor);

        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor =
                new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler(
                (sql, tableName) -> {
                    String tablePrefix =
                            SpringUtil.getProperty(
                                    "mybatis-plus.global-config.db-config.table-prefix");
                    if (StrUtil.startWith(tableName, tablePrefix)) {
                        return tableName;
                    }

                    return StrUtil.format("{}{}", tablePrefix, tableName);
                });
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        return interceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.strictInsertFill(metaObject, "createTime", Date.class, date); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", Date.class, date); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "delFlag", Boolean.class, false); // 起始版本 3.3.0(推荐使用)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();

        this.strictUpdateFill(metaObject, "updateTime", Date.class, date); // 起始版本 3.3.0(推荐)
    }
}