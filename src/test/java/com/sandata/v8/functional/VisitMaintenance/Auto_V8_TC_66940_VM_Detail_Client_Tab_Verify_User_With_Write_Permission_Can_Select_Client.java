package com.sandata.v8.functional.VisitMaintenance;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;
import com.sandata.v8.pages.VisitDetailPage;
import com.sandata.v8.pages.VisitMaintenanceFilterPage;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 4/18/2017.
 */
public class Auto_V8_TC_66940_VM_Detail_Client_Tab_Verify_User_With_Write_Permission_Can_Select_Client extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitDetailPage vmDetail = new VisitDetailPage();

    @Test(dataProvider="td")
    public void Auto_V8_TC_66940_VM_Detail_Client_Tab_Verify_User_With_Write_Permission_Can_Select_Client(String dummyData) throws IOException {
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

        testStep = "Functional Step 7 : Open the visit match with the given filter by clicking on the Client Name";
        vmFilter.openVisitDetailBy(XLReader.TestCaseDataMap.get("Client_Name"),XLReader.TestCaseDataMap.get("Employee_Name"),
                XLReader.TestCaseDataMap.get("Visit_Date"),"Client");

        testStep = "Validate the Client Name displayed on header";
        vmDetail.validateHeaderClientName("Client_Name");

        testStep = "Validate the Employee Name displayed on header";
        vmDetail.validateHeaderEmployeeName("Employee_Name");

        testStep = "Validate the Client Section section displayed in the Client tab";
        vmDetail.validateSelectEmployeeSectionDisplayed();

    }
}
