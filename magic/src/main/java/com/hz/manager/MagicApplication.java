package com.hz.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hz.manager.dao")

public class MagicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicApplication.class, args);
    }

}
