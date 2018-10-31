package com.java.dao;

import com.java.BaseTest;
import com.java.entity.Area;
import com.java.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory() {

        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.println(shopCategoryList+".......");

        assertEquals(2, shopCategoryList.size());

        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(3L);
        testCategory.setParent(parentCategory);

        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);

        System.out.println(shopCategoryList+".......");
//        assertEquals(1,shopCategoryList.size());


    }
}
