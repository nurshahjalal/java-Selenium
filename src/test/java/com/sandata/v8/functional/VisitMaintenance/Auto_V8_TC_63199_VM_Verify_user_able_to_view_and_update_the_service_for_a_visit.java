package com.sandata.v8.functional.VisitMaintenance;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Auto_V8_TC_63199_VM_Verify_user_able_to_view_and_update_the_service_for_a_visit extends BaseTest {
    LoginPage login = new LoginPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    Wrapper wrapper = new Wrapper();
    NotificationPage notify = new NotificationPage();

    @Test(dataProvider="td")
    public void Auto_V8_TC_63199_VM_Verify_user_able_to_view_and_update_the_service_for_a_visit(String dummyData) throws IOException, InterruptedException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Enter From Date";
        vmFilter.EnterDate("VisitMaintenance_StartDate_ED", "06/14/2017");
        
        testStep = "Functional Step 3 : Enter To Date";
        vmFilter.EnterDate("VisitMaintenance_EndDate_ED", "06/14/2017");
        
        testStep = "Functional Step 4 : Enter the Client";
        vmFilter.enterFilterClient("VisitMaintenance_Client_ED");
        
        testStep = "Functional Step 5 : Click On Search";
        vmFilter.clickOnSearch();
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 6 : Click On Client on the result Grid";
        wrapper.click("Visit_Date");
        
        testStep = "Functional Step 7 : Select HHA from the Service Drop Down";        
        vmFilter.selectFromDropDown("VM_Detail_Service_DD", "HHA");
      
        testStep = "Functional Step 8 : Select an option from the Reason Code Drop Down";
        wrapper.selectByIndex("VM_Detail_Reason_code", 3); 
        
        testStep = "Functional Step 9 : Click On Save Button";
        wrapper.click("VM_Detail_Save_Button");
        
        testStep = "Functional Step 10 : Validate and close the notification";
        notify.validateMessage("Status_Msg");
        wrapper.click("Status_Msg");
        
        testStep = "Functional Step 11 : Close the Visit Detail window";
        wrapper.click("VisitDetail_Close_BTN");
        Thread.sleep(2000);
        
        testStep = "Functional Step 12 : Click On Client on the result Grid";
        wrapper.click("Visit_Date");
        Thread.sleep(2000);
        
        testStep = "Functional Step 13 : Validate Service Drop Down displays HHA ";
        vmFilter.validateCurrentSlctnFrmDropDown("VM_Detail_Service_DD", "VM_Detail_Service_DD");
        
        testStep = "Functional Step 14 : Select LPN from the Service Drop Down";
        vmFilter.selectFromDropDown("VM_Detail_Service_DD", "LPN");
        Thread.sleep(2000);
        
        testStep = "Functional Step 15 : Click On Cancel Button";
        wrapper.click("Visit_Details_Cancel");
        Thread.sleep(2000);
        
        testStep = "Functional Step 16 : Click On Client on the result Grid";
        wrapper.click("Visit_Date");
        
        testStep = "Functional Step 17 : Validate Service Drop Down displays HHA ";
        vmFilter.validateCurrentSlctnFrmDropDown("VM_Detail_Service_DD", "VM_Detail_Service_DD");
        
        testStep = "Functional Step 18 : Select RNA from the Service Drop Down";
        vmFilter.selectFromDropDown("VM_Detail_Service_DD", "RNA");

        testStep = "Functional Step 19 : Select an option from the Reason Code Drop Down";
        wrapper.selectByIndex("VM_Detail_Reason_code", 3); 
        
        testStep = "Functional Step 20 : Click On Save Button";
        wrapper.click("VM_Detail_Save_Button");
        
        testStep = "Functional Step 21 : Validate and close the notification";
        notify.validateMessage("Status_Msg");
        wrapper.click("Status_Msg");
        
        testStep = "Functional Step 22 : Close the Visit Detail window";
        wrapper.click("VisitDetail_Close_BTN");
        Thread.sleep(2000);
        
        testStep = "Functional Step 23 : Click On Client on the result Grid";
        wrapper.click("Visit_Date");
        Thread.sleep(2000);
        
        testStep = "Functional Step 24 : Validate Service Drop Down displays RNA ";
        vmFilter.validateCurrentSlctnFrmDropDown("VM_Detail_Service_DD", "VM_Detail_Service_DD2");
        
        testStep = "Functional Step 25 : Select 'Select Service' from the Service Drop Down";
        wrapper.selectByIndex("VM_Detail_Service_DD", 0); 

        testStep = "Functional Step 26 : Select an option from the Reason Code Drop Down";
        wrapper.selectByIndex("VM_Detail_Reason_code", 3); 
        
        testStep = "Functional Step 27 : Click On Save Button";
        wrapper.click("VM_Detail_Save_Button");
    
        testStep = "Functional Step 28 : Validate and close the notification";
        notify.validateMessage("Status_Msg");
        wrapper.click("Status_Msg");
        
        testStep = "Functional Step 29 : Close the Visit Detail window";
        wrapper.click("VisitDetail_Close_BTN");
        Thread.sleep(2000);
        
        testStep = "Functional Step 30 : Click On Client on the result Grid";
        wrapper.click("Visit_Date");
        Thread.sleep(2000);
       
        testStep = "Functional Step 31 : Click On Exception Tab";
        wrapper.click("VM_Detail_ExceptionTab");
        
        testStep = "Functional Step 32 : Validate There are Missing Service Exception ";
        wrapper.validateInnerTextContainsDirectly("VM_Detail_Exception_MissingService", "Missing service");
        
        
   }
}
