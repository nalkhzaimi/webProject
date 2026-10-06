import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;



public class newTest {

    WebDriverWait wait;
    WebDriver driver;
    WebElement emailField;
    WebElement nextButton;
    WebElement continueButton;
    WebElement googleLoginButton;
    WebDriverWait wait1;



    @BeforeClass
    public void open_Website(){

        driver = new SafariDriver();
        driver.get("https://express-control.noonstg.team/requests?control_status=pending&creq_type_codes=b2c_delivery");

    }

    @Test
    public void firstloginTest() throws InterruptedException {

        // Create wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Click Google Login
        driver.findElement(By.xpath("//span[normalize-space()='Google Login']")).click();

        // email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));emailField.sendKeys("nalkhazimi@noon.com");

        //click next
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[normalize-space()='Next']]")));nextButton.click();

        //email agin
        wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116"))).sendKeys("nalkhazimi@noon.com");

        //click next
        nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        nextButton.click();


        // Password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
        passwordField.sendKeys("noor@1999");

        // Sign in
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        signInButton.click();

        // three lines
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wrapper']/header/div[1]/div/div[1]/button"))).click();

        // task
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wrapper']/div/div[2]/div[1]/div/div[3]/div/button"))).click();

        // click Business Pickup Tasks
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[normalize-space()='Business Pickup Task']]"))).click();

        // click C2B
        By pickupTab = By.xpath("//button[@role='tab' and normalize-space()='C2B Pickup (CIR)']");
        wait.until(ExpectedConditions.elementToBeClickable(pickupTab)).click();

        //  input "BPTFA71774353808S" in a search box
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Client Ref or Address UID']"))
        ).sendKeys("BPTFA71774353808S");

        // create wait for 5sec
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);

        // click Rest Filter
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Reset Filters']"))).click();


        /////////////// Testing that you can't cancel task with pending items/////////////////////////////////////////
        WebElement pendingItems = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wrapper\"]/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[10]")));
        String text = pendingItems.getText().trim();
        System.out.println("Number found in XPath: " + text);
        String[] parts = text.split("/");
        String extractedNumber = parts[0].trim(); // Get the first part and remove spaces
        try {
            int number = Integer.parseInt(extractedNumber);
            System.out.println("Extracted number: " + number);
            Assert.assertTrue(number > 0, "The number in the XPath is not greater than 0!");
        } catch (NumberFormatException e) {
            Assert.fail("The extracted text is not a valid number: " + text);
        }
        WebElement cancelButton = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[14]/div/button"));
        cancelButton.click();
        System.out.println("Cancel Task button clicked successfully.");

        //check if error mesage appeared
        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(10));
        By errorMessageLocator = By.xpath("//p[contains(@class,'toastCtr')]");
        WebElement errorMessage = waitt.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error pop-up did NOT appear");

        ///////////Testing that i can re-assign task////////////////////////////////////////////////////
        By reassignButtonLocator = By.xpath("//*[@id='wrapper']/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[14]/div/div/button");
        WebElement reassignButton = waitt.until(ExpectedConditions.elementToBeClickable(reassignButtonLocator));
        reassignButton.click();
        // 1️⃣ Locate the input field and enter "a"
        By inputtFieldLocator = By.xpath("//*[@id='wrapper']/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[14]/div/div/div/div[1]/div/input");
        WebElement inputtField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputtFieldLocator));
        inputtField.sendKeys("a");
        System.out.println("✅ Entered 'a' into the input field.");

// 2️⃣ Wait until the result box contains at least one suggestion
        By resultBoxLocator = By.xpath("//*[@id='wrapper']/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[14]/div/div/div/div[2]/div");
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultBoxLocator));
        System.out.println("✅ Result box is now visible.");

// 3️⃣ Wait until the first suggestion appears and click the selection checkbox
        By firstSuggestionLocator = By.xpath("//*[@id='wrapper']/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[14]/div/div/div/div[2]/div/div[1]");
        WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(firstSuggestionLocator));
        System.out.println("✅ First suggestion is visible.");

        By selectCheckboxLocator = By.xpath("//*[@id='wrapper']/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[14]/div/div/div/div[2]/div/div[1]/i");
        WebElement selectCheckbox = wait.until(ExpectedConditions.elementToBeClickable(selectCheckboxLocator));
        selectCheckbox.click();
        System.out.println("✅ Selected the first checkbox.");

// 4️⃣ Ensure the "Reassign" button is clickable and click it
        By reassignButtonnLocator = By.xpath("//*[@id='wrapper']/div[2]/div[3]/div[2]/div[2]/table/tbody/tr/td[14]/div/div/div/div[1]/div/button");
        WebElement reassignnButton = wait.until(ExpectedConditions.elementToBeClickable(reassignButtonnLocator));
        reassignnButton.click();
        System.out.println("✅ Reassign button clicked successfully.");

    }

    /*  @AfterClass
  public void tearDown(){
      if (driver != null) {
          driver.quit();
          driver = null;
      }
  }*/

}
