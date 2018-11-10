package com.java.service;

import com.java.dto.ShopExecution;
import com.java.entity.Shop;
import com.java.exceptions.ShopOperationExecption;

import java.io.File;
import java.io.InputStream;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopService {

    /**
     * 根据shopCondition分页返回相应店铺列表
     *
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * 通过Id查询店铺
     *
     * @param shopId
     * @return
     */
    Shop getByShopId(Long shopId);


    /**
     * 修改店铺信息
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationExecption
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationExecption;

    /**
     * 添加店铺
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationExecption
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationExecption;
}
