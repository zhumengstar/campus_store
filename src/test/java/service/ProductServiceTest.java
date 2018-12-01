package service;

import baseTest.BaseTest;
import com.java.dto.other.ImageHolder;
import com.java.dto.ProductExecution;
import com.java.entity.Product;
import com.java.entity.ProductCategory;
import com.java.entity.Shop;
import com.java.enums.ProductStateEnum;
import com.java.exceptions.ShopOperationExecetion;
import com.java.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws ShopOperationExecetion, FileNotFoundException {
        //创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(15L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(15L);

        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        //创建缩略图文件流
        File thumbnailFile = new File("/Users/zgh/Desktop/watermark.jpg");
        InputStream is = new FileInputStream(thumbnailFile);

        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        //创建两个商品详情图文件流并将他们添加到详情图列表中
        File productImg1 = new File("/Users/zgh/Desktop/watermark.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("/Users/zgh/Desktop/watermark.jpg");
        InputStream is2 = new FileInputStream(productImg2);

        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        //添加商品验证
        ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);

        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }

    @Test
    public void testModifyProduct() throws ShopOperationExecetion, FileNotFoundException {
        //创建shopId为1且productCategoryId为1的商品实例并给其他成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(15L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(9L);

        product.setShop(shop);
        product.setProductId(6L);
        product.setProductName("yaya");
        product.setProductDesc("yaya悦");
        product.setProductCategory(pc);
        //创建缩略图文件流
        File thumbnailFile = new File("/Users/zgh/Desktop/yan.jpg");
        InputStream is = new FileInputStream(thumbnailFile);

        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);



        File productImg1 = new File("/Users/zgh/Desktop/watermark.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("/Users/zgh/Desktop/yan.jpg");
        InputStream is2 = new FileInputStream(productImg2);

        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();

        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));


        //添加商品验证
        ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }
}
