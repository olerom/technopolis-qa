package tests;

import core.TestBase;
import core.pages.GroupDefaultPage;
import core.pages.GroupMainPage;
import core.pages.LoginPage;
import core.pages.UserMainPage;
import model.TestBot;
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
        groupMainPage.checkPostText(postText);
    }

}
