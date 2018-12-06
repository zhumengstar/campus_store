package com.java.interceptor;

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
public class All extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(ShopPermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

        logger.debug(request.getContextPath());
        logger.debug(request.getContextPath() + request.getRequestURL()+request.getParameter("shopId"));
        logger.debug(request.getContextPath() + request.getSession());
        logger.debug(request.getContextPath());
        logger.debug(request.getContextPath());
        logger.debug(request.getContextPath());
        logger.debug(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/frontend/index");
        return false;
    }
}
