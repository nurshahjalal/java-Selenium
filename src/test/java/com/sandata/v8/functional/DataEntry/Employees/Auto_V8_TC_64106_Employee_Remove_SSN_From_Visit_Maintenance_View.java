package com.sandata.v8.functional.DataEntry.Employees;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_64106_Employee_Remove_SSN_From_Visit_Maintenance_View extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	VisitMaintenanceFilterPage visitFilterPage = new VisitMaintenanceFilterPage();
	VisitDetailPage visitDetailPage = new VisitDetailPage();
	Wrapper wrapper = new Wrapper();

	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_64106_Employee_Remove_SSN_From_Visit_Maintenance_View(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1: Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2 : Enter Date Range fields on the Visit Filter";
        visitFilterPage.enterFilterDateRangeFilter("Filter_StartDate","Filter_End_Date");

        testStep = "Functional Step 3 : Enter Client Name filter of the new call";
        visitFilterPage.enterFilterClient("Client_Name");

        testStep = "Functional Step 4 : Enter Employee Name filter of the new call";
        visitFilterPage.enterFilterEmployee("Employee_Name");

        testStep = "Functional Step 5 : Click Search button";
        visitFilterPage.clickOnSearch();
        wrapper.holdOn(2000);

        testStep = "Functional Step 6 : Open the visit match with the given filter by clicking on the Client Name";
        visitFilterPage.openVisitDetailBy(XLReader.TestCaseDataMap.get("Client_Name"),XLReader.TestCaseDataMap.get("Employee_Name"),
                XLReader.TestCaseDataMap.get("Visit_Date"),"Employee");

        testStep = "Validate the Social Security Name label is not displayed in Employee tab";
        visitDetailPage.validateLabelNotExist("SSN");
        visitDetailPage.validateLabelNotExist("Social Security Number");

        testStep = "Functional Step 7 : Close the visit detail pop-up";
        visitDetailPage.close();

        testStep = "Functional Step 8: Click on Show Display Options";
        visitFilterPage.showDisplayOptions();

        testStep = "Validate the SSN option is not exists";
        visitFilterPage.checkDisplayOptionNotExisit("SSN");
        visitFilterPage.checkDisplayOptionNotExisit("Social Security Number");

    }
}
