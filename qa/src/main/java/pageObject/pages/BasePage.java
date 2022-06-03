package pageObject.pages;

import pageObject.pageElements.NavigationBar;
import utils.Config;

import static com.codeborne.selenide.Selenide.page;

public abstract class BasePage {

    Config config = new Config();

    public abstract boolean isPageIsOpen();

    protected abstract BasePage waitForPageToLoad();

    public NavigationBar getNavigationBar() {
        return page(NavigationBar.class);
    }
}
