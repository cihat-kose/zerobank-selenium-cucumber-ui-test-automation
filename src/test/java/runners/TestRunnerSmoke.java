package runners;

import io.cucumber.testng.*;

@CucumberOptions
        (
                tags = "@Smoke",
                features = {"src/test/java/features"},
                glue = {"stepDefinitions"}
        )

public class TestRunnerSmoke extends AbstractTestNGCucumberTests {
}
