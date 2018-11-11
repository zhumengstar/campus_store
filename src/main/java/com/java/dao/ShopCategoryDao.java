package com.java.dao;

import com.java.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopCategoryDao {
    /**
     * 类别查询类别列表
     *
     * @param shopCategory
     * @return
     */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
