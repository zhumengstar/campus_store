package com.java.util.http;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取session中数据
 *
 * @author:zhumeng
 * @desc:
 **/
public class HttpServletRequestUtils {
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static Long getLong(HttpServletRequest request, String key) {
        try {
            return Long.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1d;
        }
    }

    public static Boolean getBollean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
//        try {
//            String result = request.getParameter(key);
//            if (result != null) {
//                result.trim();
//            }
//            if ("".equals(result)) {
//                result = null;
//            }
//            return result;
//        } catch (Exception e) {
//            return null;
//        }
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            if (result.equals("")) {
                result = null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
