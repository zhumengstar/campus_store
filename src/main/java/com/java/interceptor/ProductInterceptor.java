package com.java.interceptor;

import com.java.dao.ProductDao;
import com.java.entity.Product;
import com.java.entity.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String productId = null;

        productId = request.getParameter("productId");

        if (productId != null) {

            Product product = new Product();
            product.setShop(currentShop);
            int count = productDao.queryProductCount(product);
            List<Product> productList = productDao.queryProductList(product, 0, count);

            for (Product p : productList) {
                if (productId.equals(p.getProductId().toString())) {
                    return true;
                }
            }
        } else {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/shopadmin/productmanagement");
        return false;
    }
}
