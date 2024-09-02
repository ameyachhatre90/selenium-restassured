package tests.api;

import com.google.inject.Inject;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import model.builders.RequestBuilder;
import org.testng.annotations.Test;
import utils.Constants;
import utils.RestUtils;

import static org.testng.Assert.assertNotNull;

public class RefinanceCreditCardGetTests extends TestBase {

    @Inject
    private RestUtils restUtils;

    @Story("API Tests")
    @Test(testName = "Personal Information Credit Card - Success")
    void personalInformationCreditCardSuccess() {
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .headers(headers)
                .method(Method.GET)
                .basePath(configuration.getProperty(Constants.UPGRADE_URL))
                .uriPath(configuration.getProperty(Constants.REFINANCE_CREDIT_CARD_PAGE_DATA_PATH))
                .build();
        restUtils.restCall(requestBuilder).isOK();
        restUtils.getResponse().assertThat().contentType(ContentType.JSON);
        restUtils.restCall(requestBuilder).isOK().isSchemaMatching("schemas/refinanceCreditCardsPageData.json");
        assertNotNull(restUtils.getJsonResponse().getString("componentChunkName"),
                "Post public event componentChunkName value is null in the response");
    }

}
