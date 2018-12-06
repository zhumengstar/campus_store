package com.java.service.impl;

import com.java.dao.ProductCategoryDao;
import com.java.dao.ProductDao;
import com.java.dto.ProductCategoryExecution;
import com.java.entity.ProductCategory;
import com.java.enums.ProductCategoryStateEnum;
import com.java.exceptions.ProductCategoryOperationException;
import com.java.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductDao productDao;


    /**
     * @param shopId
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Transactional
    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败！");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("店铺类别创建失败！");
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(Long productCategoryId, Long shopId) throws ProductCategoryOperationException {
        //解除tb_product里的商品与该productCategoryId的关联
        try {
            int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
            if (effectedNum < 0) {
                throw new RuntimeException("商品类别与商品类别解除关联失败");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("商品类别与商品类别解除关联失败:" + e.getMessage());
        }

        //删除该productCategory
        try {
            int effectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectNum <= 0) {
                throw new ProductCategoryOperationException("商品类别删除失败");

            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("商品类别删除失败：" + e.getMessage());
        }
    }


}
