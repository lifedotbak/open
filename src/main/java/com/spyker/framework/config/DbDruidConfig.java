package com.spyker.framework.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.spyker.framework.properties.DruidConfigProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DbDruidConfig {

    @Autowired DruidConfigProperties druidConfigProperties;

    /**
     * 使用@ConfigurationProperties(prefix = "spring.datasource.druid")注解，会将对应参数注入到DruidDataSource中
     *
     * @param statFilter
     * @return
     * @throws SQLException
     */
    //    @ConfigurationProperties(prefix = "spring.datasource.druid")
    //    @Bean(initMethod = "init", destroyMethod = "close")
    //    public DruidDataSource dataSource(Filter statFilter) throws SQLException {
    //        DruidDataSource dataSource = new DruidDataSource();
    //
    //        dataSource.setPassword(druidConfigProperties.getPassword());
    //        dataSource.setUsername(druidConfigProperties.getUsername());
    //        dataSource.setUrl(druidConfigProperties.getUrl());
    //        dataSource.setDriverClassName(druidConfigProperties.getDriverClassName());
    //        dataSource.setValidationQuery(druidConfigProperties.getValidationQuery());
    //        dataSource.setTimeBetweenEvictionRunsMillis(
    //                druidConfigProperties.getTimeBetweenEvictionRunsMillis());
    //        dataSource.setMinEvictableIdleTimeMillis(
    //                druidConfigProperties.getMinEvictableIdleTimeMillis());
    //        dataSource.setMinIdle(druidConfigProperties.getMinIdle());
    //        dataSource.setMaxWait(druidConfigProperties.getMaxWait());
    //
    //        return dataSource;
    //    }

    /**
     * 使用@ConfigurationProperties(prefix = "spring.datasource.druid")注解，会将对应参数注入到DruidDataSource中
     * 如果加载失败，请检查redisson.yml配置文件编码，统一编码格式为utf-8
     *
     * @param statFilter
     * @return
     * @throws SQLException
     */
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource(Filter statFilter) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();

        return dataSource;
    }

    @Bean
    public Filter statFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(5000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }
}