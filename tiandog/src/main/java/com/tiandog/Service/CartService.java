package com.tiandog.Service;

import com.tiandog.DAO.CartDAO;
import com.tiandog.Entity.Cart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class CartService {

    @Resource private CartDAO cartDAO;

    //==================================================================
    //添加商品至购物车
    public void addDealToCart(long userId, long dealId, int dealNum) {
        // 根据用户ID和商品ID查询该用户的购物车信息
        Cart dbCart = cartDAO.getCartByUserIdAndDealId(userId, dealId);
        if (dbCart == null) {
            Cart tempCartDeal = new Cart();
            tempCartDeal.setCartUserId(userId);
            tempCartDeal.setCartDealId(dealId);
            tempCartDeal.setCartDealCount(dealNum);
            tempCartDeal.setCartUpdateTime(new Date());
            cartDAO.addNewDealToCart(tempCartDeal);
        } else {
            int preDealCount = dbCart.getCartDealCount();
            dealNum = dealNum + preDealCount;
            dbCart.setCartDealCount(dealNum);
            dbCart.setCartUpdateTime(new Date());
            cartDAO.updateDealCountToCart(dbCart);
        }
    }

}
