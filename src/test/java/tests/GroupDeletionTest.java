package tests;

import core.*;
import model.TestBot;
import org.junit.Test;

/**
 * Date: 24.11.17
 *
 * @author olerom
 */
public class GroupDeletionTest extends TestBase {

    @Test
    public void deleteGroup() {
        final SessionHelper sessionHelper = new SessionHelper(driver);
        sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        final UserMainPageFactory userMainPageFactory = new UserMainPageFactory();
        final UserMainPageHelper userMainPageHelper = userMainPageFactory.getUserMainPageHelper(driver);
        userMainPageHelper.clickGroupsOnToolbar();

        final GroupMainPage groupHelper = new GroupMainPage(driver);
        groupHelper.openGroupById("53521811046534");
        groupHelper.deleteGroup();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
