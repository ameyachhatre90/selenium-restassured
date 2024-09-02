package factory;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

import java.time.Duration;

/**
 * Class to be extended by all Page Objects Model (POM) classes.
 * <p>
 * Contains common methods to be used by every page.
 */
public class PageBase {
    protected static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(10);
    protected final WebDriver driver;

    @Inject
    protected SeleniumUtils seleniumUtils;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Get the page title
     *
     * @return The title of the current page.
     */
    public String getTitle() {
        return driver.getTitle();
    }
}
