package com.tiandog.DAO;


import com.tiandog.Entity.Deal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealDAO {

    List<Deal> getDealByStoreAmountAndTypeForIndex();

    Deal getDealById(long id);

    List<Deal> getDealByTypeAndPoint(@Param("type") String type,
                                     @Param("point") int point,
                                     @Param("imageSize") String imageSize,
                                     @Param("dealNum") int dealNum);

    Integer getDealNumByType(String type);

}
