package newPages;

import newHooks.hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import newUtilities.ConfigReader;
import newUtilities.Locators;

import java.time.Duration;

public class P01_LoginPage {

    private static final String APP_URL =
            "https://express-control-alt.noonstg.team/requests?control_status=pending&creq_type_codes=b2c_delivery";

    public void LoginWithNoorCredentails(WebDriver driver) {

        driver.navigate().to(APP_URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));

        // Session already active from a previous scenario — skip Google OAuth entirely
        if (hooks.isLoggedIn) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.INLINE_SEARCH_BOX));
            System.out.println("Session active — Google login skipped.");
            return;
        }

        // First run: full Google login
        WebElement googleLoginButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.LOGIN_GOOGLE_BUTTON));
        googleLoginButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.LOGIN_EMAIL_FIELD));
        emailField.sendKeys(ConfigReader.get("username"));

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.LOGIN_NEXT_BUTTON));
        nextButton.click();

        // Wait for 2FA screen — approve on your phone
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.LOGIN_TWO_STEP_HEADING));

        // Wait for the main app to load after 2FA approval
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.INLINE_SEARCH_BOX));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.HOME_CONTENT_AREA));

        hooks.isLoggedIn = true;

        System.out.println("Login successful — session established for this suite run.");
    }
}