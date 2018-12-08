package com.java.interceptor.other;

import com.java.dao.PersonInfoDao;
import com.java.dao.ShopDao;
import com.java.entity.PersonInfo;
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
public class ShopPermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private PersonInfoDao personInfoDao;

    Logger logger = LoggerFactory.getLogger(ShopPermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
//        //从session中获取当前选择的店铺
//        //从session中获取当前用户可操作的店铺列表
//        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
//        //非空判断
//        if (currentShop != null && shopList != null) {
//            //遍历可操作的店铺列表
//            for (Shop shop : shopList) {
//                //如果当前店铺在可操作的列表里返回true,进行接下来的用户操作
//                if (shop.getShopId() == currentShop.getShopId()) {
//                    return true;
//                }
//            }
//        }
//        response.sendRedirect(request.getContextPath() + "/frontend/index");
//        return false;


        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        Long shopId = null;
        try {
            shopId = Long.valueOf(request.getParameter("shopId"));
        } catch (Exception e) {
            e.toString();
        }
        //通过用户获取有权限的店铺
        Shop shop = new Shop();
        shop.setOwner(user);
        int count = shopDao.queryShopCount(shop);
        List<Shop> shopList = shopDao.queryShopList(shop, 0, count);
        int i = 0;
        if (shopId != null) {
            for (Shop s : shopList) {
                if (!shopId.equals(s.getShopId())) {
                    i += 1;
                    continue;
                } else {
                    return true;
                }
            }
            if (i == shopList.size()) {
                response.sendRedirect(request.getContextPath() + "/shopadmin/shoplist");
                return false;
            }
        }
        if (currentShop != null) {
            for (Shop s : shopList) {
                if (s.getShopId() == currentShop.getShopId()) {
                    if (shopId != null) {
                        if (!shopId.equals(currentShop.getShopId())) {
                            response.sendRedirect(request.getContextPath() + "/shopadmin/shoplist");
                            return false;
                        }
                    }
                    return true;
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/shopadmin/shoplist");
            return false;
        }
        response.sendRedirect(request.getContextPath() + "/shopadmin/shoplist");
        return false;
    }
}
