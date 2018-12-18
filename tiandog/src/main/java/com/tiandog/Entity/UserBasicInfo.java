package com.tiandog.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserBasicInfo {

    @Getter @Setter private long id;

    @Getter @Setter private long userId;

    @Getter @Setter private String realName;

    @Getter @Setter private String email;

    @Getter @Setter private String phone;

    @Getter @Setter private int age;

    @Getter @Setter private String address;

    @Getter @Setter private String occupation;

    @Getter @Setter private Date updateTime;

}
