package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by vumvo on 3/20/2017.
 */
public class DataEntryEditClientPage {
    Wrapper baseObj = new Wrapper();

    public DataEntryEditClientPage validateOptionsOnRelationshipDropdown(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        List<WebElement> options = baseObj.getOptions("CreateClient_Relationship_DD");
        for (int i=0;i<options.size();i++){
            String expectedValue = XLReader.TestCaseDataMap.get("CreateClient_Relationship_DD_"+i);
            if (expectedValue != null && !"".equals(expectedValue)){
                String actualValue = options.get(i).getText();
                Assert.assertEquals(actualValue, expectedValue);
            }
        }
        baseObj.holdOn(5000);
        return this;
    }

    public DataEntryEditClientPage enterRequireFields(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("CreateClient_FirstName_ED","CreateClient_FirstName_ED");
        baseObj.setTextBoxValue("CreateClient_LastName_ED","CreateClient_LastName_ED");
        baseObj.setTextBoxValue("CreateClient_DOB_ED","CreateClient_DOB_ED");
        baseObj.setTextBoxValue("CreateClient_ClientID_ED","CreateClient_ClientID_ED");
        return this;
    }

    public DataEntryEditClientPage addNewPhoneNumber(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("CreateClient_NewPhoneNumber_ED","CreateClient_NewPhoneNumber_ED");
        if("true".equalsIgnoreCase(XLReader.TestCaseDataMap.get("CreateClient_PrimaryPhone_CHK"))){
            baseObj.getElement("CreateClient_PrimaryPhone_CHK").click();
        }
        baseObj.click("CreateClient_AddNewPhone_BTN");
        baseObj.holdOn(15000);
        return this;
    }

    public DataEntryEditClientPage validateEditPageIsLoadForClientID(String clientID){
        String actualClientID = baseObj.getElement("EditClient_ClientID_ED").getAttribute("value");
        Assert.assertEquals(actualClientID, clientID);
        return this;
    }

    public DataEntryEditClientPage saveClientSuccessfully(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("CreateClient_Create_BTN");
        baseObj.holdOn(2000);
        baseObj.click("Confirmation_OK_BTN");
        return this;
    }
	

}
