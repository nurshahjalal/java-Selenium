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
public class Auto_V8_TC_62716_VM_View_And_Update_Memo_Tab extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    VisitDetailPage vdPage = new VisitDetailPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_62716_VM_View_And_Update_Memo_Tab(String dummyData) throws IOException {
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
        
        testStep = "Functional Step 6 : Clicking On Client Column and Checking Visit Details Page";
        wrapper.click("ClientName");
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 10 : Click On Memo Tab From Visit Details";
        wrapper.click("VisitDetail_MemoTab_BTN");
        
        testStep = "Functional Step 7 : Click On Show Details Options ";
        wrapper.validateInnerText("MemoText","MemoText");
        
        testStep = "Functional Step 10 : Click On Memo Tab From Visit Details";
        vdPage.count_Character("Memo_Character", "512");
        
        wrapper.setTextBoxValue("Memo_Ed", "maxChar");
        
        vdPage.count_Character("Memo_Character", "0");
        
        wrapper.click("SaveMemo");
        
        wrapper.click("MemoCross_BTN");
        
        testStep = "Functional Step 6 : Clicking On Client Column and Checking Visit Details Page";
        wrapper.click("ClientName");
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 10 : Click On Memo Tab From Visit Details";
        wrapper.click("VisitDetail_MemoTab_BTN");
        
        wrapper.validateTextBoxValue("Memo_Ed", "maxChar");
        
        
        
        
        

        
        
        
        
        
        
        

    }
}
