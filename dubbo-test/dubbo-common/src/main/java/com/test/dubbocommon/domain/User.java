package com.test.dubbocommon.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -4294174087564467194L;

    @Getter @Setter private int id;

    @Getter @Setter private String name;

    @Getter @Setter private String detail;

}
