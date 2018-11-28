package com.java.dao;

import com.java.entity.PersonInfo;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface PersonInfoDao {
    /**
     * 通过用户Id查询用户
     *
     * @param personId
     * @return
     */
    PersonInfo getPersonById(Long personId);

    /**
     * 添加用户信息
     *
     * @param personInfo
     * @return
     */
    int insertPersonInfo(PersonInfo personInfo);
}
