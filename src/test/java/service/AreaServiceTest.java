package service;

import baseTest.BaseTest;
import com.java.entity.Area;
import com.java.service.AreaService;
import com.java.service.CacheService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Autowired
    private CacheService cacheService;
    Logger logger = LoggerFactory.getLogger(AreaServiceTest.class);

    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        logger.info(areaList.get(0).toString());

        assertEquals("东苑", areaList.get(0).getAreaName());

        cacheService.removeFromCache(areaService.AREALISTKEY);
        areaList = areaService.getAreaList();
    }


}