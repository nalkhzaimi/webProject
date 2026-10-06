@test
Feature: Main Search functionality

  Scenario Outline: Search by various criteria and verify results (the small search box)
    Given Iam on the homepage again
    When I enter "<searchText>" in the search box
    Then I verify that the item or message "<expectedResult>" is displayed
    And I Assert All
    Examples:
      | searchText            | expectedResult        |
      | PFB1830935005A        | Specific Item         |
      | NAE554010             | Specific Item         |
      | CR04N01M11245263322   | Specific Item         |
      | abc                   | No data available     |
