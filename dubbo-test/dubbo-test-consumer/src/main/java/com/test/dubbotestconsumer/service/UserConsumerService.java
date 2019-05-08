package com.test.dubbotestconsumer.service;

import com.test.dubbocommon.domain.User;
import com.test.dubbocommon.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerService {

    @Reference UserService userService;

    public void printUser(int id) {
        User user = userService.getUserById(id);
        System.out.println("id : " + user.getId());
        System.out.println("name : " + user.getName());
        System.out.println("detail : " + user.getDetail());
    }

    public void saveUser() {
        User user = new User();
        user.setId(4);
        user.setName("loea");
        user.setDetail("209");
        boolean result = userService.setNewUser(user);
        if (result) {
            System.out.println("save success");
        } else {
            System.out.println("save fail");
        }
    }

}
