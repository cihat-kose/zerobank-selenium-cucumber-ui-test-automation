package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import utilities.GWD;

public class LoginSteps {

    LoginPage lp=new LoginPage();

    @Given("Navigate to website")
    public void navigateToWebsite() {
        GWD.getDriver().get("http://zero.webappsecurity.com/login.html");
    }

    @And("Enter username that as {string} and password that as {string}")
    public void enterUsernameThatAsAndPasswordThatAs(String username, String password) {
        lp.mySendKeys(lp.username, "username");
        lp.mySendKeys(lp.password, "password");
    }

    @When("Click login button")
    public void clickLoginButton() {
        lp.myClick(lp.signInButton);
        GWD.getDriver().navigate().back();
    }

    @Then("Verify that user logged in.")
    public void verifyThatUserLoggedIn() {
        Assert.assertTrue(lp.userIcon.isEnabled());
    }
}
