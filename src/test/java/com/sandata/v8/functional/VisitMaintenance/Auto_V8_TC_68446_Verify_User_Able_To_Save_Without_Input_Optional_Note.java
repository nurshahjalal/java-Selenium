package com.sandata.v8.functional.VisitMaintenance;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nur on 06/02/2017.
 */
public class Auto_V8_TC_68446_Verify_User_Able_To_Save_Without_Input_Optional_Note extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    //VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    DataEntryCreateClientPage dcc = new DataEntryCreateClientPage();
    Wrapper wrapper = new Wrapper();
    VisitDetailPage vd = new VisitDetailPage();

    @Test(dataProvider="td")
    
    public void Auto_V8_TC_68446_Verify_User_Able_To_Save_Without_Input_Optional_Note(String dummyData) throws IOException {
    	
    	 BaseDriver.GetDriver();
    	 
    	testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate To Visit Maintenance Menu";
        navigation.navigateToVisitMaintenance();
        
        testStep = "Functional Step 3 : Enter From Date";
        vmFilter.EnterFromDate();
        
        testStep = "Functional Step 4 : Enter Client Name";
        vmFilter.enterFilterClient("ClientName");
        
        
        testStep = "Functional Step 5 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 6 : Click on Visit Mintenance Grid";
        vmFilter.clickOnClientName();
        
        wrapper.holdOn(1500);
        
        testStep = "Functional Step 7 : Click On Visit Detail General Tab";
        wrapper.click("VisitDetail_GeneralTab_BTN");
        
        testStep = "Functional Step 8 : Enter Adjusted In and Out Time";
        vd.Update_General_AdjustedTime();
        
        testStep = "Functional Step 9 : Click On Save";
        vd.General_Save();
        
        testStep = "Functional Step 10 : Verifying Reason Code Error";
        wrapper.validateInnerText("VD_General_ReasonCode_Err", "RequiredError");
        
        testStep = "Functional Step 11 : Selecting a Reason Code";
        wrapper.selectByIndex("VD_General_ReasonCode", 2);
        
        testStep = "Functional Step 12 : Click On Save";
        vd.General_Save();
        
        testStep = "Functional Step 13 : Verifying Updated Notification";
        dcc.checkNotificationMsg("visitUpdateNotification");
   
     
        
        
        
        

    }
}
