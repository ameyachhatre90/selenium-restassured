package tests.api;

import com.google.inject.Inject;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import model.builders.RequestBuilder;
import org.testng.annotations.Test;
import utils.Constants;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.testng.Assert.assertTrue;

public class PersonalInfoCreditCardTests extends TestBase {

    /**
     *  Injects an instance of RestUtils into this class.
     */
    @Inject
    private RestUtils restUtils;

    @Story("API Tests")
    @Test(testName = "Personal Information Credit Card - Success")
    void personalInformationCreditCardSuccess() {
        String cookieId = UUID.randomUUID().toString();
        Map<String, String> query = new HashMap<>();
        query.put("cookieId", cookieId);
        query.put("event", "landing_page");

        RequestBuilder requestBuilder = RequestBuilder.builder()
                .headers(headers)
                .body(query)
                .method(Method.GET)
                .basePath(configuration.getProperty(Constants.UPGRADE_URL))
                .uriPath(configuration.getProperty(Constants.PERSONAL_INFO_PATH))
                .build();
        restUtils.restCall(requestBuilder).isOK();
        restUtils.getResponse().assertThat().contentType(ContentType.HTML);
        assertTrue(restUtils.getResponseBodyAsString().contains("<title>Upgrade - Affordable Online Personal Loans</title>"));
    }

    @Story("API Tests")
    @Test(testName = "Login - Unauthorised")
    void loginUnauthorised() {
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .headers(headers)
                .method(Method.GET)
                .basePath(configuration.getProperty(Constants.API_URL))
                .uriPath(configuration.getProperty(Constants.LOGIN_PATH))
                .build();
        restUtils.restCall(requestBuilder).isUnauthorised();
    }
}
