package newPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import newScreens.Screenshot;
import newUtilities.Locators;

import java.time.Duration;
import java.util.List;

public class P03_SelectedItemPage {

    public void ScrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(Locators.DETAIL_PACKAGES_HEADER));
    }

    public void AssertPackages(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.DETAIL_PACKAGES_ROWS));
        List<WebElement> packageRows = driver.findElements(Locators.DETAIL_PACKAGES_ROWS);
        int packageCount = packageRows.size();
        if (!(packageCount > 0)) {
            screenshotObject.takeScreenshot("Packages_Empty");
        }
        System.out.println("Total number of packages in the column: " + packageCount);
        softAssert.assertTrue(packageCount > 0, "No packages found in the column.");
        WebElement airwayNumberElement = wait.until(ExpectedConditions.elementToBeClickable(Locators.DETAIL_PACKAGES_FIRST_AWB));
        airwayNumberElement.click();
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

    public void AssertStatusIsPending(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.DETAIL_STATUS_LABEL));
        String statusText = status.getText();
        if (statusText == null || statusText.isBlank()) {
            screenshotObject.takeScreenshot("status_Failure");
        }
        softAssert.assertEquals(statusText.trim().toLowerCase(), "pending", "Status mismatch! Expected 'Pending' but got: " + statusText);
        System.out.println("Detail page status: " + statusText);
    }

    public void AssertTypeIsb2cdelivery(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.DETAIL_TYPE_LABEL));
        String typeText = type.getText();
        if (!"b2c delivery".equalsIgnoreCase(typeText)) {
            screenshotObject.takeScreenshot("type_Failure");
        }
        softAssert.assertEquals(typeText.toLowerCase(), "b2c delivery", "Type mismatch!");
    }
}
