package tests;

import core.*;
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

        UserMainPageFactory userMainPageFactory = new UserMainPageFactory();
        UserMainPageHelper userMainPageHelper = userMainPageFactory.getUserMainPageHelper(driver);
        userMainPageHelper.clickGroupsOnToolbar();

        GroupMainPage groupHelper = new GroupMainPage(driver);
        groupHelper.clickGroupCreation();
        groupHelper.clickInterestGroup();
        groupHelper.typeGroupName("Group");
        groupHelper.clickCreateGroup();
    }
}
