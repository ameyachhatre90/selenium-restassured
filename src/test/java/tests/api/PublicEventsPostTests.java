package tests.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.qameta.allure.Story;
import io.restassured.http.Method;
import model.PublicEventRequest;
import model.builders.RequestBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Constants;
import utils.FileUtils;
import utils.ResourceUtils;
import utils.RestUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class PublicEventsPostTests extends TestBase {

    /**
     *  Injects an instance of RestUtils into this class.
     */
    @Inject
    private RestUtils restUtils;

    @Story("API Tests")
    @Test(testName = "POST public event - Success", dataProvider = "getPublicEventData")
    void createPublicEventSuccess(PublicEventRequest publicEventRequest) {
//        publicEventRequest.setUrl("https://www.upgrade.com/funnel/personal-information-1/CREDIT_CARD/2000");
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .headers(headers)
                .method(Method.POST)
                .body(publicEventRequest)
                .basePath(configuration.getProperty(Constants.API_URL))
                .uriPath(configuration.getProperty(Constants.PUBLIC_EVENTS_PATH))
                .build();
        restUtils.restCall(requestBuilder).isOK().isSchemaMatching("schemas/postPublicEventSchema.json");
        assertNotNull(restUtils.getJsonResponse().getString("cookieId"),
                "Post public event cookieId value is null in the response");
    }

    @Story("API Tests")
    @Test(testName = "POST public event - Bad Request")
    void createPublicEventBadRequest() {
        PublicEventRequest request = getTestDataFromJsonFile("postPublicEventUnhappy.json",
                PublicEventRequest.class);
        request.setCookieId(UUID.randomUUID().toString());
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .headers(headers)
                .method(Method.POST)
                .body(request)
                .basePath(configuration.getProperty(Constants.API_URL))
                .uriPath(configuration.getProperty(Constants.PUBLIC_EVENTS_PATH))
                .build();
        restUtils.restCall(requestBuilder).isBadRequest();
        assertTrue(restUtils.getJsonResponse().getString("message").contains("event: null"),
                "Post public event error message value is not as expected in the response");
    }

    //region Private Methods

    /**
     * Read and provide the JSON data to the test
     * @return PublicEventRequest test data
     * @throws IOException if an error occurs while reading or parsing the JSON file
     */
    @DataProvider
    private Object[][] getPublicEventData() throws IOException {
        String data = FileUtils
                .readAllLines(ResourceUtils.getResourceFilePathAbsPath("input/postPublicEvent.json"));
        List<PublicEventRequest> publicEventRequests = new ObjectMapper().readValue(data, new TypeReference<>() {
        });
        return publicEventRequests.stream()
                .map(quoteData -> new Object[]{quoteData})
                .toArray(Object[][]::new);
    }

    //endregion
}
