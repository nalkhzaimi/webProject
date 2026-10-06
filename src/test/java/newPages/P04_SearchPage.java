package newPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import newScreens.Screenshot;
import newUtilities.Locators;

import java.time.Duration;

public class P04_SearchPage {

    public void EnterSearchTerm(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_SEARCH_BOX));
        searchBox.clear();
        searchBox.sendKeys("CR04N01M10242441623");
    }

    public void ClickOnTheSearchIcon(WebDriver driver) {
        // No search button — search is triggered by pressing Enter
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_SEARCH_BOX));
        searchBox.sendKeys(Keys.RETURN);
    }

    public void SearchResults(WebDriver driver, SoftAssert softAssert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_SEARCH_RESULT_SPAN));
        WebElement clientRequestDetailsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_SEARCH_CLIENT_REQUEST_DETAILS));
        String expectedText = "Client Request Details";
        String actualText = clientRequestDetailsElement.getText();
        softAssert.assertTrue(actualText.contains(expectedText),
                "The text 'Client Request Details' was not found! Expected: " + expectedText + ", but got: " + actualText);
    }

    public void returnToHomeAgain(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement homeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_HOME_BUTTON));
        homeButton.click();
    }

    public void SeeHomeLogoAgain(WebDriver driver, SoftAssert softAssert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Screenshot screenshotObject = new Screenshot(driver);
        WebElement homeLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HEADER_LOGO_WRAPPER));
        if (!homeLogo.isDisplayed()) {
            screenshotObject.takeScreenshot("Homepage_logo_Failure");
        }
        softAssert.assertTrue(homeLogo.isDisplayed(), "Homepage logo is not visible.");
    }
}
