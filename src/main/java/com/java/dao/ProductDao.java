package com.java.dao;

import com.java.entity.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ProductDao {
    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 通过productId查询唯一的商品信息
     *
     * @param productId
     * @return
     */
    Product queryProductById(Long productId);

    /**
     * 查询商品的数量
     *
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 更新商品信息
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);
}
