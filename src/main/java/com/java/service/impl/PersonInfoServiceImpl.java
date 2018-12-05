package com.java.service.impl;

import com.java.dao.PersonInfoDao;
import com.java.entity.PersonInfo;
import com.java.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class PersonInfoServiceImpl implements PersonInfoService {


    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.getPersonById(userId);
    }

    @Override
    public int insertPersonInfo(PersonInfo personInfo) {
        return personInfoDao.insertPersonInfo(personInfo);
    }


}
