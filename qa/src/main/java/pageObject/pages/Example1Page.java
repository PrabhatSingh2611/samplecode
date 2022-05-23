package pageObject.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pageObject.Page;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class Example1Page implements Page {

    private static final String HEADER_LOGO = "//div[@class='navbar-container']//img";
    private static final String CONTACT_US_BUTTON = "//nav//a[@class='btn']";
    private static final String ACCEPT_COOKIES_BUTTON = "//a[@id='cn-accept-cookie']";

    @Override
    public boolean isPageIsOpen() {
        return $x(HEADER_LOGO).isDisplayed()
                && $x(CONTACT_US_BUTTON).isDisplayed();
    }

    @Step
    public void clickAcceptAllCookiesButton() {
        $x(ACCEPT_COOKIES_BUTTON).click();
    }

    @Step
    public boolean isAcceptAllCookiesButtonVisible() {
        return $x(ACCEPT_COOKIES_BUTTON).has(Condition.visible);
    }

    @Step
    public Example2Page clickContactUsButtonNew() {
        $x(CONTACT_US_BUTTON).click();
        return page(Example2Page.class);
    }


}
