package tests;

import core.GroupMainPage;
import core.SessionHelper;
import core.TestBase;
import core.UserMainPageHelper;
import model.TestBot;
import org.junit.Test;

/**
 * Класс для тестирования создания группы
 *
 * @author olerom
 */
public class GroupCreationTest extends TestBase {

    /**
     * Создание группы с валидными значениями
     */
    @Test
    public void testGroupCreation() throws Exception {
        SessionHelper sessionHelper = new SessionHelper(driver);
        sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        UserMainPageHelper userMainPageHelper = new UserMainPageHelper(driver);
        userMainPageHelper.clickGroupsOnToolbar();

        GroupMainPage groupHelper = new GroupMainPage(driver);
        groupHelper.clickGroupCreation();
        groupHelper.clickInterestGroup();
        groupHelper.typeGroupName("Group");
        groupHelper.clickCreateGroup();
    }
}
