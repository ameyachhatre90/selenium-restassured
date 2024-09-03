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
 * This class serves as the base class for all UI tests. It provides common functionalities
 * like dependency injection, WebDriver management, and test reporting.
 */
public class WebTestBase {
    /**
     * An instance of AllureReporter for generating test reports.
     */
    protected AllureReporter report;

    /**
     * Injector instance used to manage dependencies.
     */
    protected Injector injector = com.google.inject.Guice.createInjector(new DIModule());

    /**
     * WebDriver instance used for browser automation. Injected through Guice.
     */
    @Inject
    protected WebDriver driver;

    /**
     * Configuration object containing test configuration details. Injected through Guice.
     */
    @Inject
    protected Configuration configuration;

    /**
     * This method is executed before the entire test suite starts.
     * Currently empty, but can be used for any setup tasks specific to the test suite.
     */
    @BeforeSuite
    public void beforeSuite() {

    }

    /**
     * This method is executed before each test case.
     * It performs the following tasks:
     *  - Injects dependencies using the injector.
     *  - Creates an instance of AllureReporter for test reporting.
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        injector.injectMembers(this);
        report = new AllureReporter();
    }

    /**
     * This method is executed after each test case.
     * It performs the following tasks:
     *  - If the test fails, it attaches a screenshot to the Allure report.
     *  - It closes and quits the WebDriver instance.
     *
     * @param result The TestResult object containing information about the test execution.
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
