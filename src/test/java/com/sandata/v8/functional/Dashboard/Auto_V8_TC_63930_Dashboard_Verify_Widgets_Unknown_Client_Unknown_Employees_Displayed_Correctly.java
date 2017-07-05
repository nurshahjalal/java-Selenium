package com.sandata.v8.functional.Dashboard;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 4/18/2017.
 */
public class Auto_V8_TC_63930_Dashboard_Verify_Widgets_Unknown_Client_Unknown_Employees_Displayed_Correctly extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    VisitMaintenanceFilterPage visitFilterPage = new VisitMaintenanceFilterPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td", groups = {"smoke"})
    public void Auto_V8_TC_63930_Dashboard_Verify_Widgets_Unknown_Client_Unknown_Employees_Dispalyed_Correctly(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate to Dashboard Module";
        navigation.navigateToDashboard();

        testStep = "Functional Step 4 : Validate the Unknown Client widget displayed";
        wrapper.CheckObjectExist("Dashboard_UnknownClients_LNK");

        testStep = "Functional Step 5 : Validate  the Unknown Employee widget displayed";
        wrapper.CheckObjectExist("Dashboard_UnknownEmployees_LNK");

        testStep = "Functional Step 6 : Get number of Unknown Clients Visit display on the Widget";
        int numberOfUnknownClients = dashBoardPage.getNumberUnknownClientsVisit();

        testStep = "Functional Step 7 : Get number of Unknown Employees Visit display on the Widget";
        int numberOfUnknownEmployees = dashBoardPage.getNumberUnknownEmployeesVisit();

        testStep = "Functional Step 8 : Click on the Unknown Clients link";
        dashBoardPage.clickOnDashBoardLinks("Dashboard_UnknownClients_LNK");

        testStep = "Validation: Start Date and End Date filters are populated with the current date value";
        visitFilterPage.validateCurrentDatePopulatedOnStartDate();
        visitFilterPage.validateCurrentDatePopulatedOnEndDate();

        testStep = "Validation: Unknown Clients exception is selected on the Exception Type dropdown";
        visitFilterPage.validateSelectedException("Unknown Clients");

        testStep = "Validation: Number of the result grid must match with the number displayed on the widget";
        visitFilterPage.validateNumberVisit(numberOfUnknownClients);

        testStep = "Functional Step 9 : Navigate to Dashboard Module";
        navigation.navigateToDashboard();

        testStep = "Functional Step 10 : Click on the Unknown Employees link";
        dashBoardPage.clickOnDashBoardLinks("Dashboard_UnknownEmployees_LNK");

        testStep = "Validation: Start Date and End Date filters are populated with the current date value";
        visitFilterPage.validateCurrentDatePopulatedOnStartDate();
        visitFilterPage.validateCurrentDatePopulatedOnEndDate();

        testStep = "Validation: Unknown Clients exception is selected on the Exception Type dropdown";
        visitFilterPage.validateSelectedException("Unknown employees");

        testStep = "Validation: Number of the result grid must match with the number displayed on the widget";
        visitFilterPage.validateNumberVisit(numberOfUnknownEmployees);
    }
}
