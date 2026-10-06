package newSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import newUtilities.Locators;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static newHooks.hooks.driver;

public class T05_BusinessPickupTasksSteps {

    WebDriverWait wait;
    SoftAssert softAssert = new SoftAssert();
    private long downloadStartedAt;

    @Given("I am on the business pickup tasks page")
    public void i_am_on_the_business_pickup_tasks_page() {
        driver.navigate().to("https://express-control-alt.noonstg.team/business-pickup");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_SEARCH_BOX));
    }

    @When("I search for task number BPTFA71774353808S")
    public void i_search_for_task() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_SEARCH_BOX));
                inputField.clear();
                inputField.sendKeys("BPTFA71774353808S" + Keys.ENTER);
                System.out.println("Task number entered successfully.");
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException encountered. Retrying attempt " + attempts);
            }
        }
        Thread.sleep(500);
    }

    @Then("I should see the task with number BPTFA71774353808S")
    public void i_see_the_task() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement task = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_TASK_NUMBER_CELL));
        softAssert.assertTrue(task.isDisplayed(), "Task was not found in the results.");
    }

    @And("I select all checkboxes")
    public void i_select_all_checkboxes() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectAllCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_SELECT_ALL_LABEL));
        selectAllCheckbox.click();
    }

    @Then("all checkboxes should be selected")
    public void all_checkboxes_selected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By rowCheckboxes = By.xpath("//*[@id='wrapper']//table/tbody//input[@type='checkbox']");
        List<WebElement> checkboxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowCheckboxes));
        Assert.assertFalse(checkboxes.isEmpty(), "No task checkboxes were found.");
        Assert.assertTrue(checkboxes.stream().allMatch(WebElement::isSelected), "Every task checkbox should be selected.");
    }

    @When("I click to deselect all checkboxes")
    public void i_click_to_deselect_check_boxes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectAllCheckbox = wait.until(ExpectedConditions.elementToBeClickable(Locators.PICKUP_SELECT_ALL_LABEL));
        selectAllCheckbox.click();
    }

    @Then("no checkboxes should be selected")
    public void no_checkboxes_are_selected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By rowCheckboxes = By.xpath("//*[@id='wrapper']//table/tbody//input[@type='checkbox']");
        List<WebElement> checkboxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowCheckboxes));
        Assert.assertTrue(checkboxes.stream().noneMatch(WebElement::isSelected), "No task checkbox should remain selected.");
    }

    @And("I click the download button")
    public void i_click_on_download_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_DOWNLOAD_BUTTON));
        downloadStartedAt = System.currentTimeMillis();
        downloadButton.click();
    }

    @Then("a file should be downloaded")
    public void a_file_is_downloaded() {
        String downloadDirectory = System.getProperty("user.home") + "/Downloads";
        WebDriverWait downloadWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean isDownloaded;
        try {
            downloadWait.until(d -> isFileDownloaded(downloadDirectory, "bpt_search_results.csv", downloadStartedAt));
            isDownloaded = true;
        } catch (org.openqa.selenium.TimeoutException e) {
            isDownloaded = false;
        }
        Assert.assertTrue(isDownloaded, "Expected bpt_search_results.csv to be present in Downloads.");
    }

    @Then("I should not be able to cancel the task")
    public void i_should_not_be_able_to_cancel_the_task() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.PICKUP_CANCEL_BUTTON));
        cancelButton.click();
        System.out.println("Cancel Task button clicked successfully.");
    }

    @And("I should see the error message pickup")
    public void i_should_see_an_error_message() throws InterruptedException {
        Thread.sleep(100);
        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = waitt.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_ERROR_TOAST));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error pop-up did NOT appear");
    }

    @And("I search for task number ABC")
    public void i_seach_for_task_ABC() {
        driver.navigate().to("https://express-control-alt.noonstg.team/business-pickup");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_SEARCH_BOX));
        inputField.clear();
        inputField.sendKeys("ABC" + Keys.ENTER);
    }

    @Then("I should see no tasks matching ABC")
    public void i_see_NO_task_ABC() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PICKUP_NO_DATA_IMAGE));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.NO_DATA_MESSAGE));
        String messageText = message.getText();
        softAssert.assertEquals(messageText, "Uh-oh! No Data Available", "Message text does not match the expected value.");
    }

    @And("I apply Assertions")
    public void ApplyAssertion() {
        softAssert.assertAll();
    }

    public boolean isFileDownloaded(String downloadDir, String fileName, long notBefore) {
        File dir = new File(downloadDir);
        File[] files = dir.listFiles();
        if (files == null) return false;
        for (File file : files) {
            String name = file.getName();
            if (file.isFile()
                    && (name.equals(fileName) || (name.startsWith(fileName.replace(".csv", "")) && name.endsWith(".csv")))
                    && file.lastModified() >= notBefore) {
                return true;
            }
        }
        return false;
    }
}
