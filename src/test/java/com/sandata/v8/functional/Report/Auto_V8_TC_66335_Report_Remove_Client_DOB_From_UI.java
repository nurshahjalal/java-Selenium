package com.sandata.v8.functional.Report;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;
import com.sandata.v8.pages.ReportPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class Auto_V8_TC_66335_Report_Remove_Client_DOB_From_UI extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	ReportPage reportPage = new ReportPage();
	Wrapper wrapper = new Wrapper();

	@Test(dataProvider="td")
	public void Auto_V8_TC_66355_Remove_Remove_Client_DOB_From_UI(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1: Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2: Navigate to Santrax V8 >Reports & Exports > Reports";
        navigation.expandReportAndExportTab();
        navigation.navigateToReport();

  /*      for (int i=1;i<=4;i++){
            String reportType= XLReader.TestCaseDataMap.get("Report_Type_1");
            String reportName = XLReader.TestCaseDataMap.get("Report_Name_1_"+i);

            testStep = "Functional Step 3: Select " + reportType + " as Report Type ";
            reportPage.setReportType("Report_Type_1");

            testStep = "Functional Step 4: Select "+ reportName + " as Report Name";
            reportPage.setReportName("Report_Name_1_"+i);
            wrapper.holdOn(1000);

            testStep = "Functional Step 5: Enter the From Date";
            reportPage.setFromDateRange("Report_FromDate_ED");

            testStep = "Functional Step 6: Click on Run report button";
            reportPage.clickRunReport();
            wrapper.holdOn(5000);

            testStep = "Validate the column Date Of Birth not presence on the report";
            reportPage.validateHeaderColumnNotPresenceOnTheReport("DOB");

            testStep = "Validate the Date of birth column are not present on the report layout: "+ reportName;
            reportPage.takeReportScreenshot(reportName,"Validate Date Of Birth is not populated on the report?");

            testStep = "Go back to report page";
            navigation.navigateToReport();

        }*/

        for (int i=1;i<=6;i++){
            String reportType= XLReader.TestCaseDataMap.get("Report_Type_2");
            String reportName = XLReader.TestCaseDataMap.get("Report_Name_2_"+i);

            testStep = "Functional Step 3: Select " + reportType + " as Report Type ";
            reportPage.setReportType("Report_Type_2");

            testStep = "Functional Step 4: Select "+ reportName + " as Report Name";
            reportPage.setReportName("Report_Name_2_"+i);

            testStep = "Functional Step 5: Enter the From Date";
            reportPage.setFromDateRange("Report_RangeFromDate_ED");

            testStep = "Functional Step 5: Enter the From Date";
            reportPage.setToDateRange("Report_RangeToDate_ED");

            testStep = "Un-select all the options in the Contract dropdown";
            reportPage.discardSelectedContracts();

            testStep = "Functional Step 6: Click on Run report button";
            reportPage.clickRunReport();

            if (reportName.equalsIgnoreCase("Visit Summary by Client")){
                testStep= "Select View Report By Client";
                reportPage.selectReportViewByClient();
            }
            wrapper.holdOn(5000);

            testStep = "Validate the column Date Of Birth not presence on the report";
            reportPage.validateHeaderColumnNotPresenceOnTheReport("DOB");

            testStep = "Validate the Date of birth column are not present on the report layout: "+ reportName;
            reportPage.takeReportScreenshot(reportName,"Validate Date Of Birth is not populated on the report?");

            testStep = "Go back to report page";
            navigation.navigateToReport();
        }

    }
}
