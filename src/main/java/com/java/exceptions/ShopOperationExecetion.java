package com.java.exceptions;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ShopOperationExecetion extends RuntimeException {


    private static final long serialVersionUID = 85333708453067839L;

    public ShopOperationExecetion(String msg) {
        super(msg);
    }
}
