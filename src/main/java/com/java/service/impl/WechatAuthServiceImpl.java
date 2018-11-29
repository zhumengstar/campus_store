package com.java.service.impl;

import com.java.dao.PersonInfoDao;
import com.java.dao.WechatAuthDao;
import com.java.dto.ImageHolder;
import com.java.dto.WechatAuthExecution;
import com.java.entity.PersonInfo;
import com.java.entity.WechatAuth;
import com.java.enums.WechatAuthStateEnum;
import com.java.exceptions.WechatAuthOperationException;
import com.java.service.WechatAuthService;
import com.java.util.FileUtils;
import com.java.util.img.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;


/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    private static Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);


    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }


    @Override
    @Transactional
    public WechatAuthExecution regiest(WechatAuth wechatAuth, File profileImg) throws Exception {
        //空值判断
        if (wechatAuth == null || wechatAuth.getOpenId() == null) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            //设置创建时间
            wechatAuth.setCreateTime(new Date());
            //如果微信账号里夹带着用户信息并且用户ID为空，则认为该用户第一次使用平台（且通过微信登陆)）
            //则自动创建用户信息
            if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {

                if (profileImg != null) {
                    try {
                        addProfileImg(wechatAuth, profileImg);
                    } catch (Exception e) {
                        log.debug("addUserProfileImg error" + e.getMessage());
                        throw new RuntimeException("addUserProfileImg error" + e.getMessage());
                    }
                }

                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setLastEditTime(new Date());
                    wechatAuth.getPersonInfo().setCustomerFlag(1L);
                    wechatAuth.getPersonInfo().setShopOwnerFlag(1L);
                    wechatAuth.getPersonInfo().setAdminFlag(1L);
                    wechatAuth.getPersonInfo().setEnableStatus(1);

                    PersonInfo personInfo = wechatAuth.getPersonInfo();

                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);



                    wechatAuth.setUserId(personInfo.getUserId());

                    if (effectedNum <= 0) {
                        throw new WechatAuthOperationException("添加用户信息失败");

                    }
                } catch (Exception e) {
                    log.error("insertPersonInfo error:" + e.toString());
                    throw new WechatAuthOperationException("insertPersonInfo error:" + e.getMessage());

                }
            }
            //创建专属于本平台的微信账号
            int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
            if (effectedNum <= 0) {
                throw new WechatAuthOperationException("账号创建失败");

            } else {
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
            }
        } catch (Exception e) {
            log.error("insertWechatAuth error:" + e.toString());
            throw new WechatAuthOperationException("insertWechatAuth error:" + e.getMessage());
        }

    }

    private void addProfileImg(WechatAuth wechatAuth, File profileImg) throws FileNotFoundException {

        String desc = FileUtils.getPersonInfoImagePath();

        InputStream is = new FileInputStream(profileImg);

        ImageHolder imageHolder = new ImageHolder(profileImg.getName(), is);

        String profileImgAddr = ImageUtils.generateThumbnail(imageHolder, desc);

        wechatAuth.getPersonInfo().setProfileImg(profileImgAddr);
    }
}
