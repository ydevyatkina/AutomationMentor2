Feature: Testing Different Elements Page functionality

  As a user
  I want to interact with the Different Elements Page on the website
  So that I can ensure the proper functioning and logs of element interactions

  Scenario: Interact with Different Elements Page and verify logs
    Given I open JDI GitHub site
    And I login as user Roman Iovlev

    When I navigate to Service -> Different Elements Page through the header menu
    And I select the checkboxes Water and Wind
    And I select the radio button Selen
    And I select the Yellow element in the dropdown
    Then there should be log rows for each interaction with corresponding values



