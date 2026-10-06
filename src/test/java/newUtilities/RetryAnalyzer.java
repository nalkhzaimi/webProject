package newUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRIES = 3;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRIES) {
            retryCount++;
            System.out.println("Retrying: " + result.getName() + " | attempt " + retryCount + "/" + MAX_RETRIES);
            return true;
        }
        return false;
    }
}
