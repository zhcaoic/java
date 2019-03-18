package com.tiandog.Controller;

import com.tiandog.Entity.CookiesUser;
import com.tiandog.Service.CartService;
import com.tiandog.Util.CookiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    @Resource private CartService cartService;

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
