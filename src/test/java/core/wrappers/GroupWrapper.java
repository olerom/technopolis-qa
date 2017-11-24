package core.wrappers;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

/**
 * Обертка для группы
 *
 * @author olerom
 */
public class GroupWrapper {

    @NotNull
    private final WebElement element;

    public GroupWrapper(@NotNull final WebElement element) {
        this.element = element;
    }

    /**
     * @return href на группу
     */
    @NotNull
    public String getGroupHref() {
        return element.getAttribute("href");
    }

    /**
     * Кликнуть для перехода в группу
     */
    public void click() {
        element.click();
    }

}
