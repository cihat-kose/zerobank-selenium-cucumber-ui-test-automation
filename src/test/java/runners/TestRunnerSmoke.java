package runners;

import io.cucumber.testng.*;

@CucumberOptions
        (
                tags = "@SmokeTest",
                features = {"src/test/java/features"},
                glue = {"stepDefinitions"}
        )

public class TestRunnerSmoke extends AbstractTestNGCucumberTests {
}
