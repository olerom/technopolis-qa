package core;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Date: 23.11.17
 *
 * @author olerom
 */
public class UserMainPageHelper extends HelperBase {

    @NotNull
    private final By GROUPS_ON_TOOLBAR = By.xpath(".//*[@id='hook_Block_MiddleColumnTopCard_MenuUser']/div/a[4]");

    public UserMainPageHelper(WebDriver driver) {
        super(driver);
    }

    protected void check() {
//        new WebDriverWait(driver, 10).
//                until(ExpectedConditions.visibilityOfElementLocated(GROUPS_ON_TOOLBAR));
    }

    public void clickGroupsOnToolbar() {
        click(GROUPS_ON_TOOLBAR);
    }

}
