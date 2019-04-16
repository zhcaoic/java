package com.test.userservicetest.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class UserInfo extends BaseEntity{

    // not null
    @Getter @Setter private int userInfoId;

    // not null
    @Getter @Setter private int userInfoUserId;

    // default null
    @Getter @Setter private String realname;

    // default 2    性别：0--女性；1-男性；2-保密
    @Getter @Setter private int sex;

    // default null
    @Getter @Setter private int age;

    // default null
    @Getter @Setter private String address;

    // default null
    @Getter @Setter private String occupation;

    // default null
    @Getter @Setter private String organization;

    // not null
    @Getter @Setter private Date userInfoCreateTime;

    // not null
    @Getter @Setter private Date userInfoUpdateTime;

}
