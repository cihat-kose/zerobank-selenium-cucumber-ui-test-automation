package runners;

import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.GWD;

@CucumberOptions
        (
                tags = "@SmokeTest",
                features = "src/test/java/features",
                glue = "stepDefinitions",
                plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                dryRun = false
        )

public class TestRunnerParallel extends AbstractTestNGCucumberTests {

    @BeforeClass
    @Parameters("browserType")
    public void beforeClass(String browserName){
        GWD.threadBrowserName.set(browserName);
    }

    @AfterClass
    public static void writeExtentReport() {
        ExtentService.getInstance().setSystemInfo("Windows Username", "Bug Hunter"); // Alternate --> System.getProperty("user.name")
        ExtentService.getInstance().setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        ExtentService.getInstance().setSystemInfo("Username", "Cihat");
        ExtentService.getInstance().setSystemInfo("Application Name", "Zero Bank");
        ExtentService.getInstance().setSystemInfo("Operating System Info", System.getProperty("os.name"));
        ExtentService.getInstance().setSystemInfo("Department", "QA");
        ExtentService.getInstance().setSystemInfo("Ek Satır", "Açıklama");
        ExtentService.getInstance().setSystemInfo("Ek Satır", "Açıklama");
        ExtentService.getInstance().setSystemInfo("Ek Satır", "Açıklama");
        ExtentService.getInstance().setSystemInfo("Ek Satır", "Açıklama");
    }
}
