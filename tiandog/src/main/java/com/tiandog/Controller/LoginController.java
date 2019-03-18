package com.tiandog.Controller;

import com.tiandog.Entity.CookiesUser;
import com.tiandog.Entity.User;
import com.tiandog.Service.UserService;
import com.tiandog.Util.CookiesUtil;
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
    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String password,
                        @RequestParam("preUrl") String preUrl,
                        HttpServletResponse response) {
        // 校验用户合法性
        User loginUser = userService.loginService(name, password);
        if (loginUser == null) {
            return "error/loginError";
        }

        // 用户合法，则添加Cookies
        CookiesUser cookiesUser = new CookiesUser();
        cookiesUser.setId(loginUser.getId());
        cookiesUser.setName(loginUser.getName());
        cookiesUser.setLoginStatus(USER_LOGIN_STATUS_NORMAL);
        CookiesUtil.setLoginUser(response, cookiesUser);

        // 按preUrl(登录前页面URL)分别重定向
        if (preUrl == null || preUrl.isEmpty()) {
            // preUrl为null或""空字符串，重定向至首页
            return "redirect:/tiandog/index";
        } else if (!preUrl.substring(0, 22).equals("http://localhost:8080/")) {
            // preUrl为其他站点URL，重定向至首页
            return "redirect:/tiandog/index";
        } else {
            // preUrl为本站点URL，重定向至preUrl
            return "redirect:" + preUrl;
        }

    }


    //==================================================================
    // 退出登录
    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response) {
        CookiesUtil.removeCookies(response, CookiesUtil.USER_INFO, "/");

        return "redirect:/tiandog/index";
    }

}
