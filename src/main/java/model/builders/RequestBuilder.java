package model.builders;

import io.restassured.http.Headers;
import io.restassured.http.Method;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * This builder class helps create a request object for REST Assured requests.
 * It provides methods to define various aspects of the request.
 * Reduces boilerplate with Builder and Getter.
 */
@Builder
@Getter
public class RequestBuilder {
    private final String basePath;
    private final String uriPath;
    private final String url;
    private final Headers headers;
    private final Method method;
    private final Object body;
    private final Map<String, String> queryParams;
}
