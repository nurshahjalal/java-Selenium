package com.sandata.v8.functional.Navigation;

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
public class Auto_V8_TC_67657_Navigation_Open_Visit_Detail_Clicking_Anywhere_In_VM_Grid extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_67657_Navigation_Open_Visit_Detail_Clicking_Anywhere_In_VM_Grid(String dummyData) throws IOException {
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
        
        testStep = "Functional Step 6 : Clicking Every Column and Checking Visit Details Page";
        vmFilter.clickEveryColumn("VisitMaintenance_VisitGrid_TBL");
        
        testStep = "Functional Step 7 : Click On Show Details Options ";
        wrapper.click("VisitMaintenance_ShowDisplayOptions_BTN");
        
        testStep = "Functional Step 8 : Click On Memo";
        wrapper.click("Memo_CheckBox");
        
        testStep = "Functional Step 9 : Click and Minimize the Show Details Options";
        wrapper.click("VisitMaintenance_ShowDisplayOptions_BTN");
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 10 : Click On Memo Column";
        wrapper.click("Memo_BTN");
        
        testStep = "Functional Step 3 : Checking Visit Details Page";
        wrapper.CheckObjectExist("Visit_Details_Text");
        
        testStep = "Functional Step 3 : Closing Visit Details Page";
        wrapper.click("Visit_Details_Cancel");
        
        
        
        
        
        
        

    }
}
