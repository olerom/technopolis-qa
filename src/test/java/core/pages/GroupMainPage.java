package core.pages;

import core.HelperBase;
import core.wrappers.GroupWrapper;
import core.wrappers.PostWrapper;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Работы со страницей с группами
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
    private static final By GROUP_NAME_ERROR = By.xpath(".//*[contains(@class,'form_i__error')]//*[contains(@class,'input-e')]");
    private static final By ALL_GROUPS = By.xpath(".//*[contains(@class, 'ugrid __l')]//*[contains(@data-l,'t,visit')]");
    private static final By ANOTHER_ACTIONS = By.xpath(".//*[contains(@data-active-class, 'u-menu_a__sub-open')]");
    private static final By DELETE_GROUP = By.xpath(".//*[contains(@hrefattrs, 'cmd=PopLayer&st.layer.cmd=PopLayerRemoveAltGroup')]");
    private static final By CONFIRM_DELETION = By.id("hook_FormButton_button_delete");
    private static final By START_POST_CREATION = By.xpath(".//*[contains(@class, 'pf-with-ava_cnt')]");
    private static final By TYPE_POST_TEXT = By.id("posting_form_text_field");
    private static final By CONFIRM_POST_CREATION = By.xpath(".//*[contains(@class, 'form-actions')]//*[contains(@id, 'submit')]");
    private static final By ALL_POSTS = By.xpath(".//*[contains(@class, 'textWrap')]");

    private static final String TO_GO_URL = "https://ok.ru/group/";

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

    /**
     * Проверка соответствия названия группы
     * @param expectedName ожидаемое название группы
     */
    public void checkGroupName(@NotNull final String expectedName) {
        final String actualName = driver.findElement(GROUP_NAME).getText();
        Assert.assertTrue("Не соответствует название группы", expectedName.equals(actualName));
    }

    /**
     * Проверка соответствия описания группы
     * @param expectedDescription ожидаемое описание группы
     */
    public void checkGroupDescription(@NotNull final String expectedDescription) {
        final String actualName = driver.findElement(GROUP_DESCRIPTION).getText();
        Assert.assertTrue("Не соответствует описание группы", expectedDescription.equals(actualName));
    }

    /**
     * Проверка, что появилась ошибка о не введенном названии группы
     */
    public void checkErrorOnGroupName() {
        final String actualName = driver.findElement(GROUP_NAME_ERROR).getText();
        Assert.assertTrue("Нет такой ошибки", actualName.equals("Укажите название"));
    }

    @NotNull
    private List<GroupWrapper> getGroupWrappers() {
        final List<WebElement> elements = driver.findElements(ALL_GROUPS);
        final List<GroupWrapper> groupWrappers = new ArrayList<>(elements.size());

        elements.forEach((e) -> groupWrappers.add(new GroupWrapper(e)));

        return groupWrappers;
    }

    /**
     * Открыть группу по ее id
     * @param groupId id группы
     */
    public void openGroupById(@NotNull final String groupId) {
        final List<GroupWrapper> groups = getGroupWrappers();

        for (GroupWrapper group : groups) {
            if (group.getGroupHref().equals(TO_GO_URL + groupId)) {
                group.click();
                break;
            }
        }

    }

    /**
     * Удалить группу
     */
    public void deleteGroup() {
        click(ANOTHER_ACTIONS);
        click(DELETE_GROUP);
        click(CONFIRM_DELETION);
    }

    /**
     * Создать пост
     * @param postText текст поста
     */
    public void createPost(@NotNull final String postText) {
        click(START_POST_CREATION);
        type(postText, TYPE_POST_TEXT);
        click(CONFIRM_POST_CREATION);
    }

    @NotNull
    private List<PostWrapper> getPostWrappers() {
        final List<WebElement> elements = driver.findElements(ALL_POSTS);
        final List<PostWrapper> postWrappers = new ArrayList<>(elements.size());

        elements.forEach((e) -> postWrappers.add(new PostWrapper(e)));

        return postWrappers;
    }

    /**
     * Проверка последнего поста в группе на соответствие содержимого текста
     * @param expectedText ожидаемый текст
     */
    public void checkPostText(@NotNull final String expectedText) {
        final List<PostWrapper> posts = getPostWrappers();

        Assert.assertEquals("Пост не был создан с соответсвующим текстом",
                posts.get(0).getPostText(),
                expectedText);
    }
}
