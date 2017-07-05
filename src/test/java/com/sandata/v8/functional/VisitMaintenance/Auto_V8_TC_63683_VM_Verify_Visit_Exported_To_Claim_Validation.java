package com.sandata.v8.functional.VisitMaintenance;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Nur on 06/02/2017.
 */
public class Auto_V8_TC_63683_VM_Verify_Visit_Exported_To_Claim_Validation extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    //VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
   // NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_63683_VM_Verify_Visit_Exported_To_Claim_Validation(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate To Visit Maintenance Menu";
        navigation.navigateToVisitMaintenance();
        
        testStep = "Functional Step 3 : Enter From Date";
        vmFilter.EnterFromDate();
        
        testStep = "Functional Step 3 : Select Status to Omit";
        wrapper.selectByValue("VisitMaintenance_Status_DD", "Omit");
        
        
        testStep = "Functional Step 4 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 5 : Wait for Loading The Data";
        wrapper.holdOn(2000);
        
        boolean ischk2 = wrapper.isChecked("DoNotBill_Chkbx");
        System.out.print(ischk2);
        
     
        testStep = "Functional Step 6 : Click on Checkbox";
        wrapper.click("DoNotBill_Chkbox_2");
        
        testStep = "Functional Step 7 : Checking the Visit Status Message";
        wrapper.validateInnerTextContains("Status_Msg", "Success_Msg");
        
        vmFilter.re_Search();
        
        testStep = "Functional Step 3 : Select Status to Omit";
        wrapper.selectByValue("VisitMaintenance_Status_DD", "Incomplete");
        
        testStep = "Functional Step 4 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 5 : Wait for Loading The Data";
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 6 : Checking whether Do not Bill Checkbox is Enable or Disable";
     //   vmFilter.checkboxStatus("DoNotBill_Chkbx","checked");
        
        boolean ischk = wrapper.isChecked("DoNotBill_Chkbx");
        System.out.print(ischk);
        
        
        

    }
}