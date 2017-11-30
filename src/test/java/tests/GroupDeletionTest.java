package tests;

import core.TestBase;
import core.factories.UserMainPageFactory;
import core.pages.GroupMainPage;
import core.pages.LoginPage;
import core.pages.UserMainPage;
import model.TestBot;
import org.junit.Test;

/**
 * Класс для тестирования удаления группы
 *
 * @author olerom
 */
public class GroupDeletionTest extends TestBase {

    /**
     * Удаление группы c идентификатором <code>groupId</code>.
     */
    @Test
    public void deletionGroupTest() {
        final LoginPage sessionHelper = new LoginPage(driver);
        final UserMainPage userMainPage = sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        GroupMainPage groupMainPage = userMainPage.clickGroupsOnToolbar();
        final String groupId = "54747772223593";

        groupMainPage = groupMainPage.openGroupById(groupId);
        groupMainPage = groupMainPage.deleteGroup();
        groupMainPage.checkIsGroupDeleted(groupId);
    }
}
