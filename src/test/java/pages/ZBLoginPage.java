package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;

public class ZBLoginPage extends Parent {

    public ZBLoginPage() {
        PageFactory.initElements(GWD.getDriver(),this);
    }

    @FindBy(id ="user_login" )
    public WebElement username;

    @FindBy(id = "user_password")
    public WebElement password;

    @FindBy(css ="[name='submit']" )
    public WebElement signInButton;

}
