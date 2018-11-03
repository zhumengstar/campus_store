package com.java.service;

import com.java.entity.ShopCategory;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
