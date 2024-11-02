package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.*;
import pages.OnlineBanking;
import utilities.DriverManager;

import java.time.Duration;

public class PurchaseForeignCurrencySteps {

    OnlineBanking onlineBanking = new OnlineBanking();

    @When("Click to Online Banking")
    public void clickToOnlineBanking() {
        onlineBanking.myClick(onlineBanking.onlineBanking);
    }

    @And("Click to Pay Bills")
    public void clickToPayBills() {
        onlineBanking.myClick(onlineBanking.payBills);
    }

    @And("Click to Purchase Foreign Currency")
    public void clickToPurchaseForeignCurrency() {
        onlineBanking.myClick(onlineBanking.purchaseForeignCurrency);
    }

    @And("Select currency that as {string}")
    public void selectCurrencyThatAs(String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(onlineBanking.currencySelect));
        Select select = new Select(onlineBanking.currencySelect);
        select.selectByValue(value);
    }

    @And("Enter to amount that as {string}")
    public void enterToAmountThatAs(String amount) {
        onlineBanking.mySendKeys(onlineBanking.amount, amount);
    }

    @And("Click to U.S. dollar checkbox")
    public void clickToUSDollarCheckbox() {
        onlineBanking.myClick(onlineBanking.dollarCheckbox);
    }

    @And("Click to Calculate Costs button")
    public void clickToCalculateCostsButton() {
        onlineBanking.myClick(onlineBanking.calculateCostsButton);
    }

    @And("Click to Purchase")
    public void clickToPurchase() {
        onlineBanking.myClick(onlineBanking.purchaseButton);
    }

    @Then("Verify that the transaction was successful")
    public void verifyThatTheTransactionWasSuccessful() {
        onlineBanking.verifyContainsText(onlineBanking.successMessage, "success");
    }
}
