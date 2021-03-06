package com.java.web.frontend;

import com.java.dto.ProductExecution;
import com.java.entity.Product;
import com.java.entity.ProductCategory;
import com.java.entity.Shop;
import com.java.service.ProductCategoryService;
import com.java.service.ProductService;
import com.java.service.ShopService;
import com.java.util.http.HttpServletRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhumeng
 * @desc:
 **/
@Controller
@RequestMapping("/frontend")
public class ShopDetailController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;


    /**
     * 店铺详情页，及店铺商品类别,传入店铺Id
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        //获取前台传来的shopId
        Long shopId = HttpServletRequestUtils.getLong(request, "shopId");
        Shop shop = null;
        List<ProductCategory> productCategoryList = null;

        if (shopId != null) {

            //获取Id为shopId的店铺信息
            shop = shopService.getByShopId(shopId);
            //获取店铺下的商品类别列表
            productCategoryList = productCategoryService.getProductCategoryList(shopId);
            modelMap.put("shop", shop);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;


    }


    /**
     * 列出该店铺产品
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listProductsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //获取页码
        int pageIndex = HttpServletRequestUtils.getInt(request, "pageIndex");
        //获取一页需要显示的条数
        int pageSize = HttpServletRequestUtils.getInt(request, "pageSize");
        //获取店铺Id
        Long shopId = HttpServletRequestUtils.getLong(request, "shopId");
        //空值判断
        if (pageIndex > -1 && pageSize > -1 && shopId > -1) {
            //尝试获取商品类别Id
            Long productCategoryId = HttpServletRequestUtils.getLong(request, "productCategoryId");
            //尝试获取模糊查找的商品名
            String productName = HttpServletRequestUtils.getString(request, "productName");
            //组合查询条件
            Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
            //按照传入的查询条件以及分页信息返回相应商品列表以及总数
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            //返回查询结果
            modelMap.put("success", true);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    //组合产品查询条件
    private Product compactProductCondition4Search(Long shopId, Long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if (productCategoryId != -1L) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }

        if (productName != null) {
            productCondition.setProductName(productName);
        }
        productCondition.setEnableStatus(1);
        return productCondition;
    }

}
