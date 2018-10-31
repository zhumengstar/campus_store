package com.java.service;

import com.java.BaseTest;
import com.java.dao.ShopDao;
import com.java.dto.ShopExecution;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.entity.ShopCategory;
import com.java.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testAddShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUseId(1L);
        area.setAreaId(2);

        shopCategory.setShopCategoryId(1L);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);

        shop.setOwner(owner);
        shop.setOwnerId(owner.getUseId());


        shop.setShopName("测试店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中。。。");
        File shopImg= new File("/Users/zgh/Desktop/IMG_0277.jpg");
        shop.setShopImg(shopImg.getPath());


        ShopExecution se=shopService.addShop(shop,shopImg);

        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
//        int effectedNum = shopDao.insertShop(shop);
//        assertEquals(1, effectedNum);

    }
}