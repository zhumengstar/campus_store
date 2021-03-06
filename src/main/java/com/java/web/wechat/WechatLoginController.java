package com.java.web.wechat;

import com.java.dto.WechatAuthExecution;
import com.java.dto.other.UserAccessToken;
import com.java.dto.other.WechatUser;
import com.java.entity.PersonInfo;
import com.java.entity.WechatAuth;
import com.java.enums.WechatAuthStateEnum;
import com.java.exceptions.WechatAuthOperationException;
import com.java.service.PersonInfoService;
import com.java.service.WechatAuthService;
import com.java.util.wechat.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 获取关注公众号之后的微信用户信息的接口
 * 如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx299e19661fcdf7d2&redirect_uri=http://39.105.67.70/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 */
//用来获取已关注此微信号的用户信息并做相应处理
@Controller
@RequestMapping("/wechatlogin")
public class WechatLoginController {

    private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);

    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private WechatAuthService wechatAuthService;

    @RequestMapping(value = "/logincheck", method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("weixin login get...");
        //获取微信公众号传输过来的code,通过code可以获取access_token,进而获取用户信息
        String code = request.getParameter("code");
        //这个state可以用来传我们自定义的信息,方便程序调用,这里也可以不用
        String state = request.getParameter("state");
        log.debug("weixin login code: " + code);
        WechatUser user = null;
        String openId = null;
        WechatAuth wechatAuth = null;

        //必定执行
        if (code != null) {
            UserAccessToken token;
            try {
                //通过code获取access_token
                token = WechatUtils.getUserAccessToken(code);
                log.debug("weixin login token:" + token);
                //通过token获取accessToken
                String accessToken = token.getAccessToken();
                //通过token获取openId
                openId = token.getOpenId();
                //通过access_token和openId获取用户昵称等信息
                user = WechatUtils.getUserInfo(accessToken, openId);
                log.debug("weixin login user:" + user.toString());
                request.getSession().setAttribute("openId", openId);
                wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
                PersonInfo personInfo = personInfoService.getPersonInfoById(wechatAuth.getUserId());
                wechatAuth.setPersonInfo(personInfo);

            } catch (IOException e) {
                log.error("error in getUserAccessToken or getUserInfo or findByOpenId:" + e.toString());
                e.printStackTrace();
            }
        }
        //如果微信帐号为空,则注册微信帐号,同时注册用户信息
        if (wechatAuth == null) {
            wechatAuth = new WechatAuth();
            wechatAuth.setOpenId(openId);
            PersonInfo personInfo = WechatUtils.getPersonInfoFromRequest(user);
            personInfo.setUserType(1);
            wechatAuth.setPersonInfo(personInfo);
            WechatAuthExecution we = null;
            try {
                we = wechatAuthService.register(wechatAuth);
                if (we.getState() == WechatAuthStateEnum.SUCCESS.getState()) {
                    wechatAuth = we.getWechatAuth();
                } else {
                    throw new WechatAuthOperationException(WechatAuthStateEnum.REGISTER_FAIL.getStateInfo());
                }
            } catch (Exception e) {
                throw new WechatAuthOperationException(e.toString());
            }
        }
        request.getSession().setAttribute("user", wechatAuth.getPersonInfo());
        return "frontend/index";
    }
}
