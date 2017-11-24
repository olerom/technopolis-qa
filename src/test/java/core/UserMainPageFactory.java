package core;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Фабрика для основных страниц пользователя.
 *
 * @author olerom
 */
public class UserMainPageFactory {

    private final static By NEW_DESIGN_LOCATOR =
            By.xpath(".//*[@class='mctc_navMenuSec' and contains(@href,'groups')]");

    private final static By OLD_DESIGN_LOCATOR =
            By.xpath(".//*[contains(@hrefattrs,'st.cmd=userAltGroup&st._aid=NavMenu_User_AltGroups')]");

    /**
     * Проверяется дизайн страницы (должна быть текущей).
     * В зависимости от наличия элемента возвращается реализация {@link UserMainPageHelper}
     *
     * @param driver драйвер
     * @return {@link UserMainPageHelper} в зависимости от дизайна
     */
    @NotNull
    public UserMainPageHelper getUserMainPageHelper(@NotNull final WebDriver driver) {
// TODO: fix factory
        if (driver.findElements(NEW_DESIGN_LOCATOR).size() != 0) {
            return new UserMainPageHelper(driver, NEW_DESIGN_LOCATOR);
        } else {
//            Scroll down to element
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0, 350)", "");

            return new UserMainPageHelper(driver, OLD_DESIGN_LOCATOR);
        }
    }
}

