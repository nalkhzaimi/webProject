package newTestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import newUtilities.RetryAnalyzer;

@CucumberOptions(
        features = "src/test/java/newResources", // Path to your feature files
        glue = {"newSteps", "newHooks"}  ,            // Path to your step definitions
       plugin = {
                   "pretty",
                    "html:target/HtmlBasicReport.html",
                   "json:target/cucumber.json" ,
                   "junit:target/cukes.xml",
                    "rerun:target/rerun.txt"
               },
        tags = "@oneshot"
)


@org.testng.annotations.Listeners({newUtilities.RetryListener.class})
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @Test(dataProvider = "scenarios", retryAnalyzer = RetryAnalyzer.class)
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
