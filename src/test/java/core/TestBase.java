package core;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Базовый класс для тестов.
 *
 * @author olerom
 */

public class TestBase {

    @NotNull
    private final StringBuffer verificationErrors = new StringBuffer();

    @NotNull
    protected final WebDriver driver = new ChromeDriver();

    /**
     * Настрока для каждого теста.
     * По секунде на переход.
     * Начинаем все тесты с главной страницы портала.
     */
    @Before
    public void setUp() {
        final int waitMilliseconds = 1000;
        driver.manage().timeouts().implicitlyWait(waitMilliseconds, TimeUnit.MILLISECONDS);

        final String baseUrl = "https://ok.ru";
        driver.get(baseUrl + "/");
    }

    /**
     * Закрываем драйвер после каждого теста.
     * Проверяем, что все было ок.
     */
    @After
    public void tearDown() {
        driver.quit();
        final String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
