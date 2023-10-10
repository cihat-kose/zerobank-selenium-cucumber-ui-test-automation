package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.*;
import pages.OnlineBanking;
import utilities.GWD;

import java.time.Duration;

public class PurchaseForeignCurrencySteps {

    OnlineBanking obp = new OnlineBanking();

    @When("Click to Online Banking")
    public void clickToOnlineBanking() {
        obp.myClick(obp.onlineBanking);
    }

    @And("Click to Pay Bills")
    public void clickToPayBills() {
        obp.myClick(obp.payBills);
    }

    @And("Click to Purchase Foreign Currency")
    public void clickToPurchaseForeignCurrency() {
        obp.myClick(obp.purchaseForeignCurrency);
    }

    @And("Select currency that as {string}")
    public void selectCurrencyThatAs(String value) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(obp.currencySelect));
        Select select = new Select(obp.currencySelect);
        select.selectByValue(value);
    }

    @And("Enter to amount that as {string}")
    public void enterToAmountThatAs(String amount) {
        obp.mySendKeys(obp.amount, amount);
    }

    @And("Click to U.S. dollar checkbox")
    public void clickToUSDollarCheckbox() {
        obp.myClick(obp.dollarCheckbox);
    }

    @And("Click to Calculate Costs button")
    public void clickToCalculateCostsButton() {
        obp.myClick(obp.calculateCostsButton);
    }

    @And("Click to Purchase")
    public void clickToPurchase() {
        obp.myClick(obp.purchaseButton);
    }

    @Then("Verify that the transaction was successful")
    public void verifyThatTheTransactionWasSuccessful() {
        obp.verifyContainsText(obp.successMessage, "success");
    }
}
