package tests;

import core.*;
import model.TestBot;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Date: 24.11.17
 *
 * @author olerom
 */
public class GroupDeletionTest extends TestBase {

    @Test
    public void deleteGroup() {
        SessionHelper sessionHelper = new SessionHelper(driver);
        sessionHelper.doLogin(new TestBot("technopolisBot21", "technopolis16"));

        UserMainPageFactory userMainPageFactory = new UserMainPageFactory();
        UserMainPageHelper userMainPageHelper = userMainPageFactory.getUserMainPageHelper(driver);
        userMainPageHelper.clickGroupsOnToolbar();


        GroupWrapper groupWrapper = new GroupWrapper(
                driver.findElements(
                        By.xpath(".//*[contains(@class, 'ugrid __l')]//*[contains(@data-l,'t,visit')]")));

        groupWrapper.soutClassesShitty();
    }
}
