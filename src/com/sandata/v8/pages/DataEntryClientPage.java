package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by vumvo on 3/20/2017.
 */
public class DataEntryClientPage {
    Wrapper baseObj = new Wrapper();

    public DataEntryClientPage createClient(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Clients_Create_New_Client_BTN");
        return this;
    }

    public DataEntryClientPage searchClientBy(String clientID, String clientFirstName, String clientLastName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        if (clientID != null){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> by Client ID: "+ clientID+"</b>");
            baseObj.getElement("Clients_SearchClientID_ED").sendKeys(clientID);
        }

        if (clientFirstName != null){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> by Client First Name: "+ clientFirstName+"</b>");
            baseObj.getElement("Clients_SearchClientFirstName_ED").sendKeys(clientFirstName);
        }

        if (clientLastName != null){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> by Client First Name: "+ clientLastName+"</b>");
            baseObj.getElement("Clients_SearchClientLastName_ED").sendKeys(clientLastName);
        }
        return this;
    }

    public DataEntryClientPage search(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Clients_Search_BTN");
        return this;
    }

    public DataEntryClientPage clear(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Clients_Clear_BTN");
        return this;
    }

    public DataEntryClientPage validateClientExistBy(String clientID, String firstName, String lastName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement clientGrid = baseObj.getElement("Clients_ClientGrid_TBL");
        List<WebElement> rows = clientGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
            if(row.findElements(By.xpath("td[.='"+ clientID + "']")).size() > 0){

                List<WebElement> cells = row.findElements(By.cssSelector("td"));
                String actualClientID = cells.get(0).getText().trim();
                Assert.assertEquals(actualClientID,clientID);

                if (firstName != null){
                    String actualFirstName = cells.get(1).getText().trim();
                    Assert.assertEquals(actualFirstName, firstName);
                }

                if (lastName != null){
                    String actualLastName = cells.get(2).getText().trim();
                    Assert.assertEquals(actualLastName, lastName.trim());
                }
                isMatch = true;
            }
            if (isMatch){
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the client ID " + clientID);
        }
        return this;
    }


    public DataEntryClientPage validateNoResultOnVisitGrid(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.CheckObjectExist("VisitMaintenance_SearchNoResult_CELL");
        return this;
    }
    
 

    public DataEntryClientPage editClientWithID(String clientID){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement clientGrid = baseObj.getElement("Clients_ClientGrid_TBL");
        List<WebElement> rows = clientGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
            if(row.findElements(By.xpath("td[.='"+ clientID + "']")).size() > 0){
                WebElement btnEdit = row.findElement(By.cssSelector(".edit-client"));
                btnEdit.click();
                isMatch = true;
            }
            if (isMatch){
                baseObj.waitForBrowserToLoadCompletely();
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the client ID " + clientID);
        }
        return this;
    }

    public DataEntryClientPage deleteClientWithID(String clientID){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement clientGrid = baseObj.getElement("Clients_ClientGrid_TBL");
        List<WebElement> rows = clientGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
            if(row.findElements(By.xpath("td[.='"+ clientID + "']")).size() > 0){
                WebElement btnEdit = row.findElement(By.cssSelector(".delete-client"));
                btnEdit.click();
                isMatch = true;
            }
            if (isMatch){
                baseObj.waitForBrowserToLoadCompletely();
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the client ID " + clientID);
        }
        return this;
    }
    
	
}
