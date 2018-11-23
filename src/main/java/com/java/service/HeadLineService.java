package com.java.service;

import com.java.dto.HeadLineExecution;
import com.java.entity.HeadLine;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface HeadLineService {
    /**
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;

    /**
     * @param headLine
     * @param thumbnail
     * @return
     */
    HeadLineExecution addHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail);

    /**
     * @param headLine
     * @param thumbnail
     * @return
     */
    HeadlessException modifyHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail);

    /**
     * @param headId
     * @return
     */
    HeadlessException removeHeadLine(Long headId);

    /**
     * @param headLineIdList
     * @return
     */
    HeadlessException removeHeadLineList(List<Long> headLineIdList);


}
