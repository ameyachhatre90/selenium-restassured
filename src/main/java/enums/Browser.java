package enums;

import factory.BrowserProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This enum defines how to initialize each browser driver.
 */
public enum Browser {
    FIREFOX {
        @Override
        public WebDriver initialize(DesiredCapabilities capabilities) {
            synchronized (BrowserProvider.class) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.merge(capabilities);
                return new FirefoxDriver(options);
            }
        }
    },

    CHROME {
        @Override
        public WebDriver initialize(DesiredCapabilities capabilities) {
            synchronized (BrowserProvider.class) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.merge(capabilities);
                return new ChromeDriver(options);
            }
        }
    },

    IE {
        @Override
        public WebDriver initialize(DesiredCapabilities capabilities) {
            synchronized (BrowserProvider.class) {
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions options = new InternetExplorerOptions();
                options.merge(capabilities);
                return new InternetExplorerDriver(options);
            }
        }
    };

    /**
     * Method to be implemented by each Browser Enum.
     *
     * @param capabilities The desired capabilities for the browser.
     * @return A WebDriver instance.
     */
    public abstract WebDriver initialize(DesiredCapabilities capabilities);

    @Override
    public String toString() {
        return switch (this) {
            case FIREFOX -> "FIREFOX";
            case CHROME -> "CHROME";
            case IE -> "IE";
            default -> throw new IllegalArgumentException();
        };
    }
}
