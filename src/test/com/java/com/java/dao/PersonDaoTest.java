package com.java.dao;

import baseTest.BaseTest;
import com.java.entity.PersonInfo;
import com.java.service.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonDaoTest extends BaseTest {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private AreaService areaService;

    @Test
    public void test() {
        PersonInfo s = personDao.getPersonById(8L);
        System.out.println(s.getSName());
        s=areaService.getPerson(8L);
        System.out.println(s.getSName());
    }


}