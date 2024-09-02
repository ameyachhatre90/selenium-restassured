package tests.ui.tests;

import com.google.inject.Inject;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.ui.pages.HomePage;

import static org.testng.Assert.assertEquals;

/**
 * Home Page Tests
 */
public class HomePageTests extends WebTestBase {
    @Inject
    private HomePage homePage;

    /**
     * This method tests the Upgrade Home Page.
     */
    @Severity(SeverityLevel.BLOCKER)
    @Story("UI Tests")
    @Description("Verifies the Home Page Load.")
    @Test
    public void homePageLoadTest() {
        report.printReport("Home Page Load Test");
        homePage.navigateToHomePage();
        assertEquals(homePage.getTitle(), ("Upgrade - Personal Loans, Cards and Rewards Checking | Home"));
    }
}
