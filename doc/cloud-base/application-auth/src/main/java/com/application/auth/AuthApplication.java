package com.application.auth;

import com.application.common.security.annotation.EnableRyFeignClients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 认证授权中心
 *
 * @author ruoyi
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n"
                        + " .-------.       ____     __        \n"
                        + " |  _ _   \\      \\   \\   /  /    \n"
                        + " | ( ' )  |       \\  _. /  '       \n"
                        + " |(_ o _) /        _( )_ .'         \n"
                        + " | (_,_).' __  ___(_ o _)'          \n"
                        + " |  |\\ \\  |  ||   |(_,_)'         \n"
                        + " |  | \\ `'   /|   `-'  /           \n"
                        + " |  |  \\    /  \\      /           \n"
                        + " ''-'   `'-'    `-..-'              ");
    }
}