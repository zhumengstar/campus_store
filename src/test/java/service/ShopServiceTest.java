package service;

import baseTest.BaseTest;
import com.java.dao.ShopDao;
import com.java.dto.other.ImageHolder;
import com.java.dto.ShopExecution;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.entity.ShopCategory;
import com.java.enums.ShopStateEnum;
import com.java.exceptions.ShopOperationExecetion;
import com.java.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public void testQueryShopListAndCount() {
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(3L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition, 2, 9);
        System.out.println("店铺列表数：" + se.getShopList().size());
        System.out.println("店铺总数数：" + se.getCount());


    }

    @Test
    public void testAddShop() throws ShopOperationExecetion, FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(8L);
        area.setAreaId(3);

        shopCategory.setShopCategoryId(10L);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);

        shop.setOwner(owner);

        shop.setShopName("测试店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中。。。");
        File shopImg = new File("/Users/zgh/Desktop/watermark.jpg");
        shop.setShopImg(shopImg.getPath());


        InputStream is = null;
        is = new FileInputStream(shopImg);
        ImageHolder thumbnail = new ImageHolder(shopImg.getName(), is);
        ShopExecution se = shopService.addShop(shop, thumbnail);

        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);

    }

    @Test
    public void testModifyShop() throws ShopOperationExecetion, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(15L);
        shop.setShopName("yayaya");
        File shopImg = new File("/Users/zgh/Desktop/yan.jpg");
        InputStream is = new FileInputStream(shopImg);

        ImageHolder thumbnail = new ImageHolder(shopImg.getName(), is);
        ShopExecution shopExecution = shopService.modifyShop(shop, thumbnail);
        System.out.println(shopExecution.getShop().getShopImg());
    }
}
