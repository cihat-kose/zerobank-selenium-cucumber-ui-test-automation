package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import utilities.GWD;

public class ZBLogin extends Parent {

    public ZBLogin() {
        PageFactory.initElements(GWD.getDriver(), this);
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
