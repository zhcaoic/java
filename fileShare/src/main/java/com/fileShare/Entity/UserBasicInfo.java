package com.fileShare.Entity;

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


    @Override
    public int hashCode() {
        return (int) userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof UserBasicInfo)) {
            return false;
        }

        UserBasicInfo other = (UserBasicInfo) obj;
        if (this.getUserId() == 0 && other.getUserId() == 0) {
            return true;
        }

        if (this.getUserId() != 0 && other.getUserId() != 0) {
            return (this.getUserId() == other.getUserId());
        }

        return false;
    }

}
