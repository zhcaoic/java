package com.tiandog.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Cart {


    @Getter @Setter private long cartId;

    @Getter @Setter private long cartUserId;

    @Getter @Setter private long cartDealId;

    @Getter @Setter private int cartDealCount;

    @Getter @Setter private Date cartUpdateTime;


}
