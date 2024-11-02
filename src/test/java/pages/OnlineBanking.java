package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import utilities.DriverManager;

public class OnlineBanking extends Parent {

    public OnlineBanking() {
        PageFactory.initElements(DriverManager.getDriver(), this);
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

    @FindBy(linkText = "Add New Payee")
    public WebElement addNewPayee;

    @FindBy(id = "np_new_payee_name")
    public WebElement payeeName;

    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddress;

    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccount;

    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetails;

    @FindBy(id = "add_new_payee")
    public WebElement addButton;

    public WebElement getWebElement(String stringElement) {
        switch (stringElement) {
            case "onlineBanking":
                return this.onlineBanking;
            case "payBills":
                return this.payBills;
            case "addNewPayee":
                return this.addNewPayee;
            case "payeeName":
                return this.payeeName;
            case "payeeAddress":
                return this.payeeAddress;
            case "payeeAccount":
                return this.payeeAccount;
            case "payeeDetails":
                return this.payeeDetails;
        }
        return null;
    }
}
