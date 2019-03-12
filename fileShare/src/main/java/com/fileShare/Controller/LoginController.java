package com.fileShare.Controller;


import com.fileShare.Entity.CookiesUser;
import com.fileShare.Util.CookiesUtil;
import com.fileShare.Entity.User;
import com.fileShare.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    //==================================================================
    // Cookies 登录状态正常
    public static final int USER_LOGIN_STATUS_NORMAL = 1;

    @Resource private UserService userService;


    //==================================================================
    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        User loginUser = userService.loginService(name, password);
        if (loginUser == null) {
            return "error/loginError";
        } else {
            //添加Cookies
            CookiesUser cookiesUser = new CookiesUser();
            cookiesUser.setId(loginUser.getId());
            cookiesUser.setName(loginUser.getName());
            cookiesUser.setLoginStatus(USER_LOGIN_STATUS_NORMAL);
            CookiesUtil.setLoginUser(response, cookiesUser);

            return "redirect:/fileShare/index";
        }
    }


    //==================================================================
    // 退出登录
    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response) {
        CookiesUtil.removeCookies(response, CookiesUtil.USER_INFO, "/");

        return "views/index";
    }


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
