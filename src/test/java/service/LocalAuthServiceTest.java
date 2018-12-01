package service;

import baseTest.BaseTest;
import com.java.dto.LocalAuthExecution;
import com.java.entity.LocalAuth;
import com.java.entity.PersonInfo;
import com.java.entity.WechatAuth;
import com.java.enums.LocalAuthEnum;
import com.java.enums.WechatAuthStateEnum;
import com.java.service.LocalAuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/

public class LocalAuthServiceTest extends BaseTest {
    @Autowired
    private LocalAuthService localAuthService;

    @Test
    public void testBindLocalAuth() {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();

        String uesrname = "yayaer";
        String password = "1";

        personInfo.setUserId(11L);


        localAuth.setPersonInfo(personInfo);
        localAuth.setUserName(uesrname);
        localAuth.setPassword(password);
        localAuth.setUserId(personInfo.getUserId());
        LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
        assertEquals(LocalAuthEnum.SUCCESS.getState(), lae.getState());

        localAuth = localAuthService.getLocalAuth(personInfo.getUserId());
        System.out.println("username:" + localAuth.getPersonInfo().getSName());

        System.out.println("password:" + localAuth.getPassword());
    }

    @Test
    public void testModifyLocalAuth(){
        Long userId=11L;
        String username="yayaer";
        String password="1";
        String newpassword="java";

        LocalAuthExecution lea=localAuthService.modifyLocalAuth(userId,username,password,newpassword,new Date());

        assertEquals(LocalAuthEnum.SUCCESS.getState(),lea.getState());

        LocalAuth localAuth=localAuthService.getLocalAuthByUserNameAndPwd(username,newpassword);

        System.out.println(localAuth.getPersonInfo().getSName());
    }
}
