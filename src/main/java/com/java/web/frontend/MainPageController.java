package com.java.web.frontend;

import com.java.entity.ShopCategory;
import com.java.enums.ShopCategoryStateEnum;
import com.java.service.HeadLineService;
import com.java.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhumeng
 * @desc:
 **/
@Controller
@RequestMapping("/front")
public class MainPageController {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private HeadLineService headLineService;

    @RequestMapping(value = "/listmainpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> list1stshopCategory(){
        Map<String,Object> modelMap=new HashMap<String, Object>();

        List<ShopCategory> shopCategoryList=new ArrayList<ShopCategory>();

        try{
            shopCategoryList=shopCategoryService.getFirstLevelShopCategoryList();
            modelMap.put("shopcategorylist",shopCategoryList);
        }catch (Exception e){
            e.printStackTrace();
            ShopCategoryStateEnum s=ShopCategoryStateEnum.INNER_ERROR;
        }

    }
}
