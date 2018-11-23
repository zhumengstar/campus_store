package com.java.enums;

import com.java.entity.HeadLine;

/**
 * @author:zhumeng
 * @desc:
 **/
public enum HeadLineStateEnum {
    SUCCESS(0, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "头条信息为空");

    private int state;
    private String stateInfo;

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

    HeadLineStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static HeadLineStateEnum stateOf(int index){
        for (HeadLineStateEnum state:values()){
            if(state.getState()==index){
                return state;
            }
        }
        return null;
    }

}
