package com.sandbox.auto.api03;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SpecificationsService {
    private final Properties properties;
    private final Set<String> ignoredLogParams = Set.of("key", "token");

    public SpecificationsService(Properties properties) {
        this.properties = properties;
    }

    public RequestSpecification requestSpec() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", properties.get("key").toString());
        parameters.put("token", properties.get("token").toString());
        RestAssured.filters(new CustomRequestLoggingFilter(ignoredLogParams), new ResponseLoggingFilter());

        return new RequestSpecBuilder()
                .setBaseUri(properties.get("uriAPI").toString())
                .setContentType(ContentType.JSON)
                .addQueryParams(parameters)
                .build();
    }
}

