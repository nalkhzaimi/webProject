@test
Feature: Selected Item Verification

  Scenario: User verifies client reference and request details after logging in
    Given the user is logged in
    When the status should be Pending
    And the type should be B2C Delivery
    And scroll down then assert packages
    And I return to the homepage
    And I should see the homepage logo
    Then I soft assert all