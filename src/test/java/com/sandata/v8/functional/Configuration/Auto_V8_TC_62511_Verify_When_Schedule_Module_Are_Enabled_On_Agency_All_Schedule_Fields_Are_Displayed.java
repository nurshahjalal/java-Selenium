package com.sandata.v8.functional.Configuration;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 4/18/2017.
 */
public class Auto_V8_TC_62511_Verify_When_Schedule_Module_Are_Enabled_On_Agency_All_Schedule_Fields_Are_Displayed extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    ReportPage report = new ReportPage();
    AccountManagerPage amPage = new AccountManagerPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_62511_Verify_When_Schedule_Module_Are_Enabled_On_Agency_All_Schedule_Fields_Are_Displayed(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Pre-condition: Go to Account Manager and disable schedule module for the account";
        login.loginAccountManager();

        testStep = "Pre-condition: Go toSelect the account";
        amPage.goToSelectAccountPage();

        testStep = "Pre-condition: Select the account";
        amPage.selectAccount();

        testStep = "Pre-condition: Go to Set up Visit Maintenance page";
        amPage.goToSetupVisitMaintenance();

        testStep = "Pre-condition: Enable Schedule module for the given account";
//        amPage.enableSecheduleModule();
        amPage.disableScheduleModule();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.goToLoginPage();
        login.login();

        testStep = "Validate the Schedule Module is hidden on Hamburger menu";
        wrapper.CheckObjectExist("Navigation_Scheduling_ITM");

        testStep = "Functional Step 2 : Navigate to Dashboard Module";
        navigation.navigateToDashboard();

        testStep = "Validate the Unknown Client widget displayed";
        wrapper.CheckObjectExist("Dashboard_UnknownClients_LNK");

        testStep = "Validate  the Unknown Employee widget displayed";
        wrapper.CheckObjectExist("Dashboard_UnknownEmployees_LNK");

        testStep = "Validate  the Unscheduled Visits widget displayed";
        wrapper.CheckObjectExist("Dashboard_UnscheduledVisits_LNK");

        testStep = "Validate  the No Show widget displayed";
        wrapper.CheckObjectExist("Dashboard_NoShow_LNK");

        testStep = "Functional Step 3 : Navigate to Visit Maintenance page";
        navigation.navigateToVisitMaintenance();

        testStep = "Functional Step 4 : Enter Visit Date filter of the new call";
        vmFilter.enterFilterDateRangeFilter("Filter_StartDate","Filter_End_Date");

        testStep = "Functional Step 7 : Click Search button";
        vmFilter.clickOnSearch();

        testStep = "Functional Step 8 : Click on Show Display Option link";
        vmFilter.showDisplayOptions();

        testStep = "Validate the Scheduled Hours option is hidden";
        wrapper.CheckObjectExist("VMDisplayOption_SchHours_CHK");

        testStep = "Validate the Scheduled Hours option is hidden";
        wrapper.CheckObjectExist("VMDisplayOption_SchHours_CHK");

        testStep = "Validate the Scheduled Time In option is hidden";
        wrapper.CheckObjectExist("VMDisplayOption_SchIn_CHK");

        testStep = "Validate the Scheduled Time In option is hidden";
        wrapper.CheckObjectExist("VMDisplayOption_SchOut_CHK");

        testStep = "Functional Step 10 : Go to Report module";
        navigation.expandReportAndExportTab();
        navigation.navigateToReport();

        testStep = "Functional Step 11 : Select Daily as report type";
        report.setReportType("Daily_Report_Type");

        testStep = "Validate the expected template must be available on the Report Name dropdown";
        report.validateReportNameExist("DailyName_");

        testStep = "Functional Step 12 : Select Date Range as report type";
        report.setReportType("DateRange_Report_Type");
        report.validateReportNameExist("DateRangeName_");
    }
}
