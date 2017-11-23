package model;

import com.sun.istack.internal.NotNull;

/**
 * Date: 23.11.17
 *
 * @author olerom
 */
public class TestBot {
    @NotNull
    private final String login;

    @NotNull
    private final String password;

    public TestBot(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }
}
