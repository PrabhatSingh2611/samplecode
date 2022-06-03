package e2e;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.modals.AddPolicyModal;
import pageObject.modals.PolicyItemActionsModal;
import pageObject.modals.PreviewModal;
import pageObject.pages.PeoplePage;
import pageObject.pages.PoliciesPage;

public class PoliciesTest extends BaseTest {

//    TODO: All Policies should be deleted in precondition to pass this test. Need to delete them with API method
    @Test(enabled=false)
    void verifyEmptyPoliciesPageElements() {
        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton();

        //verify visibility of elements
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(policiesPage.isPageTitleDisplayed(), "isPageTitleDisplayed");
        softAssert.assertTrue(policiesPage.isAddPolicyButtonDisplayed(), "isAddPolicyButtonDisplayed");
        softAssert.assertTrue(policiesPage.isNoDocumentsTitleDisplayed(), "isNoDocumentsTitleDisplayed");
        softAssert.assertTrue(policiesPage.isNoDocumentsSubTitleDisplayed(), "isNoDocumentsSubTitleDisplayed");
        softAssert.assertTrue(policiesPage.isCreateButtonDisplayed(), "isCreateButtonDisplayed");
        softAssert.assertAll();

        //verify names of elements
        SoftAssert softAssert1 = new SoftAssert();
        softAssert1.assertEquals(policiesPage.getPageTitleText(), "Policies");
        softAssert1.assertEquals(policiesPage.getAddPolicyButtonText(), "Add Policy");
        softAssert1.assertEquals(policiesPage.getNoDocumentsTitleText(), "You have no documents");
        softAssert1.assertEquals(policiesPage.getNoDocumentsSubTitleText(), "No document subtitle");
        softAssert1.assertEquals(policiesPage.getCreateButtonText(), "Create");
        softAssert1.assertAll();
    }

    @Test
    void verifyAddedPolicyPageElements() {
        String policyName = "AQA Policy for verifyAddedPolicyPageElements test";

        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton()
                .addPolicy(policyName);

        //verify visibility of elements
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(policiesPage.isPageTitleDisplayed(), "isPageTitleDisplayed");
        softAssert.assertTrue(policiesPage.isAddPolicyButtonDisplayed(), "isAddPolicyButtonDisplayed");
        softAssert.assertTrue(policiesPage.isDenseSwitcherDisplayed(), "isDenseSwitcherDisplayed");
        softAssert.assertTrue(policiesPage.isDenseSwitcherTitleDisplayed(), "isDenseSwitcherTitleDisplayed");
        softAssert.assertTrue(policiesPage.isPaginationPagesTitleDisplayed(), "isPaginationPagesTitleDisplayed");
        softAssert.assertTrue(policiesPage.isPaginationNextButtonDisplayed(), "isPaginationNextButtonDisplayed");
        softAssert.assertTrue(policiesPage.isPaginationPreviousButtonDisplayed(), "isPaginationPreviousButtonDisplayed");
        softAssert.assertTrue(policiesPage.isRowsPerPageTitleDisplayed(), "isRowsPerPageTitleDisplayed");
        softAssert.assertTrue(policiesPage.isRowsPerPageButtonDisplayed(), "isRowsPerPageButtonDisplayed");
        softAssert.assertTrue(policiesPage.isTableHeaderCheckboxDisplayed(), "isTableHeaderCheckboxDisplayed");
        softAssert.assertTrue(policiesPage.isTableHeaderPolicyNameButtonDisplayed(), "isTableHeaderPolicyNameButtonDisplayed");
        softAssert.assertTrue(policiesPage.isTableHeaderOwnerButtonDisplayed(), "isTableHeaderOwnerButtonDisplayed");
        softAssert.assertTrue(policiesPage.isTableHeaderPublicationDateButtonDisplayed(), "isTableHeaderPublicationDateButtonDisplayed");
        softAssert.assertTrue(policiesPage.isTableHeaderStatusButtonDisplayed(), "isTableHeaderStatusButtonDisplayed");
        softAssert.assertAll();

        //verify names of elements
        SoftAssert softAssert1 = new SoftAssert();
        softAssert1.assertEquals(policiesPage.getPageTitleText(), "Policies",
                "verify PageTitleText");
        softAssert1.assertEquals(policiesPage.getTableHeaderPolicyNameButtonText(), "Policy Name",
                "verify TableHeaderPolicyNameButtonText");
        softAssert1.assertEquals(policiesPage.getTableHeaderOwnerButtonText(), "Owner",
                "verify TableHeaderOwnerButtonText");
        softAssert1.assertEquals(policiesPage.getTableHeaderPublicationDateButtonText(), "Publication Date",
                "verify TableHeaderPublicationDateButtonText");
        softAssert1.assertEquals(policiesPage.getTableHeaderStatusButtonText(), "Status",
                "verify TableHeaderStatusButtonText");
        softAssert1.assertEquals(policiesPage.getAddPolicyButtonText(), "Add Policy",
                "verify AddPolicyButtonText");
        softAssert1.assertEquals(policiesPage.getDenseSwitcherTitleText(), "Dense",
                "verify DenseSwitcherTitleText");
        softAssert1.assertEquals(policiesPage.getRowsPerPageText(), "Rows per page:",
                "Verify RowsPerPageText");
        softAssert1.assertTrue(policiesPage.getPaginationPagesTitleText().matches("^([1][–][5])\\s(\\w{2})\\s([\\d]+)$"),
                "Pagination text match regex");
        softAssert1.assertAll();
    }

    @Test
    void verifyAddPoliciesModalElements() {
        AddPolicyModal addPolicyModal = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton()
                .clickAddPolicyButton();

        //verify visibility of elements
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addPolicyModal.isModalTitleDisplayed(), "isModalTitleVisible");
        softAssert.assertTrue(addPolicyModal.isModalCloseButtonDisplayed(), "isModalCloseButtonVisible");
        softAssert.assertTrue(addPolicyModal.isSearchTitleDisplayed(), "isSearchTitleVisible");
        softAssert.assertTrue(addPolicyModal.isSearchInputDisplayed(), "isSearchInputVisible");
        softAssert.assertTrue(addPolicyModal.isUploadTitleDisplayed(), "isUploadTitleVisible");
        softAssert.assertTrue(addPolicyModal.isUploadInputTitleDisplayed(), "isUploadInputTitleVisible");
        softAssert.assertTrue(addPolicyModal.isUploadInputSubtitleDisplayed(), "isUploadInputSubtitleVisible");
        softAssert.assertTrue(addPolicyModal.isUploadLinkDisplayed(), "isUploadLinkVisible");
        softAssert.assertTrue(addPolicyModal.isModalCancelButtonDisplayed(), "isModalCancelButtonVisible");
        softAssert.assertTrue(addPolicyModal.isModalSaveButtonDisplayed(), "isModalSaveButtonVisible");
        softAssert.assertAll();

        //verify names of elements
        SoftAssert softAssert1 = new SoftAssert();
        softAssert1.assertEquals(addPolicyModal.getModalTitleText(), "Add policy",
                "Verify ModalTitleText");
        softAssert1.assertEquals(addPolicyModal.getSearchTitleText(), "Search engine",
                "Verify SearchTitleText");
        softAssert1.assertEquals(addPolicyModal.getSearchInputPlaceholderText(), "Title",
                "Verify InputPlaceholderText");
        softAssert1.assertEquals(addPolicyModal.getUploadTitleText(), "Upload",
                "Verify UploadTitleText");
        softAssert1.assertEquals(addPolicyModal.getUploadInputTitleText(), "Drop or Select file",
                "Verify UploadInputTitleText");
        softAssert1.assertEquals(addPolicyModal.getUploadInputSubtitleText(), "Drop files here or click browse thorough your machine",
                "Verify UploadInputSubtitleText");
        softAssert1.assertEquals(addPolicyModal.getUploadLinkText(), "browse",
                "Verify UploadLinkText");
        softAssert1.assertEquals(addPolicyModal.getModalCancelButtonText(), "Cancel",
                "Verify ModalCancelButtonText");
        softAssert1.assertEquals(addPolicyModal.getModalSaveButtonText(), "Save",
                "Verify ModalSaveButtonText");
        softAssert1.assertAll();
    }

//    TODO: implement Data provider for addPolicy test, for each file type - jpeg, xls, pdf etc.
    @Test
    void addPolicy() {
        String policyName = "AQA Policy for addPolicy test";

        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton();

        int initialPoliciesCount = policiesPage.getTotalItemsCount();

        policiesPage.addPolicy(policyName);

        Assert.assertEquals(policiesPage.getTotalItemsCount(), initialPoliciesCount + 1,
                "Policies items count in a table should increment");
        Assert.assertEquals(policiesPage.getTableItemPolicyName(1), policyName);
    }

    @Test
    void policiesListItemHavePreviewAndDownloadActions() {
        String policyName = "AQA Policy for policiesListItemHavePreviewAndDownloadActions test";

        PolicyItemActionsModal policyItemActionsModal = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton()
                .addPolicy(policyName)
                .clickTableItemActionsButton(policyName);

//        verify elements is displayed
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(policyItemActionsModal.isActionsTitleDisplayed());
        softAssert.assertTrue(policyItemActionsModal.isActionsPreviewButtonDisplayed());
        softAssert.assertTrue(policyItemActionsModal.isActionsDownloadButtonDisplayed());
        softAssert.assertTrue(policyItemActionsModal.isActionsPreviewIconDisplayed());
        softAssert.assertTrue(policyItemActionsModal.isActionsDownloadIconDisplayed());
        softAssert.assertAll();

//        verify names of elements
        SoftAssert softAssert1 = new SoftAssert();
        softAssert1.assertEquals(policyItemActionsModal.getActionsTitleText(), "Actions");
        softAssert1.assertEquals(policyItemActionsModal.getActionsPreviewButtonText(), "Preview");
        softAssert1.assertEquals(policyItemActionsModal.getActionsDownloadButtonText(), "Download");
        softAssert1.assertAll();
    }

    @Test(enabled=false)
    void previewPolicy() {
        String policyName = "AQA Policy for previewPolicy test";

        PreviewModal previewModal = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton()
                .addPolicy(policyName)
                .clickTableItemActionsButton(1)
                .clickPreviewButton();

        Assert.assertTrue(previewModal.isModalOpen());
//                TODO:Add assertions when functionality will be implemented.
    }

//    TODO: Test should be improved when deleting of test data will be implemented
    @Test
    void policiesListCanBeSortedByDate() {
        String policyName = "AQA Policy for policiesListCanBeSortedByDate test";

        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton()
                .addPolicy(policyName);

        String nameOfNewAddedItem = policiesPage.getTableItemPolicyName(1);

        Assert.assertEquals(nameOfNewAddedItem, policyName, "New item is first on the list");

        String nameOfFirstItemOld = policiesPage.clickTableHeaderPublicationDateButton()
                .getTableItemPolicyName(1);

        Assert.assertNotEquals(nameOfFirstItemOld, nameOfNewAddedItem, "Items was sorted from old to new");

    }

    @Test
    void policiesListCondensing() {
        String policyName = "AQA Policy for policiesListCondensing test";

        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton();

        for (int i = 0; i < 5; i++) {
            policiesPage.addPolicy(policyName + i);
        }

        int initialTableHeight = policiesPage.getTableHeight();

        policiesPage.clickDenseSwitcherButton();

        int condensedTableHeight = policiesPage.getTableHeight();
        Assert.assertTrue(initialTableHeight > condensedTableHeight);
    }

//    TODO: Test should be improved when deleting of test data will be implemented
    @Test
    void rowsPerPageOption() {
        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton()
                .clickRowsPerPageButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(policiesPage.getRowsPerPageOptionsSize(), 3, "There are 3 rows per page options");
        softAssert.assertEquals(policiesPage.getRowsPerPageOptionText(1), "5", "first rows per page option");
        softAssert.assertEquals(policiesPage.getRowsPerPageOptionText(2), "10", "second rows per page option");
        softAssert.assertEquals(policiesPage.getRowsPerPageOptionText(3), "25", "third rows per page option");
        softAssert.assertAll();

        int itemsCount = policiesPage.getVisibleTableItemsCount();

        Assert.assertEquals(itemsCount, 5);

        int itemsCount2 = policiesPage.clickRowsPerPageOption(2)
                .getVisibleTableItemsCount();
        Assert.assertEquals(itemsCount2, 10);
    }

    @Test
    void policiesListPagination() {
        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton();

        String initialPaginationTitleText = policiesPage.getPaginationPagesTitleText();
        String paginationTitleText = policiesPage.clickPaginationNextButton()
                .getPaginationPagesTitleText();

        Assert.assertNotEquals(initialPaginationTitleText, paginationTitleText, "Pagination text shouldn't be equal");
        Assert.assertTrue(paginationTitleText.matches("^([6][–]\\d{2})\\s(\\w{2})\\s([\\d]+)$"),
                "Pagination text match regex");
    }

    @Test
    void policiesSearch() {
        String policyName = "AQA Policy for policiesSearch test";

        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton()
                .addPolicy(policyName);

        String searchResult1 = policiesPage.enterSearchInput(policyName)
                .getTableItemPolicyName(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(searchResult1, policyName, "Search of policy by full name");

        String searchResult2 = policiesPage.clearSearchInput()
                .enterSearchInput("policiesSearch")
                .getTableItemPolicyName(1);

        softAssert.assertEquals(searchResult2, policyName, "Search of policy by partial name");

        String searchResult3 = policiesPage.clearSearchInput()
                .enterSearchInput("iciesSear")
                .getTableItemPolicyName(1);

        softAssert.assertEquals(searchResult3, policyName, "Search of policy by partial word");

        int itemsInSearchResult1 = policiesPage.clearSearchInput()
                .enterSearchInput("aqa")
                .getVisibleTableItemsCount();

        softAssert.assertTrue(itemsInSearchResult1 > 0, "Search is case insensitive");

        policiesPage.clearSearchInput()
                .enterSearchInput("$%^&*(OKJHG");

        softAssert.assertFalse(policiesPage.isVisibleTableItemsExist(), "Search of unexisted item is empty");
        softAssert.assertAll();
    }

    @Test
    void policiesSelection() {
        String policyName = "AQA Policy for policiesSelection test";

        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton();

        for (int i = 0; i < 5; i++) {
            policiesPage.addPolicy(policyName + i);
        }

        String selectedText = policiesPage.clickTableItemCheckbox(1).getSelectedText();

        Assert.assertEquals(selectedText, "1 selected", "Should be text about 1 selected item");

        policiesPage.clickTableItemCheckbox(1);

        Assert.assertFalse(policiesPage.isSelectedTextDisplayed(), "Text disappear when checkbox is unselected");

        selectedText = policiesPage.clickTableHeaderCheckbox().getSelectedText();

        Assert.assertEquals(selectedText, "5 selected", "Should be text about 5 selected items");
    }

    @Test
    void deletePolicy() {
        String policyName = "AQA Policy for deletePolicy test";

        PoliciesPage policiesPage = open(PeoplePage.class)
                .getNavigationBar()
                .clickPoliciesButton();

        for (int i = 0; i < 3; i++) {
            policiesPage.addPolicy(policyName + i);
        }

        int itemsCount = policiesPage.enterSearchInput(policyName)
                .getVisibleTableItemsCount();

        policiesPage.clickTableItemCheckbox(1);

        Assert.assertTrue(policiesPage.isDeleteButtonDisplayed());

        policiesPage.clickDeleteButton();

        int itemsCountAfterDelete = policiesPage.getVisibleTableItemsCount();

        Assert.assertEquals( itemsCountAfterDelete, itemsCount - 1,
                "Items count should be decreased after deletion of one selected item");

        policiesPage.clickTableHeaderCheckbox()
                .clickDeleteButton();

        Assert.assertFalse(policiesPage.isVisibleTableItemsExist(),"All selected Items should be deleted");
    }
}
