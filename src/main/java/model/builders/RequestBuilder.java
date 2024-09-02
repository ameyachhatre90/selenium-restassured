package model.builders;

import io.restassured.http.Headers;
import io.restassured.http.Method;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

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
