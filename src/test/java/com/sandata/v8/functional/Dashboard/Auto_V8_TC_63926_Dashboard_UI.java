package com.sandata.v8.functional.Dashboard;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class Auto_V8_TC_63926_Dashboard_UI extends BaseTest {
	
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    VisitMaintenanceFilterPage visitFilterPage = new VisitMaintenanceFilterPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td", groups = {"smoke"})
    public void Auto_V8_TC_63926_Dashboard_UI(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Navigate to Dashboard Module";
        navigation.navigateToDashboard();

        testStep = "Functional Step 4 : Looking for No Shows Section";
        dashBoardPage.verifyObjectExistence("No_Shows");

        testStep = "Functional Step 5 : Looking for Unsceduled Visits Section";
        dashBoardPage.verifyObjectExistence("Unsceduled_Visits");

        
    }
}
