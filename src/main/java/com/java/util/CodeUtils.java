package com.java.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:zhumeng
 * @desc:
 **/
public class CodeUtils {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected=(String)request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual=HttpServletRequestUtils.getString(request,"verifyCodeActual");

        if(verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }
}
