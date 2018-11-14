package com.java.dao;

import com.java.entity.ProductImg;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ProductImgDao {
    /**
     * 插入商品详情图列表
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除商品详情图
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(Long productId);
}
