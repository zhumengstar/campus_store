package com.java.dao;

import com.java.entity.Shop;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
