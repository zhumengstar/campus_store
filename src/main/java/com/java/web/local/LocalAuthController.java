package com.java.web.local;

import com.java.dto.LocalAuthExecution;
import com.java.entity.LocalAuth;
import com.java.entity.PersonInfo;
import com.java.enums.LocalAuthEnum;
import com.java.exceptions.LocalAuthOperationException;
import com.java.service.LocalAuthService;
import com.java.util.CodeUtils;
import com.java.util.http.HttpServletRequestUtils;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author:zhumeng
 * @desc:
 **/
@Controller
@RequestMapping(value = "/local", method = {GET, POST})
public class LocalAuthController {

    @Autowired
    private LocalAuthService localAuthService;

    @RequestMapping(value = "/bindlocalauth", method = GET)
    @ResponseBody
    public Map<String, Object> bindLocalAuth(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        if (!CodeUtils.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入错误验证码");
            return modelMap;
        }
        String username = HttpServletRequestUtils.getString(request, "username");
        String password = HttpServletRequestUtils.getString(request, "password");

        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        if (username != null && password != null && user != null) {
            LocalAuth localAuth = new LocalAuth();
            localAuth.setUserName(username);
            localAuth.setPassword(password);
            localAuth.setPersonInfo(user);
            LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
            if (le.getState() == LocalAuthEnum.SUCCESS.getState()) {
                modelMap.put("success", true);

            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", le.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/changelocalpwd", method = POST)
    public Map<String, Object> changeLocalPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtils.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入错误验证码");
            return modelMap;
        }

        String username = HttpServletRequestUtils.getString(request, "username");
        String password = HttpServletRequestUtils.getString(request, "password");

        String newpassword = HttpServletRequestUtils.getString(request, "newpassword");

        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        if (user != null && username != null && password != null && newpassword != null && user.getUserId() != null) {
            try {
                LocalAuth localAuth = localAuthService.getLocalAuth(user.getUserId());
                if (localAuth == null || !localAuth.getUserName().equals(username)) {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "输入的账号非本次登录的账号");
                    return modelMap;
                }
                LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), username, password, newpassword, new Date());

                if (le.getState() == LocalAuthEnum.SUCCESS.getState())
                    modelMap.put("success", true);
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", le.getStateInfo());
                }

            } catch (LocalAuthOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入密码");
        }
        return modelMap;
    }



    @ResponseBody
    @RequestMapping(value = "logincheck",method = POST)
    public Map<String,Object> logincheck(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();

        boolean needVerify=HttpServletRequestUtils.getBollean(request,"needVerify");

        if(needVerify&&!CodeUtils.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }

        String username=HttpServletRequestUtils.getString(request,"username");

        String password=HttpServletRequestUtils.getString(request,"password");

        if(username!=null&&password!=null){
            LocalAuth localAuth=localAuthService.getLocalAuthByUserNameAndPwd(username,password);
            if(localAuth!=null){
                modelMap.put("success",true);
                request.getSession().setAttribute("user",localAuth.getPersonInfo());
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","用户名或密码错误");
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","用户名和密码均不能为空");

        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/logout",method = POST)
    public Map<String,Object> logout(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        request.getSession().setAttribute("user",null);
        modelMap.put("success",true);
        return modelMap;
    }

}
