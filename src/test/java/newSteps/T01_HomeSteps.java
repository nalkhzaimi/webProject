package newSteps;

import newPages.P01_LoginPage;
import newPages.P02_HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import newUtilities.Locators;

import java.time.Duration;

import static newHooks.hooks.driver;

public class T01_HomeSteps {

    P02_HomePage homePge = new P02_HomePage();
    P01_LoginPage loginPge =new P01_LoginPage();
    SoftAssert softAssert = new SoftAssert();

    //background
    @Given("the user navigates to the control app")
    public void the_user_navigates_to_the_control_app() {
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized!");
        }
        homePge.GoToControlApp(driver);
    }

    /////////////1/////////////////
    @When("the user logs in and select TEST-A1")
    public void the_user_logs_in_and_select_TESTA1() {
        loginPge.LoginWithNoorCredentails(driver);
        homePge.ClickOnTEST_A1hub(driver);
    }
    @Then("the hub menu should be visible and assert that it is TEST_A1")
    public void the_hub_menu_should_be_visible_and_assert_that_it_is_test_a1() {
        homePge.waitHubMenuVisiblityANDassertThatHubIsTEST_A1(driver,softAssert);
    }
    @And("complete first soft assertion")
    public void soft_assertion_check() {
        softAssert.assertAll();
    }
    /////////////2////////////////////////
    @When("user is in home page")
    public void user_is_in_home_page() {
        //ensure home is loaded
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));

    }
    @Then("the service should be B2C Delivery")
    public void the_service_should_be_b2c_delivery() {

        homePge.AssertThat_Selected_ServiceIsB2CDelivery(driver,softAssert);
        homePge.AssertThat_Result_inTheSpecifiedColumnIsB2CDelivery(driver,softAssert);
    }
    @And("complete second soft assertion")
    public void soft_assertion_checkk() {
        softAssert.assertAll();
    }
    ///////////////////3//////////////
    @When("user is in home page three")
    public void UserInHome3Page() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @Then("the home status should be Pending")
    public void the_home_status_should_be_pending() {
        homePge.AssertThatStatusIsPending(driver,softAssert);
    }
    @And("complete third soft assertion")
    public void soft_assertion_checkking() {
        softAssert.assertAll();
    }
    //////////////4/////////////////
    @When("user is in home page four")
    public void UserInHome4Page() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @Then("the leg Type should be Delivery")
    public void the_legType_should_be_delivery() {
        homePge.SelectDeliveryLegTypeAndAssert(driver,softAssert);
    }
    @And("complete fourth soft assertion")
    public void softAssertionCheck() {
        softAssert.assertAll();
    }
    /////////////5///////////////
    @When("user is in home page five")
    public void UserInHome5Page() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @Then("the reason should be rescheduled")
    public void the_reason_should_be_rescheduled() {
        homePge.SelectReasonAndAssert(driver,softAssert);
    }
    @And("complete fifth soft assertion")
    public void softAssertionChecking() {
        softAssert.assertAll();
    }
    ///////////////////6////////////////
    @When("user is in home page six")
    public void UserInHome6Page() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @And("the country should be AE")
    public void CountryAEselectionAndAssertion() {
        homePge.SelectAEcountryZoneAndAssert(driver,softAssert);
    }
    @And("complete sixth soft assertion")
    public void softAssertion6thChecking() {
        softAssert.assertAll();
    }
    /////////////////////7////////////
    @When("user is in home page seven")
    public void UserInHome7Page() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @And("the hub sector should be TEST-A1-S")
    public void HubSectorselectionAndAssertion() {
        homePge.SelectHubSectorAndAssert(driver,softAssert);
    }
    @And("complete seventh soft assertion")
    public void softAssertion7thChecking() {
        softAssert.assertAll();
    }
    ////////////////8/////////
    @When("user is in home page eight")
    public void UserInHome8Page() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @And("the pickup type should be Regular")
    public void PickupTypeselectionAndAssertion() {
        homePge.SelectPickupTypeAndAssert(driver,softAssert);
    }
    @And("complete eight soft assertion")
    public void softAssertion8thChecking() {
        softAssert.assertAll();
    }
    ///////////////////reset//////////
    @When("user is in home page reset")
    public void UserInHomeResetPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @Then("the user resets all filters")
    public void theUserResetsAllFilters() {
        homePge.ResetAllSelectionsWithAssertion(driver, softAssert);
    }
    @And("complete reset soft assertion")
    public void completeResetSoftAssertion() {
        softAssert.assertAll();
    }
    ///////////////////9//////////
    @When("user is in home page nine")
    public void UserInHome9Page() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_RESET_BUTTON));
    }
    @And("the user clicks on the result item")
    public void the_user_clicks_on_the_result_item() {
        homePge.ClickOnResultItem(driver);
    }
    @And("complete ninth soft assertion")
    public void complete_soft_assertion_check() {
        softAssert.assertAll();
    }
}
