package e2e;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;


public class ExampleTest extends BaseTest {

    @BeforeClass
    void setupClass() {
        //do some general steps that are repeatable for each test in this class
        open(config.getBaseURL());
        if(example1Page.isAcceptAllCookiesButtonVisible()) {
            example1Page.clickAcceptAllCookiesButton();
        }
    }

    @AfterClass
    void deleteTestData() {
//        method to delete created testData
    }

    @Test
    void testExample() {
        String thankYoyText = example1Page.clickContactUsButtonNew()
                .fillInputEmail("someemailfortest@gg.com")
                .fillInputFirstname("somename")
                .fillInputCompany("TestCompany")
                .clickLeaveRequestButton()
                .getThankYouText();

        Assert.assertEquals(thankYoyText, "Thank you!");
    }

    @Test
    void testExample2() {
        String thankYoyText = example1Page.clickContactUsButtonNew()
                .fillFormAndSubmit("someemailfortest@gg.com", "somename", "TestCompany")
                .getThankYouText();

        Assert.assertEquals(thankYoyText, "Thank you!");
    }
}
