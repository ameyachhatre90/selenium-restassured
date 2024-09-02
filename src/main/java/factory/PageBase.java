package factory;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

import java.time.Duration;

/**
 * This class serves as a base class for all Page Object Model (POM) classes.
 * It provides common methods and functionality that can be reused across all pages.
 * Class to be extended by all Page Objects Model (POM) classes.
 */
public class PageBase {
    protected static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(10);
    protected final WebDriver driver;

    /**
     * Injected instance of SeleniumUtils, providing utility methods for common Selenium operations.
     */
    @Inject
    protected SeleniumUtils seleniumUtils;

    /**
     * Constructor for PageBase.
     *
     * @param driver The WebDriver instance used for this page object.
     */
    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return The title of the current page as a String.
     */
    public String getTitle() {
        return driver.getTitle();
    }
}
