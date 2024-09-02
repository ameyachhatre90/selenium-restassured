package tests.ui.pages;

import com.google.inject.Inject;
import factory.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model (POM) of the Sign-In Page.
 * <p>
 * All elements of the page that will be used must be instantiated with their respective xpath.
 */
public class SignInPage extends PageBase {

    private String emailAddressBoxXpath = "//input[@aria-labelledby='username-2']";
    //	private String passwordBoxCSSName = "//input[@name='password']";
    private String passwordBoxName = "password";
    private String signInButton = "//button[@type='submit']";

    /**
     * Constructor of the SignInPage class. Initializes the Page Factory objects.
     *
     * @param driver The WebDriver instance.
     */
    @Inject
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    // Locate the username field and enter the value
    public void enterUsername(String username) {
        WebElement usernameField = seleniumUtils.waitForElementToBeVisibleByXpath(driver, emailAddressBoxXpath, DEFAULT_WAIT_TIME);
        assert usernameField != null;
        usernameField.sendKeys(username);
    }

    // Locate the password field and enter the value
    public void enterPassword(String password) {
        WebElement passwordField = seleniumUtils.waitForElementToBeVisibleByName(driver, passwordBoxName, DEFAULT_WAIT_TIME);
        assert passwordField != null;
        passwordField.sendKeys(password);
    }

    // Locate the submit button and click it
    public void clickSignInButton() {
        WebElement SignInButton = seleniumUtils.waitForElementToBeClickable(driver, signInButton, DEFAULT_WAIT_TIME);
        assert SignInButton != null;
        SignInButton.click();
    }
}
