package core;

import org.jetbrains.annotations.NotNull;
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

    private static final By GROUP_CREATION = By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");

    private static final By INTEREST_GROUP = By.xpath("//*[contains(@class,'create-group-dialog_img __interest')]");

    private static final By CREATE_GROUP = By.id("hook_FormButton_button_create");

    private static final By TYPE_NAME_FIELD = By.id("field_name");

    private static final By TYPE_DESCRIPTION_FIELD = By.id("field_description");

    private static final By GROUP_NAME = By.xpath("(.//*[contains(@class,'mctc_name_tx')])");

    private static final By GROUP_DESCRIPTION = By.xpath("(.//*[contains(@class,'group-info_desc')])");

    public GroupMainPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(GROUP_CREATION));
    }

    /**
     * Ввод названия группы
     *
     * @param groupName название группы
     */
    public void typeGroupName(@NotNull final String groupName) {
        type(groupName, TYPE_NAME_FIELD);
    }

    /**
     * Ввод описания группы
     *
     * @param groupDescription описание группы
     */
    public void typeGroupDescription(@NotNull final String groupDescription) {
        type(groupDescription, TYPE_DESCRIPTION_FIELD);
    }

    /**
     * Выбор группы с типом "Группа по интересам или для друзей"
     */
    public void clickInterestGroup() {
        Assert.assertTrue("Нет элемента создания группы", isElementPresent(INTEREST_GROUP));
        click(INTEREST_GROUP);
    }

    /**
     * Клик для начала создания группы.
     */
    public void clickGroupCreation() {
        click(GROUP_CREATION);
    }

    /**
     * Клик для окончания создания группы.
     */
    public void clickCreateGroup() {
        Assert.assertTrue("Нет элемента создания группы", isElementPresent(CREATE_GROUP));
        click(CREATE_GROUP);
    }

    public void checkGroupName(@NotNull final String expectedName) {
        final String actualName = driver.findElement(GROUP_NAME).getText();
        Assert.assertTrue("Не соответствует название группы", expectedName.equals(actualName));
    }


    public void checkGroupDescription(@NotNull final String expectedDescription) {
        final String actualName = driver.findElement(GROUP_DESCRIPTION).getText();
        Assert.assertTrue("Не соответствует описание группы", expectedDescription.equals(actualName));
    }
}
