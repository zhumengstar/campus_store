package com.java.interceptor;

import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.service.ShopService;
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
    private ShopService shopService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        //从session中获取当前选择的店铺
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shoplist");
//        if (currentShop == null) {
//            shopService.getByShopId(15L);
//        }
//        if (shopList.size() <= 0) {
//            Shop shop = new Shop();
//            shop.setOwner((PersonInfo) request.getSession().getAttribute("user"));
//            for (Shop s : shopService.getShopList(currentShop, 0, 100).getShopList())
//                shopList.add(s);
//        }


        //非空判断
        if (currentShop != null && shopList != null) {
            //遍历可操作的店铺列表
            for (Shop shop : shopList) {
                //如果当前店铺在可操作的列表里返回true，进行接下来的用户操作
                if (shop.getShopId() == currentShop.getShopId()) {
                    return true;
                }
            }
        }
        return false;
    }
}
