package com.java.dao;

import com.java.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:zhumeng
 * @desc:
 **/
public interface HeadLineDao {
    /**
     * 查询头条
     *
     * @param headLine
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition")HeadLine headLine);


    /**
     * 通过头条Id查询头条
     *
     * @param lineId
     * @return
     */
    HeadLine queryHeadLineById(Long lineId);

    /**
     * @param lineIdList
     * @return
     */
    List<HeadLine> queryHeadLineByIds(List<Long> lineIdList);

    /**
     * @param headLine
     * @return
     */
    int insertHeadLine(HeadLine headLine);

    /**
     * @param headLine
     * @return
     */
    int updateHeadLine(HeadLine headLine);

    /**
     * @param headLineId
     * @return
     */
    int deleteHeadLine(Long headLineId);

    /**
     * @param lineIdList
     * @return
     */
    int batchDeleteHeadLine(List<Long> lineIdList);

}
