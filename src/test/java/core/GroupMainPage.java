package core;

import com.sun.istack.internal.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Date: 23.11.17
 *
 * @author olerom
 */
public class GroupMainPage extends HelperBase {

    @NotNull
    private static final By CREATE_NEW_GROUP =
            By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");

    @NotNull
    private static final By CLICK_INTEREST_GROUP =
            By.xpath("//*[contains(@class,'create-group-dialog_img __interest')]");

    @NotNull
    private static final By CLICK_CREATE_BUTTON =
            By.id("hook_FormButton_button_create");

    public GroupMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(CREATE_NEW_GROUP));
//        new WebDriverWait(driver, 10).
//                until(ExpectedConditions.visibilityOfElementLocated(CLICK_INTEREST_GROUP));
//        new WebDriverWait(driver, 10).
//                until(ExpectedConditions.visibilityOfElementLocated(CREATE_NEW_GROUP));
    }

    public void typeGroupName(String groupName) {
        type(groupName, By.id("field_name"));
    }

    public void clickInterestGroup() {
        click(CLICK_INTEREST_GROUP);
    }

    public void clickCreateGroup() {
        Assert.assertTrue("Нет элемента создания группы", isElementPresent(CREATE_NEW_GROUP));
        click(CREATE_NEW_GROUP);
    }

    public void clickCreateButton() {
        click(CLICK_CREATE_BUTTON);
    }

}
