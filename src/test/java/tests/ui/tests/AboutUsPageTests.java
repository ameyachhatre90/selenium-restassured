package tests.ui.tests;

import com.google.inject.Inject;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import tests.ui.pages.AboutUsPage;
import tests.ui.pages.HomePage;

import static org.testng.Assert.assertEquals;

/**
 *  About Us Page Tests 1
 */
public class AboutUsPageTests extends WebTestBase {
    @Inject
    private HomePage homePage;
    @Inject
    private AboutUsPage aboutUsPage;

    /**
     * This method tests the Upgrade About Us Page.
     */
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifies About Us Page Load")
    @Test
    public void aboutUsPageLoadTest() {
        report.printReport("About Us Page Test Start");

        homePage.navigateToHomePage(); // Call the new method
        assertEquals(homePage.getTitle(), ("Upgrade - Personal Loans, Cards and Rewards Checking | Home"));
        homePage.navigateToAboutUs();
        aboutUsPage.isAboutUsPageLoaded(driver);
        assertEquals(aboutUsPage.getTitle(), ("Upgrade - About Us"));
    }
}
