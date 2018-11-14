package dao;

import baseTest.BaseTest;
import com.java.dao.ProductDao;
import com.java.entity.Product;
import com.java.entity.ProductCategory;
import com.java.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(61L);
//        productCategory.setProductCategoryId(53L);
        product.setProductId(1L);
        product.setShop(shop);

        product.setProductName("yaya悦");
        product.setProductCategory(productCategory);

        int effectedNum = productDao.updateProduct(product);
        assertEquals(1, effectedNum);
    }


}
