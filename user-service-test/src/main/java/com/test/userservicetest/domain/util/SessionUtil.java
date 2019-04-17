package com.test.userservicetest.domain.util;

import com.test.userservicetest.domain.entity.SessionUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    //==================================================================
    // 默认Session过期时间（单位：秒）此处为半小时
    public static final int MAX_AGE = 60 * 30;

    // Session 用户信息实体名称
    public static final String loginUserInfo = "loginUserInfo";


    /**
     * 设置Session
     * @param request 请求
     * @param sessionUser 用户信息实体
     * @return boolean 结果
     */
    public static boolean setLoginUser(HttpServletRequest request,
                                    SessionUser sessionUser) {
        if (request == null || sessionUser == null) {
            // 设置Session失败
            return false;
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(loginUserInfo, sessionUser);
        httpSession.setMaxInactiveInterval(MAX_AGE);

        // 设置Session成功
        return true;
    }


    /**
     * 获取Session
     * @param request 请求
     * @return 用户信息实体
     */
    public static SessionUser getLoginUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        HttpSession httpSession = request.getSession();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(loginUserInfo);

        return sessionUser;
    }


    /**
     * 移除Session（设置Session失效）
     * @param request 请求
     * @return boolean 结果
     */
    public static boolean removeSession(HttpServletRequest request) {
        if (request == null) {
            // 设置Session失效失败
            return false;
        }

        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        // 设置Session失效成功
        return true;
    }


}
