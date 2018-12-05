package com.java.service;

import com.java.dto.LocalAuthExecution;
import com.java.dto.other.ImageHolder;
import com.java.entity.LocalAuth;
import com.java.exceptions.LocalAuthOperationException;

import java.io.File;
import java.util.Date;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface LocalAuthService {
    /**
     * 通过账号密码获取平台信息
     *
     * @param userName
     * @param passWord
     * @return
     */
    LocalAuth getLocalAuthByUserNameAndPwd(String userName, String passWord);

    /**
     * 通过userId获取本地账号
     *
     * @param userId
     * @return
     */
    LocalAuth getLocalAuth(Long userId);

    /**
     * 绑定微信，生成平台专属的账号
     *
     * @param localAuth
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
     * 修改平台账号的登陆密码
     *
     * @param userId
     * @param username
     * @param password
     * @param newpassword
     * @param lastEditTime
     * @return
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newpassword, Date lastEditTime) throws LocalAuthOperationException;

    LocalAuthExecution register(LocalAuth localAuth, ImageHolder thumbnail) throws LocalAuthOperationException;
}
