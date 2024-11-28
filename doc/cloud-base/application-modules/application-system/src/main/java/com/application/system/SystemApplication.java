package com.application.system;

import com.application.common.security.annotation.EnableCustomConfig;
import com.application.common.security.annotation.EnableRyFeignClients;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统模块
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
@MapperScan("com.application.system.mapper")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n"
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