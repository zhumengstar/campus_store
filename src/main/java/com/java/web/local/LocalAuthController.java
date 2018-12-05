package com.java.web.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.LocalAuthExecution;
import com.java.dto.other.ImageHolder;
import com.java.entity.LocalAuth;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Enum.valueOf;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 1.绑定本地账号
 * 2.修改密码
 * 3.登录验证
 * 4.注册本地账号
 * 5.登出
 *
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

        //判断当前用户是否有账号

        if (username != null && password != null && user != null) {
            LocalAuth localAuth = new LocalAuth();
            //设置新用户名，密码
            localAuth.setUserName(username);
            localAuth.setPassword(password);
            //当前用户
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

        String newpassword = HttpServletRequestUtils.getString(request, "newPassword");

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
    @RequestMapping(value = "/logincheck", method = POST)
    public Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        boolean needVerify = HttpServletRequestUtils.getBollean(request, "needVerify");

        if (needVerify && !CodeUtils.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        String username = HttpServletRequestUtils.getString(request, "username");

        String password = HttpServletRequestUtils.getString(request, "password");

        if (username != null && password != null) {
            LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username, password);
            if (localAuth != null) {
                modelMap.put("success", true);
                request.getSession().setAttribute("user", localAuth.getPersonInfo());
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名或密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");

        }
        return modelMap;
    }


    @ResponseBody
    @RequestMapping(value = "/registeraccount", method = POST)
    public Map<String, Object> register(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        //验证码
        if (!CodeUtils.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        LocalAuth localAuth = null;
        //判断账号信息
        String localAuthStr = HttpServletRequestUtils.getString(request, "localAuthStr");
        try {
            //接收前端传来的相关的字符串信息,将它转换成实体类
            localAuth = mapper.readValue(localAuthStr, LocalAuth.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }


        //获取前端传来的文件流,将其接收到profileImg
        CommonsMultipartFile profileImg = null;
        //图片解析器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            profileImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("profileImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未上传图片");
            return modelMap;
        }
        ImageHolder thumbnail = null;
        try {
            thumbnail = new ImageHolder(profileImg.getOriginalFilename(), profileImg.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片失败");
            return modelMap;
        }

        try {
            if (localAuth != null && localAuth.getUserName() != null && !localAuth.getUserName().equals("") && localAuth.getPassword() != null && !localAuth.getPassword().equals("")) {
                LocalAuthExecution le = localAuthService.register(localAuth, thumbnail);
                if (le.getState() == LocalAuthEnum.SUCCESS.getState()) {
                    //注册成功设置session
                    request.getSession().setAttribute("user", le.getLocalAuth().getPersonInfo());
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", le.getStateInfo());
                    return modelMap;
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "信息输入缺失");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = POST)
    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        request.getSession().setAttribute("user", null);
        modelMap.put("success", true);
        return modelMap;
    }
}
