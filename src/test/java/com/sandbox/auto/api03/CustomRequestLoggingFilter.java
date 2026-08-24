package com.sandbox.auto.api03;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomRequestLoggingFilter implements Filter {
    private static final boolean SHOW_URL_ENCODED_URI = true;
    private final LogDetail logDetail;
    private final PrintStream stream;
    private final boolean shouldPrettyPrint;
    private final boolean showUrlEncodedUri;
    private final Set<String> blacklistedHeaders;
    private final Set<String> blacklistedQueryParams;
    private final Set<LogDetail> logDetailSet = new HashSet<>();

    public CustomRequestLoggingFilter() {
        this(Collections.emptySet());
    }

    public CustomRequestLoggingFilter(Set<String> blacklistedQueryParams) {
        this.blacklistedQueryParams = blacklistedQueryParams;
        this.logDetail = LogDetail.ALL;
        this.stream = System.out;
        this.shouldPrettyPrint = true;
        this.showUrlEncodedUri = SHOW_URL_ENCODED_URI;
        this.blacklistedHeaders = Collections.emptySet();
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec, FilterContext ctx) {
        FilterableRequestSpecification loggingRequestSpec = (FilterableRequestSpecification) RestAssured
                .given().spec(requestSpec);
        for (String param : blacklistedQueryParams) {
            loggingRequestSpec.removeQueryParam(param);
        }

        String uri = loggingRequestSpec.getURI();
        if (!showUrlEncodedUri) {
            uri = UrlDecoder.urlDecode(uri, Charset.forName(loggingRequestSpec.getConfig()
                    .getEncoderConfig().defaultQueryParameterCharset()), true);
        }
        if (logDetailSet.isEmpty()) {
            RequestPrinter.print(loggingRequestSpec, loggingRequestSpec.getMethod(), uri,
                    logDetail, blacklistedHeaders, stream, shouldPrettyPrint);
        } else {
            RequestPrinter.print(loggingRequestSpec, loggingRequestSpec.getMethod(), uri,
                    logDetailSet, blacklistedHeaders, stream, shouldPrettyPrint);
        }

        return ctx.next(requestSpec, responseSpec);
    }
}
