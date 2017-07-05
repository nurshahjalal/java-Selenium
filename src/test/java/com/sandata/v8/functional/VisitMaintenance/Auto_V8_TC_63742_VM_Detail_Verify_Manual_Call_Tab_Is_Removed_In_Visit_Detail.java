package com.sandata.v8.functional.VisitMaintenance;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 4/18/2017.
 */
public class Auto_V8_TC_63742_VM_Detail_Verify_Manual_Call_Tab_Is_Removed_In_Visit_Detail extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitDetailPage vmDetail = new VisitDetailPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td", groups = {"smoke"})
    public void Auto_V8_TC_63742_VM_Detail_Verify_Manual_Call_Tab_Is_Removed_In_Visit_Detail(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate to Visit Maintenance Module";
        navigation.navigateToVisitMaintenance();

        testStep = "Functional Step 3 : Enter Visit Date filter of the new call";
        vmFilter.enterFilterDateRangeFilter("Filter_StartDate","Filter_End_Date");

        testStep = "Functional Step 4 : Enter Client Name filter of the new call";
        vmFilter.enterFilterClient("Client_Name");

        testStep = "Functional Step 5 : Enter Employee Name filter of the new call";
        vmFilter.enterFilterEmployee("Employee_Name");

        testStep = "Functional Step 6 : Click Search button";
        vmFilter.clickOnSearch();

        testStep = "Functional Step 7 : Open the visit match with the given filter";
        vmFilter.openVisitDetailBy(XLReader.TestCaseDataMap.get("Client_Name"),XLReader.TestCaseDataMap.get("Employee_Name"),
                XLReader.TestCaseDataMap.get("Visit_Date"),"Employee");

        testStep = "Functional Step 8 : Validate there is not tab with MANUAL CALLS displayed on the Visit Detail";
        vmDetail.validateManualTabNotExist();
    }
}
