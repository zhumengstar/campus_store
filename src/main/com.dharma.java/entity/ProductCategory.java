package entity;

import java.util.Date;

public class ProductCategory {
    private Long productCategory;

    private Long shopId;

    private String productCategoryName;

    private Integer prority;

    private Date createTime;

    public Long getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Long productCategory) {
        this.productCategory = productCategory;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getPrority() {
        return prority;
    }

    public void setPrority(Integer prority) {
        this.prority = prority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
