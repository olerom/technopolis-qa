package core.pages;

import core.HelperBase;
import core.wrappers.GroupWrapper;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Дефолтная страница с группами
 *
 * @author olerom
 */
public class GroupDefaultPage extends HelperBase {
    private static final By ALL_GROUPS = By.xpath(".//*[contains(@class, 'ugrid __l')]//*[contains(@data-l,'t,visit')]");
    private static final By GROUP_CREATION = By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");

    public GroupDefaultPage(@NotNull WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не найден элемент для начала создания группы",
                isElementPresentWait(GROUP_CREATION, 10));
    }


    /**
     * Клик для начала создания группы.
     *
     * @return {@link GroupCreationPage} страница с группами с измененным состоянием
     */
    @NotNull
    public GroupCreationPage clickGroupCreation() {
        Assert.assertTrue("Не найден элемент для начала создания группы",
                isElementPresent(GROUP_CREATION));
        click(GROUP_CREATION);

        return new GroupCreationPage(driver);
    }

    /**
     * Открыть группу по ее id, в левом баре будем брать группы
     *
     * @param groupId id группы
     * @return {@link GroupMainPage} страница с группами
     */
    @NotNull
    public GroupMainPage openGroupById(@NotNull final String groupId) {
        final List<GroupWrapper> groups = getGroupWrappers();

        for (GroupWrapper group : groups) {
            if (group.getGroupId().equals(groupId)) {
                group.click();
                break;
            }
        }
        return new GroupMainPage(driver);
    }

    @NotNull
    private List<GroupWrapper> getGroupWrappers() {
        Assert.assertTrue("Не найдены группы слева в блоке на странице с группами",
                isElementPresent(ALL_GROUPS));

        final List<WebElement> elements = driver.findElements(ALL_GROUPS);
        final List<GroupWrapper> groupWrappers = new ArrayList<>(elements.size());

        elements.forEach((e) -> groupWrappers.add(new GroupWrapper(e)));

        return groupWrappers;
    }

    /**
     * Проверка группы на удаление (проеряем левый бар)
     *
     * @param groupId id группы
     */
    public void checkIsGroupDeleted(@NotNull final String groupId) {
        final List<GroupWrapper> groups = getGroupWrappers();

        boolean isDeleted = true;
        for (GroupWrapper group : groups) {
            if (group.getGroupId().equals(groupId)) {
                isDeleted = false;
                break;
            }
        }

        Assert.assertTrue("Группа не была удалена",
                isDeleted);
    }
}
