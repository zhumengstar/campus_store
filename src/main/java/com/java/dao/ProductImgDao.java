package com.java.dao;

import com.java.entity.ProductImg;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ProductImgDao {
    /**
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);
}
