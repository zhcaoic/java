package com.fileShare.Interceptor;

import com.fileShare.Entity.CookiesUser;
import com.fileShare.Util.CookiesUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // 从Cookies中取出用户信息用于页面显示
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);

        if (cookiesUser != null && !StringUtils.isEmpty(cookiesUser.getName())) {
            modelAndView.addObject("username", cookiesUser.getName());
        }

        if (modelAndView != null && modelAndView.getViewName().startsWith("redirect:")) {
            modelAndView.getModel().clear();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
