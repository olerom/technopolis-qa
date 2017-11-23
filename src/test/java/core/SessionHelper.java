package core;

import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Date: 23.11.17
 *
 * @author olerom
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    protected void check() {

    }

    public void doLogin(TestBot testBot) {
        type(testBot.getLogin(), By.id("field_email"));
        type(testBot.getPassword(), By.id("field_password"));
        click(By.cssSelector("div.form-actions > div > input.button-pro"));
    }


}
