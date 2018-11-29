package dao;

import baseTest.BaseTest;
import com.java.dao.PersonInfoDao;
import com.java.entity.PersonInfo;
import com.java.service.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PersonDaoTest extends BaseTest {

    @Autowired
    private PersonInfoDao personDao;

    @Test
    @Transactional
    public void test() {
        PersonInfo s = personDao.getPersonById(8L);
        System.out.println(s.getSName());
        s = personDao.getPersonById(8L);
        System.out.println(s.getSName());
    }


    @Test
    @Transactional
    public void testInsertPersonInfo() throws Exception {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setSName("yaya");
        personInfo.setGender("女");
        personInfo.setUserType(1);
        personInfo.setCreateTime(new Date());
        personInfo.setLastEditTime(new Date());
        personInfo.setEnableStatus(1);
        personInfo.setAdminFlag(1L);
        int effectedNum = personDao.insertPersonInfo(personInfo);
        assertEquals(1, effectedNum);

    }

    @Test
    public void testQueryPersonInfoById(){
        Long userId=8L;
        PersonInfo personInfo=personDao.getPersonById(userId);
        System.out.println(personInfo);

    }

}