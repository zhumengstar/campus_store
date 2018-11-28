package com.java.entity;

import java.util.Date;

public class PersonInfo {
    private Long userId;

    private String sName;

    private String profileImg;

    private String email;

    private String gender;

    private Integer enableStatus;

    private Integer userType;

    private Long customerFlag;

    private Long shopOwnerFlag;

    private Long adminFlag;

    private Date createTime;

    private Date lastEditTime;

    public Long getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Long adminFlag) {
        this.adminFlag = adminFlag;
    }

    public Long getShopOwnerFlag() {
        return shopOwnerFlag;
    }

    public void setShopOwnerFlag(Long shopOwnerFlag) {
        this.shopOwnerFlag = shopOwnerFlag;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Long getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Long customerFlag) {
        this.customerFlag = customerFlag;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
