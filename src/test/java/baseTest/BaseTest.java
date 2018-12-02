package baseTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:zhumeng
 * @desc:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration("src/main/resources")
@ContextConfiguration({"classpath*:spring/spring-dao.xml", "classpath*:spring/spring-service.xml", "classpath*:spring/spring-web.xml", "classpath*:spring/spring-redis.xml"})
public class BaseTest {

    @Test
    public void test() {

    }

}
