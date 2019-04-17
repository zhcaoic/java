package com.test.userservicetest.domain.entity;

import lombok.Getter;
import lombok.Setter;

public class SessionUser extends BaseEntity{

    @Getter @Setter private int id;

    @Getter @Setter private int userNumber;

    @Getter @Setter private String nickname;

    @Getter @Setter private int loginPermission;

}
