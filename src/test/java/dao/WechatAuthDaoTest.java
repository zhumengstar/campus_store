package dao;

import baseTest.BaseTest;
import com.java.dao.WechatAuthDao;
import com.java.entity.PersonInfo;
import com.java.entity.WechatAuth;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
public class WechatAuthDaoTest extends BaseTest {
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void testInsertWechatAuth() throws Exception {

        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(8L);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId("zgh");
        wechatAuth.setUserId(personInfo.getUserId());
        wechatAuth.setCreateTime(new Date());
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQuertWechatAuthByopenId()throws Exception{
        WechatAuth wechatAuth=wechatAuthDao.queryWechatInfoByOpenId("ovLbns-gxJHqC-UTPQKvgEuENl-E");
        assertEquals("逐梦",wechatAuth.getPersonInfo().getSName());
    }
}
