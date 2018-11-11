package com.java.dto;

import com.java.entity.Product;
import com.java.entity.ProductCategory;
import com.java.enums.ProductCategoryStateEnum;
import com.java.enums.ProductStateEnum;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public class ProductExecution {
    //结果状态
    private int state;

    private String stateInfo;

    private List<Product> productList;

    private Product product;

    public ProductExecution() {
    }

    //操作失败时使用的构造器
    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //操作成功时使用的构造器
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    //操作成功时使用的构造器
    public ProductExecution(ProductStateEnum stateEnum, Product product) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
