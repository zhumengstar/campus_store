package com.java.dao;

import com.java.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
