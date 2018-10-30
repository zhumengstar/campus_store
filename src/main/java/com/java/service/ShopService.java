package com.java.service;

import com.java.dto.ShopExecution;
import com.java.entity.Shop;

import java.io.File;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
