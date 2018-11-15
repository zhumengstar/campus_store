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
     * 查询商品列表并分页，可输入条件有：商品名（模糊），商品状态，店铺id，商品类别
     *
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 添加商品
     *
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;

    /**
     * 通过id获取商品信息
     *
     * @param productId
     * @return
     */
    Product getProductById(Long productId);

    /**
     * 修改店铺信息及图片处理
     *
     * @param product
     * @param thymbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thymbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;


}
