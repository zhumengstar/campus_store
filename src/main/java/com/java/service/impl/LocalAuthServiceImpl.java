package com.java.service.impl;

import com.java.dao.LocalAuthDao;
import com.java.dao.PersonInfoDao;
import com.java.dto.LocalAuthExecution;
import com.java.dto.other.ImageHolder;
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
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUserName() == null || localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
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
                throw new LocalAuthOperationException("账号绑定失败");
            } else {
                return new LocalAuthExecution(LocalAuthEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //本地账号注册
    @Transactional
    public LocalAuthExecution register(LocalAuth localAuth, ImageHolder profileImg) throws RuntimeException {
        //判断本地账号是否为空
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUserName() == null) {
            return new LocalAuthExecution(LocalAuthEnum.NULL_AUTH_INFO);
        }
        try {
            //设置账号创建时间
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));

            //判断用户信息是否为空
            if (localAuth.getPersonInfo() != null && localAuth.getPersonInfo().getSName() != null && !localAuth.getPersonInfo().getSName().equals("") && localAuth.getPersonInfo().getUserId() == null) {
                PersonInfo personInfo = localAuth.getPersonInfo();
                localAuth.getPersonInfo().setCreateTime(new Date());
                localAuth.getPersonInfo().setLastEditTime(new Date());
                localAuth.getPersonInfo().setAdminFlag(0L);
                localAuth.getPersonInfo().setEnableStatus(1);
                //用户头像
                if (profileImg != null) {
                    try {
                        AddProfileImg.addProfileImg(personInfo, profileImg);
                    } catch (Exception e) {
                        throw new LocalAuthOperationException("添加用户头像失败异常" + e.getMessage());
                    }
                }
                //创建个人信息
                try {
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                    if (effectedNum <= 0) {
                        return new LocalAuthExecution(LocalAuthEnum.CREATE_PERSON_FAIL);
                    }
                    localAuth.setPersonInfo(personInfo);
                    localAuth.setUserId(personInfo.getUserId());
                } catch (Exception e) {
                    throw new LocalAuthOperationException("插入个人信息异常");
                }
            } else {
                return new LocalAuthExecution(LocalAuthEnum.NULL_AUTH_INFO);
            }
            //创建本地账号
            try {
                int effectedNum = localAuthDao.insertLocalAuth(localAuth);
                if (effectedNum > 0) {
                    return new LocalAuthExecution(LocalAuthEnum.SUCCESS, localAuth);
                }
            } catch (Exception e) {
                return new LocalAuthExecution(LocalAuthEnum.ONLY_ONE_LOCAL_AUTH);
            }
            //成功
            return new LocalAuthExecution(LocalAuthEnum.SUCCESS, localAuth);
        } catch (Exception e) {
            throw new LocalAuthOperationException(e.toString());
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
