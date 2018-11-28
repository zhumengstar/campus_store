package com.java.exceptions;

/**
 * @author:zhumeng
 * @desc:
 **/
public class WechatAuthOperationException extends RuntimeException {

    private static final long serialVersionUID = -8999559310397602906L;

    public WechatAuthOperationException(String msg) {super(msg);
    }
}
