package com.test.userservicetest.domain.interceptor;

import com.test.userservicetest.domain.entity.SessionUser;
import com.test.userservicetest.domain.util.SessionUtil;
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

        // 从Session中取出用户信息用于页面显示
        SessionUser sessionUser = SessionUtil.getLoginUser(request);

        if (sessionUser != null && !StringUtils.isEmpty(sessionUser.getNickname()) &&
                (sessionUser.getId() != 0) && (sessionUser.getUserNumber() != 0)) {
            modelAndView.addObject("userId", sessionUser.getId());
            modelAndView.addObject("userNumber", sessionUser.getUserNumber());
            modelAndView.addObject("nickname", sessionUser.getNickname());
        }

        if (modelAndView != null && modelAndView.getViewName().startsWith("redirect:")) {
            // FIXME 此处可能会在登录控制器中异常清除，有待测试
            modelAndView.getModel().clear();
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
