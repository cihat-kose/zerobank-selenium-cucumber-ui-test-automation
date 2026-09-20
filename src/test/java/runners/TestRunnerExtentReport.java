package runners;

import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        tags = "@Smoke",
        features = "src/test/java/features",
        glue = "stepDefinitions",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        dryRun = false
)
public class TestRunnerExtentReport extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void writeExtentReport() {
        ExtentService.getInstance().setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        ExtentService.getInstance().setSystemInfo("Java Version", System.getProperty("java.version"));
        ExtentService.getInstance().setSystemInfo("Application Name", "Zero Bank");
        ExtentService.getInstance().setSystemInfo("Operating System Info", System.getProperty("os.name"));
        ExtentService.getInstance().setSystemInfo("Department", "QA");
    }
}
