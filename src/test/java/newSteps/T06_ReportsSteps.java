package newSteps;

import newPages.P06_ReportsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static newHooks.hooks.driver;

public class T06_ReportsSteps {

    P06_ReportsPage reportsPage = new P06_ReportsPage();
    SoftAssert softAssert = new SoftAssert();

    @Given("I am on the reports page")
    public void i_am_on_the_reports_page() {
        reportsPage.NavigateToReports(driver);
    }

    @Given("I am on the staging reports page")
    public void i_am_on_the_staging_reports_page() {
        reportsPage.NavigateToStagingReports(driver);
    }

    @When("I search for {string} in the reports search box")
    public void i_search_for_in_the_reports_search_box(String keyword) {
        reportsPage.SearchByKeyword(driver, keyword);
    }

    @And("I clear the reports search box")
    public void i_clear_the_reports_search_box() {
        reportsPage.ClearSearchBox(driver);
    }

    @And("I click on the tpl_failure_report link")
    public void i_click_on_the_tpl_failure_report_link() {
        reportsPage.ClickTplFailureReportLink(driver);
    }

    @And("I scroll to and click the business_pickup_tasks_pending report")
    public void i_scroll_to_and_click_the_business_pickup_tasks_pending_report() {
        reportsPage.ScrollToAndClickBusinessPickupPendingReport(driver);
    }

    @And("I scroll to and click the cir_potential_user_assign report")
    public void i_scroll_to_and_click_the_cir_potential_user_assign_report() {
        reportsPage.ScrollToAndClickCirPotentialReport(driver);
    }

    @And("I enter {string} in the cir country input")
    public void i_enter_in_the_cir_country_input(String value) {
        reportsPage.EnterCirCountryInput(driver, value);
    }

    @And("I enter {string} in the cir limit input")
    public void i_enter_in_the_cir_limit_input(String value) {
        reportsPage.EnterCirLimitInput(driver, value);
    }

    @Then("the report table header should be visible")
    public void the_report_table_header_should_be_visible() {
        reportsPage.AssertReportTableHeaderVisible(driver, softAssert);
    }

    @And("I click the cir download button")
    public void i_click_the_cir_download_button() {
        reportsPage.ClickCirDownloadButton(driver);
    }

    @Then("a cir report csv should be downloaded")
    public void a_cir_report_csv_should_be_downloaded() {
        reportsPage.AssertCsvDownloaded(driver, softAssert);
    }

    @When("I click on the ageing session files report")
    public void i_click_on_the_ageing_session_files_report() {
        reportsPage.ClickAgeingSessionReport(driver);
    }

    @And("I enter {string} in the hub input field")
    public void i_enter_in_the_hub_input_field(String hub) {
        reportsPage.EnterHubInput(driver, hub);
    }

    @And("I click the reports generate button")
    public void i_click_the_reports_generate_button() {
        reportsPage.ClickGenerateButton(driver);
    }

    @Then("the report table should show an erroneous shipment")
    public void the_report_table_should_show_an_erroneous_shipment() {
        reportsPage.AssertTableFirstCellVisible(driver, softAssert);
    }

    @Then("the search results should contain {string}")
    public void the_search_results_should_contain(String expectedText) {
        reportsPage.AssertResultContainsText(driver, softAssert, expectedText);
    }

    @Then("the search results should not contain {string}")
    public void the_search_results_should_not_contain(String unexpectedText) {
        reportsPage.AssertResultNotContaining(driver, softAssert, unexpectedText);
    }

    @Then("the reports results list should be visible")
    public void the_reports_results_list_should_be_visible() {
        reportsPage.AssertResultsListNotEmpty(driver, softAssert);
    }

    @And("I apply reports assertions")
    public void i_apply_reports_assertions() {
        softAssert.assertAll();
    }
}
