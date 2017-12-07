package tests;

import core.TestBase;
import core.pages.*;
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

        final GroupDefaultPage groupDefaultPage = userMainPage.clickGroupsOnToolbar();

        final String groupName = "Test Group";
        final String groupDescription = "Test Group Description";

        final GroupCreationPage groupCreationPage = groupDefaultPage.clickGroupCreation();
        groupCreationPage.clickInterestGroup();

        groupCreationPage.typeGroupName(groupName);
        groupCreationPage.typeGroupDescription(groupDescription);
        final GroupMainPage groupMainPage = groupCreationPage.clickCreateGroup();

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

        GroupDefaultPage groupDefaultPage = userMainPage.clickGroupsOnToolbar();

        final GroupCreationPage groupCreationPage = groupDefaultPage.clickGroupCreation();
        groupCreationPage.clickInterestGroup();
        groupCreationPage.clickCreateGroup();

        groupCreationPage.checkErrorOnGroupName();
    }
}
