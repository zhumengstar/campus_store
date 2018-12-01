package com.java.service;

import com.java.dto.other.ImageHolder;
import com.java.dto.ShopExecution;
import com.java.entity.Shop;
import com.java.exceptions.ShopOperationExecetion;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopService {

    /**
     * 根据shopCondition分页返回相应店铺列表
     *
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * 通过Id查询店铺
     *
     * @param shopId
     * @return
     */
    Shop getByShopId(Long shopId);


    /**
     * 修改店铺信息
     *
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationExecetion
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationExecetion;

    /**
     * 添加店铺
     *
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationExecetion
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationExecetion;
}
