package com.test.userservicetest.controller;

import com.test.userservicetest.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserBaseController {

    @Resource UserServiceImpl userServiceImpl;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String userIndex() {
        return "views/index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        return "";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("nickname") String nickname,
                           @RequestParam("password") String pwd,
                           @RequestParam("pwdConfirm") String pwdConfirm,
                           @RequestParam("email") String email,
                           @RequestParam("cellphone") String cellphoneTemp,
                           ModelMap modelMap) {
        // 检查输入是否为空
        if (nickname == "" || pwd == "" || pwdConfirm == "" || email == "" || cellphoneTemp == "") {
            modelMap.addAttribute("inputIsNull", 1);
            return "views/register";
        }
        // 检查密码是否一致
        if (!pwd.equals(pwdConfirm)) {
            modelMap.addAttribute("pwdNotEqual", 1);
            return "views/register";
        }
        // 调用注册服务
        int registerResult = userServiceImpl.registerService(nickname, pwd, email, cellphoneTemp);
        if (registerResult == 1) {
            modelMap.addAttribute("nicknameIsExisted", 1);
            return "views/register";
        } else if (registerResult == 2) {
            modelMap.addAttribute("emailIsExisted", 1);
            return "views/register";
        } else if (registerResult == 3) {
            modelMap.addAttribute("cellphoneIsExisted", 1);
            return "views/register";
        } else {
            return "views/index";
        }

    }

}
