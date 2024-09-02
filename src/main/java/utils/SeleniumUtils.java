package utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import com.google.inject.Inject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This utility class provides helper methods for interacting with web elements using Selenium WebDriver.
 */
public class SeleniumUtils {
	private static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(10);
    private static final Logger log = LoggerFactory.getLogger(SeleniumUtils.class);

    private final AllureReporter report = new AllureReporter();

	/**
	 * Finds a WebElement based on the specified locator strategy and value.
	 *
	 * @param driver The WebDriver instance.
	 * @param locator The locator strategy (e.g., "xpath", "id", "name", "class", "css selector").
	 * @param locatorValue The value of the locator.
	 * @return The WebElement if found, otherwise null.
	 * @throws IllegalArgumentException If the locator strategy is invalid.
	 */
	public WebElement findElement(WebDriver driver, String locator, String locatorValue) {
		try {
			report.printReport("findElement");
            return switch (locator.toLowerCase()) {
                case "xpath" -> driver.findElement(By.xpath(locatorValue));
                case "id" -> driver.findElement(By.id(locatorValue));
                case "name" -> driver.findElement(By.name(locatorValue));
                case "class" -> driver.findElement(By.className(locatorValue));
                case "css selector" -> driver.findElement(By.cssSelector(locatorValue));
                default -> throw new IllegalArgumentException("Invalid locator strategy: " + locator);
            };
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}

	/**
	 * Try to find an element in the page by xpath. If the element is not found,
	 * null is returned.
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @return The WebElement if found, otherwise null.
	 */
	public WebElement findElement(WebDriver driver, String xpath) {
		try {
			report.printReport("findElement");
			return driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}

	/**
	 * Try to find elements in the page by xpath. If the elements are not found,
	 * null is returned.
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @return A list of WebElements if found, otherwise an empty list.
	 */
	public List<WebElement> findElements(WebDriver driver, String xpath) {
		try {
			return driver.findElements(By.xpath(xpath));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be visible on the screen, given a maximum time to wait
	 *
	 * @param driver The WebDriver instance.
	 * @param name The name expression.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return The WebElement if found and visible within the timeout, otherwise null.
	 */
	public WebElement waitForElementToBeVisibleByName(WebDriver driver, String name, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}

	/**
	 * Wait for an element to be visible on the screen, given a maximum time to wait
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return The WebElement if found and visible within the timeout, otherwise null.
	 */
	public WebElement waitForElementToBeVisible(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}

	/**
	 * Wait for an element to be visible on the screen, given a maximum time to wait
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return The WebElement if found and visible within the timeout, otherwise null.
	 */
	public WebElement waitForElementToBeVisibleByXpath(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}

	/**
	 * Wait for an element to be visible on the screen, given a maximum time to wait
	 *
	 * @param driver The WebDriver instance.
	 * @param id The id expression.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return The WebElement if found and visible within the timeout, otherwise null.
	 */
	public WebElement waitForElementToBeVisibleById(WebDriver driver, String id, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be visible on the screen, using the DEFAULT_WAIT_TIME
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @return The WebElement if found and visible within the DEFAULT_WAIT_TIME, otherwise null.
	 */
	public WebElement waitForElementToBeVisible(WebDriver driver, String xpath) {
		return waitForElementToBeVisible(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for an element to be present in the DOM, given a maximum time to wait
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return The WebElement if found within the timeout, otherwise null.
	 */
	public WebElement waitForElement(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be present in the DOM, using the DEFAULT_WAIT_TIME
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @return The WebElement if found within the DEFAULT_WAIT_TIME, otherwise null.
	 */
	public WebElement waitForElement(WebDriver driver, String xpath) {
		return waitForElement(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for at least one element to be present in the DOM, given a maximum time to wait
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return A list of WebElements if at least one element is found within the timeout, otherwise an empty list.
	 */
	public List<WebElement> waitForElements(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}
	
	/**
	 * Wait for at least one element to be present in the DOM, using the DEFAULT_WAIT_TIME
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @return A list of WebElements if at least one element is found within the DEFAULT_WAIT_TIME, otherwise an empty list.
	 */
	public List<WebElement> waitForElements(WebDriver driver, String xpath) {
		return waitForElements(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for an element to be clickable, given a maximum time to wait
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return The WebElement if found and clickable within the timeout, otherwise null.
	 */
	public WebElement waitForElementToBeClickable(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {
			log.error("e: ", e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be clickable, using the DEFAULT_WAIT_TIME
	 *
	 * @param driver The WebDriver instance.
	 * @param xpath The XPath expression.
	 * @return The WebElement if found and clickable within the DEFAULT_WAIT_TIME, otherwise null.
	 */
	public WebElement waitForElementToBeClickable(WebDriver driver, String xpath) {
		return waitForElementToBeClickable(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for a certain condition to occur for up to DEFAULT_WAIT_TIME
	 * <p>
	 * Usage: waitUntil(driver, ExpectedConditions.elementToBeClickable(By.xpath("/input[@id='q']")))
	 *
	 * @param driver The WebDriver instance.
	 * @param condition The condition to wait for.
	 * @return The result of the condition if it occurs within the timeout, otherwise null.
	 */
	public <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition) {
		return waitUntil(driver, condition, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for a certain condition to occur for up to a given time in seconds
	 *
	 * @param driver The WebDriver instance.
	 * @param condition The condition to wait for.
	 * @param timeOutInSeconds The maximum time to wait in seconds.
	 * @return The result of the condition if it occurs within the timeout, otherwise null.
	 */
	public <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(condition);
	}

	/**
	 * Implicitly Waits for a condition to be met within a specified timeout using implicit wait.
	 *
	 * @param driver The WebDriver instance.
	 * @param timeOutInSeconds The maximum time to wait for the condition to be met.
	 */
	public void implicitWaitUntil(WebDriver driver, Duration timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds);
	}

	/**
	 * Fluently Waits for an element to be visible within a specified timeout and
	 * polling interval using fluent wait.
	 *
	 * @param driver The WebDriver instance.
	 * @param element The WebElement to wait for.
	 * @param timeOutInSeconds The maximum time to wait for the element to be visible.
	 * @param pollingEveryInSeconds The interval at which the condition should be checked.
	 * @return The visible WebElement.
	 */
	public WebElement fluentWaitForElement(WebDriver driver, WebElement element, Duration timeOutInSeconds, Duration pollingEveryInSeconds) {
		FluentWait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(timeOutInSeconds)
				.pollingEvery(pollingEveryInSeconds)
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				element);
	}

	public void click(WebDriver driver, String element) {
		int maxTries = 5;
		for (int i = 0; i <= maxTries; i++) {
			try {
				driver.findElement(By.xpath(element)).click();
				report.printReport("Click on button successful after " + (i + 1) + " attempt");
				return; // Exit the function if successful
			} catch (StaleElementReferenceException e) {
				// Handle stale element reference by retrying
				driver.findElement(By.xpath(element)).click();
				report.printReport("Stale element detected. Retrying click (attempt " + (i + 1) + ")...");
			} catch (NoSuchElementException e) {
				// Handle element not found
				report.printReport("Element not found: " + element);
				return; // Exit the function if element is not found
			}
		}

		// Handle unsuccessful clicks after retries
		report.printReport("Failed to click on button after " + maxTries + " attempts.");
		// Consider throwing a custom exception or logging an error
	}

	public static byte[] captureScreenshot(WebDriver driver) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		return screenshot.getScreenshotAs(OutputType.BYTES);
	}

}
