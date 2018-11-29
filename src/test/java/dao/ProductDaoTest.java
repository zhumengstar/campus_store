package dao;

import baseTest.BaseTest;
import com.java.dao.ProductDao;
import com.java.entity.Product;
import com.java.entity.ProductCategory;
import com.java.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void insertProductTest() {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(61L);
        product.setProductName("111");
        product.setShop(shop);
        product.setPriority(20);
        product.setEnableStatus(1);
        int effectedNum = productDao.insertProduct(product);
        assertEquals(1, effectedNum);

    }

    @Test
    @Transactional
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(8L);
//        productCategory.setProductCategoryId(53L);
        product.setProductId(1L);
        product.setShop(shop);

        product.setProductName("yaya悦");
        product.setProductCategory(productCategory);

        int effectedNum = productDao.updateProduct(product);
        assertEquals(0, effectedNum);
    }


    @Test
    public void testQueryProductList() throws Exception {
        Product productCondition = new Product();

//        productCondition.setProductCategory();


        //分页查询，预期返回三条结果
        List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
        //查询名称为yaya的商品数量
        int count = productDao.queryProductCount(productCondition);
        assertEquals(13, count);


        //使用商品模糊查询，预期返回两条结果
        productCondition.setProductName("yaya");
        productList = productDao.queryProductList(productCondition, 0, 3);

        assertEquals(0, productList.size());

        count = productDao.queryProductCount(productCondition);
        assertEquals(0, count);

    }


    @Test
    public void updateProductCategoryToNull() throws Exception {
        int effectedNum = productDao.updateProductCategoryToNull(44L);
        assertEquals(0, effectedNum);
    }

}
