package com.java.service;

import com.java.entity.PersonInfo;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface PersonInfoService {
    /**
     * 根据用户Id获取personInfo信息
     *
     * @param personInfoId
     * @return
     */
    PersonInfo getPersonInfoById(Long personInfoId);
}
