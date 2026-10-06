package newSteps;

import newPages.P05_SecondSearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static newHooks.hooks.driver;

public class T04_SecondSearchSteps {
    P05_SecondSearchPage SecSearchPge = new P05_SecondSearchPage();
    SoftAssert softAssert = new SoftAssert();
    @Given("Iam on the homepage again")
    public void iAmOnTheHomepageAgain() {
        System.out.println("Driver before use in search: " + driver);
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized in search!");
        }
    }
    @When("I enter {string} in the search box")
    public void i_enter_in_the_search_box(String searchText) {
        SecSearchPge.EnterSearchTermInTheBox(searchText,driver);
    }

    @Then("I verify that the item or message {string} is displayed")
    public void i_verify_that_the_item_or_message_is_displayed(String expectedResult) throws InterruptedException {
       SecSearchPge.VerifyResults(expectedResult,driver,softAssert);
    }
    @And("I Assert All")
    public void i_Assert_All() {
        softAssert.assertAll();
    }
}
