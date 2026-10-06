package newScreens;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Screenshot {

    private final WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String testName) {
        try {
            Path screenshotDir = Paths.get("target", "screenshots");
            Files.createDirectories(screenshotDir);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String safeName  = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
            Path dest = screenshotDir.resolve(timestamp + "-" + safeName + ".png");

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + dest.toAbsolutePath());
        } catch (IOException | WebDriverException e) {
            System.err.println("Screenshot failed: " + e.getMessage());
        }
    }
}
