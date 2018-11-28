package com.logintest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    // Cookies 登录状态正常
    public static final int USER_LOGIN_STATUS_NORMAL = 1;

    @Resource private UserService userService;


    // 主页操作
    @RequestMapping(value = "/homepage")
    public String homePage(HttpServletRequest request,
                           ModelMap modelMap) {
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);
        if (cookiesUser == null || StringUtils.isEmpty(cookiesUser.getUsername())) {
            return "homepage";
        } else {
            String cookieUsername = cookiesUser.getUsername();
            User homepageUser = userService.findUserByUsername(cookieUsername);
            modelMap.addAttribute("user", homepageUser);

            return "homepage";
        }
    }


    // 登录操作
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        User loginUser = userService.loginService(username, password);
        if (loginUser == null) {
            return "loginerror";
        } else {
            //添加Cookies
            CookiesUser cookiesUser = new CookiesUser();
            cookiesUser.setId(loginUser.getId());
            cookiesUser.setUsername(loginUser.getUsername());
            cookiesUser.setLoginStatus(USER_LOGIN_STATUS_NORMAL);
            CookiesUtil.setLoginUser(response, cookiesUser);

            return "redirect:/test/homepage";
        }
    }


    // 退出登录
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        CookiesUtil.removeCookies(response, CookiesUtil.USER_INFO, "/");

        return "homepage";
    }


}
