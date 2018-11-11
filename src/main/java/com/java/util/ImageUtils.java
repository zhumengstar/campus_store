package com.java.util;

import com.java.dto.ImageHolder;
import com.java.enums.ShopStateEnum;
import com.java.web.shopadmin.ShopManagementController;
import com.sun.javafx.scene.shape.PathUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
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

    private final static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random r = new Random();

    /**
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;

        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
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
            dirPath.mkdirs();
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

    /**
     * storePath是文件的路径还是目录的路径
     * 如果是文件的路径则删除该文件
     * 如果是目录的路径则删除该目录下的文件
     *
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File file[] = fileOrPath.listFiles();
                for (int i = 0; i < file.length; i++) {
                    file[i].delete();
                }

            }
            fileOrPath.delete();
        }
    }

    public static void main(String[] args) throws IOException {

        Thumbnails.of(new File("/Users/zgh/Desktop/IMG_0277.jpg"))
                .size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/IM62.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("/Users/zgh/Desktop/合成.jpg");
    }

    /**
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        //获取不重复随机名
        String realFileName = getRandomFileName();
        //获取文件到扩展名
        String extension = getFileExtension(thumbnail.getImageName());
        //如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        //获取文件要保存到到相对路径（带文件名）
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("currentAddr is" + relativeAddr);
        //获取文件要保存到目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is " + PathUtil.getImgBasePath() + relativeAddr);
        //调用Thumbnail生成带有水印到图片
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }

        return relativeAddr;
    }
}
