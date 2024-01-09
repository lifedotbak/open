package com.spyker.framework.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DbDruidConfig {

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