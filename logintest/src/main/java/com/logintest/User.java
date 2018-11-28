package com.logintest;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class User {

    @Getter @Setter private int id;

    @Getter @Setter private String username;

    @Getter @Setter private String password;

    @Getter @Setter private Date loginTime;

    @Getter @Setter private Date createTime;

    @Getter @Setter private Date updateTime;

    @Getter @Setter private int age;

    @Getter @Setter private String address;

}
