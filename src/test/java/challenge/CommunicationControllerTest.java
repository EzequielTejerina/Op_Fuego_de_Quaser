package challenge;

import challenge.controller.CommunicationController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CommunicationController.class)
public class CommunicationControllerTest {
}
