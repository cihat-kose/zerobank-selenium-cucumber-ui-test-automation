package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@Regression",
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        plugin = {
                "pretty", // More readable output in the console
                "html:target/cucumber-report-regression.html", // HTML report
                "json:target/cucumber-report-regression.json"  // JSON report
        },
        dryRun = false // If true, checks for missing step definitions without running tests
)
public class TestRunnerRegression extends AbstractTestNGCucumberTests {
}
