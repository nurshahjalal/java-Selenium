package com.sandata.v8.functional.DataEntry.Clients;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_63495_Search_User_able_to_search_client_and_go_to_edit_client_page_from_result_grid extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	DataEntryEditClientPage editClientPage = new DataEntryEditClientPage();


	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_63495_01_Search_User_able_to_search_client(String dummyData) throws IOException {
		BaseDriver.GetDriver();
		    
		    testStep="Functional Step 1: Login with invalid Agency value";
		    login.login();
		    
		    testStep="Functional Step 2: Go to Client page";
		    navigation.navigateToClient();

		    testStep="Functional Step 3: User can search client by Client ID only";
		    clientPage.searchClientBy(TestCaseDataMap.get("ClientID_1"), "", "");
		    clientPage.search();

		    testStep="Validate the client with ClientID displayed in the grid";
		    clientPage.validateClientExistBy(TestCaseDataMap.get("ClientID_1"),
                                            TestCaseDataMap.get("ClientFirstName_1"),
                                            TestCaseDataMap.get("ClientLastName"));

		    testStep="Functional Step 4: Clear the client filter";
            clientPage.clear();

		    testStep="Functional Step 5: User can search clients by First Name";
            clientPage.searchClientBy(null, TestCaseDataMap.get("ClientFirstName_2"), "");
            clientPage.search();

            testStep="Validate the client with ClientID displayed in the grid";
            clientPage.validateClientExistBy(TestCaseDataMap.get("ClientID_2"),
                                    TestCaseDataMap.get("ClientFirstName_2"),
                                    TestCaseDataMap.get("ClientLastName"));

            testStep="Functional Step 6: Clear the client filter";
            clientPage.clear();

            testStep="Functional Step 7: User can search clients by Last Name";
            clientPage.searchClientBy(null, null, TestCaseDataMap.get("ClientLastName"));
            clientPage.search();

            testStep="Validate both clients with given LastName displayed in the grid";
            clientPage.validateClientExistBy(TestCaseDataMap.get("ClientID_1"),
                    TestCaseDataMap.get("ClientFirstName_1"),
                    TestCaseDataMap.get("ClientLastName"));
            clientPage.validateClientExistBy(TestCaseDataMap.get("ClientID_2"),
                    TestCaseDataMap.get("ClientFirstName_2"),
                    TestCaseDataMap.get("ClientLastName"));

            testStep="Functional Step 8: Click on the Edit button on the visit grid";
            clientPage.editClientWithID(TestCaseDataMap.get("ClientID_1"));

            testStep="Validate user will be lead to edit page for the selected client ID";
            editClientPage.validateEditPageIsLoadForClientID(TestCaseDataMap.get("ClientID_1"));
    }
}
