package tests.ui.tests;

import com.deque.html.axecore.args.AxeRunOnlyOptions;
import com.deque.html.axecore.args.AxeRunOptions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.ui.pages.HomePage;
import utils.ReporterUtils;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AccessibilityTest extends WebTestBase {

    /**
     * This method tests the Upgrade About Us Page.
     */
    @Severity(SeverityLevel.BLOCKER)
    @Story("Accessibility Tests")
    @Description("Verifies About Us Page Load")
    @Test(testName = "Accessibility Test using Selenium")
    public void checkAccessibility() {
        AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
        runOnlyOptions.setType("tag");
        runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));

        AxeRunOptions options = new AxeRunOptions();
        options.setRunOnly(runOnlyOptions);

        AxeBuilder axeBuilder = new AxeBuilder().withOptions(options);
        Results result = axeBuilder.analyze(goToHomePage());
        List<Rule> violationList = result.getViolations();

        ReporterUtils.log("Violation list size :" + violationList.size());
        for (Rule r : violationList) {
            ReporterUtils.log("Complete = " + r.toString());
            ReporterUtils.log("Tags = " + r.getTags());
            ReporterUtils.log("Description = " + r.getDescription());
            ReporterUtils.log("Help Url = " + r.getHelpUrl());
        }

        ReporterUtils.log("Inapplicable list size :" + result.getInapplicable().size());
        for (Rule r : result.getInapplicable()) {
            ReporterUtils.log("Complete = " + r.toString());
            ReporterUtils.log("Tags = " + r.getTags());
            ReporterUtils.log("Description = " + r.getDescription());
            ReporterUtils.log("Help Url = " + r.getHelpUrl());
        }

        assertEquals(violationList.size(), 0, "Accessibility violations on the page are more than 0.");
    }

    private WebDriver goToHomePage() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        return driver;
    }
}
