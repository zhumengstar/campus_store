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

public class ShopPermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ShopDao shopDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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


        //非空判断
        if (currentShop != null && shopList != null) {
            //遍历可操作的店铺列表
            for (Shop shop : shopList) {
                //如果当前店铺在可操作的列表里返回true,进行接下来的用户操作
                if (shop.getShopId() == currentShop.getShopId()) {
                    return true;
                }
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open('" + request.getContextPath() + "/shopadmin/shoplist','_self')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }
}
