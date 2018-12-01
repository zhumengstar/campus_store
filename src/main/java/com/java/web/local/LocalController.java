package com.java.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author:zhumeng
 * @desc:
 **/

@Controller
@RequestMapping(value = "/local")
public class LocalController {
    @RequestMapping(value = "/accountbind", method = RequestMethod.GET)
    public String accountbind() {
        return "local/accountbind";
    }

    @RequestMapping(value = "/changepsw", method = RequestMethod.GET)
    public String changepsw() {
        return "local/changepsw";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "local/login";
    }


}
