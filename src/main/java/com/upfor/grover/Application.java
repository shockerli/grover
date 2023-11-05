package com.upfor.grover;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.upfor.grover.mapper") // 配置 MapperScan 注解
public class Application {

    /**
     * 应用程序主入口
     *
     * @param args 程序参数
     */
    public static void main(String[] args) {
        // 启动Spring
        SpringApplication.run(Application.class, args);
    }

}
