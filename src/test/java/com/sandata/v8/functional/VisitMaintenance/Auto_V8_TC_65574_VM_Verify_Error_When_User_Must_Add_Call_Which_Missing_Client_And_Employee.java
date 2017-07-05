package com.sandata.v8.functional.VisitMaintenance;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 4/18/2017.
 */
public class Auto_V8_TC_65574_VM_Verify_Error_When_User_Must_Add_Call_Which_Missing_Client_And_Employee extends BaseTest {
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

        testStep = "Functional Step 4 : Click on Next button without select any Client";
        vmCreateCall.next();

        testStep = "Validate the error message";
        errorPage.validateMessage("V_Error_Missing_Client");

        testStep = "Functional Step 5 : Enter a Client filter";
        vmCreateCall.enterClientFilter("Client_Name", null, null, null, null);

        testStep = "Functional Step 6 : Click Search button";
        vmCreateCall.searchClient();

        testStep = "Functional Step 6 : Select an existing client from the list";
        vmCreateCall.selectClientByClientId("Client_ID");

        testStep = "Functional Step 7 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 8 : Click on Next button without select any Employee";
        vmCreateCall.next();

        testStep = "Validate the error message";
        errorPage.validateMessage("V_Error_Missing_Employee");

        testStep = "Functional Step 9 : Enter employee filter";
        vmCreateCall.enterEmployeeFilter("Employee_Name", null);

        testStep = "Functional Step 6 : Click Search button";
        vmCreateCall.searchEmployee();

        testStep = "Functional Step 10: Select an existing employee from the list";
        vmCreateCall.selectEmployeeById("Employee_ID");

        testStep = "Functional Step 11 : Click on Next";
        vmCreateCall.next();

        testStep = "Functional Step 12: Click on Finish button";
        vmCreateCall.finishNewCall();

        testStep = "Validate the Require field message must displayed on Call Date and Call Time text field";
        wrapper.validateInnerText("VM_CreateCall_CallDateError_LBL", "V_Error_RequireField");
        wrapper.validateInnerText("VM_CreateCall_CallTimeError_LBL","V_Error_RequireField");

        testStep = "Functional Step 13 : Enter valid Call Date and Call Time";
        vmCreateCall.enterNewCallInformation("Call_Date", "Call_Time", null);

        testStep = "Functional Step 14 : Click on Finish button";
        vmCreateCall.finishNewCall();

        testStep = "Functional Step 15: Confirm the new call";
        errorPage.confirmOK();

        testStep = "Validate the successfully message display";
        errorPage.validateMessage("V_Call_Create_Successfully");

        testStep = "Functional Step 12 : Enter Call which date is past over 18 months and Call Time";
        vmCreateCall.enterNewCallInformation("Call_Invalid_Pass_Date", "Call_Time", null);


    }
}
