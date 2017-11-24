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

    @NotNull
    private static final By GROUP_CREATION =
            By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");

    @NotNull
    private static final By INTEREST_GROUP =
            By.xpath("//*[contains(@class,'create-group-dialog_img __interest')]");

    @NotNull
    private static final By CREATE_GROUP =
            By.id("hook_FormButton_button_create");

    private static final By TYPE_NAME_FIELD = By.id("field_name");

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

}
