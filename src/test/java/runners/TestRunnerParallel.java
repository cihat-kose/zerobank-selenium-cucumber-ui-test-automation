package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Suite-level TestNG reporting avoids multiple browser runners writing the same Cucumber report.
@CucumberOptions(
        tags = "@Smoke",
        features = "src/test/java/features",
        glue = "stepDefinitions",
        plugin = {"pretty"}
)
public class
TestRunnerParallel extends AbstractTestNGCucumberTests {
}
