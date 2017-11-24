package tests;

import core.*;
import model.TestBot;
import org.junit.Test;

/**
 * Date: 24.11.17
 *
 * @author olerom
 */
public class PostCreationTest extends TestBase {

    @Test
    public void postCreationTest() {
        final SessionHelper sessionHelper = new SessionHelper(driver);
        sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        final UserMainPageFactory userMainPageFactory = new UserMainPageFactory();
        final UserMainPageHelper userMainPageHelper = userMainPageFactory.getUserMainPageHelper(driver);
        userMainPageHelper.clickGroupsOnToolbar();

        final GroupMainPage groupHelper = new GroupMainPage(driver);
        groupHelper.openGroupById("53521809211526");

        groupHelper.createPost("NEW POST");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
