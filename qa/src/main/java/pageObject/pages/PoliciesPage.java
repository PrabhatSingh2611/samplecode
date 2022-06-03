package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import pageObject.modals.AddPolicyModal;
import pageObject.modals.PolicyItemActionsModal;
import pageObject.pageElements.NavigationBar;
import utils.Waiter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PoliciesPage extends BasePage {

    private static final String PAGE_TITLE = "//h1";
    private static final String ADD_POLICY_BUTTON = "//button[text()='Add Policy']";
    private static final String NO_DOCUMENT_TITLE = "//div[text()='You have no documents']";
    private static final String NO_DOCUMENT_SUBTITLE = "//div[text()='You have no documents']/following-sibling::div[1]";
    private static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String SEARCH_INPUT = "(//main//input)[1]";
    private static final String TABLE_CONTAINER = "//table";
    private static final String TABLE_HEADER_CHECKBOX = "//thead//input";
    private static final String TABLE_HEADER_POLICY_NAME_BUTTON = "(//thead//th[@scope='col'])[2]";
    private static final String TABLE_HEADER_OWNER_BUTTON = "(//thead//th[@scope='col'])[3]";
    private static final String TABLE_HEADER_PUBLICATION_DATE_BUTTON = "(//thead//span[@role='button'])";
    private static final String TABLE_HEADER_STATUS_BUTTON = "(//thead//th[@scope='col'])[5]";
    private static final String TABLE_ITEMS = "(//tbody/tr)";
    private static final String TABLE_ITEMS_INPUTS = TABLE_ITEMS + "//input";
    private static final String TABLE_ITEM = TABLE_ITEMS + "[%s]";
    private static final String TABLE_ITEM_CHECKBOX = "(" + TABLE_ITEM + ")//input";
    private static final String TABLE_ITEM_IMAGE = "(" + TABLE_ITEM + ")//img";
    private static final String TABLE_ITEM_POLICY_NAME = "(" + TABLE_ITEM + "//p)[1]";
    private static final String TABLE_ITEM_OWNER_AVATAR = "(" + TABLE_ITEM + "//p)[2]";
    private static final String TABLE_ITEM_OWNER_NAME = "(" + TABLE_ITEM + "//p)[2]/../div";
    private static final String TABLE_ITEM_DATE = "(" + TABLE_ITEM + "//td)[4]";
    private static final String TABLE_ITEM_STATUS = "(" + TABLE_ITEM + "//div)[4]";
    private static final String TABLE_ITEM_ACTIONS_BUTTON = "(" + TABLE_ITEM + ")//button";
    private static final String TABLE_ITEM_BY_TEXT = TABLE_ITEMS + "//p[contains (text(),'%s')]";
    private static final String TABLE_ITEM_BY_TEXT_CHECKBOX = "(" + TABLE_ITEM_BY_TEXT + ")/../../..//input";
    private static final String TABLE_ITEM_BY_TEXT_IMAGE = "(" + TABLE_ITEM_BY_TEXT + ")/../../..//img";
    private static final String TABLE_ITEM_BY_TEXT_ACTIONS_BUTTON = "(" + TABLE_ITEM_BY_TEXT + ")/../../..//button";
    private static final String DENSE_SWITCHER = "//label/span";
    private static final String DENSE_SWITCHER_TITLE = "(//label/span)[2]";
    private static final String ROWS_PER_PAGE_TITLE = "//main//p[contains(@class,'Pagination')][1]";
    private static final String ROWS_PER_PAGE_BUTTON = "//main//div[contains(@aria-haspopup,'listbox')]";
    private static final String ROWS_PER_PAGE_POPUP_OPTIONS = "(//li[@role='option'])";
    private static final String ROWS_PER_PAGE_POPUP_OPTION = ROWS_PER_PAGE_POPUP_OPTIONS + "[%s]";
    private static final String PAGINATION_PAGES_TITLE = "//main//p[contains(@class,'Pagination')][2]";
    private static final String PAGINATION_NEXT_BUTTON = "//button[contains(@aria-label,'next')]";
    private static final String PAGINATION_PREVIOUS_BUTTON = "//button[contains(@aria-label,'previous')]";
    private static final String SELECTED_TEXT = "//h6";
    private static final String DELETE_BUTTON = "//h6/../button";

    @Override
    public boolean isPageIsOpen() {
        return $x(SEARCH_INPUT).should(exist).has(visible) &&
                $x(TABLE_CONTAINER).should(exist).has(visible);
    }

    @Override
    public PoliciesPage waitForPageToLoad() {
        Waiter.waitForCondition(() -> isPageIsOpen(), 8, "Policies page is not open");
        return this;
    }

    @Override
    public NavigationBar getNavigationBar() {
        return page(NavigationBar.class);
    }

    @Step
    public boolean isPageTitleDisplayed() {
        return $x(PAGE_TITLE).isDisplayed();
    }

    @Step
    public String getPageTitleText() {
        return $x(PAGE_TITLE).getText();
    }

    @Step
    public boolean isAddPolicyButtonDisplayed() {
        return $x(ADD_POLICY_BUTTON).isDisplayed();
    }

    @Step
    public String getAddPolicyButtonText() {
        return $x(ADD_POLICY_BUTTON).shouldBe(visible).getText();
    }

    @Step ()
    public AddPolicyModal clickAddPolicyButton() {
        $x(ADD_POLICY_BUTTON).shouldBe(visible).click();
        AddPolicyModal addPolicyModal = page(AddPolicyModal.class).waitForAppearance();
        return page(AddPolicyModal.class);
    }

    @Step
    public boolean isNoDocumentsTitleDisplayed() {
        return $x(NO_DOCUMENT_TITLE).isDisplayed();
    }

    @Step
    public String getNoDocumentsTitleText() {
        return $x(NO_DOCUMENT_TITLE).getText();
    }

    @Step
    public boolean isNoDocumentsSubTitleDisplayed() {
        return $x(NO_DOCUMENT_SUBTITLE).isDisplayed();
    }

    @Step
    public boolean getNoDocumentsSubTitleText() {
        return $x(NO_DOCUMENT_SUBTITLE).has(visible);
    }

    @Step
    public boolean isCreateButtonDisplayed() {
        return $x(CREATE_BUTTON).isDisplayed();
    }

    @Step
    public boolean getCreateButtonText() {
        return $x(CREATE_BUTTON).has(visible);
    }

    @Step
    public AddPolicyModal clickCreateButton() {
        $x(CREATE_BUTTON).shouldBe(visible).click();
        return page(AddPolicyModal.class);
    }

    @Step
    public boolean isSearchInputDisplayed() {
        return $x(SEARCH_INPUT).isDisplayed();
    }

    @Step
    public boolean isTableHeaderCheckboxDisplayed() {
        return $x(TABLE_HEADER_CHECKBOX).exists();
    }

    @Step
    public PoliciesPage clickTableHeaderCheckbox() {
        $x(TABLE_HEADER_CHECKBOX).should(exist).click();
        waitForPageToLoad();
        return page(PoliciesPage.class);
    }

    @Step
    public int getTableHeight() {
        return $x(TABLE_CONTAINER).getSize().height;
    }

    @Step
    public boolean isTableHeaderPolicyNameButtonDisplayed() {
        return $x(TABLE_HEADER_POLICY_NAME_BUTTON).isDisplayed();
    }

    @Step
    public String getTableHeaderPolicyNameButtonText() {
        return $x(TABLE_HEADER_POLICY_NAME_BUTTON).getText();
    }

    @Step
    public boolean isTableHeaderOwnerButtonDisplayed() {
        return $x(TABLE_HEADER_OWNER_BUTTON).isDisplayed();
    }

    @Step
    public String getTableHeaderOwnerButtonText() {
        return $x(TABLE_HEADER_OWNER_BUTTON).getText();
    }

    @Step
    public boolean isTableHeaderPublicationDateButtonDisplayed() {
        return $x(TABLE_HEADER_PUBLICATION_DATE_BUTTON).isDisplayed();
    }

    @Step
    public String getTableHeaderPublicationDateButtonText() {
        return $x(TABLE_HEADER_PUBLICATION_DATE_BUTTON).getText().substring(0, 16);
    }

    @Step
    public PoliciesPage clickTableHeaderPublicationDateButton() {
        $x(TABLE_HEADER_PUBLICATION_DATE_BUTTON).shouldBe(visible).click();
        waitForPageToLoad();
        return page(PoliciesPage.class);
    }

    @Step
    public boolean isTableHeaderStatusButtonDisplayed() {
        return $x(TABLE_HEADER_STATUS_BUTTON).isDisplayed();
    }

    @Step
    public String getTableHeaderStatusButtonText() {
        return $x(TABLE_HEADER_STATUS_BUTTON).getText();
    }

    @Step
    public boolean isTableItemsDisplayed() {
        return $x(TABLE_ITEMS).isDisplayed();
    }

    @Step
    public int getVisibleTableItemsCount() {
        $x(TABLE_ITEMS_INPUTS).should(exist);
        return $$x(TABLE_ITEMS_INPUTS).size();
    }

    @Step
    public boolean isVisibleTableItemsExist() {
        return $$x(TABLE_ITEMS_INPUTS).size() > 0;
    }

    @Step
    public boolean isTableItemCheckboxDisplayed(int itemIndex) {
        return $x(TABLE_ITEM_CHECKBOX).isDisplayed();
    }

    @Step
    public PoliciesPage clickTableItemCheckbox(int itemIndex) {
        $x(String.format(TABLE_ITEM_CHECKBOX, itemIndex)).should(exist).click();
        waitForPageToLoad();
        return page(PoliciesPage.class);
    }

    @Step
    public boolean isTableItemImageDisplayed(int itemIndex) {
        return $x(String.format(TABLE_ITEM_IMAGE, itemIndex)).isDisplayed();
    }

    @Step
    public String getTableItemPolicyName(int itemIndex) {
        return $x(String.format(TABLE_ITEM_POLICY_NAME, itemIndex)).getText();
    }

    @Step
    public PoliciesPage waitForPolicyToAppear(String policyName) {
        Waiter.waitForCondition(() -> isTableItemByTextDisplayed(policyName), 5,
                "Policy with name '" + policyName+ "' wasn't added");
        return this;
    }

    @Step
    public boolean isTableItemByTextDisplayed(String policyName) {
        return $x(String.format(TABLE_ITEM_BY_TEXT, policyName)).should(exist).has(visible);
    }

    @Step
    public boolean isTableItemsCounterIncremented(int initCount) {
        int countOfItems = getTotalItemsCount();
        System.out.println(countOfItems);
        return getTotalItemsCount() == initCount + 1;
    }

    @Step
    public boolean isTableItemOwnerAvatarDisplayed(int itemIndex) {
        return $x(String.format(TABLE_ITEM_OWNER_AVATAR, itemIndex)).isDisplayed();
    }

    @Step
    public boolean isTableItemOwnerNameDisplayed(int itemIndex) {
        return $x(String.format(TABLE_ITEM_OWNER_NAME, itemIndex)).isDisplayed();
    }

    @Step
    public String getTableItemOwnerName(int itemIndex) {
        return $x(String.format(TABLE_ITEM_OWNER_NAME, itemIndex)).getText();
    }

    @Step
    public boolean isTableItemDateDisplayed(int itemIndex) {
        return $x(String.format(TABLE_ITEM_DATE, itemIndex)).isDisplayed();
    }

    @Step
    public String getTableItemDateName(int itemIndex) {
        return $x(String.format(TABLE_ITEM_DATE, itemIndex)).getText();
    }

    @Step
    public boolean isTableItemStatusDisplayed(int itemIndex) {
        return $x(String.format(TABLE_ITEM_STATUS, itemIndex)).isDisplayed();
    }

    @Step
    public String getTableItemStatusText(int itemIndex) {
        return $x(String.format(TABLE_ITEM_STATUS, itemIndex)).getText();
    }

    @Step
    public boolean isTableItemActionsButtonDisplayed(int itemIndex) {
        return $x(String.format(TABLE_ITEM_ACTIONS_BUTTON, itemIndex)).isDisplayed();
    }

    @Step
    public PolicyItemActionsModal clickTableItemActionsButton(int itemIndex) {
        $x(String.format(TABLE_ITEM_ACTIONS_BUTTON, itemIndex)).shouldBe(visible).click();
        PolicyItemActionsModal policyItemActionsModal = page(PolicyItemActionsModal.class).waitForAppearance();
        return page(PolicyItemActionsModal.class);
    }

    @Step
    public PolicyItemActionsModal clickTableItemActionsButton(String policyName) {
        $x(String.format(TABLE_ITEM_BY_TEXT_ACTIONS_BUTTON, policyName)).shouldBe(visible).click();
        PolicyItemActionsModal policyItemActionsModal = page(PolicyItemActionsModal.class).waitForAppearance();
        return page(PolicyItemActionsModal.class);
    }

    @Step
    public boolean isDenseSwitcherDisplayed() {
        return $x(DENSE_SWITCHER).isDisplayed();
    }

    @Step
    public PoliciesPage clickDenseSwitcherButton() {
        $x(DENSE_SWITCHER).shouldBe(visible).click();
        waitForPageToLoad();
        return this;
    }

    @Step
    public boolean isDenseSwitcherTitleDisplayed() {
        return $x(DENSE_SWITCHER_TITLE).isDisplayed();
    }

    @Step
    public String getDenseSwitcherTitleText() {
        return $x(DENSE_SWITCHER_TITLE).getText();
    }

    @Step
    public boolean isRowsPerPageTitleDisplayed() {
        return $x(ROWS_PER_PAGE_TITLE).isDisplayed();
    }

    @Step
    public String getRowsPerPageText() {
        return $x(ROWS_PER_PAGE_TITLE).getText();
    }

    @Step
    public boolean isRowsPerPageButtonDisplayed() {
        return $x(ROWS_PER_PAGE_BUTTON).isDisplayed();
    }

    @Step
    public PoliciesPage clickRowsPerPageButton() {
        $x(ROWS_PER_PAGE_BUTTON).shouldBe(visible).click();
        $x(ROWS_PER_PAGE_POPUP_OPTIONS).shouldBe(exist).shouldBe(visible);
        waitForPageToLoad();
        return this;
    }

    @Step
    public boolean isRowsPerPageOptionsDisplayed() {
        return $x(ROWS_PER_PAGE_POPUP_OPTIONS).isDisplayed();
    }

    @Step
    public int getRowsPerPageOptionsSize() {
        return $$x(ROWS_PER_PAGE_POPUP_OPTIONS).size();
    }

    @Step
    public String getRowsPerPageOptionText(int index) {
        return $x(String.format(ROWS_PER_PAGE_POPUP_OPTION, index)).shouldBe(visible).getText();
    }

    @Step
    public PoliciesPage clickRowsPerPageOption(int index) {
        $x(String.format(ROWS_PER_PAGE_POPUP_OPTION, index)).shouldBe(visible).click();
        $x(ROWS_PER_PAGE_POPUP_OPTIONS).shouldNotBe(visible);
        waitForPageToLoad();
        return this;
    }

    @Step
    public String getPaginationPagesTitleText() {
        return $x(PAGINATION_PAGES_TITLE).getText();
    }

    @Step
    public int getTotalItemsCount() {
        return Integer.parseInt(getPaginationPagesTitleText().replaceAll("^(\\d+[–]\\d+)\\s(\\w{2})\\s", ""));
    }

    @Step
    public boolean isPaginationPagesTitleDisplayed() {
        return $x(PAGINATION_PAGES_TITLE).isDisplayed();
    }

    @Step
    public boolean isPaginationNextButtonDisplayed() {
        return $x(PAGINATION_NEXT_BUTTON).isDisplayed();
    }

    @Step
    public PoliciesPage clickPaginationNextButton() {
        $x(PAGINATION_NEXT_BUTTON).shouldBe(visible).click();
        waitForPageToLoad();
        return this;
    }

    @Step
    public boolean isPaginationPreviousButtonDisplayed() {
        return $x(PAGINATION_PREVIOUS_BUTTON).isDisplayed();
    }

    @Step
    public PoliciesPage clickPaginationPreviousButton() {
        $x(PAGINATION_PREVIOUS_BUTTON).shouldBe(visible).click();
        return this;
    }

    @Step
    public PoliciesPage addPolicy(String policyName) {
        return addPolicy(policyName, config.getTestImageJpeg());
    }

    @Step
    public PoliciesPage addPolicy(String policyName, String policyPath) {
        return clickAddPolicyButton()
                .enterSearchText(policyName)
                .uploadFile(policyPath)
                .clickModalSaveButton()
                .waitForPageToLoad()
                .waitForPolicyToAppear(policyName);
    }

    @Step
    public PoliciesPage enterSearchInput(String NameToSearch) {
        $x(SEARCH_INPUT).setValue(NameToSearch).sendKeys(Keys.ENTER);
        waitForPageToLoad();
        return this;
    }

    @Step
    public PoliciesPage clearSearchInput() {
        for (int i = 0; i < 10; i++) {
            $x(SEARCH_INPUT).doubleClick().sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    @Step
    public String getSelectedText() {
        return $x(SELECTED_TEXT).getText();
    }

    @Step
    public boolean isSelectedTextDisplayed() {
        return $x(SELECTED_TEXT).isDisplayed();
    }

    @Step
    public boolean isDeleteButtonDisplayed() {
        return $x(DELETE_BUTTON).isDisplayed();
    }

    @Step
    public PoliciesPage clickDeleteButton() {
        $x(DELETE_BUTTON).shouldBe(visible).click();
        waitForPageToLoad();
        Waiter.waitForCondition(() -> !isDeleteButtonDisplayed(), 5, "Delete button is displayed");
        Waiter.waitForCondition(() -> !isSelectedTextDisplayed(), 5, "Selected text is displayed");
        waitForPageToLoad();
        return this;
    }
}
