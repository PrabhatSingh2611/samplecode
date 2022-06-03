package pageObject.modals;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import pageObject.pages.PoliciesPage;
import utils.Waiter;

import java.io.File;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PolicyItemActionsModal extends BaseModal {

    private static final String ACTIONS_TITLE = "//h6";
    private static final String PREVIEW_BUTTON = "(//h6/../li)[1]";
    private static final String DOWNLOAD_BUTTON = "(//h6/../li)[2]";
    private static final String PREVIEW_ICON = "//*[@data-testid='VisibilityIcon']";
    private static final String DOWNLOAD_ICON = "//*[@data-testid='DownloadIcon']";

    @Override
    public PolicyItemActionsModal waitForAppearance() {
        Waiter.waitForCondition(() -> isModalOpen(), 5, "Item actions modal is not opened");
        return this;
    }

    @Override
    public boolean isModalOpen() {
        return $x(ACTIONS_TITLE).shouldBe(exist).has(visible) &&
                $x(DOWNLOAD_BUTTON).shouldBe(exist).has(visible);
    }

    @Step
    public boolean isActionsTitleDisplayed() {
        return $x(ACTIONS_TITLE).isDisplayed();
    }

    @Step
    public boolean isActionsPreviewButtonDisplayed() {
        return $x(PREVIEW_BUTTON).isDisplayed();
    }

    @Step
    public boolean isActionsDownloadButtonDisplayed() {
        return $x(DOWNLOAD_BUTTON).isDisplayed();
    }

    @Step
    public String getActionsTitleText() {
        return $x(ACTIONS_TITLE).getText();
    }

    @Step
    public String getActionsPreviewButtonText() {
        return $x(PREVIEW_BUTTON).getText();
    }

    @Step
    public String getActionsDownloadButtonText() {
        return $x(DOWNLOAD_BUTTON).getText();
    }

//    TODO: the return statement of the method should be updated when functionality will be implemented
    @Step
    public PreviewModal clickPreviewButton() {
        $x(PREVIEW_BUTTON).shouldBe(visible).click();
        $x(PREVIEW_BUTTON).shouldNotBe(visible);
        return page(PreviewModal.class);
    }

//    TODO: the return statement of the method should be updated when functionality will be implemented
    @Step
    public PoliciesPage clickDownloadButton() {
        $x(DOWNLOAD_BUTTON).shouldBe(visible).click();
        sleep(4000);
        return page(PoliciesPage.class);
    }

    @Step
    public boolean isActionsPreviewIconDisplayed() {
        return $x(PREVIEW_ICON).isDisplayed();
    }

    @Step
    public boolean isActionsDownloadIconDisplayed() {
        return $x(DOWNLOAD_ICON).isDisplayed();
    }
}
