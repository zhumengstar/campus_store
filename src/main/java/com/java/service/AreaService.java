package com.java.service;

import com.java.entity.Area;
import com.java.entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface AreaService {
    public static final String AREALISTKEY = "arealist";

    List<Area> getAreaList();
}
