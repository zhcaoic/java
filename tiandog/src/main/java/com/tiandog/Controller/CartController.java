package com.tiandog.Controller;

import com.tiandog.Entity.Cart;
import com.tiandog.Entity.CookiesUser;
import com.tiandog.Entity.Deal;
import com.tiandog.Service.CartService;
import com.tiandog.Service.DealService;
import com.tiandog.Util.CookiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Resource private CartService cartService;
    @Resource private DealService dealService;


    //==================================================================
    //购物车内容显示，仅在登录状态下可见
    @RequestMapping(value = "/cart/show", method = RequestMethod.GET)
    public String showCartByUser(HttpServletRequest request,
                                 ModelMap modelMap) {
        // 获取当前登录的用户ID
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);
        long userId = cookiesUser.getId();
        // 根据用户ID查询购物车所有商品信息
        List<Cart> cartList = cartService.getCartDealByUserId(userId);
        if (cartList == null) {
            return "/views/cartShow";
        }

        // 根据商品ID获取商品详细信息
        List<Deal> dealList = new ArrayList<>();
        for (Cart cart : cartList) {
            Deal deal = dealService.getDealById(cart.getCartDealId());
            // 计算实际价格，ps:检查数量<=99且<=库存量在订单环节完成
            if (deal.getDiscount() > 0) {
                BigDecimal param = new BigDecimal(100);
                BigDecimal discount = new BigDecimal(deal.getDiscount());
                BigDecimal bd = param.subtract(discount);
                bd = bd.divide(param);
                BigDecimal curPrice = deal.getPrice().multiply(bd);
                // 将实际价格赋值给deal.price属性
                deal.setPrice(curPrice);
            }
            // dealList中每个deal的price属性即为折后价格
            dealList.add(deal);
        }

        modelMap.addAttribute("cartList", cartList);
        modelMap.addAttribute("dealList", dealList);

        return "/views/cartShow";
    }




    //==================================================================
    //添加商品至购物车
    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public String addDealToCart(@RequestParam("userId") String stringUserId,
                                @RequestParam("dealId") String stringDealId,
                                @RequestParam("dealNum") String stringDealNum,
                                @RequestParam("dealName") String dealName,
                                @RequestParam("curUrl") String preUrl,
                                HttpServletRequest request,
                                ModelMap modelMap) {
        // 参数类型转化
        long userId = Long.parseLong(stringUserId);
        long dealId = Long.parseLong(stringDealId);
        int dealNum = Integer.parseInt(stringDealNum);
        // 从Cookies中获取当前登录用户的ID
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);
        long cookieUserId = cookiesUser.getId();
        // 校验用户ID是否相等
        // FIXME 该步骤可以移至前端JQuery & Ajax中实现，弹窗提示报错
        if (userId != cookieUserId) {
            return "error/addToCartError";
        }

        // 添加商品至购物车中
        cartService.addDealToCart(userId, dealId, dealNum);

        modelMap.addAttribute("dealName", dealName);
        modelMap.addAttribute("dealCount", dealNum);
        modelMap.addAttribute("preUrl", preUrl);

        return "views/addToCartSuccess";
    }

}
