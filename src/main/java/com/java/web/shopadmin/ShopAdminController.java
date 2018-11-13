package com.java.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * 解析路由并转发
 * @author:zhumeng
 * @desc:
 **/
@Controller
@RequestMapping(value = "/shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        //转发至店铺注册或者编辑界面
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList() {
        //转发到店铺列表界面
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        //转发到店铺管理界面
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement")
    public String productCategoryManagement() {
        //商品类别管理
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManagement() {
        //商品管理
        return "shop/productoperation";
    }


    @RequestMapping(value = "/productoperation")
    public String productOperation() {
        //商品添加或编辑界面
        return "shop/productoperation";
    }


}
