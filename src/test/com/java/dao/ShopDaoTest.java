package com.java.dao;

import com.java.BaseTest;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void insertShop() {
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


        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中。。。");

        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);


    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(2L);


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


        shop.setShopName("测试店铺");
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址1111");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中。。。");

        int effectedNum = shopDao.updateShop(shop);
//        assertEquals(0, effectedNum);


    }
}