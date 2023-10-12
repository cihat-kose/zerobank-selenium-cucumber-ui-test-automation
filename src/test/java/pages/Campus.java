package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.GWD;

public class Campus extends Parent {
    public Campus() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(css = "input[formcontrolname='username']")
    public WebElement username;
    @FindBy(css = "input[formcontrolname='password']")
    public WebElement password;
    @FindBy(css = "button[aria-label='LOGIN']")
    public WebElement loginButton;
    @FindBy(css = "span[class='mat-mdc-tooltip-trigger logo-text']")
    public WebElement textTechnoStudy;
    @FindBy(xpath = "//ms-add-button[contains(@tooltip,'ADD')]//button")
    public WebElement addButton;
    @FindBy(xpath = "//ms-text-field[@formcontrolname='name']/input")
    public WebElement nameInput;
    @FindBy(xpath = "//ms-save-button/button")
    public WebElement saveButton;
    @FindBy(xpath = "//div[contains(text(),'successfully')]")
    public WebElement successMessage;
    @FindBy(xpath = "//mat-form-field//input[@data-placeholder='Name']")
    public WebElement searchInput;
    @FindBy(xpath = "//ms-search-button//button")
    public WebElement searchButton;
    @FindBy(xpath = "(//ms-delete-button//button)[1]")
    public WebElement deleteImageButton;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement deleteDialogButton;
    @FindBy(xpath = "(//ms-edit-button)[1]")
    public WebElement editButton;
    @FindBy(xpath = "(//span[text()='Setup'])[1]")
    public WebElement setup;
    @FindBy(xpath = "//span[text()='Human Resources']")
    public WebElement humanResources;
    @FindBy(xpath = "(//span[text()='Setup'])[3]")
    public WebElement setupHumanResources;
    @FindBy(xpath = "//span[text()='Position Categories']")
    public WebElement positionCategories;

    public void addItem(String newItem) {
        myClick(addButton);
        mySendKeys(nameInput, newItem);
        myClick(saveButton);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//fuse-progress-bar/*"), 0));
    }

    public void deleteItem(String searchText) {
        mySendKeys(searchInput, searchText);
        myClick(searchButton);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//fuse-progress-bar/*"), 0));
        myClick(deleteImageButton);
        myClick(deleteDialogButton);
    }

    public void editItem(String oldText, String newText) {
        mySendKeys(searchInput, oldText);
        myClick(searchButton);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//fuse-progress-bar/*"), 0));
        myClick(editButton);
        mySendKeys(nameInput, newText);
        myClick(saveButton);
    }
}
