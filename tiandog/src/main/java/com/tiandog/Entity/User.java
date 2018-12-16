package com.tiandog.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class User {

    @Getter @Setter private long id;

    @Getter @Setter private String name;

    @Getter @Setter private String password;

    @Getter @Setter private Date loginTime;

    @Getter @Setter private Date createTime;

    @Getter @Setter private Date updateTime;


}
