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
public class Auto_V8_TC_63747_VM_Verify_Error_When_User_Add_New_Call_With_Furture_Date_Time extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_65574_VM_Verify_Error_When_User_Must_Add_Call_Which_Missing_Client_And_Employee(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate to Visit Maintenance Module";
        navigation.navigateToVisitMaintenance();

        testStep = "Functional Step 3 : Click on Create Call button";
        vmFilter.createCall();

        testStep = "Functional Step 4 : Enter a Client filter";
        vmCreateCall.enterClientFilter("Client_Name", null, null, null, null);

        testStep = "Functional Step 5 : Click Search button";
        vmCreateCall.searchClient();

        testStep = "Functional Step 6 : Select an existing client from the list";
        vmCreateCall.selectClientByClientId("Client_ID");

        testStep = "Functional Step 7 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 8 : Enter employee filter";
        vmCreateCall.enterEmployeeFilter("Employee_Name", null);

        testStep = "Functional Step 09 : Click Search button";
        vmCreateCall.searchEmployee();

        testStep = "Functional Step 10: Select an existing employee from the list";
        vmCreateCall.selectEmployeeById("Employee_ID");

        testStep = "Functional Step 11 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 13 : Enter Full Call Date and Call Time";
        vmCreateCall.enterNewCallInformation("Call_Future_Date", "Call_Time", null);

        testStep = "Validate the error message: Call date/time in future is not allowed.";
        wrapper.validateInnerText("VM_CreateCall_CallDateError_LBL", "V_Error_Message");

        String callTime = XLReader.TestCaseDataMap.get("Call_Time");
        testStep = "Functional Step 14 : Enter a valid date";
        vmCreateCall.enterNewCallInformation("Call_Date", "Call_Time", null);

        testStep = "Functional Step 15 : Click on Finish button";
        vmCreateCall.finishNewCall();

        testStep = "Functional Step 16 : Confirm the new call";
        errorPage.confirmOK();

        testStep = "Validate the successfully message display";
        errorPage.validateMessage("V_Call_Create_Successfully");

        testStep = "Functional Step 17 : Go back to Visit Maintenance page";
        navigation.navigateToVisitMaintenance();

        testStep = "Functional Step 18 : Enter Visit Date filter of the new call";
        vmFilter.enterFilterDateRangeFilter("Filter_StartDate","Filter_End_Date");

        testStep = "Functional Step 19 : Enter Client Name filter of the new call";
        vmFilter.enterFilterClient("Client_Name");

        testStep = "Functional Step 20 : Enter Employee Name filter of the new call";
        vmFilter.enterFilterEmployee("Employee_Name");

        testStep = "Functional Step 21 : Click Search button";
        vmFilter.clickOnSearch();

        testStep = "Validate the call with given Date and Time are displayed in the Visit Grid after filtering";
        vmFilter.validateActualVisitExist(XLReader.TestCaseDataMap.get("Call_Date"),XLReader.TestCaseDataMap.get("Call_Time"));
    }
}
