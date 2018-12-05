package com.java.service;

import com.java.dto.WechatAuthExecution;
import com.java.entity.WechatAuth;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface WechatAuthService {

    /**
     * 通过openId查找平台对应的微信账号
     *
     * @param openId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String openId);

    WechatAuthExecution register(WechatAuth wechatAuth)throws Exception;
}
