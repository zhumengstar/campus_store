package com.java.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dao.HeadLineDao;
import com.java.dto.HeadLineExecution;
import com.java.entity.HeadLine;
import com.java.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class HeadLineServiceImpl implements HeadLineService {

//    @Autowired
//    private JedisUtil.Strings jedisStrings;

//    @Autowired
//    private JedisUtil.Keys jedisKeys;

    @Autowired
    private HeadLineDao headLineDao;

    private static String HLLISTKEY = "headlinelist";

//    @Override
//    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
//        List<HeadLine> headLineList=null;
//        ObjectMapper mapper=new ObjectMapper();
//        String key=HLLISTKEY;
//        if(headLineCondition.getEnableStatus()!=null){
//            key=key+"_"+headLineCondition.getEnableStatus();
//        }
//        if(!jedisKeys.exists(key)){
//            headLineList=headLineDao.queryHeadLine(headLineCondition);
//            String jsonString=mapper.writeValueAsString(headLineList);
//            jedisStrings.set(key,jsonString);
//        }else {
//            String jsonString=jedisStrings.get(key);
//            JavaType javaType=mapper.getTypeFactory().constructParametricType(Array.class,HeadLine.class);
//            headLineList=mapper.readValue(jsonString,javaType);
//        }
//        return headLineList;
//    }

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }

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
