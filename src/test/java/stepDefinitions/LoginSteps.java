package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Login;
import utilities.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;


public class LoginSteps {
    Login login = new Login();

    @Given("Navigate to login page")
    public void navigateToLoginPage() {
        DriverManager.getDriver().get("http://zero.webappsecurity.com/login.html");
    }

    @And("Enter username that as {string} and password that as {string}")
    public void enterUsernameThatAsAndPasswordThatAs(String username, String password) {
        login.mySendKeys(login.username, username);
        login.mySendKeys(login.password, password);
    }

    @When("Click Sign In button")
    public void clickSignInButton() {
        try {
            login.myClick(login.signInButton);
        } catch (WebDriverException exception) {
            // Only the public demo's known Firefox HTTPS redirect error is tolerated.
            String message = exception.getMessage();
            if (!message.contains("nssFailure2") ||
                    !message.contains("zero.webappsecurity.com/auth/accept-certs.html")) {
                throw exception;
            }
        }
        // The public demo redirects to HTTPS after login. Return to its HTTP home
        // explicitly while retaining the authenticated session cookie.
        login.wait.until(ExpectedConditions.urlContains("/auth/accept-certs.html"));
        DriverManager.getDriver().get("http://zero.webappsecurity.com/index.html");
    }

    @Then("Verify that the authenticated home page is displayed")
    public void verifyThatTheAuthenticatedHomePageIsDisplayed() {
        String expectedUrl = "http://zero.webappsecurity.com/index.html";
        login.wait.until(ExpectedConditions.urlToBe(expectedUrl));
        login.wait.until(ExpectedConditions.visibilityOf(login.userIcon));
        Assert.assertTrue(login.userIcon.findElement(By.xpath("..")).getText().contains("username"),
                "The authenticated username must be visible.");
    }

    @And("Verify that user icon is displayed")
    public void verifyThatUserIconIsDisplayed() {
        Assert.assertTrue(login.wait.until(ExpectedConditions.visibilityOf(login.userIcon)).isDisplayed(),
                "User icon is not displayed, login may have failed.");
    }

    @When("Submit invalid login credentials")
    public void submitInvalidLoginCredentials() {
        login.myClick(login.signInButton);
    }

    @Then("Verify that login is rejected")
    public void verifyThatLoginIsRejected() {
        login.verifyContainsText(login.loginError, "Login and/or password are wrong.");
        Assert.assertTrue(login.username.isDisplayed(), "Login form must remain available.");
        Assert.assertTrue(DriverManager.getDriver().findElements(By.cssSelector(".icon-user")).isEmpty(),
                "Invalid credentials must not create an authenticated session.");
    }

    @And("Retrieve and enter username and password from Excel file {string}")
    public void retrieveAndEnterUsernameAndPasswordFromExcelFileZeroBankDataXlsx(String fileName) throws IOException {

        String path = "src/test/java/apachePOI/" + fileName;

        try (FileInputStream fileInputStream = new FileInputStream(path);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            Sheet sheet = workbook.getSheet("Sheet1");
            if (sheet == null || sheet.getRow(4) == null) {
                throw new IllegalArgumentException("Missing credentials in Sheet1, row 5: " + path);
            }
            org.apache.poi.ss.usermodel.DataFormatter formatter = new org.apache.poi.ss.usermodel.DataFormatter();
            String username = formatter.formatCellValue(sheet.getRow(4).getCell(5));
            String password = formatter.formatCellValue(sheet.getRow(4).getCell(6));
            if (username.isBlank() || password.isBlank()) {
                throw new IllegalArgumentException("Missing credentials in cells F5/G5: " + path);
            }
            login.mySendKeys(login.username, username);
            login.mySendKeys(login.password, password);
        }
    }
}
