package tests.ui.pages;

import com.google.inject.Inject;
import factory.PageBase;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

/**
 * Page Object Model (POM) of the Rates Page.
 * <p>
 * All elements of the page that will be used must be instantiated with their respective xpath.
 */
public class RatesPage extends PageBase {

    @Inject
    private SeleniumUtils seleniumUtils;

    private String txtSearch = "//input[@name='q']";
    private String btnSearch = "//input[@name='btnK']";

    /**
     * Constructor of the RatesPage class. Initializes the Page Factory objects.
     *
     * @param driver The WebDriver instance.
     */
    @Inject
    public RatesPage(WebDriver driver) {
        super(driver);
    }
}
