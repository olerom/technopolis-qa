package core;

import com.sun.istack.internal.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс-помощник для работы со страницей с группами
 *
 * @author olerom
 */
public class GroupMainPage extends HelperBase {

    @NotNull
    private static final By GROUP_CREATION =
            By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");

    @NotNull
    private static final By INTEREST_GROUP =
            By.xpath("//*[contains(@class,'create-group-dialog_img __interest')]");

    @NotNull
    private static final By CREATE_GROUP =
            By.id("hook_FormButton_button_create");

    public GroupMainPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(GROUP_CREATION));
    }

    public void typeGroupName(@NotNull final String groupName) {
        type(groupName, By.id("field_name"));
    }

    public void clickInterestGroup() {
        Assert.assertTrue("Нет элемента создания группы", isElementPresent(CREATE_GROUP));
        click(INTEREST_GROUP);
    }

    /**
     * Клик для начала создания группы.
     *
     */
    public void clickGroupCreation() {
        click(GROUP_CREATION);
    }

    public void clickCreateGroup() {
        Assert.assertTrue("Нет элемента создания группы", isElementPresent(CREATE_GROUP));
        click(CREATE_GROUP);
    }

}
