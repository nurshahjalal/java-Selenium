package com.sandata.v8.functional.Dashboard;

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
public class Auto_V8_TC_63926_Dashboard_Verify_Widgets_Available_In_Ohio_Env extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td", groups = {"smoke"})
    public void Auto_V8_TC_63926_Dashboard_Verify_Widgets_Available_In_Ohio_Env(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate to Dashboard Module";
        navigation.navigateToDashboard();

        testStep = "Functional Step 4 : Validate the Unknown Client widget displayed";
        wrapper.CheckObjectExist("Dashboard_UnknownClients_LNK");

        testStep = "Functional Step 5 : Validate  the Unknown Employee widget displayed";
        wrapper.CheckObjectExist("Dashboard_UnknownEmployees_LNK");

        testStep = "Functional Step 5 : Validate  the Unscheduled Visits widget displayed";
        wrapper.checkObjectNotExist("Dashboard_UnscheduledVisits_LNK");

        testStep = "Functional Step 5 : Validate  the No Show widget displayed";
        wrapper.checkObjectNotExist("Dashboard_NoShow_LNK");
    }
}
