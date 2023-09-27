package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;

public class OnlineBankingPage extends Parent{
    public OnlineBankingPage() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(id = "onlineBankingMenu")
    public WebElement onlineBanking;
    @FindBy(id = "pay_bills_link")
    public WebElement payBills;
    @FindBy(linkText = "Purchase Foreign Currency")
    public WebElement purchaseForeignCurrency;
    @FindBy(id = "pc_currency")
    public WebElement currencySelect;
    @FindBy(id = "pc_amount")
    public WebElement amount;
    @FindBy(id = "pc_inDollars_true")
    public WebElement dollarCheckbox;
    @FindBy(id = "pc_calculate_costs")
    public WebElement calculateCostsButton;
    @FindBy(id = "purchase_cash")
    public WebElement purchaseButton;
    @FindBy(id = "alert_content")
    public WebElement successMessage;

}
