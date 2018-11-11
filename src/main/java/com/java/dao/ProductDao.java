package com.java.dao;

import com.java.entity.Product;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ProductDao {
    /**
     * 添加商品
     * @param product
     * @return
     */
    int insertProduct(Product product);
}
