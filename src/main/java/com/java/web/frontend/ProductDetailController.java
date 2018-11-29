package com.java.web.frontend;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.java.entity.PersonInfo;
import com.java.entity.Product;
import com.java.entity.ProductImg;
import com.java.service.ProductImgService;
import com.java.service.ProductService;
import com.java.util.http.HttpServletRequestUtils;
import com.java.util.QRCodeUtil;
import com.java.util.ShortNetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhumeng
 * @desc:
 **/
@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    @Autowired
    protected ProductImgService productImgService;

    private static String URLPREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?"
            + "appid=wxd7f6c5b8899fba83&"
            + "redirect_uri=115.28.159.6/myo2o/shop/adduserproductmap&"
            + "response_type=code&scope=snsapi_userinfo&state=";
    private static String URLSUFFIX = "#wechat_redirect";

    @ResponseBody
    @RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
    private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        Long productId = HttpServletRequestUtils.getLong(request, "productId");

        Product product = null;

        if (productId != -1) {
            product = productService.getProductById(productId);
            List<ProductImg> productImgList=productImgService.getProductImgListByProductId(productId);
            product.setProductImgList(productImgList);
            modelMap.put("product", product);
//            modelMap.put("imgAddr",productImgList);
            modelMap.put("success", true);

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/generateqrcode4product", method = RequestMethod.GET)
    @ResponseBody
    private void generateQRCode4Product(HttpServletRequest request,
                                        HttpServletResponse response) {
        long productId = HttpServletRequestUtils.getLong(request, "productId");
        PersonInfo user = (PersonInfo) request.getSession()
                .getAttribute("user");
        if (productId != -1 && user != null && user.getUserId() != null) {
            long timpStamp = System.currentTimeMillis();
            String content = "{\"productId\":" + productId + ",\"customerId\":"
                    + user.getUserId() + ",\"createTime\":" + timpStamp + "}";
            String longUrl = URLPREFIX + content + URLSUFFIX;
            String shortUrl = ShortNetAddress.getShortURL(longUrl);
            BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(shortUrl,
                    response);
            try {
                MatrixToImageWriter.writeToStream(qRcodeImg, "png",
                        response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
