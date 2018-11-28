package com.java.service.impl;

import com.java.dao.ProductImgDao;
import com.java.entity.ProductImg;
import com.java.service.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class ProductImgServiceImpl implements ProductImgService {

    @Autowired
    private ProductImgDao productImgDao;
    @Override
    public List<ProductImg> getProductImgListByProductId(Long productId) {
        return productImgDao.queryProductImgList(productId);
    }
}
