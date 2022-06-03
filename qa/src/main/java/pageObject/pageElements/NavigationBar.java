package pageObject.pageElements;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.Condition;
import pageObject.pages.PoliciesPage;

public class NavigationBar {

    private static final String POLICIES_BUTTON = "//a/div[text()='Policies']";

    public PoliciesPage clickPoliciesButton() {
        $x(POLICIES_BUTTON).shouldBe(Condition.visible).click();
        PoliciesPage policiesPage = page(PoliciesPage.class).waitForPageToLoad();
        return page(PoliciesPage.class);
    }
}
