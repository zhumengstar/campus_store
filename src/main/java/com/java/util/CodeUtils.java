package com.java.util;

import com.google.code.kaptcha.Constants;
import com.java.util.http.HttpServletRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码验证
 *
 * @author:zhumeng
 * @desc:
 **/
public class CodeUtils {
    //    public static boolean checkVerifyCode(HttpServletRequest request) {
//        String verifyCodeExpected = (String) request.getSession().getAttribute(
//                Constants.KAPTCHA_SESSION_KEY);
//        String verifyCodeActual = HttpServletRequestUtils.getString(request, "verifyCodeActual");
//
//        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
//            return false;
//        }
//        return true;
//    }
    private final static Logger logger = LoggerFactory.getLogger(CodeUtils.class);

    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtils.getString(request, "verifyCodeActual");
        logger.info(verifyCodeActual);
        logger.info(verifyCodeExpected);
        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}
