package core;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

/**
 * Date: 24.11.17
 *
 * @author olerom
 */
public class GroupWrapper {

    @NotNull
    private final WebElement element;

    public GroupWrapper(@NotNull final WebElement element) {
        this.element = element;
    }

    @NotNull
    public String getGroupHref() {
        return element.getAttribute("href");
    }

    public void click() {
        element.click();
    }

}
