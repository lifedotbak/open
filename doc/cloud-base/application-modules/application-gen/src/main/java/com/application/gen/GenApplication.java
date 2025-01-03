package com.application.gen;

import com.application.common.security.annotation.EnableCustomConfig;
import com.application.common.security.annotation.EnableRyFeignClients;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
@MapperScan("com.application.gen.mapper")
public class GenApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ  \n"
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