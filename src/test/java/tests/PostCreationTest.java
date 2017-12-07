package tests;

import core.TestBase;
import core.pages.GroupDefaultPage;
import core.pages.GroupMainPage;
import core.pages.LoginPage;
import core.pages.UserMainPage;
import model.TestBot;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

/**
 * Класс для тестирования создания поста
 *
 * @author olerom
 */
public class PostCreationTest extends TestBase {

    /**
     * Создание поста.
     * Проверяется соответствие введенного текста поста и полученного.
     */
    @Test
    public void postCreationTest() {
        final LoginPage sessionHelper = new LoginPage(driver);
        final UserMainPage userMainPage = sessionHelper.doLogin(new TestBot("technopolisBot13", "technopolis16"));

        final GroupDefaultPage groupDefaultPage = userMainPage.clickGroupsOnToolbar();
        final String groupId = "54742336077929";
        final GroupMainPage groupMainPage = groupDefaultPage.openGroupById(groupId);

        final String postText = "Post text to be tested";
        groupMainPage.createPost(postText);

        final String actualText = groupMainPage.getPostTextByIndex(0);
        checkPostText(postText, actualText);
    }

    /**
     * Проверка последнего (самого свежего) поста в группе на соответствие содержимого текста
     *
     * @param expectedText ожидаемый текст
     * @param actualText действительный текст
     */
    private void checkPostText(@NotNull final String expectedText,
                               @NotNull final String actualText) {
        Assert.assertEquals("Пост не был создан с соответсвующим текстом",
                expectedText,
                actualText);
    }

}
