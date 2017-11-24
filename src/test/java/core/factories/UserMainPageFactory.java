package core.factories;

import core.pages.UserMainPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
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
     * В зависимости от наличия элемента возвращается реализация {@link UserMainPage}
     *
     * @param driver драйвер
     * @return {@link UserMainPage} в зависимости от дизайна
     */
    @NotNull
    public UserMainPage getUserMainPageHelper(@NotNull final WebDriver driver) {
// TODO: do smth w/ factory
        if (driver.findElements(NEW_DESIGN_LOCATOR).size() != 0) {
            return new UserMainPage(driver, NEW_DESIGN_LOCATOR);
        } else {
            return new UserMainPage(driver, OLD_DESIGN_LOCATOR);
        }
    }
}

