package com.java.util;

import com.java.enums.ShopStateEnum;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author:zhumeng
 * @desc:图片
 **/
public class ImageUtils {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();


    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random r = new Random();


    /**
     * @param thumbnailInputStream
     * @param targetAddr
     * @return
     */
    public static String gengerateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;

        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/IM62.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到到目录
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            Boolean a = dirPath.mkdirs();
            Boolean s = a;
        } else {
            ShopStateEnum.INNER_ERROR.getState();
        }
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
//        String originalFileName = cFile.getOriginalFilename();
//        return originalFileName.substring(originalFileName.lastIndexOf("."));
//        String originalFileName = cFile.getName();
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        //获取随机五位随机数
        int rannum = r.nextInt(89999) + 10000;
        String nowTImeStr = sDateFormat.format(new Date());
        return nowTImeStr + rannum;
    }


    public static void main(String[] args) throws IOException {

        Thumbnails.of(new File("/Users/zgh/Desktop/IMG_0277.jpg"))
                .size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/IM62.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("/Users/zgh/Desktop/合成.jpg");
    }
}
