package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;

public class ZBOnlineBanking extends Parent{

    public ZBOnlineBanking() {
        PageFactory.initElements(GWD.getDriver(),this);
    }

    @FindBy(id = "onlineBankingMenu")
    public WebElement onlineBanking;

    @FindBy(id = "pay_bills_link")
    public WebElement payBills;

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

    @FindBy(id = "alert_content")
    public WebElement successMessage;

    public WebElement getWebElement(String stringElement){
        switch (stringElement){
            case "onlineBanking":
                return onlineBanking;
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
