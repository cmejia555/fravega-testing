import core.WebDriver;
import org.junit.After;
import org.junit.Before;

public class TestBase {

    @Before
    public void setUp() {
        WebDriver.getInstance();
    }

    @After
    public void tearDown() {
        WebDriver.closeDriver();
    }
}
