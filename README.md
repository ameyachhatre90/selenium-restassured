# selenium-template-java

A Java implementation of a **Selenium Project** using the developing Factory Pattern to manage the WebDrivers, containing Utility Functions and using **TestNG** to execute the tests. This project intends to be in constant evolution and help other tester to create their own test framework using the best developing practices as possible. 

**Feel free to fork it and suggest any improvement!**


## The Factory Pattern

In class-based programming, the factory method pattern is a pattern that uses factory methods to deal with the problem of **creating objects without having to specify the exact class** of the object that will be created.

The purpose of using this pattern in this project is to help us to create the **WebDriver** object without worrying about the creation logic every time.

### The Browser Enum

This enum defines how each browser is initialized or instantiated.

```java
public abstract WebDriver initialize(DesiredCapabilities capabilities);
```

To handle a new browser, just create a new **Browser Enum field** and implement the **initialize** method that return a WebDriver.

```java
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
	}
```

## Instantiating a WebDriver

WebDriver will be created automatically whenever the web test(extending ```WebTestBase```) is called. We are initialising and closing the WebDriver using ```@BeforeMethod``` and ```@AfterMethod``` classes. 

We are using google Guice DI(```DIModule```) to create the driver. ```BrowserProvider``` class will handle the driver creation and closing after each test.

To use the **WebDriver**, we need to inject the **WebDriver** class where required. 

```java
@Inject
private WebDriver driver;
```

Page classes will also be created using guice DI and will need to inject them into the class where required. Page class constructor should have the **Inject** annotation.

```Page class constructor
@Inject
public HomePage(WebDriver driver) {
    super(driver);
}
```
**Inject Page Class**

```inject page class
@Inject
private HomePage homePage;
```

**Call page class methods**

```java
homePage.navigateToAboutUs();
```


## The Page Object Model (POM)

**Page Object Model** is a design pattern which has become popular in test automation for enhancing test maintenance and reducing code duplication. A page object is an object-oriented class that serves as an interface to a page of the system under test.

In this framework, **the Page Objects do not use the Page Factory** concept in order to let the project more flexible and also to avoid slowness.

### Creating a Page Object

All Page Object is class that extends the **PageBase** class. This class don't have much utility now, but let us add functionalities in the future, if necessary.

#### Declaring the Web Elements

The elements of the Page Object are **Strings** containing their **xpath**. In order to keep everything organized and easy to read, the elements must be declared following the pattern bellow:

Web Element Type|	Prefix|	Examples
----------------|-------------|----------
Text Box	|txt|	txtEmail , txtPassword
Button|	btn|	btnRegister , btnLogin
Drop down|	dd|	ddCountry , ddYear
Select Drop Down|	sdd|	sddMonth , sddYear
Check Box|	cb|	cbGender, cbSalaryRange
Header|	hdr|	hdrPrint, hdrUser
Table|	tbl|	tblBooks, tblProducts
Label|	lbl|	lblUserName, lblPassword
Image|	img|	imgProfile, imgCart

#### Example

```java
public class HomePage extends PageBase {
	private WebDriver driver;

	private String txtSearch = "//input[@name='q']";
	private String btnSearch = "//input[@name='btnK']";
```

#### Using the Web Elements

If we want to use the Web Elements out of the class or even inside of it, we must find them on the page. For this, we can create **get methods** for the elements we want to access. There are a bunch of **Utility Methods** to help us in this task, but it will be more explained later.

```java
/**
 * Get the WebElement of the 'txtSearch'.
 * 
 * @return
 */
public WebElement getTxtSearch() {
	return SeleniumUtils.waitForElement(driver, txtSearch);
}
```

It is common in some projects **do not give public access to the elements** in order to avoid messing the project, but **it depends on your purposes**. In some cases, it is better to **create methods to perform actions** inside the class, for example, if we need to search for something in page, is much better call a method giving the query we want, instead of doing it outside of the class.

```java

```

## Utility Classes

Utility Classes are very useful to provide reusable methods. The main utility class in this project is the **SeleniumUtils** one. This class intends to provide methods to help us to find elements, wait for certain conditions, manage checkboxes, radio buttons and more.

```java
SeleniumUtils.findElement(driver, xpath);
SeleniumUtils.findElements(driver, xpath);
SeleniumUtils.waitForElement(driver, xpath);
SeleniumUtils.waitForElementToBeClickable(driver, xpath);
SeleniumUtils.waitForElementToBeVisible(driver, xpath);
SeleniumUtils.waitForElementToBeInvisible(driver, xpath);
```




### To be continued...
