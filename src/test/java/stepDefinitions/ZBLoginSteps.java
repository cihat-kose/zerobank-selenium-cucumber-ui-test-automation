package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ZBLoginPage;
import utilities.GWD;

public class ZBLoginSteps {
    ZBLoginPage zbLoginPage = new ZBLoginPage();

    @Given("Navigate to website")
    public void navigateToWebsite() {
        GWD.getDriver().get("http://zero.webappsecurity.com/login.html");
    }

    @And("Enter username that as {string} and password that as {string}")
    public void enterUsernameThatAsAndPasswordThatAs(String username, String password) {
        zbLoginPage.mySendKeys(zbLoginPage.username, username);
        zbLoginPage.mySendKeys(zbLoginPage.password, password);
    }

    @When("Click sign in button")
    public void clickSignInButton() {
        zbLoginPage.myClick(zbLoginPage.signInButton);
        GWD.getDriver().navigate().back();
    }

    @Then("Verify that user logged in")
    public void verifyThatUserLoggedIn() {
        Assert.assertTrue(zbLoginPage.userIcon.isEnabled());
    }
}
