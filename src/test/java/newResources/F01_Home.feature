@test
Feature: Homepage Login and Verification
  Background: User logs into the control app
    Given the user navigates to the control app

  Scenario: Verify the hub menu
    When the user logs in and select TEST-A1
    Then the hub menu should be visible and assert that it is TEST_A1
    And complete first soft assertion

  Scenario: Verify the service type
    When user is in home page
    Then the service should be B2C Delivery
    And complete second soft assertion

  Scenario: Verify the home status
    When user is in home page three
    Then the home status should be Pending
    And complete third soft assertion

  Scenario: Verify the leg type
    When user is in home page four
    Then the leg Type should be Delivery
    And complete fourth soft assertion

  Scenario: Verify the reason
    When user is in home page five
    Then the reason should be rescheduled
    And complete fifth soft assertion

  Scenario: Verify the country
    When user is in home page six
    Then the country should be AE
    And complete sixth soft assertion

  Scenario: Verify the hub sector
    When user is in home page seven
    Then the hub sector should be TEST-A1-S
    And complete seventh soft assertion

  Scenario: Verify the pickup type
    When user is in home page eight
    Then the pickup type should be Regular
    And complete eight soft assertion

  Scenario: Reset all filters
    When user is in home page reset
    Then the user resets all filters
    And complete reset soft assertion

  Scenario: User clicks on the result item
    When user is in home page nine
    And the user clicks on the result item
    And complete ninth soft assertion