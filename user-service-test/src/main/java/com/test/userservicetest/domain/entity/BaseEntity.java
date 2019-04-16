package com.test.userservicetest.domain.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体基类
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4294174087564467194L;

    @Getter @Setter private int id;


    /**
     * 根据实体类列表，获取实体类列表对应的实体ID列表
     * @param entities 实体集合
     * @param <T> 实体类型
     * @return ID集合
     */
    public static <T extends BaseEntity> List<Integer> getIdList(List<T> entities) {
        List<Integer> ids = new ArrayList<>();
        for (T entity : entities) {
            ids.add(entity.getId());
        }
        return ids;
    }

    /**
     * 根据实体类列表，获取实体类列表对应的ID与实体Map
     * @param entities 实体集合
     * @param <T> 实体类型
     * @return ID与实体Map
     */
    public static <T extends BaseEntity> Map<Integer, T> getIDEntityMap(List<T> entities) {
        Map<Integer, T> idEntityMap = new HashMap<>();
        for (T entity : entities) {
            idEntityMap.put(entity.getId(), entity);
        }
        return idEntityMap;
    }

    /*
    public String toString() {

    }
    */

}
