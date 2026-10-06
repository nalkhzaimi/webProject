package newPages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import newScreens.Screenshot;
import newUtilities.Locators;

import java.time.Duration;
import java.util.List;

public class P02_HomePage {

    public void GoToControlApp(WebDriver driver) {
        driver.navigate().to("https://express-control-alt.noonstg.team/requests?control_status=pending&creq_type_codes=b2c_delivery"); // staging
    }

    public void ClickOnTEST_A1hub(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownMenu = wait.until(ExpectedConditions.elementToBeClickable(Locators.HUB_SELECTOR_DROPDOWN));
        String currentSelection = dropdownMenu.getText().trim();
        if (!"TEST-A1".equalsIgnoreCase(currentSelection)) {
            dropdownMenu.click();
            WebElement testA1Option = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HUB_OPTION_TEST_A1));
            testA1Option.click();
            wait.until(ExpectedConditions.textToBePresentInElement(dropdownMenu, "TEST-A1"));
        } else {
            System.out.println("TEST-A1 is already selected, no action taken.");
        }
    }

    public void waitHubMenuVisiblityANDassertThatHubIsTEST_A1(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement hubMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HUB_SELECTOR_DROPDOWN));
        String selectedHub = hubMenu.getText();
        if (!"TEST-A1".equals(selectedHub)) {
            screenshotObject.takeScreenshot("Hub_Selection_Failure");
        }
        softAssert.assertEquals(selectedHub, "TEST-A1", "The selected Hub is not 'TEST-A1'.");
    }

    public void AssertThat_Selected_ServiceIsB2CDelivery(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_SERVICE_TYPE));
        String selectedOption = dropdown.getText();
        if (!selectedOption.toLowerCase().contains("b2c")) {
            screenshotObject.takeScreenshot("B2C_Selection_Failure");
        }
        softAssert.assertTrue(selectedOption.toLowerCase().contains("b2c"), "B2C not found in selected option!");
    }

    public void AssertThat_Result_inTheSpecifiedColumnIsB2CDelivery(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> columnElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.RESULTS_TABLE_SERVICE_COL));
        for (WebElement element : columnElements) {
            String cellText = element.getText();
            if (!cellText.toLowerCase().contains("b2c")) {
                screenshotObject.takeScreenshot("HomPg_B2C_Results_Column");
            }
            softAssert.assertTrue(cellText.toLowerCase().contains("b2c"), "Found a non-B2C Delivery order: " + cellText);
        }
    }

    public void AssertThatStatusIsPending(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownIcon = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_STATUS));
        String selectedOptionText = dropdownIcon.getText();
        if (!"pending".equalsIgnoreCase(selectedOptionText)) {
            screenshotObject.takeScreenshot("Pending_Selection_Failure");
        }
        softAssert.assertEquals(selectedOptionText.toLowerCase(), "pending", "The selected dropdown option is not 'Pending'.");
    }

    public void SelectDeliveryLegTypeAndAssert(WebDriver driver, SoftAssert softAssert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Screenshot screenshotObject = new Screenshot(driver);
        WebElement legTypeMenu = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_LEG_TYPE));
        legTypeMenu.click();
        WebElement deliveryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.OPTION_DELIVERY));
        deliveryOption.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(Locators.FILTER_LEG_TYPE, "elivery"));
        String selectedOption = driver.findElement(Locators.FILTER_LEG_TYPE).getText();
        if (!"delivery".equalsIgnoreCase(selectedOption)) {
            screenshotObject.takeScreenshot("HomPg_Delivery_Selection");
        }
        softAssert.assertTrue(selectedOption.toLowerCase().contains("delivery"), "The selected option is not 'Delivery'.");
    }

    public void SelectReasonAndAssert(WebDriver driver, SoftAssert softAssert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Screenshot screenshotObject = new Screenshot(driver);
        WebElement reasonMenu = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_REASON));
        reasonMenu.click();
        WebElement rescheduledOption = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.OPTION_RESCHEDULED));
        rescheduledOption.click();
        reasonMenu = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_REASON)));
        String selectedReason = reasonMenu.getText();
        if (!"rescheduled".equalsIgnoreCase(selectedReason)) {
            screenshotObject.takeScreenshot("reason_rescheduled_Selection_Failure");
        }
        softAssert.assertTrue(selectedReason.toLowerCase().contains("rescheduled"), "The selected reason is not 'Rescheduled'.");
    }

    public void SelectAEcountryZoneAndAssert(WebDriver driver, SoftAssert softAssert) {
        Screenshot screenshotObject = new Screenshot(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        GoToControlApp(driver);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_RESET_BUTTON));
        WebElement countryZoneMenu = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_COUNTRY_ZONE));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", countryZoneMenu);
        new Actions(driver).moveToElement(countryZoneMenu).click()
                .pause(Duration.ofMillis(600))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
        WebElement countryZoneDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_COUNTRY_ZONE_SELECTED));
        String selectedCountry = countryZoneDropdown.getText();
        if (!"AE".equals(selectedCountry)) {
            screenshotObject.takeScreenshot("HomPg_Country_AE");
        }
        softAssert.assertTrue(selectedCountry.contains("AE"), "The selected country is not 'AE'.");
    }

    public void SelectHubSectorAndAssert(WebDriver driver, SoftAssert softAssert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Screenshot screenshotObject = new Screenshot(driver);
        // Navigate fresh so no previous filters narrow the hub sector options
        GoToControlApp(driver);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_RESET_BUTTON));
        WebElement hubSectorMenu = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_HUB_SECTOR));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", hubSectorMenu);
        new Actions(driver).moveToElement(hubSectorMenu).click()
                .pause(Duration.ofMillis(600))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
        WebElement selectedHubSector = wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_HUB_SECTOR_SELECTED)));
        String selectedHubText = selectedHubSector.getText();
        if ("All".equalsIgnoreCase(selectedHubText)) {
            screenshotObject.takeScreenshot("HomPg_Hub_Sector");
        }
        softAssert.assertFalse("All".equalsIgnoreCase(selectedHubText), "Hub sector was not changed from 'All'.");
    }

    public void SelectPickupTypeAndAssert(WebDriver driver, SoftAssert softAssert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Screenshot screenshotObject = new Screenshot(driver);
        GoToControlApp(driver);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_RESET_BUTTON));
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_PICKUP_TYPE));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", menu);
        new Actions(driver).moveToElement(menu).click()
                .pause(Duration.ofMillis(600))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .pause(Duration.ofMillis(500))
                .perform();
        WebElement selectedMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_PICKUP_TYPE));
        String selectedMenuText = selectedMenu.getText();
        System.out.println("Pickup type selected: " + selectedMenuText);
        if (!"Regular".equalsIgnoreCase(selectedMenuText.trim())) {
            screenshotObject.takeScreenshot("HomPg_Pickup_Type");
        }
        softAssert.assertEquals(selectedMenuText.trim().toLowerCase(), "regular", "The selected pickup type is not 'Regular'.");
    }

    public void ResetAllSelectionsWithAssertion(WebDriver driver, SoftAssert softAssert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Screenshot screenshotObject = new Screenshot(driver);
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_RESET_BUTTON));
        resetButton.click();
        WebElement legTypeMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_LEG_TYPE));
        String legTypeText = legTypeMenu.getText();
        if (!"All".equals(legTypeText)) {
            screenshotObject.takeScreenshot("reset_Failure");
        }
        softAssert.assertEquals(legTypeText, "All", "The 'Leg Type' menu is not reset to 'All'.");
        WebElement reasonMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.FILTER_REASON));
        String reasonText = reasonMenu.getText();
        if (!"All".equals(reasonText)) {
            screenshotObject.takeScreenshot("reset_Failure");
        }
        softAssert.assertEquals(reasonText, "All", "The 'Reason' menu is not reset to 'All'.");
    }

    public void ClickOnResultItem(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESULTS_TABLE_THIRD_ROW));
        try {
            targetElement.click();
        } catch (ElementNotInteractableException e) {
            System.out.println("Regular click failed, trying JavaScript click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetElement);
        }
    }
}
