package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.DriverManager;
import utilities.ExcelUtility;

public class Hooks {
    @Before
    public void before() {
        ITestResult result = Reporter.getCurrentTestResult();
        String browser = result == null ? null
                : result.getTestContext().getCurrentXmlTest().getParameter("browserType");
        DriverManager.setThreadBrowserName(browser == null
                ? System.getProperty("browser", "edge") : browser);
        System.out.println("The scenario has started.");
    }

    @After
    public void after(Scenario scenario) {

        try {
            ExcelUtility.writeToExcel(System.getProperty("results.path", "target/results/ScenarioResults.xlsx"),
                    scenario, DriverManager.getThreadBrowserName());

            if (scenario.isFailed() && DriverManager.hasDriver()) {
                TakesScreenshot screenshot = ((TakesScreenshot) DriverManager.getDriver());
                byte[] stateInMemory = screenshot.getScreenshotAs(OutputType.BYTES);
                scenario.attach(stateInMemory, "image/png", "screenshot name");
            }

            System.out.println("The scenario finished.");
        } finally {
            DriverManager.quitDriver();
        }
    }
}
