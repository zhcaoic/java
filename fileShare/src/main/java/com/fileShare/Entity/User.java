package com.fileShare.Entity;

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


    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        if (this.getName() == null && other.getName() == null) {
            return true;
        }

        if (this.getName() != null && other.getName() != null) {
            return this.getName().equals(other.getName());
        }

        return false;
    }


}
