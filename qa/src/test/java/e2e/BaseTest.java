package e2e;
// Main test class. Should contain driver setup/teardown methods and hooks. Also, could contain some base test methods.
// Global before/after methods should be api/e2e tests sensitive.
// After any change here, full regression for e2e and api should be provided for verification.

import org.testng.annotations.AfterMethod;
import pageObject.pages.Example1Page;
import pageObject.pages.Example2Page;
import utils.Config;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class BaseTest {


    Config config = new Config();
    Example1Page example1Page = page(Example1Page.class);
    Example2Page example2Page = page(Example2Page.class);

    @AfterMethod
    public void teardown() {
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}