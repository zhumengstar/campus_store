package com.java.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author:zhumeng
 * @desc:
 **/
@Controller
@RequestMapping(value = "/frontend", method = RequestMethod.GET)
public class FrontendController {
    @RequestMapping(value = "/index")
    public String Index() {
        return "/frontend/index";
    }

    //店铺类别列表页
    @RequestMapping(value = "/shoplist")
    private String Shoplist() {
        return "frontend/shoplist";
    }

    //店铺详情页
    @RequestMapping(value = "/shopdetail")
    private String Shopdetail() {
        return "frontend/shopdetail";
    }

    //店铺详情页
    @RequestMapping(value = "/productdetail")
    private String Productdetail() {
        return "frontend/productdetail";
    }
}
