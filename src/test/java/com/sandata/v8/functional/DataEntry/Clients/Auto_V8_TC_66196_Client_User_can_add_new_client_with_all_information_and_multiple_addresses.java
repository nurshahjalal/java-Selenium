package com.sandata.v8.functional.DataEntry.Clients;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_66196_Client_User_can_add_new_client_with_all_information_and_multiple_addresses extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	DataEntryCreateClientPage createClientPage = new DataEntryCreateClientPage();
	NotificationPage notificationPage = new NotificationPage();

	@Test(dataProvider="td")
	public void Auto_V8_TC_66196_Client_User_can_add_new_client_with_all_information_and_multiple_addresses(String dummyData) throws IOException {
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

            testStep="Functional Step 6 : Click on Manage";
            createClientPage.clickOnManageAddress();

            testStep="Functional Step 7 : Add new additional address";
            createClientPage.addNewAdditionalAddress(TestCaseDataMap.get("Additional_AddressType"), TestCaseDataMap.get("Additional_AddressLine"), null,
                                                    TestCaseDataMap.get("Additional_City"),TestCaseDataMap.get("Additional_State"),
                                                    TestCaseDataMap.get("Additional_Zipcode"));
            testStep="Functional Step 8 : Click to Save address";
            createClientPage.clickToSaveAdditionalAddress();

            testStep="Functional Step 9 : Click OK to confirm";
            notificationPage.confirmOK();

            testStep="Functional Step 8 : Add new phone number";
            createClientPage.addNewPhoneNumber();

            testStep="Functional Step 10 : Enter Emergency Contact";
            createClientPage.enterEmergencyContact();

            testStep="Functional Step 11 : click on Save button";
            createClientPage.clickCreateNew();

            testStep="Functional Step 12 : Confirm OK";
            notificationPage.confirmOK();

            testStep="Validate the new user is created successfully.";
            notificationPage.validateMessage("V_Client_Create_Success_Message");

            testStep="Functional Step 13 : Go back to Search Client page";
            navigation.browserToPage("Search Clients");

		    testStep="Functional Step 14: Search new client by Client ID only";
		    clientPage.searchClientBy(TestCaseDataMap.get("CreateClient_ClientID_ED"), "", "");
		    clientPage.search();

		    testStep="Validate the new client with ClientID displayed in the grid";
		    clientPage.validateClientExistBy(TestCaseDataMap.get("CreateClient_ClientID_ED"),
                                            TestCaseDataMap.get("CreateClient_FirstName_ED"),
                                            TestCaseDataMap.get("CreateClient_LastName_ED"));

		    testStep="Functional Step 15: Click on the trash icon on the client record";
            clientPage.deleteClientWithID(TestCaseDataMap.get("CreateClient_ClientID_ED"));

            testStep="Functional Step 16: Click on Delete button on confirmation";
            notificationPage.confirmDelete();

                testStep="Validate the client is deleted successfully";
            notificationPage.validateMessage("V_Client_Delete_Success_Message");

            testStep="Functional Step 17 : Clear the filter";
            clientPage.clear();

            testStep="Functional Step 18 : Re-search the new client from the search screen";
            clientPage.searchClientBy(TestCaseDataMap.get("CreateClient_ClientID_ED"), "", "");
            clientPage.search();

            testStep="Validate the Client grid display with no result";
            clientPage.validateNoResultOnVisitGrid();
    }
}
