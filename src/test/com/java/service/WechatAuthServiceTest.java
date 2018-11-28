package service;

import baseTest.BaseTest;
import com.java.dto.WechatAuthExecution;
import com.java.entity.PersonInfo;
import com.java.entity.WechatAuth;
import com.java.enums.WechatAuthStateEnum;
import com.java.service.WechatAuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
public class WechatAuthServiceTest extends BaseTest {
    @Autowired
    private WechatAuthService wechatService;


    @Transactional
    @Test
    public void testRegister()throws Exception{
        WechatAuth wechatAuth=new WechatAuth();
        PersonInfo personInfo=new PersonInfo();
        String openId="hhy";
        //给微信账号设置上用户信息，但不设置上用户Id
        //希望创建微信账号的时候自动创建用户信息
        personInfo.setCreateTime(new Date());
        personInfo.setSName("zgh");
        personInfo.setUserType(1);
        personInfo.setAdminFlag(1L);

        wechatAuth.setUserId(personInfo.getUserId());
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId(openId);
        wechatAuth.setCreateTime(new Date());
        WechatAuthExecution wae=wechatService.regiest(wechatAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wae.getState());
        wechatAuth=wechatService.getWechatAuthByOpenId(openId);
        System.out.println(wechatAuth.getPersonInfo().getSName());

    }
}
