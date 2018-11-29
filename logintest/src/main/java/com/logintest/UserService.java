package com.logintest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Service
public class UserService {

    // Cookies 登录状态正常
    public static final int USER_LOGIN_STATUS_NORMAL = 1;

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

    // 新用户注册服务
    public void registerService(String username, String password, int age, String address) {
        User registerUser = new User();
        registerUser.setUsername(username);
        registerUser.setPassword(password);
        registerUser.setCreateTime(new Date());
        registerUser.setUpdateTime(new Date());
        registerUser.setAge(age);
        registerUser.setAddress(address);

        userDAO.insertNewUser(registerUser);

    }

    // 更新数据库和Cookies中的用户信息
    public CookiesUser updateInfoService(int userId, String username, String password, int age, String address) {
        // 根据Cookies中的UserID从数据库中获取当前登录的用户信息
        User dbUserForUpdate = userDAO.selectByUserId(userId);
        if (dbUserForUpdate == null) {
            return null;
        } else {
            if (!username.equals(dbUserForUpdate.getUsername()) && !StringUtils.isEmpty(username)) {
                dbUserForUpdate.setUsername(username);
            }
            if (!password.equals(dbUserForUpdate.getPassword()) && !StringUtils.isEmpty(password)) {
                dbUserForUpdate.setPassword(password);
            }
            if (age != dbUserForUpdate.getAge() && age != 0) {
                dbUserForUpdate.setAge(age);
            }
            if (!address.equals(dbUserForUpdate.getAddress()) && !StringUtils.isEmpty(address)) {
                dbUserForUpdate.setAddress(address);
            }
            dbUserForUpdate.setUpdateTime(new Date());

            // 更新数据库中的用户信息
            userDAO.updateUserInfo(dbUserForUpdate);

            // 更新Cookies中的当前登录用户信息
            CookiesUser updatedCookiesUser = new CookiesUser();
            updatedCookiesUser.setId(userId);
            updatedCookiesUser.setUsername(dbUserForUpdate.getUsername());
            updatedCookiesUser.setLoginStatus(USER_LOGIN_STATUS_NORMAL);

            return updatedCookiesUser;
        }
    }
}
