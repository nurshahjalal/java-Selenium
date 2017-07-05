package com.sandata.v8.functional.DataEntry.Clients;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_65970_Search_User_able_to_create_and_delete_client_from_result_grid extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	DataEntryCreateClientPage createClientPage = new DataEntryCreateClientPage();
	NotificationPage notificationPage = new NotificationPage();

	@Test(dataProvider="td")
	public void Auto_V8_TC_65970_Search_User_able_to_create_and_delete_client_from_result_grid(String dummyData) throws IOException {
		BaseDriver.GetDriver();
		    
		    testStep="Functional Step 1 : Login with invalid Agency value";
		    login.login();
		    
		    testStep="Functional Step 2 : Go to Client page";
		    navigation.navigateToClient();

		    testStep="functional Step 3 : Click on Create Client button";
		    clientPage.createClient();

		    testStep="Functional Step 4 : Enter the require field for the new client";
		    createClientPage.enterRequireFields();

            testStep="Functional Step 5 : Enter the Address Information for the new client";
            createClientPage.enterPrimaryAddress();

            testStep="Functional Step 6 : Click on Create New";
            createClientPage.clickCreateNew();

            testStep="Functional Step 7 : Confirm OK";
            notificationPage.confirmOK();

            testStep="Validate the new user is created successfully.";
            notificationPage.validateMessage("V_Client_Create_Success_Message");

            testStep="Functional Step 8 : Go back to Search Client page";
            navigation.browserToPage("Search Clients");

		    testStep="Functional Step 9: Search new client by Client ID only";
		    clientPage.searchClientBy(TestCaseDataMap.get("CreateClient_ClientID_ED"), "", "");
		    clientPage.search();

		    testStep="Validate the new client with ClientID displayed in the grid";
		    clientPage.validateClientExistBy(TestCaseDataMap.get("CreateClient_ClientID_ED"),
                                            TestCaseDataMap.get("CreateClient_FirstName_ED"),
                                            TestCaseDataMap.get("CreateClient_LastName_ED"));

		    testStep="Functional Step 10: Click on the trash icon on the client record";
            clientPage.deleteClientWithID(TestCaseDataMap.get("CreateClient_ClientID_ED"));

            testStep="Functional Step 11: Click on Delete button on confirmation";
            notificationPage.confirmDelete();

            testStep="Validate the client is deleted successfully";
            notificationPage.validateMessage("V_Client_Delete_Success_Message");

            testStep="Functional Step 12 : Clear the filter";
            clientPage.clear();

            testStep="Functional Step 13 : Re-search the new client from the search screen";
            clientPage.searchClientBy(TestCaseDataMap.get("CreateClient_ClientID_ED"), "", "");
            clientPage.search();

            testStep="Validate the Client grid display with no result";
            clientPage.validateNoResultOnVisitGrid();

    }
}
