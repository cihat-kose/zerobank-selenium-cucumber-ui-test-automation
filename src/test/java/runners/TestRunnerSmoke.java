package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@Smoke",
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        plugin = {"pretty"}
)
public class TestRunnerSmoke extends AbstractTestNGCucumberTests {
}
