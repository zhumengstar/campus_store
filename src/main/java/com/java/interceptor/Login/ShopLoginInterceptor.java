package com.java.interceptor.Login;

import com.java.entity.PersonInfo;
import com.java.interceptor.ProductInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ShopLoginInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(ShopLoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        //从session获取用户信息
        Object userObj = request.getSession().getAttribute("user");
        if (userObj != null) {
            //若用户信息不为空则将用户信息转换为PersonInfo实体类对象
            PersonInfo user = (PersonInfo) userObj;

            if (user != null && user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1) {//若通过验证则返回true，拦截器返回true之后，用户接下来得以正常操作
                return true;
            }

        }

        logger.debug(request.getContextPath()+"/frontend/index");
        response.sendRedirect(request.getContextPath()+"/frontend/index");
        return false;
    }
}
