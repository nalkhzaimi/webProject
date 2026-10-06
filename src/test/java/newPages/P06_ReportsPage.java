package newPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import newScreens.Screenshot;
import newUtilities.Locators;

import java.time.Duration;
import java.util.List;

public class P06_ReportsPage {

    private static final String REPORTS_URL         = "https://express-control-alt.noonstg.team/reports";
    private static final String REPORTS_STAGING_URL = "https://express-control.noonstg.team/reports";

    public void NavigateToReports(WebDriver driver) {
        driver.navigate().to(REPORTS_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.REPORTS_SEARCH_BOX));
    }

    public void NavigateToStagingReports(WebDriver driver) {
        driver.navigate().to(REPORTS_STAGING_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.REPORTS_AGEING_LINK));
    }

    public void SearchByKeyword(WebDriver driver, String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_SEARCH_BOX));
        ((JavascriptExecutor) driver).executeScript(
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "setter.call(arguments[0], '');" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                searchBox
        );
        searchBox.sendKeys(keyword);
    }

    public void ClearSearchBox(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_SEARCH_BOX));
        ((JavascriptExecutor) driver).executeScript(
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "setter.call(arguments[0], '');" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                searchBox
        );
    }

    public void ClickTplFailureReportLink(WebDriver driver) {
        // Wait for the filtered result to appear after search
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_TPL_RESULT));
        link.click();
    }

    public void ScrollToAndClickBusinessPickupPendingReport(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.REPORTS_BUSINESS_PICKUP_PENDING));
        // Center the element in the viewport so the sticky header doesn't cover it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        // JS click bypasses ElementClickInterceptedException from overlapping header
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }

    public void ScrollToAndClickCirPotentialReport(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.REPORTS_CIR_POTENTIAL_LINK));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }

    public void EnterCirCountryInput(WebDriver driver, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_CIR_COUNTRY_INPUT));
        ((JavascriptExecutor) driver).executeScript(
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "setter.call(arguments[0], '');" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                input
        );
        input.sendKeys(value);
    }

    public void EnterCirLimitInput(WebDriver driver, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_CIR_LIMIT_INPUT));
        ((JavascriptExecutor) driver).executeScript(
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "setter.call(arguments[0], '');" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                input
        );
        input.sendKeys(value);
    }

    public void AssertReportTableHeaderVisible(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        try {
            wait.until(d -> {
                List<WebElement> headers = d.findElements(Locators.REPORTS_TABLE_HEADER_FIRST);
                return !headers.isEmpty()
                        && headers.get(0).isDisplayed()
                        && !headers.get(0).getText().trim().isEmpty();
            });
        } catch (Exception e) {
            screenshotObject.takeScreenshot("cir_report_table_not_loaded");
            softAssert.fail("Report table did not load within 60 seconds after clicking Generate.");
            return;
        }
        WebElement header = driver.findElement(Locators.REPORTS_TABLE_HEADER_FIRST);
        softAssert.assertFalse(header.getText().trim().isEmpty(), "Report table header is empty after generation.");
    }

    public void ClickCirDownloadButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_CIR_DOWNLOAD_BUTTON));
        button.click();
    }

    public void AssertCsvDownloaded(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.REPORTS_DOWNLOAD_SUCCESS_MSG));
            System.out.println("Download success toast appeared.");
        } catch (Exception e) {
            screenshotObject.takeScreenshot("cir_download_toast_not_found");
            softAssert.fail("Download success toast did not appear after clicking download.");
        }
    }

    public void ClickAgeingSessionReport(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_AGEING_LINK));
        link.click();
    }

    public void EnterHubInput(WebDriver driver, String hub) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_HUB_INPUT));
        new Actions(driver)
                .click(input)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(hub)
                .perform();
    }

    public void ClickGenerateButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(Locators.REPORTS_GENERATE_BUTTON));
        button.click();
    }

    public void AssertTableFirstCellVisible(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        // Wait until the cell is visible AND has non-empty text (data loads async after generate)
        try {
            wait.until(d -> {
                List<WebElement> cells = d.findElements(Locators.REPORTS_TABLE_FIRST_CELL);
                return !cells.isEmpty()
                        && cells.get(0).isDisplayed()
                        && !cells.get(0).getText().trim().isEmpty();
            });
        } catch (Exception e) {
            screenshotObject.takeScreenshot("tpl_report_empty_result");
            softAssert.fail("Report table did not load data within 60 seconds after clicking Generate.");
            return;
        }
        WebElement cell = driver.findElement(Locators.REPORTS_TABLE_FIRST_CELL);
        softAssert.assertFalse(
                cell.getText().trim().isEmpty(),
                "Report table first row first cell is empty after generation."
        );
    }

    public void AssertResultContainsText(WebDriver driver, SoftAssert softAssert, String expectedText) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.REPORTS_TPL_RESULT));
        String resultText = result.getText();
        if (!resultText.toLowerCase().contains(expectedText.toLowerCase())) {
            screenshotObject.takeScreenshot("reports_search_failure");
        }
        softAssert.assertTrue(
                resultText.toLowerCase().contains(expectedText.toLowerCase()),
                "Expected '" + expectedText + "' not found in results. Got: " + resultText
        );
    }

    public void AssertResultsListNotEmpty(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(Locators.REPORTS_RESULT_LINKS, 0));
        List<WebElement> results = driver.findElements(Locators.REPORTS_RESULT_LINKS);
        if (results.isEmpty()) {
            screenshotObject.takeScreenshot("reports_results_empty_after_clear");
        }
        softAssert.assertFalse(results.isEmpty(), "Expected results list to be non-empty after clearing search.");
    }

    public void AssertResultNotContaining(WebDriver driver, SoftAssert softAssert, String unexpectedText) {
        Screenshot screenshotObject = new Screenshot(driver);
        List<WebElement> results = driver.findElements(Locators.REPORTS_RESULT_LINKS);
        boolean found = results.stream()
                .anyMatch(el -> el.getText().toLowerCase().contains(unexpectedText.toLowerCase()));
        if (found) {
            screenshotObject.takeScreenshot("reports_unexpected_result_found");
        }
        softAssert.assertFalse(found, "'" + unexpectedText + "' should not appear in filtered results.");
    }
}
