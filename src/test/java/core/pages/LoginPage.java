package core.pages;

import core.HelperBase;
import model.TestBot;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Страница логина
 *
 * @author olerom
 */
public class LoginPage extends HelperBase {

    @NotNull
    private static final By EMAIL_FIELD = By.id("field_email");
    @NotNull
    private static final By PASSWORD_FIELD = By.id("field_password");
    @NotNull
    private static final By LOGIN_BUTTON = By.cssSelector("div.form-actions > div > input.button-pro");

    public LoginPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    /**
     * Метод для логина бота
     *
     * @param testBot бот, за которого осуществляется вход
     */
    public void doLogin(@NotNull final TestBot testBot) {
        type(testBot.getLogin(), EMAIL_FIELD);
        type(testBot.getPassword(), PASSWORD_FIELD);
        click(LOGIN_BUTTON);
    }


}
