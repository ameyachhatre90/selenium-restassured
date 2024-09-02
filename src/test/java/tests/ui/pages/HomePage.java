package tests.ui.pages;

import com.google.inject.Inject;
import factory.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.AllureReporter;

/**
 * Page Object Model (POM) of the Home Page.
 * <p>
 * All elements of the page that will be used must be instantiated with their respective xpath.
 */
public class HomePage extends PageBase {

    private static final AllureReporter report = new AllureReporter();

    private String signInPage = "//a[contains(text(),'Sign In')]";
    private String aboutUs = "//a[contains(@href, '/about/')]";
    private String emailAddressBoxXpath = "//input[@aria-labelledby='username-2']";
    private String aboutUsPage = "//a[contains(text(),'About Us')]";

    /**
     * Constructor of the Home Page class. Initializes the Page Factory objects.
     *
     * @param driver The WebDriver instance.
     */
    @Inject
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("http://www.upgrade.com");
    }

    public WebElement getLearnMoreButton() {
        return driver.findElement(By.linkText("Learn More"));
    }

    public WebElement getCheckRatesButton() {
        return driver.findElement(By.linkText("Check Rates"));
    }

    public void clickSignInButton() {
        // Locate the login button and click it
        seleniumUtils.waitForElementToBeVisible(driver, signInPage, DEFAULT_WAIT_TIME);
        WebElement element3 = seleniumUtils.findElement(driver, "xpath", signInPage);
        assert element3 != null;
        element3.click();
        seleniumUtils.waitForElementToBeVisible(driver, emailAddressBoxXpath, DEFAULT_WAIT_TIME);
    }

    public void navigateToAboutUs() {
        seleniumUtils.scrollToElement(driver, aboutUs);
        seleniumUtils.click(driver, aboutUs);
        seleniumUtils.waitForElementToBeVisible(driver, aboutUsPage, DEFAULT_WAIT_TIME);
    }

}
