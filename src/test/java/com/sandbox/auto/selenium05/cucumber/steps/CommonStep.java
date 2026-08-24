package com.sandbox.auto.selenium05.cucumber.steps;

import com.sandbox.auto.selenium05.PropertiesService;
import io.cucumber.java.en.Given;

public class CommonStep extends AbstractBaseStep {
    protected static final String uri = PropertiesService.getProperties().get("uriJDI").toString();
    protected static final String username = PropertiesService.getProperties().get("username").toString();
    protected static final String userPassword = PropertiesService.getProperties().get("password").toString();

    @Given("I open JDI GitHub site")
    public void openHomePage() {
        homePage.openPage(uri);
    }

    @Given("I login as user Roman Iovlev")
    public void loginHomePage() {
        homePage.login(username, userPassword);
    }

}
