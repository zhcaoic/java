package com.tiandog.Controller;

import com.tiandog.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class RegisterController {

    //==================================================================
    @Resource private UserService userService;


    //==================================================================
    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String name,
                           @RequestParam("password") String password,
                           @RequestParam("pwdConfirm") String pwdConfirm) {
        if (!password.equals(pwdConfirm)) {
            return "error/registerError";
        } else {
            userService.registerService(name, password);

            return "views/loginAfterRegister";
        }

    }

}
