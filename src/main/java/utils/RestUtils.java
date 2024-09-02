package utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.builders.RequestBuilder;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

/**
 * The type Rest utils.
 */
public class RestUtils {

    @Inject
    @Named("apiUrl")
    private String apiUrl;

    private ValidatableResponse validatableResponse;

    /**
     * Rest call rest utils.
     *
     * @param requestBuilder the request builder
     * @return the rest utils
     */
    public RestUtils restCall(final RequestBuilder requestBuilder) {
        RequestSpecification rs = given()
                .baseUri(requestBuilder.getBasePath())
                .log().all()
                .headers(requestBuilder.getHeaders());

        if (MapUtils.isNotEmpty(requestBuilder.getQueryParams())) {
            for (Map.Entry<String, String> entry : requestBuilder.getQueryParams().entrySet()) {
                rs.queryParam(entry.getKey(), entry.getValue());
            }
        }

        if (ObjectUtils.isNotEmpty(requestBuilder.getBody())) {
            rs.body(requestBuilder.getBody());
        }

        validatableResponse = rs.request(requestBuilder.getMethod(), requestBuilder.getUriPath())
                .then()
                .log().all();

        return this;
    }

    /**
     * Gets response.
     *
     * @return the response
     */
    public ValidatableResponse getResponse() {
        return validatableResponse;
    }

    /**
     * Gets response body as string.
     *
     * @return the response body as string
     */
    public String getResponseBodyAsString() {
        return validatableResponse.extract().body().asString();
    }

    /**
     * Is ok rest utils.
     *
     * @return the rest utils
     */
    public RestUtils isOK() {
        validatableResponse.assertThat().statusCode(is(200));
        return this;
    }

    /**
     * Is bad request rest utils.
     *
     * @return the rest utils
     */
    public RestUtils isBadRequest() {
        validatableResponse.assertThat().statusCode(is(400));
        return this;
    }

    /**
     * Is unauthorised rest utils.
     *
     * @return the rest utils
     */
    public RestUtils isUnauthorised() {
        validatableResponse.assertThat().statusCode(is(401));
        return this;
    }

    /**
     * Is internal server error rest utils.
     *
     * @return the rest utils
     */
    public RestUtils isInternalServerError() {
        validatableResponse.assertThat().statusCode(is(500));
        return this;
    }

    /**
     * Is created rest utils.
     *
     * @return the rest utils
     */
    public RestUtils isCreated() {
        validatableResponse.assertThat().statusCode(is(201));
        return this;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return validatableResponse.extract().response().getStatusCode();
    }

    /**
     * Gets json response.
     *
     * @return the json response
     */
    public JsonPath getJsonResponse() {
        return validatableResponse.extract().jsonPath();
    }

    /**
     * Is schema matching rest utils.
     *
     * @param schemaPath the schema path
     * @return the rest utils
     */
    public RestUtils isSchemaMatching(final String schemaPath) {
        validatableResponse.assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
        return this;
    }
}

