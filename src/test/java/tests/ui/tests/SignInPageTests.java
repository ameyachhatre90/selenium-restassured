package tests.ui.tests;

import com.google.inject.Inject;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.ui.pages.HomePage;
import tests.ui.pages.SignInPage;
import utils.SeleniumUtils;

import static org.testng.Assert.assertEquals;

/**
 * Sign In Page Tests
 */
@Test
public class SignInPageTests extends WebTestBase {
    @Inject
    private HomePage homePage;
    @Inject
    private SignInPage signInPage;

    /**
     * This method tests the Sign-In Page.
     */
    @Severity(SeverityLevel.BLOCKER)
    @Story("UI Tests")
    @Description("Verifies Sign In Functionality")
    @Test
    public void testSignIn() {
        report.printReport("Sign In Page Test Start");

        homePage.navigateToHomePage(); // Call the new method
        report.attachScreenshot(SeleniumUtils.captureScreenshot(driver), "Screenshot");

        assertEquals(homePage.getTitle(), ("Upgrade - Personal Loans, Cards and Rewards Checking | Home"));
        homePage.clickSignInButton();
        assertEquals(signInPage.getTitle(), ("Sign in | Upgrade"));
        signInPage.enterUsername("abcd@gmail.com");
        signInPage.enterPassword("Password");
        signInPage.clickSignInButton();
    }
}
