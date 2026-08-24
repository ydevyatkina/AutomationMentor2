package com.sandbox.auto.selenium05.cucumber.steps;

import com.sandbox.auto.selenium05.DifferentElementsPage;
import com.sandbox.auto.selenium05.DriverSingleton;
import com.sandbox.auto.selenium05.HomePage;
import com.sandbox.auto.selenium05.UserTablePage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractBaseStep {
    protected HomePage homePage;
    protected DifferentElementsPage differentElementsPage;
    protected UserTablePage userTablePage;
    protected WebDriver driver = DriverSingleton.getDriver();

    public AbstractBaseStep() {
        homePage = new HomePage(driver);
        differentElementsPage = new DifferentElementsPage(driver);
        userTablePage = new UserTablePage(driver);
    }
}
