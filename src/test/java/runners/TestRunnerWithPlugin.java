package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                tags = "@Smoke",
                features = "src/test/java/features",
                glue = "stepDefinitions",
                plugin = {"html:target/site/cucumber-pretty.html"},
                dryRun = false
        )

public class TestRunnerWithPlugin extends AbstractTestNGCucumberTests {
}
