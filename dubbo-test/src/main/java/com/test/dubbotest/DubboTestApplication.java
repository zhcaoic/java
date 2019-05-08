package com.test.dubbotest;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboTestApplication.class, args);
    }

}
