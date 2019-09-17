package com.hz.userloan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan(value = "com.hz.userloan.mapper")
public class UserLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserLoanApplication.class, args);
    }

}
