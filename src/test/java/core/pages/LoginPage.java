package core.pages;

import core.HelperBase;
import core.factories.UserMainPageFactory;
import model.TestBot;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
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
        Assert.assertTrue("Не найдено поле логина",
                isElementPresentWait(EMAIL_FIELD, 10));

        Assert.assertTrue("Не найдено поле с паролем",
                isElementPresentWait(PASSWORD_FIELD, 10));

        Assert.assertTrue("Не найдена кнопка для того, чтобы залогиниться",
                isElementPresentWait(LOGIN_BUTTON, 10));
    }

    /**
     * Метод для логина бота
     *
     * @param testBot бот, за которого осуществляется вход
     * @return {@link UserMainPage} главная страница пользователья в зависимости от дезайна
     */
    @NotNull
    public UserMainPage doLogin(@NotNull final TestBot testBot) {
        type(testBot.getLogin(), EMAIL_FIELD);
        type(testBot.getPassword(), PASSWORD_FIELD);
        click(LOGIN_BUTTON);
        return new UserMainPageFactory().getUserMainPageHelper(driver);
    }


}
