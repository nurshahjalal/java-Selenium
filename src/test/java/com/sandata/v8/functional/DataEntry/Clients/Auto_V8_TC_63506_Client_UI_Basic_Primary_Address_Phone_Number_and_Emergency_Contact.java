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

public class Auto_V8_TC_63506_Client_UI_Basic_Primary_Address_Phone_Number_and_Emergency_Contact extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	Wrapper wrapper = new Wrapper();

	@Test(dataProvider="td")
	public void Auto_V8_TC_63506_Client_UI_Basic_Primary_Address_Phone_Number_and_Emergency_Contact(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1: Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2: Go to Client page";
        navigation.navigateToClient();

        testStep = "Functional Step 3: Create on Create New button on the top right of the page";
        clientPage.createClient();

        testStep = "Validate Last Name display on the UI";
        wrapper.validateElementEnabled("CreateClient_LastName_ED");

        testStep = "Validate First Name display on the UI";
        wrapper.validateElementEnabled("CreateClient_FirstName_ED");

        testStep = "Validate Client ID display on the UI";
        wrapper.validateElementEnabled("CreateClient_ClientID_ED");

        testStep = "Validate Other Client ID display on the UI";
        wrapper.validateElementEnabled("CreateClient_ClientOtherID_ED");

        testStep = "Validate Medicaid ID display on the UI";
        wrapper.validateElementEnabled("CreateClient_MedicaidID_ED");

        testStep = "Validate Supervisor display on the UI";
        wrapper.validateElementEnabled("CreateClient_Supervisor_DD");

        testStep = "Validate Gender display on the UI";
        wrapper.validateElementEnabled("CreateClient_Sex_DD");

        testStep = "Validate Language Preferences display on the UI";
        wrapper.validateElementEnabled("CreateClient_LanguagePreference_DD");

        testStep = "Validate TimeZone displayed on the UI and Default Value is Eastern";
        wrapper.validateElementEnabled("CreateClient_TimeZone_DD");
        wrapper.validateSelectedValueInSingleSelectionDropdown("CreateClient_TimeZone_DD","DefaultTimeZone");

        testStep = "Validate Primary Address panel display with following fields";
        testStep = "Validate Address Type displayed on the UI";
        wrapper.validateElementEnabled("CreateClient_AddressType_ED");

        testStep = "Validate Address Line 1 display as require field on the UI";
        wrapper.validateElementEnabled("CreateClient_StreetAddress_ED");

        testStep = "Validate Address Line 2 display as require field on the UI";
        wrapper.validateElementEnabled("CreateClient_APTNo_ED");

        testStep = "Validate City display on the UI";
        wrapper.validateElementEnabled("CreateClient_City_ED");

        testStep = "Validate State display on the UI";
        wrapper.validateElementEnabled("CreateClient_State_DD");

        testStep = "Validate ZipCode display on the UI";
        wrapper.validateElementEnabled("CreateClient_ZipCode_ED");

        testStep = "Validate format of the Zip Code must be #####-####";
        wrapper.setTextBoxValue("CreateClient_ZipCode_ED","ZipCode");
        wrapper.holdOn(2000);
        wrapper.validateValueAttribute("CreateClient_ZipCode_ED","V_ZipCode");

        testStep = "Validate the Phone Address type";
        testStep = "Validate Phone Type display on the UI";
        wrapper.validateElementEnabled("CreateClient_PhoneType_DD");

        testStep = "Validate Phone Number display on the UI";
        wrapper.validateElementEnabled("CreateClient_PhoneNumber_ED");

        testStep = "Validate format of the phone number must be (###) ###-####";
        wrapper.setTextBoxValue("CreateClient_PhoneNumber_ED","Phone_Number");
        wrapper.holdOn(2000);
        wrapper.validateValueAttribute("CreateClient_PhoneNumber_ED","V_Phone_Number");

        testStep = "Validate Add button display on the UI";
        wrapper.validateElementEnabled("CreateClient_AddNewPhone_BTN");

        testStep = "Validate Emergency Contact";
        testStep = "Validate Emergency - Relationship";
        wrapper.validateElementEnabled("ClientEmergency_Relationship_DD");

        testStep = "Validate Emergency - First Name display on UI";
        wrapper.validateElementEnabled("ClientEmergency_FirstName_ED");

        testStep = "Validate Emergency - Last Name display on UI";
        wrapper.validateElementEnabled("ClientEmergency_LastName_ED");

        testStep = "Validate Emergency - Phone Type display on UI";
        wrapper.validateElementEnabled("ClientEmergency_PhoneType_DD");

        testStep = "Validate Emergency - Phone Number display on UI";
        wrapper.validateElementEnabled("ClientEmergency_PhoneNumber_ED");

        testStep = "Validate Emergency - Email Address display on UI";
        wrapper.validateElementEnabled("ClientEmergency_EmailAddress_ED");
    }
}
