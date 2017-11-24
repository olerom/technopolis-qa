package core;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Главная страница пользователя.
 *
 * @author olerom
 */
public class UserMainPageHelper extends HelperBase {

    @NotNull
    private final By groupsOnToolbar;

    public UserMainPageHelper(@NotNull final WebDriver driver,
                              @NotNull final By groupsOnToolbarLocator) {
        super(driver);
        groupsOnToolbar = groupsOnToolbarLocator;
    }

    protected void check() {
//        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
//                .until(ExpectedConditions.visibilityOfElementLocated(groupsOnToolbar));
    }

    /**
     * Метод для того, чтобы перейти на страницу с группами.
     */
    public void clickGroupsOnToolbar() {
        click(groupsOnToolbar);
    }

}
