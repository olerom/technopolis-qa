package core.wrappers;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

/**
 * Обертка для поста в группе
 *
 * @author olerom
 */
public class PostWrapper {
    @NotNull
    private final WebElement element;

    public PostWrapper(@NotNull final WebElement element) {
        this.element = element;
    }

    /**
     * @return текст, который содержится в этом посте
     */
    @NotNull
    public String getPostText() {
        return element.getAttribute("innerText");
    }

}
