package core;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;

/**
 * Базовый класс хелпера
 *
 * @author olerom
 */
public abstract class HelperBase {
    @NotNull
    protected final WebDriver driver;
    protected static final int TIME_OUT_IN_SECONDS = 1;
    protected static final int WAIT_IN_SECONDS = 10;

    public HelperBase(@NotNull final WebDriver driver) {
        this.driver = driver;
        check();
    }

    /**
     * Проверка каких-нибудь нужных элементов на странице.
     */
    protected abstract void check();

    /**
     * Метод для того, чтобы произвести клик на элемент.
     * Производится скролл, чтобы элемент был в видимости.
     * Если этого не делать, то будет выкинуто исключение,
     * так как нельзя достучаться до этого элемента.
     *
     * @param locator элемент
     */
    protected void click(@NotNull final By locator) {
        final WebElement element = driver.findElement(locator);
        final int elementPosition = element.getLocation().getY();

//        Because of header
        final int defaultOffset = 100;

        final String js = String.format("window.scroll(0, %s);", elementPosition - defaultOffset);
        ((JavascriptExecutor) driver).executeScript(js);
        element.click();
    }

    /**
     * Метод для того, чтобы вводить значения
     *
     * @param text    название группы
     * @param locator элемент
     */
    protected void type(@NotNull final String text,
                        @NotNull final By locator) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    /**
     * Метод для проверки присутствия элемента на странице
     *
     * @param element элемент
     * @return true, если элемент присутствует на странице
     */
    protected boolean isElementPresent(@NotNull final By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
