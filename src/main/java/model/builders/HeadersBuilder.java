package model.builders;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import lombok.Builder;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Builder
public class HeadersBuilder {

    @Singular
    private final List<Header> headers;

    public static class HeadersBuilderBuilder {

        /**
         * Adds the base headers (Accept and Content-Type) to the list of headers.
         *
         * @return This builder instance for method chaining.
         */
        public HeadersBuilderBuilder setBaseHeaders () {
            headers = new ArrayList<>();
            headers.add(new Header("Accept", "application/json"));
            headers.add(new Header("Content-Type", "application/json"));
            return this;
        }

        /**
         * Adds a custom header to the list of headers.
         *
         * @param key   The header key.
         * @param value The header value.
         * @return This builder instance for method chaining.
         */
        public HeadersBuilderBuilder setHeader(final String key, final String value) {
            headers.add(new Header(key, value));
            return this;
        }

        /**
         * Builds a new Headers object from the collected headers.
         *
         * @return A new Headers object containing the collected headers.
         */
        public Headers build() {
            return new Headers(headers);
        }
    }
}
