package newSteps;

import newPages.P04_SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static newHooks.hooks.driver;

public class T03_SearchSteps {
    private SoftAssert softAssert = new SoftAssert();
    P04_SearchPage searchpge=new P04_SearchPage();
    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        System.out.println("Driver before use in search: " + driver);
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized in search!");
        }
    }

    @When("I enter a search term")
    public void iEnterASearchTerm() throws InterruptedException {
        searchpge.EnterSearchTerm(driver);
    }

    @When("I click on the search icon")
    public void iClickOnTheSearchIcon() {
        searchpge.ClickOnTheSearchIcon(driver);
    }

    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        searchpge.SearchResults(driver,softAssert);

    }

    @And("I return to the homepage again")
    public void returnToHomeAgain() {
       searchpge.returnToHomeAgain(driver);
    }

    @And("I should see the homepage logo again")
    public void IshouldSeeHomeLogoAgain() {
        searchpge.SeeHomeLogoAgain(driver,softAssert);
    }

    @Then("I assert all results")
    public void assertAllResults() {
        softAssert.assertAll();
    }
}
