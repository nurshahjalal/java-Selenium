package com.sandata.v8.functional.DataEntry.Employees;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;
import com.sandata.v8.pages.ReportPage;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_66555_Employee_Remove_SSN_From_Report extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	ReportPage reportPage = new ReportPage();
	Wrapper wrapper = new Wrapper();

	    @Test(dataProvider="td")
	public void Auto_V8_TC_66555_Employee_Remove_SSN_From_Report(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1: Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2: Go to Report page";
        navigation.expandReportAndExportTab();
        navigation.navigateToReport();



        for (int i=1;i<=4;i++){
            String reportType= XLReader.TestCaseDataMap.get("Report_Type_1");
            String reportName = XLReader.TestCaseDataMap.get("Report_Name_1_"+i);

            testStep = "Functional Step 3: Select " + reportType + " as Report Type ";
            reportPage.setReportType("Report_Type_1");

            testStep = "Functional Step 4: Select "+ reportName + " as Report Name";
            reportPage.setReportName("Report_Name_1_"+i);

            testStep = "Functional Step 5: Enter the From Date";
            reportPage.setFromDateRange();

            testStep = "Functional Step 6: Click on Run report button";
            reportPage.clickRunReport();

            testStep = "Validate the column SSN not presence on the report";
            reportPage.validateHeaderColumnNotPresenceOnTheReport("SSN");

            testStep = "Go back to report page";
            navigation.navigateToReport();
        }


        for (int i=1;i<=6;i++){
            String reportType= XLReader.TestCaseDataMap.get("Report_Type_2");
            String reportName = XLReader.TestCaseDataMap.get("Report_Name_2_"+i);

            testStep = "Functional Step 3: Select " + reportType + " as Report Type ";
            reportPage.setReportType("Report_Type_2");

            testStep = "Functional Step 4: Select "+ reportName + " as Report Name";
            reportPage.setReportName("Report_Name_2_"+i);

            testStep = "Functional Step 5: Enter the From Date";
            reportPage.setFromDateRange();

            testStep = "Functional Step 5: Enter the From Date";
            reportPage.setToDateRange();

            testStep = "Functional Step 6: Click on Run report button";
            reportPage.clickRunReport();

            wrapper.holdOn(5000);

            testStep = "Validate the column SSN not presence on the report";
            reportPage.validateHeaderColumnNotPresenceOnTheReport("SSN");

            testStep = "Go back to report page";
            navigation.navigateToReport();
        }

    }
}
