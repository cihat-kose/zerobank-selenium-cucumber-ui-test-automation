package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
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
        login.myClick(login.signInButton);
        DriverManager.getDriver().navigate().back();
    }

    @Then("Verify that user is redirected to account summary page")
    public void verifyThatUserIsRedirectedToAccountSummaryPage() {
        String expectedUrl = "http://zero.webappsecurity.com/index.html";
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), expectedUrl, "User was not redirected to account summary page.");
    }

    @And("Verify that user icon is displayed")
    public void verifyThatUserIconIsDisplayed() {
        Assert.assertTrue(login.userIcon.isDisplayed(), "User icon is not displayed, login may have failed.");
    }

    @And("Enter username and password from ZeroBankData")
    public void enterUsernameAndPasswordFromZeroBankData() throws IOException {

        String path = "src/test/java/apachePOI/ZeroBankData.xlsx";

        FileInputStream fileInputStream = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet("Sheet1");

        String username = String.valueOf(sheet.getRow(4).getCell(5));
        String password = String.valueOf(sheet.getRow(4).getCell(6));

        login.mySendKeys(login.username, username);
        login.mySendKeys(login.password, password);
    }
}
