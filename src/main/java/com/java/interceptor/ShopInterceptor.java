package com.java.interceptor;

import com.java.dao.ShopDao;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
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
public class ShopInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ShopDao shopDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        //从session中获取当前选择的店铺
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        //从session中获取当前用户可操作的店铺列表
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        String shopId = null;
        shopId = request.getParameter("shopId");

        Shop tempShop = new Shop();
        tempShop.setOwner(user);
        int count = shopDao.queryShopCount(tempShop);
        List<Shop> shopList = shopDao.queryShopList(tempShop, 0, count);
        if (shopId==null&&shopList.size() == 0) {
//            response.sendRedirect(request.getContextPath() + "/shopadmin/shopoperation");
            return true;
        }

        if (shopId != null) {
            for (Shop s : shopList) {
                if (shopId.equals(s.getShopId().toString())) {
                    return true;
                }
            }
            response.sendRedirect(request.getContextPath() + "/shopadmin/shoplist");
            return false;
        } else {

            return true;
        }
    }
}
