package factory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import config.Configuration;
import enums.Browser;
import exceptions.StopTestException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Class responsible to handle the WebDrivers
 */
public class BrowserProvider implements Provider<WebDriver> {

    /**
     * Injected configuration object used to retrieve browser and grid settings.
     */
    @Inject
    private Configuration configuration;

    /**
     * ThreadLocal variable to store the WebDriver instance for the current thread.
     */
    public ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Provides a new WebDriver instance based on configuration settings.
     *
     * @return A new WebDriver instance.
     */
    @Override
    public WebDriver get() {
        Browser browserName;
        switch (configuration.getProperty("browser")) {
            case "chrome" -> {
                browserName = Browser.CHROME;
            }
            case "firefox" -> {
                browserName = Browser.FIREFOX;
            }
            default -> {
                browserName = Browser.IE;
            }
        }
        try {
            if (configuration.isGridEnabled()) {
                driverThreadLocal.set(createDriver(configuration.getGridHubServerDetails(), browserName, new DesiredCapabilities()));
            } else {
                driverThreadLocal.set(createDriver(browserName, new DesiredCapabilities()));
            }
        } catch (StopTestException ex) {
            ex.printStackTrace();
        }
        driverThreadLocal.get().manage().window().maximize();
        return driverThreadLocal.get();
    }

    /**
     * Create a driver with the given capabilities.
     *
     * @param browser      The desired browser (e.g., CHROME, FIREFOX).
     * @param capabilities The desired capabilities for the browser.
     * @return A WebDriver instance.
     */
    public static WebDriver createDriver(Browser browser, DesiredCapabilities capabilities) {
        capabilities.setBrowserName(browser.toString().toLowerCase());
        return browser.initialize(capabilities);
    }

    /**
     * Create a driver with default capabilities.
     *
     * @param browser The desired browser (e.g., CHROME, FIREFOX).
     * @return A WebDriver instance.
     */
    public static WebDriver createDriver(Browser browser) {
        return createDriver(browser, new DesiredCapabilities());
    }

    /**
     * Create a remote driver given the hub URL and capabilities
     *
     * @param hubUrl       The URL of the Selenium Grid hub.
     * @param browser      The desired browser (e.g., CHROME, FIREFOX).
     * @param capabilities The desired capabilities for the browser.
     * @return A RemoteWebDriver instance.
     */
    public static RemoteWebDriver createDriver(URL hubUrl, Browser browser, DesiredCapabilities capabilities) {
        capabilities.setBrowserName(browser.toString().toLowerCase());
        return new RemoteWebDriver(hubUrl, capabilities);
    }

    /**
     * Create a remote driver given the hub URL using the default capabilities
     *
     * @param hubUrl  The URL of the Selenium Grid hub.
     * @param browser The desired browser (e.g., CHROME, FIREFOX).
     * @return A RemoteWebDriver instance.
     */
    public static RemoteWebDriver createDriver(URL hubUrl, Browser browser) {
        return createDriver(hubUrl, browser, new DesiredCapabilities());
    }
}
