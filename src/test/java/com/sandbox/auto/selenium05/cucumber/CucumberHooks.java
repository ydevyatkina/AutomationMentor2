package com.sandbox.auto.selenium05.cucumber;

import com.sandbox.auto.selenium05.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class CucumberHooks {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @After
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
