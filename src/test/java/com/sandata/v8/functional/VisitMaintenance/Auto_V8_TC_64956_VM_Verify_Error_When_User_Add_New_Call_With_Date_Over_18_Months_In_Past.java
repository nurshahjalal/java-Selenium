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
public class Auto_V8_TC_64956_VM_Verify_Error_When_User_Add_New_Call_With_Date_Over_18_Months_In_Past extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_64956_VM_Verify_Error_When_User_Add_New_Call_With_Date_Over_18_Months_In_Past(String dummyData) throws IOException {
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

        testStep = "Functional Step 12 : Enter Call which date is past over 18 months and Call Time";
        vmCreateCall.enterNewCallInformation("Call_Invalid_Pass_Date", "Call_Time", null);

        testStep = "Functional Step 13 : Click on Finish button";
        vmCreateCall.finishNewCall();

        testStep = "Functional Step 14: Confirm the new call";
        errorPage.confirmOK();

        testStep = "Validate the error message: Date of call is older than allowed";
        errorPage.validateMessage("V_Error_Message");
    }
}
