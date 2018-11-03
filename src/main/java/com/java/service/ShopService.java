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
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationExecption;
}
