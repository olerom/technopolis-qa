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
     * Выбор группы с типом "Группа по интересам или для друзей"
     *
     * @return {@link GroupMainPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupMainPage clickInterestGroup() {
        Assert.assertTrue("Нет группы по интересам",
                isElementPresent(INTEREST_GROUP));
        click(INTEREST_GROUP);

        return this;
    }

    /**
     * Клик для начала создания группы.
     *
     * @return {@link GroupMainPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupMainPage clickGroupCreation() {
        Assert.assertTrue("Не найден элемент для начала создания группы",
                isElementPresent(GROUP_CREATION));
        click(GROUP_CREATION);

        return this;
    }

    /**
     * Клик для окончания создания группы.
     *
     * @return {@link GroupMainPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupMainPage clickCreateGroup() {
        Assert.assertTrue("Нет финальной кнопки для создания группы",
                isElementPresent(CREATE_GROUP));
        click(CREATE_GROUP);

        return this;
    }

    /**
     * Проверка соответствия названия группы
     *
     * @param expectedName ожидаемое название группы
     */
    public void checkGroupName(@NotNull final String expectedName) {
        Assert.assertTrue("Не найден элемент с названием группы",
                isElementPresent(GROUP_NAME));

        final String actualName = driver.findElement(GROUP_NAME).getText();
        Assert.assertTrue("Не соответствует название группы", expectedName.equals(actualName));
    }

    /**
     * Проверка соответствия описания группы
     *
     * @param expectedDescription ожидаемое описание группы
     */
    public void checkGroupDescription(@NotNull final String expectedDescription) {
        Assert.assertTrue("Не найден элемент с описанием группы",
                isElementPresent(GROUP_DESCRIPTION));

        final String actualName = driver.findElement(GROUP_DESCRIPTION).getText();
        Assert.assertEquals("Не соответствует описание группы",
                expectedDescription,
                actualName);
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

    @NotNull
    private List<GroupWrapper> getGroupWrappers() {
        Assert.assertTrue("Не найдены группы слева в блоке на странице с группами",
                isElementPresent(ALL_GROUPS));

        final List<WebElement> elements = driver.findElements(ALL_GROUPS);
        final List<GroupWrapper> groupWrappers = new ArrayList<>(elements.size());

        elements.forEach((e) -> groupWrappers.add(new GroupWrapper(e)));

        return groupWrappers;
    }

    /**
     * Открыть группу по ее id, в левом баре будем брать группы
     *
     * @param groupId id группы
     * @return {@link GroupMainPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupMainPage openGroupById(@NotNull final String groupId) {
        final List<GroupWrapper> groups = getGroupWrappers();

        for (GroupWrapper group : groups) {
            if (group.getGroupHref().equals(TO_GO_URL + groupId)) {
                group.click();
                break;
            }
        }
        return this;
    }

    /**
     * Удалить группу
     *
     * @return {@link GroupMainPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupMainPage deleteGroup() {
        Assert.assertTrue("Не найден элемент с дополнительными действиями",
                isElementPresent(ANOTHER_ACTIONS));
        click(ANOTHER_ACTIONS);

        Assert.assertTrue("Не найден элемент с удалением группы",
                isElementPresentWait(DELETE_GROUP, 10));
        click(DELETE_GROUP);

        Assert.assertTrue("Не найден элемент с подтверждением удаления группы",
                isElementPresentWait(CONFIRM_DELETION, 10));
        click(CONFIRM_DELETION);

        return this;
    }

    /**
     * Проверка группы на удаление (проеряем левый бар)
     *
     * @param groupId id группы
     */
    public void checkIsGroupDeleted(@NotNull final String groupId) {
        final List<GroupWrapper> groups = getGroupWrappers();

        boolean isDeleted = true;
        for (GroupWrapper group : groups) {
            if (group.getGroupHref().equals(TO_GO_URL + groupId)) {
                isDeleted = false;
                break;
            }
        }

        Assert.assertTrue("Группа не была удалена",
                isDeleted);
    }

    /**
     * Создать пост
     *
     * @param postText текст поста
     * @return {@link GroupMainPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupMainPage createPost(@NotNull final String postText) {
        Assert.assertTrue("Не найдено поле для создания поста",
                isElementPresent(START_POST_CREATION));
        click(START_POST_CREATION);

        Assert.assertTrue("Не найдено поле для текста поста",
                isElementPresentWait(TYPE_POST_TEXT, 10));
        type(postText, TYPE_POST_TEXT);

        Assert.assertTrue("Не найден элемент для публикации поста",
                isElementPresentWait(CONFIRM_POST_CREATION, 10));
        click(CONFIRM_POST_CREATION);

        return this;
    }

    @NotNull
    private List<PostWrapper> getPostWrappers() {
        Assert.assertTrue("Не найдены посты",
                isElementPresent(ALL_POSTS));

        final List<WebElement> elements = driver.findElements(ALL_POSTS);
        final List<PostWrapper> postWrappers = new ArrayList<>(elements.size());
        elements.forEach((e) -> postWrappers.add(new PostWrapper(e)));

        return postWrappers;
    }

    /**
     * Проверка последнего (самого свежего) поста в группе на соответствие содержимого текста
     *
     * @param expectedText ожидаемый текст
     */
    public void checkPostText(@NotNull final String expectedText) {
        final List<PostWrapper> posts = getPostWrappers();

        Assert.assertEquals("Пост не был создан с соответсвующим текстом",
                expectedText,
                posts.get(0).getPostText());
    }
}
