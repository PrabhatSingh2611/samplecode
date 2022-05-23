package pageObject.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Example2Page {

    private static final String INPUT_FIRSTNAME = "//input[@name='firstname']";
    private static final String INPUT_EMAIL = "//input[@name='email']";
    private static final String INPUT_COMPANY = "//input[@name='company']";
    private static final String LEAVE_REQUEST_BUTTON = "//input[@type='submit']";
    private static final String THANK_YOU_TEXT = "//p/strong";


    @Step
    public String getThankYouText() {
        return $x(THANK_YOU_TEXT).getText();
    }

    @Step
    public Example2Page fillInputEmail(String email) {
        $x(INPUT_EMAIL).setValue(email);
        return this;
    }

    @Step
    public Example2Page fillInputCompany(String company) {
        $x(INPUT_COMPANY).setValue(company);
        return this;
    }

    @Step
    public Example2Page clickLeaveRequestButton() {
        $x(LEAVE_REQUEST_BUTTON).click();
        return this;
    }

    @Step
    public Example2Page fillInputFirstname(String firstname) {
        $x(INPUT_FIRSTNAME).setValue(firstname);
        return this;
    }

    @Step
    public Example2Page fillFormAndSubmit(String one, String two, String three) {
        fillInputFirstname(one);
        fillInputCompany(two);
        fillInputEmail(three);
        clickLeaveRequestButton();
        return this;
    }
}
