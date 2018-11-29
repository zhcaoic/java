package com.logintest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {

    @Resource private UserService userService;

    // 注册操作
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("passwordConfirm") String passwordConfirm,
                           @RequestParam("age") int age,
                           @RequestParam("address") String address) {
        if (!password.equals(passwordConfirm)) {
            return "registererror";
        } else {
            userService.registerService(username, password, age, address);

            return "loginAfterRegister";
        }
    }

    // 修改用户信息
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    public String updateUserInfo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("passwordConfirm") String passwordConfirm,
                                 @RequestParam("age") int age,
                                 @RequestParam("address") String address) {
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);
        if (cookiesUser == null || StringUtils.isEmpty(cookiesUser.getUsername())) {
            return "homepage";
        } else if (!password.equals(passwordConfirm)) {
            return "registererror";
        } else {
            // 从Cookies中获取当前登录用户的ID
            int userId = cookiesUser.getId();
            // 更新数据库和Cookies中的用户信息
            CookiesUser updatedCookiesUser = userService.updateInfoService(userId, username, password, age, address);
            CookiesUtil.setLoginUser(response, updatedCookiesUser);

            return "loginAfterRegister";
        }
    }
}
