package core;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Date: 24.11.17
 *
 * @author olerom
 */
public class GroupWrapper {

    @NotNull
    private final List<WebElement> elements;

    public GroupWrapper(@NotNull final List<WebElement> elements) {
        this.elements = elements;
    }

    public void soutClassesShitty() {
        for (WebElement element : elements) {
            System.out.println(element.getAttribute("href"));
        }
    }
}
