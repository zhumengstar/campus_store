package com.java.service;

import com.java.dto.ProductCategoryExecution;
import com.java.entity.ProductCategory;
import com.java.exceptions.ProductCategoryOperationException;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ProductCategoryService {
    /**
     * 查询指定某个店铺下的所有商品类别信息
     *
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(Long shopId);


    /**
     * 批量添加商品类别
     * @param productCategoryList
     * @return
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;


    /**
     * 删除商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(Long productCategoryId,Long shopId) throws ProductCategoryOperationException;
}
