package lu.smarthome.usermanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(value = {"classpath:application.properties","classpath:application-test.properties"})
class UserManagerApplicationTests {

    @Test
    void contextLoads() {
    }

}
