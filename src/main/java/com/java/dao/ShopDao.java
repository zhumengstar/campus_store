package com.java.dao;

import com.java.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopDao {
    /**
     * 分业查询店铺，可输入的条件：店铺名（模糊）店铺状态，店铺类别，区域id，owner
     *
     * @param shopCondition
     * @param rowIndex      从第几行开始去数据
     * @param pageSize      返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);


    /**
     * 返回queryShopList总数
     *
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);

    /**
     * 新增店铺
     *
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
