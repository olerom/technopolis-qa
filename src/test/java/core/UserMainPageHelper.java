package core;

import com.sun.istack.internal.NotNull;
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
    private final By GROUPS_ON_TOOLBAR = By.xpath(".//*[@class='mctc_navMenuSec' and contains(@href,'groups')]");

    public UserMainPageHelper(@NotNull final WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(GROUPS_ON_TOOLBAR));
    }

    /**
     * Метод для того, чтобы перейти на страницу с группами.
     */
    public void clickGroupsOnToolbar() {
        click(GROUPS_ON_TOOLBAR);
    }

}
