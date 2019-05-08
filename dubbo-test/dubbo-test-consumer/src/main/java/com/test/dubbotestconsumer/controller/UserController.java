package com.test.dubbotestconsumer.controller;

import com.test.dubbotestconsumer.service.UserConsumerService;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {


    @Resource UserConsumerService userConsumerService;

    @RequestMapping("/user")
    public String index() {
        userConsumerService.printUser(3);
        userConsumerService.saveUser();

        return "consumer index";
    }

}
