package tests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Configuration;
import config.DIModule;
import io.restassured.http.Headers;
import model.builders.HeadersBuilder;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import utils.FileUtils;
import utils.ResourceUtils;

import java.io.IOException;

import static org.testng.Assert.fail;

/**
 * This class serves as the base class for all API tests. It provides common functionalities
 * like setting up headers, accessing configuration, reading test data from JSON files, and basic test reporting.
 *
 */
@Guice(modules = DIModule.class)
public class TestBase {
    /**
     * The base headers used in API requests.
     */
    protected Headers headers;

    /**
     * The configuration object containing test configuration details.
     */
    protected Configuration configuration;

    /**
     * This method is executed before each test case.
     * It performs the following tasks:
     *  - Sets the current TestResult for reporting.
     *  - Logs the start of the test case.
     *  - Creates a default set of headers using HeadersBuilder.
     *  - Reads the configuration object.
     *
     * @param result The TestResult object containing information about the test execution.
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result) {
        Reporter.setCurrentTestResult(result);
        Reporter.log("Test started: " + result.getMethod().getMethodName(), true);
        headers = HeadersBuilder.builder().setBaseHeaders().build();
        configuration = Configuration.getConfiguration();
    }

    /**
     * This method is executed after each test case.
     * It logs the completion of the test case.
     *
     * @param result The TestResult object containing information about the test execution.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        Reporter.setCurrentTestResult(result);
        Reporter.log("Test completed successfully: " + result.getMethod().getMethodName(), true);
    }


    /**
     * Reads test data from a JSON file located in the "input" directory of the test resources.
     *
     * @param <T>      The type of the object to be deserialized from the JSON file.
     * @param filePath  The relative path of the JSON file within the "input" directory.
     * @param type     The class representing the type of the object to be deserialized.
     * @return         An instance of the specified type populated from the JSON data.
     * @throws IOException If there is an error reading the file or deserializing the data.
     */
    protected <T> T getTestDataFromJsonFile(final String filePath, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String data = FileUtils.readAllLines(
                    ResourceUtils.getResourceFilePathAbsPath("input/" + filePath));
            return mapper.readValue(data, type);
        } catch (IOException exception) {
            fail("Test failed while reading the data from the input file. Exception: " + exception.getMessage());
        }
        return null;
    }
}
