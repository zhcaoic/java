package com.fileShare.Service;

import com.fileShare.DAO.UserDAO;
import org.springframework.stereotype.Service;
import com.fileShare.Entity.User;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Service
public class UserService {

    @Resource private UserDAO userDAO;


    //==================================================================
    //注册
    public void registerService(String name, String password) {
        User registerUser = new User();
        registerUser.setName(name);
        registerUser.setPassword(password);
        registerUser.setCreateTime(new Date());
        registerUser.setUpdateTime(new Date());

        userDAO.insertNewUser(registerUser);
    }


    //==================================================================
    //登录
    public User loginService(String name, String password) {
        //验证输入用户名与密码不为空
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return null;
        }

        //验证数据库中该用户不为空
        User dbUser = userDAO.selectByName(name);
        if (dbUser == null) {
            return null;
        }

        //验证密码
        if (!Objects.equals(password, dbUser.getPassword())) {
            return null;
        }

        //更改登录时间
        dbUser.setLoginTime(new Date());
        userDAO.updateLoginTime(dbUser);

        return dbUser;
    }

}
