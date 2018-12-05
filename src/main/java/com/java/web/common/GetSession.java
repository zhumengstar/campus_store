package com.java.web.common;

import com.java.entity.PersonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:zhumeng
 * @desc:
 **/
@RequestMapping("/get")
@Controller
public class GetSession {
    @RequestMapping(value = "/getsession", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getsession(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("user");

        if (personInfo != null) {
            modelMap.put("personInfo", personInfo);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "session中无user");
        }
        return modelMap;
    }
}
