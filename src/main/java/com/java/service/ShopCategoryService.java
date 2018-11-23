package com.java.service;

import com.java.entity.ShopCategory;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopCategoryService {

    /**
     * 根据查询条件获取ShopCategory列表
     *
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
