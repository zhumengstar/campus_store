package com.java.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.ShopExecution;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.entity.ShopCategory;
import com.java.enums.ShopStateEnum;
import com.java.service.AreaService;
import com.java.service.ShopCategoryService;
import com.java.service.ShopService;
import com.java.util.CodeUtils;
import com.java.util.HttpServletRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhumeng
 * @desc:
 **/
@Controller
@RequestMapping(value = "/shopadmin")
public class ShopManagementController {

    private final static Logger logger = LoggerFactory.getLogger(ShopManagementController.class);


    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();

        List<Area> areaList = new ArrayList<Area>();

        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        logger.info("成功。emmm");

        return modelMap;
    }


    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        //1.接受并转化相应参数，包括店铺信息以及图片信息

        logger.info("shopStr.....",request.getParameter("shopStr"));
        logger.info("verifyCodeActual.....",request.getParameter("verifyCodeActual"));


        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (request == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "错误。。。！");
            logger.info("错误。emmm");
            return modelMap;
        }


        logger.info(request.getRequestURL().toString());


        //验证码验证
        if (!CodeUtils.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入验证码错误。。。！");
            return modelMap;
        }


        //获取前端传的字符串转为对象
        String shopStr = HttpServletRequestUtils.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //转换为pojo
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "pojo转换错误。。。");
            return modelMap;
        }


        //2.图片流转换
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取请求信息流
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空！");
            return modelMap;
        }


        //2。注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();

            //Session TODO
            owner.setUseId(1L);
            shop.setOwner(owner);

//            xxxxx
//            File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtils.getRandomFileName());
//            try {
//                shopImgFile.createNewFile();
//            } catch (IOException e) {
//
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.getMessage());
//                return modelMap;
//            }
//            try {
//                inputStreamToFile(shopImg.getInputStream(), shopImgFile);
//            } catch (IOException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.getMessage());
//                return modelMap;
//            }
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);

                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "请输入店铺信息");
            }


            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

    /*
    private static void inputStreamToFile(InputStream ins, File file) {
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用inputStreamToFile产生异常:" + e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    ins.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("inputStreamToFile关闭io产生异常:" + e.getMessage());
            }
        }
    }*/
}
