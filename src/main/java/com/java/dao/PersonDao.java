package com.java.dao;

import com.java.entity.PersonInfo;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface PersonDao {
    PersonInfo getPersonById(Long personId);
}
