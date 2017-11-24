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

    private boolean acceptNextAlert = true;

    public HelperBase(@NotNull final WebDriver driver) {
        this.driver = driver;
        check();
    }

    protected abstract void check();

    /**
     * Метод для того, чтобы произвести клик на элемент.
     * Производится скролл, чтобы элемент был в видимости.
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
     * Метод для того, чтобы произвести клик на элемент
     *
     * @param groupName название группы
     * @param locator   элемент
     */
    protected void type(@NotNull final String groupName,
                        @NotNull final By locator) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(groupName);
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

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @NotNull
    private String closeAlertAndGetItsText() {
        try {
            final Alert alert = driver.switchTo().alert();
            final String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
