package newHooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class hooks {

    public static WebDriver driver;
    public static boolean isLoggedIn = false;

    @Before
    public void setUp() {

        if (driver == null) {
            driver = new SafariDriver();
            driver.manage().window().maximize();
            System.out.println("Safari Driver initialized in Hooks: " + driver);
        }
    }

    @AfterAll
    public static void tearDown() {

        if (driver != null) {
            driver.quit();
            System.out.println("Safari Driver quit in hooks.");
            driver = null;
            isLoggedIn = false;
        }
    }
}