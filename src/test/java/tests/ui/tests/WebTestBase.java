package tests.ui.tests;

import com.google.inject.Inject;
import com.google.inject.Injector;
import config.Configuration;
import config.DIModule;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.AllureReporter;
import utils.SeleniumUtils;

/**
 * The type Test base.
 */
public class WebTestBase {
    /**
     * The Report.
     */
    protected AllureReporter report;

    /**
     * The Injector.
     */
    protected Injector injector = com.google.inject.Guice.createInjector(new DIModule());

    /**
     * The Driver.
     */
    @Inject
    protected WebDriver driver;

    /**
     * The Configuration.
     */
    @Inject
    protected Configuration configuration;

    /**
     * Before suite.
     */
    @BeforeSuite
    public void beforeSuite() {

    }

    /**
     * This method will be executed before the test starts.
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        injector.injectMembers(this);
        report = new AllureReporter();
    }

    /**
     * This method will be executed at the end of the test.
     *
     * @param result the result
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            report.attachScreenshot(SeleniumUtils.captureScreenshot(driver), "Failure Screenshot");
        }
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
