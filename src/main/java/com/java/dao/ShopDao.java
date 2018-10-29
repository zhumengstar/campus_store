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
}
