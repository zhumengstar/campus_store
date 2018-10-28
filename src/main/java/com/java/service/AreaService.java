package com.java.service;

import com.java.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface AreaService {
    List<Area> getAreaList();

}
