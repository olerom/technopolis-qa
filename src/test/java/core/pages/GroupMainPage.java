package core.pages;

import core.HelperBase;
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

    private static final By GROUP_NAME = By.xpath("(.//*[contains(@class,'mctc_name_tx')])");
    private static final By GROUP_DESCRIPTION = By.xpath("(.//*[contains(@class,'group-info_desc')])");
    private static final By ANOTHER_ACTIONS = By.xpath(".//*[contains(@data-active-class, 'u-menu_a__sub-open')]");
    private static final By DELETE_GROUP = By.xpath(".//*[contains(@hrefattrs, 'cmd=PopLayer&st.layer.cmd=PopLayerRemoveAltGroup')]");
    private static final By CONFIRM_DELETION = By.id("hook_FormButton_button_delete");
    private static final By START_POST_CREATION = By.xpath(".//*[contains(@class, 'pf-with-ava_cnt')]");
    private static final By TYPE_POST_TEXT = By.id("posting_form_text_field");
    private static final By CONFIRM_POST_CREATION = By.xpath(".//*[contains(@class, 'form-actions')]//*[contains(@id, 'submit')]");
    private static final By ALL_POSTS = By.xpath(".//*[contains(@class, 'textWrap')]");


    public GroupMainPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    protected void check() {
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
     * Удалить группу
     *
     * @return {@link GroupDefaultPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupDefaultPage deleteGroup() {
        Assert.assertTrue("Не найден элемент с дополнительными действиями",
                isElementPresent(ANOTHER_ACTIONS));
        click(ANOTHER_ACTIONS);

        Assert.assertTrue("Не найден элемент с удалением группы",
                isElementPresentWait(DELETE_GROUP, 10));
        click(DELETE_GROUP);

        Assert.assertTrue("Не найден элемент с подтверждением удаления группы",
                isElementPresentWait(CONFIRM_DELETION, 10));
        click(CONFIRM_DELETION);

        return new GroupDefaultPage(driver);
    }


    /**
     * Создать пост
     *
     * @param postText текст поста
     * @return {@link GroupMainPage} страница с группами с измененным состоянием
     */
    @NotNull
    public void createPost(@NotNull final String postText) {
        Assert.assertTrue("Не найдено поле для создания поста",
                isElementPresentWait(START_POST_CREATION, 10));
        click(START_POST_CREATION);


        Assert.assertTrue("Не найдено поле для текста поста",
                isElementPresentWait(TYPE_POST_TEXT, 10));
        type(postText, TYPE_POST_TEXT);

        Assert.assertTrue("Не найден элемент для публикации поста",
                isElementPresentWait(CONFIRM_POST_CREATION, 10));

        waitUntilClickable(10, CONFIRM_POST_CREATION);
        click(CONFIRM_POST_CREATION);
    }

    @NotNull
    private List<PostWrapper> getPostWrappers() {
        Assert.assertTrue("Не найдены посты",
                isElementPresentWait(ALL_POSTS, 10));

        final List<WebElement> elements = driver.findElements(ALL_POSTS);
        final List<PostWrapper> postWrappers = new ArrayList<>(elements.size());
        elements.forEach((e) -> postWrappers.add(new PostWrapper(e)));

        return postWrappers;
    }

    /**
     * Получить текст поста по индексу
     *
     * @param index индекс
     * @return текст поста
     */
    @NotNull
    public String getPostTextByIndex(final int index) {
        final List<PostWrapper> posts = getPostWrappers();
        return posts.get(index).getPostText();
    }
}
