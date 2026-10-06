@test
Feature: Business Pickup Tasks

  Scenario: Test searching by task number
    Given I am on the business pickup tasks page
    When I search for task number BPTFA71774353808S
    Then I should see the task with number BPTFA71774353808S

  Scenario: Test downloading file from download button
    Given I am on the business pickup tasks page
    When I search for task number BPTFA71774353808S
    And I click the download button
    Then a file should be downloaded

  Scenario: Test selecting and deselecting checkboxes
    Given I am on the business pickup tasks page
    When I search for task number BPTFA71774353808S
    And  I select all checkboxes
    Then all checkboxes should be selected
    When I click to deselect all checkboxes
    Then no checkboxes should be selected

  Scenario: Testing that you can't cancel task with pending items
    Given I am on the business pickup tasks page
    When I search for task number BPTFA71774353808S
    Then I should see the task with number BPTFA71774353808S
    And I should not be able to cancel the task
    And I should see the error message pickup

  Scenario: Test negative scenario for search
    Given I am on the business pickup tasks page
    When I search for task number BPTFA71774353808S
    And I search for task number ABC
    Then I should see no tasks matching ABC
    And I apply Assertions

