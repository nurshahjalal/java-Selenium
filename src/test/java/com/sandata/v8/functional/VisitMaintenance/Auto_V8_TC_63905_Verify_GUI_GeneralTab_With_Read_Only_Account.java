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
public class Auto_V8_TC_63905_Verify_GUI_GeneralTab_With_Read_Only_Account extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    //VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    DataEntryCreateClientPage dcc = new DataEntryCreateClientPage();
    Wrapper wrapper = new Wrapper();
    VisitDetailPage vd = new VisitDetailPage();

    @Test(dataProvider="td")
    
    public void Auto_V8_TC_63053_VM_Verify_User_Is_Able_To_Filter_Visits_Status(String dummyData) throws IOException {
    	
    	 BaseDriver.GetDriver();
    	 
    	testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate To Visit Maintenance Menu";
        navigation.navigateToVisitMaintenance();
        
        testStep = "Functional Step 3 : Enter From Date";
        vmFilter.EnterFromDate();
        
        //testStep = "Functional Step 4 : Enter Client Name";
        //vmFilter.enterFilterClient("ClientName");
        
        
        testStep = "Functional Step 5 : Click On Search";
        vmFilter.clickOnSearch();
        
        wrapper.holdOn(1500);
        
        testStep = "Functional Step 6 : Click on Visit Mintenance Grid";
        vmFilter.clickOnClientName();
        
        
        
        wrapper.checkObjectNotExist("VD_ScheduleTab");
        wrapper.checkObjectNotExist("VD_AdjustedHour");
        wrapper.checkObjectNotExist("VD_Service");
        
        
        testStep = "Functional Step 7 : Click On Visit Detail Exception Tab";
        wrapper.click("VisitDetail_ExceptionTab_BTN");
        
        testStep = "Functional Step 8 : Checking Fixable and Non Fixable Exceptions";
        vd.CheckExceptions();
        
        testStep = "Functional Step 7 : Click On Visit Detail General Tab";
        wrapper.click("VisitDetail_GeneralTab_BTN");
        
        vd.GeneralTab_VisitItem();
        vd.Check_Visit_Disabality();
        vd.GeneralTab_AgencyItem();
        vd.Check_Agency_Disabality();
        vd.GeneralTab_VisitVerification();
        vd.Check_VisitVerification_Disabality();
        
        wrapper.click("Visit_Details_Cancel");
        

    }
}
