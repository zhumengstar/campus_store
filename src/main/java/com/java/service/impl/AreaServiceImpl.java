package com.java.service.impl;

import com.java.dao.AreaDao;
import com.java.dao.PersonInfoDao;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private PersonInfoDao personDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public PersonInfo getPerson(Long personId) {
        return personDao.getPersonById(personId);
    }
}
