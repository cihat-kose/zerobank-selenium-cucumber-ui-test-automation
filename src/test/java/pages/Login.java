package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import utilities.DriverManager;

public class Login extends Parent {

    public Login() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(id = "user_login")
    public WebElement username;

    @FindBy(id = "user_password")
    public WebElement password;

    @FindBy(css = "[name='submit']")
    public WebElement signInButton;

    @FindBy(css = "[class='icon-user']")
    public WebElement userIcon;
}
