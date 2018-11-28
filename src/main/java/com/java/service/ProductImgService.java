package com.java.service;

import com.java.entity.ProductImg;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ProductImgService {
    List<ProductImg> getProductImgListByProductId(Long productId);
}
