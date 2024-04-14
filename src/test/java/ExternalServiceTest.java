import com.eCommerceInterface.Main;
import com.eCommerceInterface.service.ExternalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
public class ExternalServiceTest {
    @Autowired
    ExternalService externalService;

    @Test
    void checkTest(){
        int x = 7;
        int y = 90;

        int sum = x+y;
        assertEquals(97,sum);

    }
}
