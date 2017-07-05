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
public class Auto_V8_TC_65581_VM_Verify_Adding_Manual_Call_Can_Map_To_Actual_Call_In_Call_Out extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_65581_VM_Verify_Error_When_User_Cannot_Add_A_Manual_Call_With_Duplicate_Date_Time(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate to Visit Maintenance Module";
        navigation.navigateToVisitMaintenance();

/*
        testStep = "Functional Step 3 : Click on Create Call button";
        vmFilter.createCall();

        testStep = "Functional Step 5 : Enter a Client filter";
        vmCreateCall.enterClientFilter("Client_Name", null, null, null, null);

        testStep = "Functional Step 6 : Click Search button";
        vmCreateCall.searchClient();

        testStep = "Functional Step 6 : Select an existing client from the list";
        vmCreateCall.selectClientByClientId("Client_ID");

        testStep = "Functional Step 7 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 9 : Enter employee filter";
        vmCreateCall.enterEmployeeFilter("Employee_Name", null);

        testStep = "Functional Step 6 : Click Search button";
        vmCreateCall.searchEmployee();

        testStep = "Functional Step 10: Select an existing employee from the list";
        vmCreateCall.selectEmployeeById("Employee_ID");

        testStep = "Functional Step 11 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 12 : Enter a valid date";
        vmCreateCall.enterNewCallInformation("Visit_Date", "Call_Time_1", null);

        testStep = "Functional Step 13 : Finish new call";
        vmCreateCall.finishNewCall();

        testStep = "Functional Step 15: Confirm the new call";
        errorPage.confirmOK();

        testStep = "Functional Step 14 : Repeat the step to add second manual call";
        vmFilter.createCall();

        testStep = "Functional Step 14 : Enter a Client filter";
        vmCreateCall.enterClientFilter("Client_Name", null, null, null, null);

        testStep = "Functional Step 15 : Click Search button";
        vmCreateCall.searchClient();

        testStep = "Functional Step 16 : Select an existing client from the list";
        vmCreateCall.selectClientByClientId("Client_ID");

        testStep = "Functional Step 17 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 18 : Enter employee filter";
        vmCreateCall.enterEmployeeFilter("Employee_Name", null);

        testStep = "Functional Step 19 : Click Search button";
        vmCreateCall.searchEmployee();

        testStep = "Functional Step 20 : Select an existing employee from the list";
        vmCreateCall.selectEmployeeById("Employee_ID");

        testStep = "Functional Step 21 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 22 : Enter a valid date";
        vmCreateCall.enterNewCallInformation("Visit_Date", "Call_Time_2", null);

        testStep = "Functional Step 13 : Finish new call";
        vmCreateCall.finishNewCall();

        testStep = "Functional Step 15: Confirm the new call";
        errorPage.confirmOK();
*/

        testStep = "Functional Step 3 : Enter Visit Date filter of the new call";
        vmFilter.enterFilterDateRangeFilter("Filter_StartDate","Filter_End_Date");

        testStep = "Functional Step 4 : Enter Client Name filter of the new call";
        vmFilter.enterFilterClient("Client_Name");

        testStep = "Functional Step 5 : Enter Employee Name filter of the new call";
        vmFilter.enterFilterEmployee("Employee_Name");

        testStep = "Functional Step 6 : Click Search button";
        vmFilter.clickOnSearch();

        testStep = "Validate the call with given Date and Time are displayed in the Visit Grid after filtering";
        vmFilter.validateActualInOutOnVisitDate(XLReader.TestCaseDataMap.get("Visit_Date"),XLReader.TestCaseDataMap.get("Call_Time_1"),XLReader.TestCaseDataMap.get("Call_Time_2"));





    }
}
