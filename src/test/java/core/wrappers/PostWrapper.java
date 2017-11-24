package core.wrappers;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

/**
 * Date: 25.11.17
 *
 * @author olerom
 */
public class PostWrapper {
    @NotNull
    private final WebElement element;

    public PostWrapper(@NotNull final WebElement element) {
        this.element = element;
    }

    @NotNull
    public String getPostText() {
        return element.getAttribute("innerText");
    }

    public void click() {
        element.click();
    }

}
