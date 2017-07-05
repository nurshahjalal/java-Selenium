package com.sandata.v8.functional.SJM;

import com.sandata.v8.pages.ReportPage;
import org.testng.annotations.Test;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;

public class Auto_V8_TC_64962_Verify_User_Can_Access_All_Functionalities_In_Reporting_Module extends BaseTest{

	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	ReportPage report = new ReportPage();

	@Test(dataProvider="td")
	public void Auto_V8_TC_64962_Verify_User_Can_Access_All_Functionalities_In_Reporting_Module(String dummyData) {
		BaseDriver.GetDriver();    
		testStep="Functional Step 1: Login SJM system with selected agency that schedule module is disabled";
		login.login();
		testStep="Functional Step 2: Navigate to Report module";
		navigation.expandReportAndExportTab();
		navigation.navigateToReport();	
		testStep="Functional Step 3: Select the report 'Date Range Reports' from dropdown list and select date range (E.g: 01/01/2017- 04/04/2017) and Click 'Run report' button  ";
		report.setDataAndValidateReport("Date Range Reports","Report_ReportType_DD_1", "Report_ReportName_DD_1");
		report.validatePresenceOfSandataTelephonyImage();
		testStep="Functional Step 4: Select the report 'Daily Reports' from dropdown list and select date range (E.g: 01/01/2017- 04/04/2017) and Click 'Run report' button  ";
		navigation.navigateToReport();
		report.setDataAndValidateReport("Daily Reports","Report_ReportType_DD_2", "Report_ReportName_DD_2");

	}
}
