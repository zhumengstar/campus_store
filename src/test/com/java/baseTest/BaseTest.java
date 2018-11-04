package baseTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author:zhumeng
 * @desc:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources")
@ContextConfiguration({"classpath*:spring/spring-dao.xml", "classpath*:spring/spring-service.xml", "classpath*:spring/spring-web.xml"})
public class BaseTest {

    @Test
    public void test() {

    }

}
