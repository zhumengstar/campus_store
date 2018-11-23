package com.java.service.impl;

import com.java.dto.HeadLineExecution;
import com.java.entity.HeadLine;
import com.java.service.HeadLineService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return null;
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
