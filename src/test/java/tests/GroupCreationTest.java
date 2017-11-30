package tests;

import core.TestBase;
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
        final LoginPage sessionHelper = new LoginPage(driver);
        final UserMainPage userMainPage = sessionHelper.doLogin(new TestBot("technopolisBot13", "technopolis16"));

        GroupMainPage groupMainPage = userMainPage.clickGroupsOnToolbar();

        final String groupName = "Test Group";
        final String groupDescription = "Test Group Description";

        groupMainPage = groupMainPage.clickGroupCreation();
        groupMainPage = groupMainPage.clickInterestGroup();
        groupMainPage.typeGroupName(groupName);
        groupMainPage.typeGroupDescription(groupDescription);
        groupMainPage = groupMainPage.clickCreateGroup();

        groupMainPage.checkGroupName(groupName);
        groupMainPage.checkGroupDescription(groupDescription);
    }

    /**
     * Создание группы с невалидными значениями
     */
    @Test
    public void groupCreationWithInvalidValuesTest() {
        final LoginPage sessionHelper = new LoginPage(driver);
        final UserMainPage userMainPage = sessionHelper.doLogin(new TestBot("technopolisBot13", "technopolis16"));

        GroupMainPage groupMainPage = userMainPage.clickGroupsOnToolbar();

        groupMainPage = groupMainPage.clickGroupCreation();
        groupMainPage = groupMainPage.clickInterestGroup();
        groupMainPage = groupMainPage.clickCreateGroup();

        groupMainPage.checkErrorOnGroupName();
    }
}
