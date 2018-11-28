package com.logintest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserService {

    @Resource UserDAO userDAO;


    // 登录服务
    public User loginService(String username, String password) {
        // 验证输入用户名与密码不为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return null;
        }

        // 验证数据库中该用户不为空
        User dbuser = userDAO.selectByUsername(username);
        if (dbuser == null) {
            return null;
        }

        // 验证密码
        if (!Objects.equals(password, dbuser.getPassword())) {
            return null;
        }

        return dbuser;
    }

    // 按用户名查询用户信息
    public User findUserByUsername(String username) {
        return userDAO.selectByUsername(username);
    }

}
