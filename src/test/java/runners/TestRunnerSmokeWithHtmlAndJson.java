package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@Smoke",
        features = "src/test/java/features",
        glue = "stepDefinitions",
        plugin = {
                "pretty",
                "html:target/reports/smoke-html-report.html",
                "json:target/reports/smoke-json-report.json"
        },
        dryRun = false
)
public class TestRunnerSmokeWithHtmlAndJson extends AbstractTestNGCucumberTests {
}
