package dao;

import baseTest.BaseTest;
import com.java.dao.LocalAuthDao;
import com.java.entity.LocalAuth;
import com.java.entity.PersonInfo;
import com.java.util.MD5.MD5;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest {
    @Autowired
    private LocalAuthDao localAuthDao;
    private static final String username = "yaya";
    private static final String password = "newpass";

    @Test
    public void testInsertLocalAuth() throws Exception {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(9L);

        localAuth.setPersonInfo(personInfo);

        localAuth.setUserName(username);
        localAuth.setPassword(password);

        localAuth.setCreateTime(new Date());
        int effectedNum = localAuthDao.insertLocalAuth(localAuth);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryLocalByUserNameAndPwd()throws Exception{
        LocalAuth localAuth=localAuthDao.queryLocalByUserNameAndPwd(username,password);
        assertEquals("逐梦",localAuth.getPersonInfo().getSName());
    }

    @Test
    public void testQueryLocalByUserId()throws Exception{
        LocalAuth localAuth=localAuthDao.queryLocalByUserId(11L);
        assertEquals("音策",localAuth.getPersonInfo().getSName());
    }

    @Test
    public void testUpdateLocalAuth()throws Exception{
        int effectedNum=localAuthDao.updateLocalAuth(8L,username,password, MD5.getMd5("newpass"),new Date());
        assertEquals(1,effectedNum);

    }


}
