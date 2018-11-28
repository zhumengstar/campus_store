package com.java.enums;

import com.java.entity.WechatAuth;

/**
 * @author:zhumeng
 * @desc:
 **/
public enum WechatAuthStateEnum {
    LOGINFAIL(-1,"openId输入有误"),SUCCESS(0,"操作成功"),NULL_AUTH_INFO(-1006,"注册信息为空");

    private int state;

    private String stateInfo;

    private WechatAuthStateEnum(int state,String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
