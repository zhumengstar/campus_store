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
}
