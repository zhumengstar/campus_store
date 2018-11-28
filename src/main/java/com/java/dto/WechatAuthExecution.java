package com.java.dto;

import com.java.entity.HeadLine;
import com.java.entity.ProductCategory;
import com.java.entity.WechatAuth;
import com.java.enums.HeadLineStateEnum;
import com.java.enums.ProductCategoryStateEnum;
import com.java.enums.WechatAuthStateEnum;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public class WechatAuthExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;


    private WechatAuth wechatAuth;

    //获取award列表（查询商品列表的时候用）
    private List<WechatAuth> wechatAuthList;

    public WechatAuthExecution() {
    }

    //操作失败时使用的构造器
    public WechatAuthExecution(WechatAuthStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //操作成功时使用的构造器
    public WechatAuthExecution(WechatAuthStateEnum stateEnum, WechatAuth wechatAuth) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.wechatAuth = wechatAuth;
    }

    //操作成功时使用的构造器
    public WechatAuthExecution(WechatAuthStateEnum stateEnum, List<WechatAuth> wechatAuthList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.wechatAuthList = wechatAuthList;
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

    public WechatAuth getWechatAuth() {
        return wechatAuth;
    }

    public void setWechatAuth(WechatAuth wechatAuth) {
        this.wechatAuth = wechatAuth;
    }

    public List<WechatAuth> getWechatAuthList() {
        return wechatAuthList;
    }

    public void setWechatAuthList(List<WechatAuth> wechatAuthList) {
        this.wechatAuthList = wechatAuthList;
    }
}
