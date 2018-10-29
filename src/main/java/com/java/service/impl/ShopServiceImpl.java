package com.java.service.impl;

import com.java.dao.ShopDao;
import com.java.dto.ShopExecution;
import com.java.entity.Shop;
import com.java.enums.ShopStateEnum;
import com.java.service.ShopService;
import com.java.util.ImageUtils;
import com.java.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;


    @Transactional
    @Override
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {

        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {

            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺创建失败...");
            } else {
                if (shopImg != null) {
                    //存储图片
                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new RuntimeException("addShopImg error" + e.getMessage());
                    }

                    //更新店铺图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new RuntimeException("更新图片地址失败。。。");
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("addShop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        //获取shop图片目录的相对路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtils.gengerateThumbnail(shopImg, dest);
    }
}
