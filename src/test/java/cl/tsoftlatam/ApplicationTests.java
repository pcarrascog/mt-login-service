package cl.tsoftlatam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cl.tsoftlatam.controllers.LoginController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void productoControllerTrue() {
		LoginController loginTest = new LoginController();

		try {
			assertEquals("must be true", Boolean.TRUE,
					loginTest.isLogin("{user:'admin', pass:'admin'}"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void productoControllerFail() {
		LoginController loginTest = new LoginController();

		try {
			assertNotEquals("does not must be false", Boolean.FALSE,
					loginTest.isLogin("{user:'admin', pass:'admin'}"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
