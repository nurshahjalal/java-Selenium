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
public class Auto_V8_TC_63053_VM_Verify_User_Is_Able_To_Filter_Visits_Status extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    //VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
   // NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    
    public void Auto_V8_TC_63053_VM_Verify_User_Is_Able_To_Filter_Visits_Status(String dummyData) throws IOException {
    	
    	 BaseDriver.GetDriver();
    	 
    	testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate To Visit Maintenance Menu";
        navigation.navigateToVisitMaintenance();
        
        testStep = "Functional Step 3 : Enter From Date";
        vmFilter.EnterFromDate();
     
        testStep = "Functional Step 4 : Verify All Options in Status";
        vmFilter.verifyAllOptions();
        
        testStep = "Functional Step 5 : Select Status to Omit";
        wrapper.selectByValue("VisitMaintenance_Status_DD", "In Process");
        
        
        testStep = "Functional Step 6 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 7 : Wait for Loading The Data";
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 8 : Verify Visit Status";
        wrapper.validateInnerText("Visit_Status", "VisitStatus");
        
        
        testStep = "Functional Step 9 : Select Status to Omit";
        wrapper.selectByValue("VisitMaintenance_Status_DD", "Incomplete");
        
        testStep = "Functional Step 10 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 11 : Verify Visit Status";
        wrapper.validateInnerText("Visit_Status", "IncompleteStatus");
        
        
        
        testStep = "Functional Step 12 : Select Status to Omit";
        wrapper.selectByValue("VisitMaintenance_Status_DD", "Verified");
        
        testStep = "Functional Step 13 : Click On Search";
        vmFilter.clickOnSearch();
        
        if (!wrapper.CheckObjectExist("No_Data")){
        	testStep = "Functional Step 5 : Verify Visit Status";
            wrapper.validateInnerText("Visit_Status", "VerifiedStatus");
        }
        
        
        testStep = "Functional Step 14 : Select Status to Omit";
        wrapper.selectByValue("VisitMaintenance_Status_DD", "Processed");
        
        testStep = "Functional Step 15 : Click On Search";
        vmFilter.clickOnSearch();
        
        if (!wrapper.CheckObjectExist("No_Data")){
	        testStep = "Functional Step 5 : Verify Visit Status";
	        wrapper.validateInnerText("Visit_Status", "ProcessedStatus");
        }
        testStep = "Functional Step 16 : Select Status to Omit";
        wrapper.selectByValue("VisitMaintenance_Status_DD", "Omit");
        
        testStep = "Functional Step 17 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 18 : Verify Visit Status";
        wrapper.validateInnerText("Visit_Status", "OmitStatus");
        
        
        
        
        
        
        

    }
}
