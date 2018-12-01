package com.java.exceptions;

/**
 * @author:zhumeng
 * @desc:
 **/
public class LocalAuthOperationException extends RuntimeException {


    private static final long serialVersionUID = 3444519056276799843L;

    public LocalAuthOperationException(String msg) {
        super(msg);
    }

}
