package com.tiandog.Entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class Deal {

    // other Entity
    @Getter @Setter private Merchant merchant;

    @Getter @Setter private List<Image> imageList;


    // columns
    @Getter @Setter private long id;

    @Getter @Setter private String name;

    @Getter @Setter private BigDecimal price;

    @Getter @Setter private int discount;

    @Getter @Setter private int storeAmount;

    @Getter @Setter private int saleAmount;

    @Getter @Setter private String type;

    @Getter @Setter private long merchantId;

    @Getter @Setter private String detailInfo;



}
