package com.java.exceptions;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ProductOperationException extends RuntimeException {
    private static final long serialVersionUID = -6287952843168457061L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}
