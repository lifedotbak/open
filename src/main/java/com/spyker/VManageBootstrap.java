package com.spyker;

import com.spyker.iot.vmp.utils.GitUtil;
import com.spyker.iot.vmp.utils.SpringBeanFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collections;

/** 启动类 */
// @ServletComponentScan("com.genersoft.iot.vmp.conf")
// @ServletComponentScan("com.spyker.iot.vmp.conf")
@MapperScan({"com.spyker.*.mapper", "com.spyker.iot.vmp.storager.dao"})
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class VManageBootstrap extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(VManageBootstrap.class);

    private static String[] args;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        VManageBootstrap.args = args;
        VManageBootstrap.context = SpringApplication.run(VManageBootstrap.class, args);
        GitUtil gitUtil1 = SpringBeanFactory.getBean("gitUtil");
        logger.info("构建版本： {}", gitUtil1.getBuildVersion());
        logger.info("构建时间： {}", gitUtil1.getBuildDate());
        logger.info("GIT最后提交时间： {}", gitUtil1.getCommitTime());
    }

    // 项目重启
    public static void restart() {
        context.close();
        VManageBootstrap.context = SpringApplication.run(VManageBootstrap.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VManageBootstrap.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
        SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);
    }
}