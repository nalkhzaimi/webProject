package newPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import newScreens.Screenshot;
import newUtilities.Locators;

import java.time.Duration;

public class P05_SecondSearchPage {

    public void EnterSearchTermInTheBox(String searchText, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.INLINE_SEARCH_BOX));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBox);
        searchBox.clear();
        searchBox.sendKeys(Keys.CONTROL + "a");
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.RETURN);
    }

    public void VerifyResults(String expectedResult, WebDriver driver, SoftAssert softAssert) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Screenshot screenshotObject = new Screenshot(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.INLINE_SEARCH_CONTENT_AREA));

        if ("Specific Item".equals(expectedResult)) {
            WebElement clientRef = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.INLINE_SEARCH_FIRST_CELL));
            if (!clientRef.isDisplayed()) {
                screenshotObject.takeScreenshot("SecSearchResultsNotDisplayed");
            }
            softAssert.assertTrue(clientRef.isDisplayed(), "Client reference not visible after search.");
            WebElement currentLeg = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.INLINE_SEARCH_CURRENT_LEG));
            softAssert.assertTrue(currentLeg.isDisplayed(), "Current leg section not visible. Got: " + currentLeg.getText());

        } else if ("No data available".equals(expectedResult)) {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, document.body.scrollHeight)");
            Thread.sleep(1000);
            WebElement noDataMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.NO_DATA_MESSAGE));
            String noDataMessageText = noDataMessageElement.getText();
            if (!"Uh-oh! No Data Available".equals(noDataMessageText)) {
                screenshotObject.takeScreenshot("SecSearchResults_NOTFoundMsg_NotDisplayed");
            }
            softAssert.assertEquals(noDataMessageText, "Uh-oh! No Data Available", "No message is displayed");
        }
    }
}
