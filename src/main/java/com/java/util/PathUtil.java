package com.java.util;

/**
 * @author:zhumeng
 * @desc:
 **/
public class PathUtil {

    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/project/image/";
        } else {
            basePath = "/Users/zgh/Desktop/images";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImagePath(Long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";

        return imagePath.replace("/", seperator);
    }
}
