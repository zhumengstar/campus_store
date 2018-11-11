package com.java.exceptions;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ProductCategoryOperationException extends RuntimeException {


    private static final long serialVersionUID = 8815134062483082631L;

    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}
