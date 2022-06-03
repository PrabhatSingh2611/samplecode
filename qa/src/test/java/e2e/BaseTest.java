package e2e;
// Main test class. Should contain driver setup/teardown methods and hooks. Also, could contain some base test methods.
// Global before/after methods should be api/e2e tests sensitive.
// After any change here, full regression for e2e and api should be provided for verification.

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Config;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class BaseTest {

    Config config = new Config();

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = config.getBaseURL();
    }

    @AfterMethod
    public void teardown() {
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Step
    public void open(String url) {
        Selenide.open(url);
    }

    @Step
    public <T> T open(String url, Class<T> pageObjectClass) {
        return Selenide.open(url, pageObjectClass);
    }

    @Step
    public <T> T open(Class<T> pageObjectClass) {
        return open(config.getBaseURL(), pageObjectClass);
    }
}
