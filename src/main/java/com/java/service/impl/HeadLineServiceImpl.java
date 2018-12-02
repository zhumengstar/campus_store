package com.java.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dao.HeadLineDao;
import com.java.dto.HeadLineExecution;
import com.java.entity.HeadLine;
import com.java.service.HeadLineService;
import com.java.util.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private HeadLineDao headLineDao;

    private static String HLLISTKEY = "headlinelist";

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        //定义接受对象
        List<HeadLine> headLineList = null;
        //定义jackson数据转换对象
        ObjectMapper mapper = new ObjectMapper();
        //定义redis的key前缀
        String key = HLLISTKEY;
        //拼接redis的key
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        //判断key是否存在
        if (!jedisKeys.exists(key)) {
            //不存在则数据库取数据
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            //将实体集合转换为string，存入redis里面对应的key中
            String jsonString = mapper.writeValueAsString(headLineList);
            jedisStrings.set(key, jsonString);
        } else {
            //存在则从key中取数据
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            headLineList = mapper.readValue(jsonString, javaType);
        }
        return headLineList;
    }

//    @Override
//    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
//        return headLineDao.queryHeadLine(headLineCondition);
//    }

    @Override
    public HeadLineExecution addHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
        return null;
    }

    @Override
    public HeadlessException modifyHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
        return null;
    }

    @Override
    public HeadlessException removeHeadLine(Long headId) {
        return null;
    }

    @Override
    public HeadlessException removeHeadLineList(List<Long> headLineIdList) {
        return null;
    }
}
