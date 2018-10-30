package com.java.exceptions;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ShopOperationExecption extends RuntimeException {


    private static final long serialVersionUID = 85333708453067839L;

    public ShopOperationExecption(String msg) {
        super(msg);
    }
}
