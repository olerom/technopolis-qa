package tests;

import core.GroupMainPage;
import core.SessionHelper;
import core.TestBase;
import core.UserMainPageHelper;
import model.TestBot;
import org.junit.Test;

/**
 * Date: 23.11.17
 *
 * @author olerom
 */
public class GroupCreationTest extends TestBase {
    @Test
    public void testGroupCreation() throws Exception {
        SessionHelper sessionHelper = new SessionHelper(driver);
        sessionHelper.doLogin(new TestBot("technopolisBot13", "technopolis16"));

        UserMainPageHelper userMainPageHelper = new UserMainPageHelper(driver);
        userMainPageHelper.clickGroupsOnToolbar();

        GroupMainPage groupHelper = new GroupMainPage(driver);
        groupHelper.clickCreateGroup();
        groupHelper.clickInterestGroup();
        groupHelper.typeGroupName("Group");
        groupHelper.clickCreateButton();
    }
}
