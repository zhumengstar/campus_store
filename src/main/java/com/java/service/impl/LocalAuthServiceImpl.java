package com.java.service.impl;

import com.java.dao.LocalAuthDao;
import com.java.dao.PersonInfoDao;
import com.java.dto.LocalAuthExecution;
import com.java.entity.LocalAuth;
import com.java.entity.PersonInfo;
import com.java.enums.LocalAuthEnum;
import com.java.exceptions.LocalAuthOperationException;
import com.java.service.LocalAuthService;
import com.java.util.MD5.MD5;
import com.java.util.img.AddProfileImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Autowired
    private LocalAuthDao localAuthDao;


    @Override
    public LocalAuth getLocalAuthByUserNameAndPwd(String userName, String passWord) {
        return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5.getMd5(passWord));
    }

    @Override
    public LocalAuth getLocalAuth(Long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    @Transactional
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUserName() == null || localAuth.getPersonInfo() == null||localAuth.getPersonInfo().getUserId()==null) {
            return new LocalAuthExecution(LocalAuthEnum.NULL_AUTH_INFO);
        }
        LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
        if (tempAuth != null) {
            return new LocalAuthExecution(LocalAuthEnum.ONLY_ONE_ACCOUNT);
        }
        try {
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            if (effectedNum <= 0) {
                throw new RuntimeException("账号绑定失败");

            } else {
                return new LocalAuthExecution(LocalAuthEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new RuntimeException("insertLocalAuth error:" + e.getMessage());
        }
    }

    @Transactional
    public LocalAuthExecution regiest(LocalAuth localAuth, File profileImg) throws RuntimeException {
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUserName() == null) {
            return new LocalAuthExecution(LocalAuthEnum.NULL_AUTH_INFO);
        }
        try {
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            if (localAuth.getPersonInfo() != null && localAuth.getPersonInfo().getUserId() == null) {
                if (profileImg != null) {
                    localAuth.getPersonInfo().setCreateTime(new Date());
                    localAuth.getPersonInfo().setLastEditTime(new Date());
                    localAuth.getPersonInfo().setEnableStatus(1);
                    try {
                        PersonInfo personInfo=localAuth.getPersonInfo();
                        AddProfileImg.addProfileImg(personInfo, profileImg);
                    } catch (Exception e) {
                        throw new RuntimeException("addUserProfileImg error:" + e.getMessage());
                    }
                }
                try {
                    PersonInfo personInfo = localAuth.getPersonInfo();
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                    localAuth.setUserId(personInfo.getUserId());
                    if (effectedNum <= 0) {
                        throw new RuntimeException("添加用户信息失败");

                    }
                } catch (Exception e) {
                    throw new RuntimeException("insertPersonInfo error:" + e.getMessage());
                }


            }
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            if (effectedNum <= 0) {
                throw new RuntimeException("账号创建失败");

            } else {
                return new LocalAuthExecution(LocalAuthEnum.SUCCESS, localAuth);

            }

        } catch (Exception e) {
            throw new RuntimeException("insertLocalAuth error:" + e.getMessage());
        }
    }


    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newpassword, Date lastEditTime) throws LocalAuthOperationException {
        if (userId != null && username != null && password != null && newpassword != null && !password.equals(newpassword)) {
            try {

                int effectedNum = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password), MD5.getMd5(newpassword), new Date());
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("更新密码失败");

                }
                return new LocalAuthExecution(LocalAuthEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException("更新密码失败" + e.getMessage());
            }
        } else {
            return new LocalAuthExecution(LocalAuthEnum.NULL_AUTH_INFO);
        }
    }
}
