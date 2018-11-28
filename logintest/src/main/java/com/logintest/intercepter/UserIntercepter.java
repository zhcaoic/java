package com.logintest.intercepter;

import com.logintest.CookiesUser;
import com.logintest.CookiesUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserIntercepter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        // 从Cookies中取出用户信息用于页面显示
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);

        if (cookiesUser != null && !StringUtils.isEmpty(cookiesUser.getUsername())) {
            modelAndView.addObject("username", cookiesUser.getUsername());
        }

        if (modelAndView != null && modelAndView.getViewName().startsWith("redirect:")) {
            modelAndView.getModel().clear();
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
