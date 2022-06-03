package pageObject.modals;

import io.qameta.allure.Step;
import pageObject.pages.PoliciesPage;
import utils.Waiter;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AddPolicyModal extends BaseModal {

    private static final String MODAL_TITLE = "(//div[@role='presentation'])[1]//div[text()='Add policy']";
    private static final String MODAL_CLOSE_BUTTON = "((//div[@role='presentation'])[1]//button)[1]";
    private static final String SEARCH_TITLE = "(//form[@id='add-policy-form']//fieldset)[1]/legend";
    private static final String SEARCH_INPUT = "(//form[@id='add-policy-form']//input)[1]";
    private static final String UPLOAD_TITLE = "(//form[@id='add-policy-form']//fieldset)[3]/legend";
    private static final String UPLOAD_INPUT_TITLE = "(//form[@id='add-policy-form']//fieldset)[3]//h5";
    private static final String UPLOAD_INPUT_SUBTITLE = "(//form[@id='add-policy-form']//fieldset)[3]//p";
    private static final String UPLOAD_LINK = "(//form[@id='add-policy-form']//fieldset)[3]//p/span";
    private static final String UPLOAD_INPUT = "//input[@type='file']";
    private static final String MODAL_CANCEL_BUTTON = "((//div[@role='presentation'])[1]//button)[2]";
    private static final String MODAL_SAVE_BUTTON = "((//div[@role='presentation'])[1]//button)[3]";
    private static final String FILE_PREVIEW = "//img[@alt='file preview']";

    @Override
    public AddPolicyModal waitForAppearance() {
        Waiter.waitForCondition(() -> isModalOpen(), 5, "Add policy modal is not opened");
        return this;
    }

    @Override
    public boolean isModalOpen() {
        return $x(MODAL_TITLE).shouldBe(exist).has(visible) &&
        $x(UPLOAD_LINK).shouldBe(exist).has(visible);
    }

    @Step
    public boolean isModalTitleDisplayed() {
        return $x(MODAL_TITLE).isDisplayed();
    }

    @Step
    public String getModalTitleText() {
        return $x(MODAL_TITLE).getText();
    }

    @Step
    public boolean isModalCloseButtonDisplayed() {
        return $x(MODAL_CLOSE_BUTTON).isDisplayed();
    }

    @Step
    public String getModalCloseButtonText() {
        return $x(MODAL_CLOSE_BUTTON).getText();
    }

    @Step
    public PoliciesPage clickModalCloseButton() {
        $x(MODAL_CLOSE_BUTTON).shouldBe(visible).click();
        return page(PoliciesPage.class);
    }

    @Step
    public boolean isSearchTitleDisplayed() {
        return $x(SEARCH_TITLE).isDisplayed();
    }

    @Step
    public String getSearchTitleText() {
        return $x(SEARCH_TITLE).getText();
    }

    @Step
    public boolean isSearchInputDisplayed() {
        return $x(SEARCH_INPUT).isDisplayed();
    }

    @Step
    public String getSearchInputPlaceholderText() {
        return $x(SEARCH_INPUT).getAttribute("placeholder");
    }

    @Step
    public AddPolicyModal enterSearchText(String text) {
        $x(SEARCH_INPUT).setValue(text);
        return this;
    }

    @Step
    public boolean isUploadTitleDisplayed() {
        return $x(UPLOAD_TITLE).isDisplayed();
    }

    @Step
    public String getUploadTitleText() {
        return $x(UPLOAD_TITLE).getText();
    }

    @Step
    public boolean isUploadInputTitleDisplayed() {
        return $x(UPLOAD_INPUT_TITLE).isDisplayed();
    }

    @Step
    public String getUploadInputTitleText() {
        return $x(UPLOAD_INPUT_TITLE).getText();
    }

    @Step
    public boolean isUploadInputSubtitleDisplayed() {
        return $x(UPLOAD_INPUT_SUBTITLE).isDisplayed();
    }

    @Step
    public String getUploadInputSubtitleText() {
        return $x(UPLOAD_INPUT_SUBTITLE).getText();
    }

    @Step
    public boolean isUploadLinkDisplayed() {
        return $x(UPLOAD_LINK).isDisplayed();
    }

    @Step
    public String getUploadLinkText() {
        return $x(UPLOAD_LINK).getText();
    }

    @Step
    public AddPolicyModal uploadFile(String linkToFile) {
        File file = $x(UPLOAD_INPUT)
                .uploadFromClasspath(linkToFile);
        Waiter.waitForCondition(() -> isFilePreviewDisplayed(), 5, "Uploaded file preview is not visible");
        return this;
    }

    @Step
    public boolean isFilePreviewDisplayed() {
        waitForAppearance();
        return $x(FILE_PREVIEW).should(exist).has(visible);
    }

    @Step
    public boolean isModalCancelButtonDisplayed() {
        return $x(MODAL_CANCEL_BUTTON).isDisplayed();
    }

    @Step
    public String getModalCancelButtonText() {
        return $x(MODAL_CANCEL_BUTTON).getText();
    }

    @Step
    public PoliciesPage clickModalCancelButton() {
        $x(MODAL_CANCEL_BUTTON).shouldBe(visible).click();
        return page(PoliciesPage.class);
    }

    @Step
    public boolean isModalSaveButtonDisplayed() {
        return $x(MODAL_SAVE_BUTTON).isDisplayed();
    }

    @Step
    public String getModalSaveButtonText() {
        return $x(MODAL_SAVE_BUTTON).getText();
    }

    @Step
    public PoliciesPage clickModalSaveButton() {
        $x(MODAL_SAVE_BUTTON).shouldBe(visible).click();
        return page(PoliciesPage.class);
    }
}
