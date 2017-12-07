package core.wrappers;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

/**
 * Обертка для группы
 *
 * @author olerom
 */
public class GroupWrapper {
    private static final String TO_GO_URL = "https://ok.ru/group/";

    @NotNull
    private final WebElement element;

    public GroupWrapper(@NotNull final WebElement element) {
        this.element = element;
    }

    /**
     * @return href на группу
     */
    @NotNull
    public String getGroupId() {
        return element.getAttribute("href").substring(TO_GO_URL.length());
    }

    /**
     * Кликнуть для перехода в группу
     */
    public void click() {
        element.click();
    }

}
