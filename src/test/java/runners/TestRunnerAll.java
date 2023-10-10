package runners;

import io.cucumber.testng.*;

@CucumberOptions
        (
                features = {"src/test/java/features"},
                glue = {"stepDefinitions"}
        )

public class TestRunnerAll extends AbstractTestNGCucumberTests {
}
