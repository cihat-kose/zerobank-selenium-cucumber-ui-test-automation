package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DriverManager;
import utilities.ExcelUtility;

public class Hooks {
    @Before
    public void before() {
        System.out.println("The scenario has started.");
    }

    @After
    public void after(Scenario scenario) {

        ExcelUtility.writeToExcel("src/test/java/apachePOI/ScenarioResults.xlsx",
                scenario, DriverManager.getThreadBrowserName());

        if (scenario.isFailed()) {
            TakesScreenshot screenshot = ((TakesScreenshot) DriverManager.getDriver());
            byte[] stateInMemory = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(stateInMemory, "image/png", "screenshot name");
        }

        System.out.println("The scenario finished.");

        DriverManager.quitDriver();
    }
}
