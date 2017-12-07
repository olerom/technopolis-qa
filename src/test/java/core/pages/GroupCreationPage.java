package core.pages;

import core.HelperBase;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Date: 07.12.17
 *
 * @author olerom
 */
public class GroupCreationPage extends HelperBase {
    private static final By INTEREST_GROUP = By.xpath("//*[contains(@class,'create-group-dialog_img __interest')]");
    private static final By CREATE_GROUP = By.id("hook_FormButton_button_create");
    private static final By TYPE_NAME_FIELD = By.id("field_name");
    private static final By TYPE_DESCRIPTION_FIELD = By.id("field_description");
    private static final By GROUP_NAME_ERROR = By.xpath(".//*[contains(@class,'form_i__error')]//*[contains(@class,'input-e')]");


    public GroupCreationPage(@NotNull WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

    }

    /**
     * Выбор группы с типом "Группа по интересам или для друзей"
     */

    public void clickInterestGroup() {
        Assert.assertTrue("Нет группы по интересам",
                isElementPresent(INTEREST_GROUP));
        click(INTEREST_GROUP);
    }

    /**
     * Ввод названия группы
     *
     * @param groupName название группы
     */
    public void typeGroupName(@NotNull final String groupName) {
        Assert.assertTrue("Не найден элемент для названия группы",
                isElementPresent(TYPE_NAME_FIELD));
        type(groupName, TYPE_NAME_FIELD);
    }

    /**
     * Ввод описания группы
     *
     * @param groupDescription описание группы
     */
    public void typeGroupDescription(@NotNull final String groupDescription) {
        Assert.assertTrue("Не найден элемент для описания группы",
                isElementPresent(TYPE_DESCRIPTION_FIELD));
        type(groupDescription, TYPE_DESCRIPTION_FIELD);
    }


    /**
     * Клик для окончания создания группы.
     *
     * @return {@link GroupMainPage} страница с группами
     */
    @NotNull
    public GroupMainPage clickCreateGroup() {
        Assert.assertTrue("Нет финальной кнопки для создания группы",
                isElementPresent(CREATE_GROUP));
        click(CREATE_GROUP);

        return new GroupMainPage(driver);
    }

    /**
     * Проверка, что появилась ошибка о не введенном названии группы
     */
    public void checkErrorOnGroupName() {
        Assert.assertTrue("Не найден элемент с ошибкой",
                isElementPresent(GROUP_NAME_ERROR));

        final String actualName = driver.findElement(GROUP_NAME_ERROR).getText();
        Assert.assertTrue("Нет такой ошибки", actualName.equals("Укажите название"));
    }

}
