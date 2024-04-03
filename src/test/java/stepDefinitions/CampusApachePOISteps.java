package stepDefinitions;

import io.cucumber.java.en.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Campus;
import pages.Parent;
import utilities.ExcelUtility;
import utilities.GWD;

import java.util.ArrayList;

public class CampusApachePOISteps extends Parent {
    Campus campus = new Campus();

    @Given("Navigate to campus")
    public void navigateToCampus() {
        GWD.getDriver().get("https://test.mersys.io/");
    }

    @When("Enter username and password and click button")
    public void enterUsernameAndPasswordAndClickButton() {
        campus.mySendKeys(campus.username, "turkeyts");
        campus.mySendKeys(campus.password, "TechnoStudy123");
        campus.myClick(campus.loginButton);
    }

    @Then("User should login successfully")
    public void userShouldLoginSuccessfully() {
        campus.verifyContainsText(campus.textTechnoStudy, "Internship");
    }

    @And("Navigate to position categories")
    public void navigateToPositionCategories() {
        campus.myClick(campus.humanResources);
        campus.myClick(campus.setupHumanResources);
        campus.myClick(campus.positionCategories);
    }

    @And("User add a position category with ApachePOI")
    public void userAddAPositionCategoryWithApachePOI() {

        ArrayList<ArrayList<String>> table =
                ExcelUtility.getData
                        ("src/test/java/apachePOI/resource/_CampusPositionCategories.xlsx",
                                "Position Categories", 2);

        for (int i = 0; i < table.size(); i++) {
            ArrayList<String> row = table.get(i);
            campus.addItem(row.get(0));
            wait.until(ExpectedConditions.visibilityOfAllElements(campus.successMessage));
            campus.verifyContainsText(campus.successMessage, "success");
        }
    }

    @And("User edit the position category with ApachePOI")
    public void userEditThePositionCategoryWithApachePOI() {

        ArrayList<ArrayList<String>> table =
                ExcelUtility.getData
                        ("src/test/java/apachePOI/resource/_CampusPositionCategories.xlsx",
                                "Position Categories", 2);

        for (int i = 0; i < table.size(); i++) {
            ArrayList<String> row = table.get(i);
            campus.editItem(row.get(0), row.get(1));
            wait.until(ExpectedConditions.visibilityOfAllElements(campus.successMessage));
            campus.verifyContainsText(campus.successMessage, "success");
        }
    }

    @And("User delete the position category with ApachePOI")
    public void userDeleteThePositionCategoryWithApachePOI() {

        ArrayList<ArrayList<String>> table =
                ExcelUtility.getData
                        ("src/test/java/apachePOI/resource/_CampusPositionCategories.xlsx",
                                "Position Categories", 2);

        for (int i = 0; i < table.size(); i++) {
            ArrayList<String> row = table.get(i);
            campus.deleteItem(row.get(1));
            wait.until(ExpectedConditions.visibilityOfAllElements(campus.successMessage));
            campus.verifyContainsText(campus.successMessage, "success");
        }
    }
}
