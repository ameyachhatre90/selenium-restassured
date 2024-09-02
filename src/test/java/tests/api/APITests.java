package tests.api;

import com.google.inject.Inject;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import jdk.jfr.Enabled;
import model.LoginRequest;
import model.builders.HeadersBuilder;
import model.builders.RequestBuilder;
import org.testng.annotations.Test;
import utils.AllureReporter;
import utils.Constants;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class APITests extends TestBase {
    private static final AllureReporter report = new AllureReporter();

    @Inject
    private RestUtils restUtils;

    /**
     * This method gets the Details of a Specific Movie
     */
    @Story("API Tests")
    @Test
    public void testUpgradeHomepageResponseCode() {
        // Send GET request to Upgrade homepage
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .headers(headers)
                .method(Method.GET)
                .basePath(configuration.getProperty(Constants.UPGRADE_URL))
                .uriPath("")
                .build();
        restUtils.restCall(requestBuilder).isOK().getResponse().assertThat().contentType(ContentType.HTML);
    }

//    @Test
//    public void testSuccessfulLogin() {
//        LoginRequest loginRequest = LoginRequest.builder()
//                .username("valid_username")
//                .password("valid_password")
//                .build();
//
//        RequestBuilder requestBuilder = RequestBuilder.builder()
//                .headers(headers)
//                .method(Method.POST)
//                .body(loginRequest)
//                .basePath(configuration.getProperty(Constants.UPGRADE_URL))
//                .uriPath(configuration.getProperty(Constants.LOGIN_PATH))
//                .build();
//        restUtils.restCall(requestBuilder).isOK();
//    }

    /**
     * Test Debt Consolidation loan for $2000
     */
    @Test
    public void testDebtConsolidation() {
        testLoan("DEBT_CONSOLIDATION", "2000");
    }

    @Test
    public void testCreditCard() {
        testLoan("CREDIT_CARD", "2000");
    }

    @Test
    public void testSmallBusiness() {
        testLoan("SMALL_BUSINESS", "2000");
    }

    @Test
    public void testHomeImprovement() {
        testLoan("HOME_IMPROVEMENT", "2000");
    }

    @Test
    public void testLargePurchase() {
        testLoan("LARGE_PURCHASE", "2000");
    }

    @Test
    public void testOther() {
        testLoan("OTHER", "2000");
    }

    /**
     * Tests the loan feature for a given loan type and amount.
     *
     * @param loanType      The type of loan.
     * @param fundingAmount The loan amount.
     */
    public void testLoan(String loanType, String fundingAmount) {
        String cookieId = UUID.randomUUID().toString();
        // Define request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("cookieId", cookieId);
        requestBody.put("event", "landing_page");

        // Build the URL dynamically based on the loan type
        requestBody.put("url", String.format("https://www.upgrade.com/funnel/personal-information-1/%s/%s", loanType, fundingAmount));

        report.printReport(requestBody.toString());

        RequestBuilder requestBuilder = RequestBuilder.builder()
                .headers(headers)
                .method(Method.POST)
                .body(requestBody)
                .basePath(configuration.getProperty(Constants.API_URL))
                .uriPath(configuration.getProperty(Constants.PUBLIC_EVENTS_PATH))
                .build();
        restUtils.restCall(requestBuilder).isOK();
        assertEquals(cookieId, restUtils.getJsonResponse().getString("cookieId"), "Cookie id from response is not as expected");
    }

//    private static Headers getCommonHeaders() {
//        return HeadersBuilder.builder()
//                .setBaseHeaders()
//                .setHeader("host", "credapi.upgrade.com")
//                .build();
//    }
}
