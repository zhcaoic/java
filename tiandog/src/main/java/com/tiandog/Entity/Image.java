package com.tiandog.Entity;

import lombok.Getter;
import lombok.Setter;

public class Image {

    @Getter @Setter private long id;

    @Getter @Setter private long dealId;

    @Getter @Setter private String size;

    @Getter @Setter private String sourcePath;

}
