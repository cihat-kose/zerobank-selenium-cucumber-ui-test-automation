package stepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.*;
import utilities.GWD;

public class Hooks {
    @Before
    public void before() {
        System.out.println("The scenario started.");
    }

    @After
    public void after(Scenario scenario) {

        if (scenario.isFailed()) {
            TakesScreenshot screenshot = ((TakesScreenshot) GWD.getDriver());
            byte[] stateInMemory = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(stateInMemory, "image/png", "screenshot name");
        }
        System.out.println("The scenario finished.");
        GWD.quitDriver();
    }
}
