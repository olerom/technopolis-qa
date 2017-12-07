package core.pages;

import core.HelperBase;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Главная страница пользователя.
 *
 * @author olerom
 */
public class UserMainPage extends HelperBase {

    @NotNull
    private final By groupsOnToolbar;

    public UserMainPage(@NotNull final WebDriver driver,
                        @NotNull final By groupsOnToolbarLocator) {
        super(driver);
        groupsOnToolbar = groupsOnToolbarLocator;
    }

    protected void check() {
    }

    /**
     * Метод для того, чтобы перейти на страницу с группами.
     *
     * @return {@link GroupDefaultPage} дефолтная страница с группами
     */
    @NotNull
    public GroupDefaultPage clickGroupsOnToolbar() {
        Assert.assertTrue("Не найден элемент со ссылкой на группы",
                isElementPresent(groupsOnToolbar));
        click(groupsOnToolbar);
        return new GroupDefaultPage(driver);
    }

}
