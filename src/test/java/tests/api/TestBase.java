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
 * The type Test base.
 */
@Guice(modules = DIModule.class)
public class TestBase {
    protected Headers headers;

    /**
     * The Configuration.
     */
    protected Configuration configuration;

    /**
     * Before method
     *
     * @param result the result
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result) {
        Reporter.setCurrentTestResult(result);
        Reporter.log("Test started: " + result.getMethod().getMethodName(), true);
        headers = HeadersBuilder.builder().setBaseHeaders().build();
        configuration = Configuration.getConfiguration();
    }

    /**
     * After method.
     *
     * @param result the result
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        Reporter.setCurrentTestResult(result);
        Reporter.log("Test completed successfully: " + result.getMethod().getMethodName(), true);
    }


    /**
     * Gets test data from json file.
     *
     * @param <T>      the type parameter
     * @param filePath the file path
     * @param type     the type
     * @return the test data from json file
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
