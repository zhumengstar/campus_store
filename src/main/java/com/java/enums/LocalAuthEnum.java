package com.java.enums;

public enum LocalAuthEnum {

    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    ONLY_ONE_ACCOUNT(2, "仅有一个账号"),
    NULL_AUTH_INFO(-1003, "账号信息不完整");

    private int state;

    private String stateInfo;

    LocalAuthEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static LocalAuthEnum stateOf(int state) {
        for (LocalAuthEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

}
