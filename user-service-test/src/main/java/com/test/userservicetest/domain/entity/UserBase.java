package com.test.userservicetest.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserBase extends BaseEntity{

    // not null
    @Getter @Setter private int userId;

    // not null
    @Getter @Setter private int userNumber;

    // not null
    @Getter @Setter private String email;

    // not null
    @Getter @Setter private long cellphone;

    // not null
    @Getter @Setter private String password;

    // not null
    @Getter @Setter private String realPassword;

    // not null
    @Getter @Setter private String nickname;

    // not null 登录权限：0--白名单，允许访问；1--黑名单，禁止访问
    @Getter @Setter private int loginPermission;

    // not null
    @Getter @Setter private Date userCreateTime;

    // not null
    @Getter @Setter private Date userUpdateTime;

    // default null
    @Getter @Setter private Date userLoginTime;

}
