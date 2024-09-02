package tests.ui.pages;

import com.google.inject.Inject;
import factory.PageBase;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model (POM) of the About Us Page.
 * <p>
 * All elements of the page that will be used must be instantiated with their respective xpath.
 */
public class AboutUsPage extends PageBase {

    private String aboutUs = "//a[contains(text(),'About Us')]";
    private static String aboutUsHeader = "//h1[contains(.,'About Us')]";

    /**
     * Constructor of the AboutUsPage class. Initializes the Page Factory objects.
     *
     * @param driver The WebDriver instance.
     */
    @Inject
    public AboutUsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAboutUsPageLoaded(WebDriver driver) {
        try {
            WebElement aboutUs = seleniumUtils.waitForElementToBeVisible(driver, aboutUsHeader, DEFAULT_WAIT_TIME);
            return aboutUs.isDisplayed();
        } catch (TimeoutException e) {
            // Handle timeout exception if element is not found within the specified time
            System.out.println("Page might not have loaded successfully. Waited for 10 seconds to find 'About Us' header");
            return false;
        }
    }

}
