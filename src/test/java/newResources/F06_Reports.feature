@test
Feature: Reports Page

  Scenario: Search by 'tpl' and verify tpl_failure_report appears in results
    Given I am on the reports page
    When I search for "tpl" in the reports search box
    Then the search results should contain "tpl_failure_report"
    And I apply reports assertions

  Scenario: Clearing the search box restores the full results list
    Given I am on the reports page
    When I search for "tpl" in the reports search box
    And I clear the reports search box
    Then the reports results list should be visible
    And I apply reports assertions

  Scenario: Non-matching search term hides tpl_failure_report from results
    Given I am on the reports page
    When I search for "zz_no_match_xyz" in the reports search box
    Then the search results should not contain "tpl_failure_report"
    And I apply reports assertions

  Scenario: Search is case-insensitive - uppercase 'TPL' also finds tpl_failure_report
    Given I am on the reports page
    When I search for "TPL" in the reports search box
    Then the search results should contain "tpl_failure_report"
    And I apply reports assertions

  Scenario: Open tpl_failure_report and verify erroneous shipment appears after generation
    Given I am on the reports page
    When I search for "tpl" in the reports search box
    And I click on the tpl_failure_report link
    And I click the reports generate button
    Then the report table should show an erroneous shipment
    And I apply reports assertions

  Scenario: Scroll to business_pickup_tasks_pending report and generate
    Given I am on the reports page
    And I scroll to and click the business_pickup_tasks_pending report
    And I click the reports generate button
    And I apply reports assertions

  Scenario: Generate cir_potential_user_assign report with country 'sa' and limit 100
    Given I am on the reports page
    And I scroll to and click the cir_potential_user_assign report
    And I enter "sa" in the cir country input
    And I enter "100" in the cir limit input
    And I click the reports generate button
    Then the report table header should be visible
    And I apply reports assertions

  Scenario: Download cir_potential_user_assign report as CSV
    Given I am on the reports page
    And I scroll to and click the cir_potential_user_assign report
    And I enter "sa" in the cir country input
    And I enter "100" in the cir limit input
    And I click the reports generate button
    Then the report table header should be visible
    And I click the cir download button
    Then a cir report csv should be downloaded
    And I apply reports assertions

  Scenario: Generate ageing session files report for test-a1 hub
    Given I am on the staging reports page
    When I click on the ageing session files report
    And I enter "test-a1" in the hub input field
    And I click the reports generate button
    And I apply reports assertions
