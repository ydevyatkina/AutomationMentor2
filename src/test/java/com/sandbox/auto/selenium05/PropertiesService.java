package com.sandbox.auto.selenium05;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesService {
    protected static Properties properties;

    static {
        properties = loadProperties();
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        String propFileName = "test.properties";
        try (InputStream inputStream = PropertiesService.class.getClassLoader().getResourceAsStream(propFileName)) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Properties getProperties() {
        return properties;
    }
}
