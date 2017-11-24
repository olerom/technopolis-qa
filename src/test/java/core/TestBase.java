package core;

import org.jetbrains.annotations.NotNull;
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

    private final int WAIT_TIME_TIMEOUTS = 1;

    protected void init() {
        driver.manage().timeouts().implicitlyWait(WAIT_TIME_TIMEOUTS, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    public void stop() {
        driver.quit();
        final String verificationErrorString = verificationErrors.toString();
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
