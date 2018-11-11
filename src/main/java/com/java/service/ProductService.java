package com.java.service;

import com.java.dto.ImageHolder;
import com.java.dto.ProductExecution;
import com.java.entity.Product;
import com.java.exceptions.ProductOperationException;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ProductService {
    /**
     * 添加商品
     *
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;
}
