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
public class Auto_V8_TC_66994_Navigation_Verify_UI_Changes_In_VM_And_Visit_Detail extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_66994_Navigation_Verify_UI_Changes_In_VM_And_Visit_Detail(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Click to Expand the Security menu";
        navigation.navigateToVisitMaintenance();

        testStep = "Validate that The Date Filter Has AM and PM Option";
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        vmFilter.VeriFy_AM_PM("StartDate","EndDate");
        
        testStep = "Validate that Delete Button Is Not Displayed";
        vmFilter.CheckDelete("Delete");
        
        testStep = "Validate that Advance Filter Option is Hidden";
        vmFilter.CheckDelete("VisitType");
        
        testStep = "Functional Step 3 : Enter From Date";
        vmFilter.EnterFromDate();
        
        testStep = "Functional Step 4 : Click On Search";
        vmFilter.clickOnSearch();
        
        testStep = "Functional Step 5 : Wait for Loading The Data";
        wrapper.holdOn(2000);
        
        wrapper.click("ClientName");
        
        wrapper.click("VisitDetail_GeneralTab_BTN");
        
        

        
    }
}
