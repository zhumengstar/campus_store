package com.java.dto;

import com.java.entity.LocalAuth;
import com.java.enums.LocalAuthEnum;
import com.java.enums.ProductStateEnum;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public class LocalAuthExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    private int count;

    private List<LocalAuth> localAuthList;

    private LocalAuth localAuth;


//    //获取product列表（查询商品列表的时候用）
//    private List<Product> productList;


    public LocalAuthExecution() {
    }

    //操作失败时使用的构造器
    public LocalAuthExecution(LocalAuthEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }


    //操作成功时使用的构造器
    public LocalAuthExecution(LocalAuthEnum stateEnum, LocalAuth localAuth) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuth = localAuth;
    }

    //操作成功时使用的构造器
    public LocalAuthExecution(LocalAuthEnum stateEnum, List<LocalAuth> localAuthList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuthList = localAuthList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<LocalAuth> getLocalAuthList() {
        return localAuthList;
    }

    public void setLocalAuthList(List<LocalAuth> localAuthList) {
        this.localAuthList = localAuthList;
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

    public LocalAuth getLocalAuth() {
        return localAuth;
    }

    public void setLocalAuth(LocalAuth localAuth) {
        this.localAuth = localAuth;
    }
}
