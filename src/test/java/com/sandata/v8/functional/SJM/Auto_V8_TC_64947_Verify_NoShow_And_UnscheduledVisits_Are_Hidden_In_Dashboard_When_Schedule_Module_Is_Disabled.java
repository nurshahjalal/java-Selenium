package com.sandata.v8.functional.SJM;

import java.io.IOException;

import org.testng.annotations.Test;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.v8.pages.DashBoardPage;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;

public class Auto_V8_TC_64947_Verify_NoShow_And_UnscheduledVisits_Are_Hidden_In_Dashboard_When_Schedule_Module_Is_Disabled extends BaseTest {

	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DashBoardPage dashboard = new DashBoardPage();
	
	@Test(dataProvider="td")
	public void Auto_V8_TC_64947_Verify_NoShow_And_UnscheduledVisits_Are_Hidden_In_Dashboard_When_Schedule_Module_Is_Disabled(String dummyData) throws IOException {
		BaseDriver.GetDriver();
		    
		    testStep="Functional Step 1: Login SJM system with selected agency that schedule module is disabled";
		    login.login();
		    testStep="Functional Step 2: Navigate to Dashboard module";
		    navigation.navigateToDashBoard();
		    testStep="Functional Step 3: Verify No Show and Unscheduled Visits are hidden in Dashboard screen";
		    dashboard.validateAbsenceOfField("Dasboard_NoShow_LBL");
		    dashboard.validateAbsenceOfField("Dasboard_UnscheduledVisits_LBL");
		    testStep="Functional Step 4: Verify Unknown Clients and Unknown Employees are shown in Dashboard screen ";
			 dashboard.validatePresenceOfField_UnknownClients_UnknownEmployees();   
	}
}
