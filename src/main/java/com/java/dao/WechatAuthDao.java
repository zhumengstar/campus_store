package com.java.dao;

import com.java.entity.WechatAuth;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface WechatAuthDao {
    /**
     * t通过openId查询对应本平台的微信账号
     *
     * @param openId
     * @return
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /**
     * 添加对应本平台的微信账号
     *
     * @param wechatAuth
     * @return
     */
    int insertWechatAuth(WechatAuth wechatAuth);
}
