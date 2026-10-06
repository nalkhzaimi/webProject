@test
Feature: Small second Search Functionality
  As a user
  I want to search for a term on the homepage
  So that I can see relevant search results

  Scenario: Search for a term on the homepage (main search)
    Given I am on the homepage
    When I enter a search term
    And I click on the search icon
    Then I should see search results
    And I return to the homepage again
    And I should see the homepage logo again
    Then I assert all results
