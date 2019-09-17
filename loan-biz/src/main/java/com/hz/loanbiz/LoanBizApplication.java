package com.hz.loanbiz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan(value = "com.hz.loanbiz.mapper")
public class LoanBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanBizApplication.class, args);
    }

}
