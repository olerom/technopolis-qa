package core;

import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Date: 10.11.17
 *
 * @author olerom
 */

public class TestBase {
    @NotNull
    private final String baseUrl = "https://ok.ru";

    @NotNull
    private final StringBuffer verificationErrors = new StringBuffer();

    @NotNull
    protected final WebDriver driver = new ChromeDriver();

    protected void init() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


    @Before
    public void setUp() throws Exception {
        init();
    }

    @After
    public void tearDown() throws Exception {
        stop();
    }

}
