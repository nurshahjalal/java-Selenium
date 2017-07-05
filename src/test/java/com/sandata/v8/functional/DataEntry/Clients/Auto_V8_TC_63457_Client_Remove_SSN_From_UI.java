package com.sandata.v8.functional.DataEntry.Clients;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.DataEntryClientPage;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_63457_Client_Remove_SSN_From_UI extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	Wrapper wrapper = new Wrapper();

	@Test(dataProvider="td")
	public void Auto_V8_TC_63457_Client_Remove_SSN_From_UI(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1: Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2: Go to Client page";
        navigation.navigateToClient();

        testStep = "Functional Step 3: Create on Create New button on the top right of the page";
        clientPage.createClient();

        testStep = "Validate the value display on the Relationship dropdown";
        wrapper.checkObjectNotExist("CreateClient_ClientSSN_ED");

        testStep = "Functional Step 4: Enter require fields";
        navigation.browserToPage("Search Clients");

        testStep = "Functional Step 5: Add new phone number";
        clientPage.searchClientBy(TestCaseDataMap.get("ClientID"), "", "");
        clientPage.search();

        testStep = "Validate the client with ClientID displayed in the grid";
            clientPage.validateClientExistBy(TestCaseDataMap.get("ClientID"),
                TestCaseDataMap.get("ClientFirstName"),
                TestCaseDataMap.get("ClientLastName"));

        clientPage.editClientWithID(TestCaseDataMap.get("ClientID"));
        wrapper.checkObjectNotExist("CreateClient_ClientSSN_ED");
    }
}
