package newSteps;

import newPages.P03_SelectedItemPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import newUtilities.Locators;

import java.time.Duration;

import static newHooks.hooks.driver;

public class T02_SecondPageSteps {
    P03_SelectedItemPage secPage = new P03_SelectedItemPage();
    SoftAssert softAssert = new SoftAssert();

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        System.out.println("Driver before use in SecTest: " + driver);
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized in SecTest!");
        }
    }

    @When("the status should be Pending")
    public void theStatusShouldBePending() {
        secPage.AssertStatusIsPending(driver, softAssert);
    }

    @And("the type should be B2C Delivery")
    public void theTypeShouldBeB2CDelivery() {
        secPage.AssertTypeIsb2cdelivery(driver, softAssert);
    }

    @And("scroll down then assert packages")
    public void ScrollDownAndAssertPckgs() {
        secPage.ScrollDown(driver);
        secPage.AssertPackages(driver, softAssert);
    }

    @And("I return to the homepage")
    public void returnToHome() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement homeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_HOME_BUTTON));
        homeButton.click();
    }

    @And("I should see the homepage logo")
    public void IshouldSeeHomeLogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement homeLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_LOGO_WRAPPER));
        softAssert.assertTrue(homeLogo.isDisplayed(), "Homepage logo is not visible.");
    }

    @Then("I soft assert all")
    public void I_soft_asser_all() {
        softAssert.assertAll();
    }
}
