package com.tiandog.Controller;

import com.tiandog.Entity.Deal;
import com.tiandog.Service.DealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Resource private DealService dealService;


    //==================================================================
    //首页
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        List<Deal> dealList = dealService.getDealForIndexShow();
        List<String> typeList = new ArrayList<>();
        Deal tempDeal;
        String tempType = "";
        for (int i = 0; i < dealList.size(); i ++) {
            tempDeal = dealList.get(i);
            if (!(tempType.equals(tempDeal.getType()))) {
                typeList.add(tempDeal.getType());
                tempType = tempDeal.getType();
            }
        }

        modelMap.addAttribute("dealList", dealList);
        modelMap.addAttribute("typeList", typeList);

        return "views/index";

    }



    //==================================================================
    //商品详情页面
    @RequestMapping(value = "/deal/{id}", method = RequestMethod.GET)
    public String dealInfo(@PathVariable("id") long id,
                           ModelMap modelMap) {
        Deal deal = dealService.getDealById(id);
        modelMap.addAttribute("deal", deal);

        // 计算折扣后的优惠价格
        if (deal.getDiscount() > 0) {
            BigDecimal param = new BigDecimal(100);
            BigDecimal discount = new BigDecimal(deal.getDiscount());
            BigDecimal bd = param.subtract(discount);
            bd = bd.divide(param);
            BigDecimal curPrice = deal.getPrice().multiply(bd);
            modelMap.addAttribute("curPrice", curPrice);
        }


        return "views/dealDetail";
    }



    //==================================================================
    //商品分类分页展示页面
    @RequestMapping(value = "/deal/{type}/{page}")
    public String dealByType(@PathVariable("type") String type,
                             @PathVariable("page") int page,
                             ModelMap modelMap) {
        // 每页显示商品数目
        int dealNumInOnePage = 2;
        // 该类型商品总数
        int sum = dealService.getDealNumByType(type);
        // 计算总页数与最后一页的显示数目（一般小于每页显示商品数目）
        int dealNumMod = sum % dealNumInOnePage;
        int maxPageNum;
        int dealNumInLastPage;
        if (dealNumMod == 0) {
            maxPageNum = sum / dealNumInOnePage;
            dealNumInLastPage = dealNumInOnePage;
        } else {
            maxPageNum = sum / dealNumInOnePage + 1;
            dealNumInLastPage = dealNumMod;
        }

        // 根据当前请求页面编号查询数据
        List<Deal> dealList;
        if (page == maxPageNum && dealNumMod > 0) {
            dealList = dealService.getDealByTypeAndPage(type, page, dealNumInLastPage);
        } else {
            dealList = dealService.getDealByTypeAndPage(type, page, dealNumInOnePage);
        }


        modelMap.addAttribute("dealList", dealList);
        modelMap.addAttribute("type", type);
        modelMap.addAttribute("curPage", page);
        modelMap.addAttribute("maxPage", maxPageNum);

        return "views/dealByType";
    }




}
