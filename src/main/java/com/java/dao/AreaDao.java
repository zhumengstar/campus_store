package com.java.dao;

import com.java.entity.Area;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
