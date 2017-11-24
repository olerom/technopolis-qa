package model;


import org.jetbrains.annotations.NotNull;

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

    public TestBot(@NotNull final String login,
                   @NotNull final String password) {
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
