package com.tiandog.DAO;

import com.tiandog.Entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CartDAO {

    Cart getCartByUserIdAndDealId(@Param("userId") long userId,
                                  @Param("dealId") long dealId);

    void updateDealCountToCart(Cart dbCart);

    void addNewDealToCart(Cart tempDealCart);

}
