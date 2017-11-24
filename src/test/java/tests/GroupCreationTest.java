package tests;

import core.*;
import core.factories.UserMainPageFactory;
import core.pages.GroupMainPage;
import core.pages.LoginPage;
import core.pages.UserMainPage;
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
    public void groupCreationWithValidValuesTest() throws Exception {
        LoginPage sessionHelper = new LoginPage(driver);
        sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        UserMainPageFactory userMainPageFactory = new UserMainPageFactory();
        UserMainPage userMainPage = userMainPageFactory.getUserMainPageHelper(driver);
        userMainPage.clickGroupsOnToolbar();

        GroupMainPage groupHelper = new GroupMainPage(driver);

        final String groupName = "Test Group";
        final String groupDescription = "Test Group Description";

        groupHelper.clickGroupCreation();
        groupHelper.clickInterestGroup();
        groupHelper.typeGroupName(groupName);
        groupHelper.typeGroupDescription(groupDescription);
        groupHelper.clickCreateGroup();

        groupHelper.checkGroupName(groupName);
        groupHelper.checkGroupDescription(groupDescription);
    }

    @Test
    public void groupCreationWithInvalidValuesTest() {
        LoginPage sessionHelper = new LoginPage(driver);
        sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        UserMainPageFactory userMainPageFactory = new UserMainPageFactory();
        UserMainPage userMainPage = userMainPageFactory.getUserMainPageHelper(driver);
        userMainPage.clickGroupsOnToolbar();

        GroupMainPage groupHelper = new GroupMainPage(driver);

        groupHelper.clickGroupCreation();
        groupHelper.clickInterestGroup();
        groupHelper.clickCreateGroup();

        groupHelper.checkErrorOnGroupName();
    }
}
