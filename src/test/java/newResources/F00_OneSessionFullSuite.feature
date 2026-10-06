@oneshot
Feature: Control App Full Workflow - Single Session

  Scenario: Complete Control App automation suite in one continuous browser session

    # ── 1. AUTHENTICATION & HUB SELECTION ─────────────────────────────────
    Given the user navigates to the control app
    When the user logs in and select TEST-A1
    Then the hub menu should be visible and assert that it is TEST_A1
    And complete first soft assertion

    # ── 2. HOME PAGE - SERVICE TYPE B2C ───────────────────────────────────
    When user is in home page
    Then the service should be B2C Delivery
    And complete second soft assertion

    # ── 3. HOME PAGE - STATUS PENDING ─────────────────────────────────────
    When user is in home page three
    Then the home status should be Pending
    And complete third soft assertion

    # ── 4. FILTER - LEG TYPE = DELIVERY ───────────────────────────────────
    When user is in home page four
    Then the leg Type should be Delivery
    And complete fourth soft assertion

    # ── 5. FILTER - HELD REASON = RESCHEDULED ─────────────────────────────
    When user is in home page five
    Then the reason should be rescheduled
    And complete fifth soft assertion

    # ── 6. FILTER - COUNTRY ZONE = AE ─────────────────────────────────────
    When user is in home page six
    And the country should be AE
    And complete sixth soft assertion

    # ── 7. FILTER - HUB SECTOR = TEST-A1-S ───────────────────────────────
    When user is in home page seven
    And the hub sector should be TEST-A1-S
    And complete seventh soft assertion

    # ── 8. FILTER - PICKUP TYPE = REGULAR ─────────────────────────────────
    When user is in home page eight
    And the pickup type should be Regular
    And complete eight soft assertion

    # ── 9. RESET ALL FILTERS & OPEN DETAIL PAGE ───────────────────────────
    When user is in home page nine
    And the user clicks on the result item
    And complete ninth soft assertion

    # ── 10. DETAIL PAGE - STATUS / TYPE / ATTEMPTS / PACKAGES ─────────────
    Then the status should be Pending
    And the type should be B2C Delivery
    And scroll down then assert packages
    And I return to the homepage
    And I should see the homepage logo
    And I soft assert all

    # ── 11. HEADER SEARCH ─────────────────────────────────────────────────
    When I enter a search term
    And I click on the search icon
    Then I should see search results
    And I return to the homepage again
    And I should see the homepage logo again
    And I assert all results

    # ── 12. INLINE SEARCH - VALID REFERENCES ──────────────────────────────
    When I enter "PFB1830935005A" in the search box
    Then I verify that the item or message "Specific Item" is displayed
    When I enter "NAE554010" in the search box
    Then I verify that the item or message "Specific Item" is displayed
    When I enter "CR04N01M11245263322" in the search box
    Then I verify that the item or message "Specific Item" is displayed

    # ── 13. INLINE SEARCH - NEGATIVE (invalid input) ──────────────────────
    When I enter "abc" in the search box
    Then I verify that the item or message "No data available" is displayed
    And I Assert All

    # ── 14. BUSINESS PICKUP - SEARCH VALID TASK ───────────────────────────
    Given I am on the business pickup tasks page
    When I search for task number BPTFA71774353808S
    Then I should see the task with number BPTFA71774353808S

    # ── 15. BUSINESS PICKUP - CANCEL BLOCKED (pending items) ──────────────
    And I should not be able to cancel the task
    And I should see the error message pickup

    # ── 16. BUSINESS PICKUP - SELECT / DESELECT ALL ───────────────────────
    And I select all checkboxes
    Then all checkboxes should be selected
    When I click to deselect all checkboxes
    Then no checkboxes should be selected

    # ── 17. BUSINESS PICKUP - CSV DOWNLOAD ────────────────────────────────
    And I click the download button
    Then a file should be downloaded

    # ── 18. BUSINESS PICKUP - NEGATIVE SEARCH ─────────────────────────────
    And I search for task number ABC
    Then I should see no tasks matching ABC
    And I apply Assertions

    # ── 19. REPORTS - SEARCH FOR TPL FAILURE REPORT ────────────────────────
    Given I am on the reports page
    When I search for "tpl" in the reports search box
    Then the search results should contain "tpl_failure_report"
    And I apply reports assertions

    # ── 20. REPORTS - GENERATE TPL FAILURE REPORT ──────────────────────────
    When I search for "tpl" in the reports search box
    And I click on the tpl_failure_report link
    And I click the reports generate button
    Then the report table should show an erroneous shipment
    And I apply reports assertions

    # ── 21. REPORTS - GENERATE AGEING SESSION FILES REPORT ─────────────────
    Given I am on the staging reports page
    When I click on the ageing session files report
    And I enter "test-a1" in the hub input field
    And I click the reports generate button
    And I apply reports assertions

    # ── 22. REPORTS - GENERATE BUSINESS PICKUP TASKS PENDING REPORT ────────
    Given I am on the reports page
    And I scroll to and click the business_pickup_tasks_pending report
    And I click the reports generate button
    And I apply reports assertions

    # ── 23. REPORTS - GENERATE CIR POTENTIAL USER ASSIGN REPORT ────────────
    Given I am on the reports page
    And I scroll to and click the cir_potential_user_assign report
    And I enter "sa" in the cir country input
    And I enter "100" in the cir limit input
    And I click the reports generate button
    Then the report table header should be visible
    And I apply reports assertions

    # ── 24. REPORTS - DOWNLOAD CIR POTENTIAL USER ASSIGN CSV ───────────────
    And I click the cir download button
    Then a cir report csv should be downloaded
    And I apply reports assertions
