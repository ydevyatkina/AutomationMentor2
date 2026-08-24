Feature: Testing VIP checkbox functionality on the User Table Page

  As a user, I want to check and verify the vip checkbox functionality and logs.

  Scenario: Select vip checkbox for a user and verify log entry
    Given I open JDI GitHub site
    And I login as user Roman Iovlev
    And I click on Service button in Header
    And I click on User Table button in Service dropdown

    When I select vip checkbox for "Sergey Ivan"
    Then 1 log row has "Vip: condition changed to true" text in log section
