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
public class Auto_V8_TC_63662_VM_Verify_User_Cannot_Turn_On_Do_Not_Bill_Checkbox extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    //VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
   // NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_63662_VM_Verify_User_Cannot_Turn_On_Do_Not_Bill_Checkbox(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate To Visit Maintenance Menu";
        navigation.navigateToVisitMaintenance();
        
        
        
        testStep = "Functional Step 3 : Enter From Date";
        vmFilter.EnterFromDate();
        
        testStep = "Functional Step 4 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 5 : Wait for Loading The Data";
        wrapper.holdOn(2000);
        
     
        testStep = "Functional Step 6 : Checking whether Do not Bill Checkbox is Enable or Disable";
        vmFilter.checkDoNotBill_CheckBox("DoNotBill_Chkbx", "Unchecked");
 

    }
}
