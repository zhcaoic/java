package com.tiandog.Entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CookiesUser implements Serializable {

    @Getter @Setter private long id;

    @Getter @Setter private String name;

    @Getter @Setter private int loginStatus;

}
