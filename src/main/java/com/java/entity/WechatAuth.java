package com.java.entity;

import java.util.Date;

public class WechatAuth {

    private Long wechatAutoId;

    private String openId;

    private Date createTime;

    private PersonInfo personInfo;

    public Long getWechatAutoId() {
        return wechatAutoId;
    }

    public void setWechatAutoId(Long wechatAutoId) {
        this.wechatAutoId = wechatAutoId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
