package com.tiandog.Service;

import com.tiandog.DAO.DealDAO;
import com.tiandog.Entity.Deal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {

    @Resource private DealDAO dealDAO;


    //==================================================================
    // 获取首页展示的商品，共计两类：手机、服饰。分别取库存量前4位。
    public List<Deal> getDealForIndexShow() {
        List<Deal> dealList = new ArrayList<>();
        dealList = dealDAO.getDealByStoreAmountAndTypeForIndex();
        return dealList;
    }



    //==================================================================
    //根据商品ID查询商品完整信息，用于商品详情页展示
    public Deal getDealById(long id) {
        Deal dbDeal = dealDAO.getDealById(id);
        return dbDeal;
    }



    //==================================================================
    //根据商品Type、分页查询的页数，查询对应数目的商品信息
    public List<Deal> getDealByTypeAndPage(String type, int page, int dealNum) {
        if (page < 1) {
            return null;
        }

        int point = 2 * page - 2;
        String imageSize = "small";
        List<Deal> dbDealList = dealDAO.getDealByTypeAndPoint(type, point, imageSize, dealNum);
        return dbDealList;
    }


    //==================================================================
    //根据商品Type获取全部商品数目
    public int getDealNumByType(String type) {
        Integer dbSum = dealDAO.getDealNumByType(type);
        return dbSum;
    }



}
