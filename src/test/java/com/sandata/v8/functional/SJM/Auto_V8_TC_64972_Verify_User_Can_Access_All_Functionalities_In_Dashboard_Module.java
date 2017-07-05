package com.sandata.v8.functional.SJM;

import org.testng.annotations.Test;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.v8.pages.DashBoardPage;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;

public class Auto_V8_TC_64972_Verify_User_Can_Access_All_Functionalities_In_Dashboard_Module extends BaseTest{
	
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DashBoardPage dashboard = new DashBoardPage();
	
	@Test(dataProvider="td")
	public void Auto_V8_TC_64972_Verify_User_Can_Access_All_Functionalities_In_Dashboard_Module(String dummyData) {
		BaseDriver.GetDriver();
		    
		 testStep="Functional Step 1: Login SJM system with selected agency that schedule module is disabled";
		    login.login();
		    testStep="Functional Step 2: Navigate to Dashboard module";
		    navigation.navigateToDashBoard();
		    testStep="Functional Step 3: Click on 'No Show' Link And Validate the Date and Exception Type Fields ";
		    dashboard.clickOnDashBoardLinks("Dasboard_NoShow_LBL");
		    dashboard.validateNavigationPage("V_VisitMaintenance_ExceptionType_DD_NoShow");
		    testStep="Functional Step 4: Click on 'Unscheduled Visits' Link And Validate the Date and Exception Type Fields ";
		    navigation.navigateToDashBoard();
		    dashboard.clickOnDashBoardLinks("Dasboard_UnscheduledVisits_LBL");
		    dashboard.validateNavigationPage("V_VisitMaintenance_ExceptionType_DD_UnscheduledVisit");
		    testStep="Functional Step 5: Click on 'Unknown Clients' Link And Validate the Date and Exception Type Fields ";
		    navigation.navigateToDashBoard();
		    dashboard.clickOnDashBoardLinks("Dasboard_UnknownClients_LBL");
		    dashboard.validateNavigationPage("V_VisitMaintenance_ExceptionType_DD_UnknownClients");
		    testStep="Functional Step 6: Click on 'Unknown Employee' Link And Validate the Date and Exception Type Fields ";
		    navigation.navigateToDashBoard();
		    dashboard.clickOnDashBoardLinks("Dasboard_UnknownEmployees_LBL");
		    dashboard.validateNavigationPage("V_VisitMaintenance_ExceptionType_DD_UnknownEmployees");
		    
	}



}
