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
        sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        final UserMainPageFactory userMainPageFactory = new UserMainPageFactory();
        final UserMainPage userMainPage = userMainPageFactory.getUserMainPageHelper(driver);
        userMainPage.clickGroupsOnToolbar();

        final GroupMainPage groupHelper = new GroupMainPage(driver);
        final String groupId = "53521804034182";

        groupHelper.openGroupById(groupId);
        groupHelper.deleteGroup();
        groupHelper.checkIsGroupDeleted(groupId);
    }
}
