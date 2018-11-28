package com.logintest;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CookiesUser implements Serializable {

    @Getter @Setter private int id;

    @Getter @Setter private String username;

    @Getter @Setter private int loginStatus;

}
