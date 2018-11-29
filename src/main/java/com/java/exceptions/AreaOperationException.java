package com.java.exceptions;

/**
 * @author:zhumeng
 * @desc:
 **/
public class AreaOperationException extends RuntimeException {

    private static final long serialVersionUID = 5299061800101362626L;

    public AreaOperationException(String msg) {
        super(msg);
    }
}
