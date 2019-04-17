package com.test.userservicetest.service;

import com.test.userservicetest.domain.entity.UserBase;

public interface UserService {

    /**
     * 登录服务
     * @param name 登录名，可以是用户编号、昵称、邮箱、手机号
     * @param pwd 明文密码
     * @return 查询结果
     */
    UserBase loginService(String name, String pwd);


    /**
     * 注册服务
     * @param nickname 昵称
     * @param pwd 明文密码（未加密）
     * @param email 邮箱
     * @param cellphoneTemp 手机号码（未转型）
     * @return 0--注册成功；1--昵称已被占用；2--邮箱已被占用；3--手机号已被占用
     */
    int registerService(String nickname, String pwd, String email, String cellphoneTemp);
}
