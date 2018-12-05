package com.java.enums;

public enum LocalAuthEnum {

    LOGIN_FAIL(-1, "密码或帐号输入有误"),
    SUCCESS(0, "操作成功"),
    ONLY_ONE_LOCAL_AUTH(-1005, "该账号名已经被注册了哦~"),
    NULL_AUTH_INFO(-1006, "信息不能为空哦~"),
    ONLY_ONE_ACCOUNT(-1007, "您已经有本地帐号了"),
    NULL_IMG(-1008, "请上传用户头像"),
    CREATE_PERSON_FAIL(-1009, "创建用户失败");

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
