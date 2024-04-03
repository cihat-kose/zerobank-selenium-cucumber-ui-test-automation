package runners;

import io.cucumber.testng.*;

@CucumberOptions
        (
        tags = "@RegressionTest",
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"}
        )

public class TestRunnerRegression extends AbstractTestNGCucumberTests {
}
