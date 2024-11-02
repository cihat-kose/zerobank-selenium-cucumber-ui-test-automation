package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.ZBOnlineBanking;

import java.util.List;

public class ZBAddNewPayeeSteps {

    ZBOnlineBanking zbOnlineBanking=new ZBOnlineBanking();

    @When("Click on the element in OnlineBanking")
    public void clickOnTheElementInOnlineBanking(DataTable links) {

        List<String> linksList = links.asList();

        for (int i = 0; i < linksList.size(); i++) {
            String link= linksList.get(i);
            WebElement element= zbOnlineBanking.getWebElement(link);
            zbOnlineBanking.myClick(element);
        }
    }

    @And("User sending name, address, account and details in OnlineBanking")
    public void userSendingNameAddressAccountAndDetailsInOnlineBanking(DataTable dataTable) {

        List<List<String>> items = dataTable.asLists();

        for (int i = 0; i < items.size(); i++) {
            List<String> item=items.get(i);
            WebElement element= zbOnlineBanking.getWebElement(item.get(0));
            String text=item.get(1);
            zbOnlineBanking.mySendKeys(element,text);
        }
    }
}
