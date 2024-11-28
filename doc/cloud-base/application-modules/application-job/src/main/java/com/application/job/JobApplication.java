package com.application.job;

import com.application.common.security.annotation.EnableCustomConfig;
import com.application.common.security.annotation.EnableRyFeignClients;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 定时任务
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
@MapperScan("com.application.job.mapper")
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n"
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