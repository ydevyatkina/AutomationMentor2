package com.sandbox.auto.selenium05.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTableSteps extends AbstractBaseStep {
    public static final String EXPECTED_USER_TABLE_TITLE = "User Table";

    @When("I click on Service button in Header")
    public void clickOnServiceButton() {
        homePage.clickOnServiceButton();
    }

    @When("I click on User Table button in Service dropdown")
    public void clickOnUserTableButton() {
        homePage.clickOnUserTablePage();
    }

    @Then("User Table page should be opened")
    public void checkUserTablePageTitle() {
        assertThat(userTablePage.getTitle()).isEqualTo(EXPECTED_USER_TABLE_TITLE);
    }

    @Then("{int} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void checkDropdownsNumber(int expectedDropdownNumber) {
        assertThat(userTablePage.countDropdowns()).isEqualTo(expectedDropdownNumber);
    }

    @Then("{int} Usernames should be displayed on Users Table on User Table Page")
    public void checkUsersNumber(int expectedUsersNumber) {
        assertThat(userTablePage.countUsers()).isEqualTo(expectedUsersNumber);
    }

    @Then("{int} Description texts under images should be displayed on Users Table on User Table Page")
    public void checkDescriptionsNumber(int expetedDescNumber) {
        assertThat(userTablePage.countDescriptions()).isEqualTo(expetedDescNumber);
    }

    @Then("{int} checkboxes should be displayed on Users Table on User Table Page")
    public void checkCheckboxesNumber(int expectedCheckboxesNumber) {
        assertThat(userTablePage.countCheckboxes()).isEqualTo(expectedCheckboxesNumber);
    }

    @Then("User table should contain following values:")
    public void checkTableContent(DataTable dataTable) {
        List<List<String>> expectedTable = dataTable.asLists(String.class);
        List<List<String>> tableWithoutHeader = new ArrayList<>(expectedTable);
        tableWithoutHeader.remove(0);
        assertThat(userTablePage.getUserTableData()).isEqualTo(tableWithoutHeader);
    }

    @Then("droplist should contain values in column Type for user {string}")
    public void checkDroplistContent(String userName, DataTable dataTable) {
        List<String> expectedTable = dataTable.asList(String.class);
        List<String> tableWithoutHeader = new ArrayList<>(expectedTable);
        tableWithoutHeader.remove(0);
        assertThat(userTablePage.getDropdownValuesForUser(userName)).isEqualTo(tableWithoutHeader);
    }

    @When("I select vip checkbox for {string}")
    public void selectVipCheckbox(String userName) {
        userTablePage.selectVipCheckboxForUser(userName);
    }

    @Then("{int} log row has {string} text in log section")
    public void verifyLogMessage(int logIndex, String expectedLogMessage) {
        List<String> logMessages = userTablePage.getLogRecords();
        String actualLogMessage = logMessages.get(logIndex - 1);
        assertThat(logMessages).hasSize(logIndex);
        assertThat(actualLogMessage).contains(expectedLogMessage);
    }
}
