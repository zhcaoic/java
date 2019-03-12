package com.fileShare.Util;

import com.fileShare.Entity.CookiesUser;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

public class CookiesUtil {

    //==================================================================
    // 默认Cookie过期时间（单位：秒）此处为半小时
    public static final int MAX_AGE = 60 * 30;

    // Cookies name
    public static final String USER_INFO = "userInfo";


    //==================================================================
    // 设置Cookies
    public static void setLoginUser(HttpServletResponse response, CookiesUser cookiesUser) {
        if (cookiesUser == null || response == null) {
            return;
        }

        long id = cookiesUser.getId();
        String name = cookiesUser.getName();
        try{
            name = URLEncoder.encode(name, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder cookiesValue = new StringBuilder();
        // cookies : ID | 用户名 | 登录状态
        cookiesValue.append(id).append("|").append(name).append("|").append(cookiesUser.getLoginStatus());
        addCookies(response, USER_INFO, cookiesValue.toString());
    }

    public static void addCookies(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        //FIXME 此处需要测试Cookie适用的路径
        cookie.setPath("/");
        cookie.setMaxAge(MAX_AGE);
        response.addCookie(cookie);
    }


    //==================================================================
    // 获取Cookies
    public static CookiesUser getLoginUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        String value = getCookieValue(USER_INFO, request);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        String[] array = value.split("\\|");

        CookiesUser cookiesUser = new CookiesUser();
        cookiesUser.setId(Long.parseLong(array[0]));
        try {
            cookiesUser.setName(URLDecoder.decode(array[1], "UTF-8"));
        } catch(UnsupportedEncodingException e) {
            cookiesUser.setName(array[1]);
        }
        cookiesUser.setLoginStatus(Integer.parseInt(array[2]));

        return cookiesUser;
    }

    public static String getCookieValue(String name, HttpServletRequest request) {
        if (request == null || StringUtils.isEmpty(name)) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), name)) {
                return cookie.getValue();
            }
        }
        return null;
    }


    //==================================================================
    // 删除Cookies
    public static void removeCookies(HttpServletResponse response, String name, String path) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(path)) {
            return;
        }
        Cookie cookie = new Cookie(name, "");
        cookie.setPath(path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
