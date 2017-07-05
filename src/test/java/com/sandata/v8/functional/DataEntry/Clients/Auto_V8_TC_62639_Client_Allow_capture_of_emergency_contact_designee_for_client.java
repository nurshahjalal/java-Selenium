package com.sandata.v8.functional.DataEntry.Clients;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.v8.pages.DataEntryClientPage;
import com.sandata.v8.pages.DataEntryCreateClientPage;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class    Auto_V8_TC_62639_Client_Allow_capture_of_emergency_contact_designee_for_client extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	DataEntryCreateClientPage createClientPage = new DataEntryCreateClientPage();

	@Test(dataProvider="td", enabled = false)
	public void Auto_V8_TC_62639_Client_Allow_capture_of_emergency_contact_designee_for_client(String dummyData) throws IOException {
		BaseDriver.GetDriver();
		    
		    testStep="Functional Step 1: Login with invalid Agency value";
		    login.login();
		    
		    testStep="Functional Step 2: Go to Client page";
		    navigation.navigateToClient();

		    testStep="Functional Step 3: Create on Create New button on the top right of the page";
		    clientPage.createClient();

		    testStep="Functional Step 4: Validate the value display on the Relationship dropdown";
		    createClientPage.validateOptionsOnRelationshipDropdown();

		    testStep="Functional Step 5: Enter require fields";
            createClientPage.enterRequireFields();

		    testStep="Functional Step 6: Add new phone number";
		    createClientPage.addNewPhoneNumber();

	}
}
