package com.java.interceptor;

import com.java.dao.ProductDao;
import com.java.dao.ShopDao;
import com.java.entity.PersonInfo;
import com.java.entity.Product;
import com.java.entity.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ProductInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(ProductInterceptor.class);

    @Autowired
    private ProductDao productDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        Long productId = null;
        try {
            productId = Long.valueOf(request.getParameter("productId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (productId != null) {

            Product product = new Product();
            product.setShop(currentShop);
            int count = productDao.queryProductCount(product);
            List<Product> productList = productDao.queryProductList(product, 0, count);

            for (Product p : productList) {
                if (productId == p.getProductId()) {
                    return true;
                }
            }
        }else {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/frontend/index");
        return false;
    }
}
