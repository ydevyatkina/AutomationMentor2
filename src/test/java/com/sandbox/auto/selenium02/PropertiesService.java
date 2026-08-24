package com.sandbox.auto.selenium02;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesService {
    private final Properties properties;

    public PropertiesService() {
        properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        String propFileName = "test.properties";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public Properties getProperties() {
        return properties;
    }
}
