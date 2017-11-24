package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Date: 23.11.17
 *
 * @author olerom
 */
public class UserMainPageFactory {

    public UserMainPageHelper getUserMainPageHelper(WebDriver driver) {

//        new WebDriverWait(driver, 10).
//                until(ExpectedConditions.visibilityOfElementLocated(GROUPS_ON_TOOLBAR));


        return new UserMainPageHelper(driver);
    }
}

