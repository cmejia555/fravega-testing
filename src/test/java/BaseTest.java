import core.WebDriver;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    @Before
    public void setUp() {
        WebDriver.getInstance();
    }

    @After
    public void tearDown() {
        WebDriver.closeDriver();
    }
}
