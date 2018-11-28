package dao;

import baseTest.BaseTest;
import com.java.dao.HeadLineDao;
import com.java.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author:zhumeng
 * @desc:
 **/
public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine(){
        List<HeadLine> headLineList=headLineDao.queryHeadLine(new HeadLine());
        assertEquals(4,headLineList.size());

    }
}
