package com.java.enums;

/**
 * @author:zhumeng
 * @desc:
 **/
public enum ShopStateEnum {
    CHECK(0, "审核中"),
    OFFLINE(-1, "非法店铺"),
    SUCCESS(1, "操作成功"),
    PASS(2, "通过审核"),
    INNER_ERROR(-1001, "内部系统错误"),
    NULL_SHOPID(-1002, "ShopId为空"),
    NULL_SHOP(-1003,"shop无");


    //结果状态

    private int state;

    //状态标识

    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }


    public String getStateInfo() {
        return stateInfo;
    }


    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum shopStateEnum:values()){
            if(shopStateEnum.getState()==state){
                return shopStateEnum;
            }
        }
        return null;
    }
}
