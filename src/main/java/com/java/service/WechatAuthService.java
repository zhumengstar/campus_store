package com.java.service;

import com.java.dto.WechatAuthExecution;
import com.java.entity.WechatAuth;

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

    WechatAuthExecution regiest(WechatAuth wechatAuth)throws Exception;
}
