package pageObject.pages;

import pageObject.pageElements.NavigationBar;

import static com.codeborne.selenide.Selenide.page;

public class PeoplePage extends BasePage {
    @Override
    public boolean isPageIsOpen() {
        return false;
    }

    @Override
    public PeoplePage waitForPageToLoad() {
        return this;
    }

    @Override
    public NavigationBar getNavigationBar() {
        return page(NavigationBar.class);
    }


}
