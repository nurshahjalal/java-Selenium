package com.sandata.v8.functional.DataEntry.Clients;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_67304_Client_User_can_edit_existing_client_from_grid extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	DataEntryEditClientPage editClientPage = new DataEntryEditClientPage();
	NotificationPage notificationPage = new NotificationPage();
	Wrapper wrapper = new Wrapper();


	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_67304_Client_User_can_edit_existing_client_from_grid(String dummyData) throws IOException {
		BaseDriver.GetDriver();
		    
		    testStep = "Functional Step 1: Login with invalid Agency value";
		    login.login();
		    
		    testStep = "Functional Step 2: Go to Client page";
		    navigation.navigateToClient();

		    testStep = "Functional Step 3: User can search client by Client ID only";
		    clientPage.searchClientBy(TestCaseDataMap.get("ClientID"), "", "");
		    clientPage.search();

            testStep = "Functional Step 8: Click on the Edit button on the visit grid";
            clientPage.editClientWithID(TestCaseDataMap.get("ClientID"));

            testStep = "Validate user will be lead to edit page for the selected client ID";
            editClientPage.validateEditPageIsLoadForClientID(TestCaseDataMap.get("ClientID"));

            testStep = "Functional Step 9: Edit Client Last Name, First Name";
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
            wrapper.setTextBoxValue("CreateClient_FirstName_ED","ClientFirstNameUpdate");
            wrapper.setTextBoxValue("CreateClient_LastName_ED","ClientLastNameUpdate");

            testStep = "Functional Step 10: Select another Gender for the new client";
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
            wrapper.selectByVisibleText("CreateClient_Sex_DD","ClientGender2");

            testStep = "Functional Step 11: Enter another Address Line";
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
            wrapper.setTextBoxValue("CreateClient_StreetAddress_ED","AddressLine2");

            testStep = "Functional Step 12: Click Save button";
            editClientPage.saveClientSuccessfully();

            testStep = "Functional Step 13: Validate the message";
            notificationPage.validateMessage("V_Message");

            testStep = "Functional Step 14: Navigate to Search Client Page";
            navigation.browserToPage("Search Clients");

            testStep = "Functional Step 15: User can search client by Client ID only";
            clientPage.searchClientBy(TestCaseDataMap.get("ClientID"), "", "");
            clientPage.search();

            testStep = "Functional Step 16: Click on the Edit button on the visit grid";
            clientPage.editClientWithID(TestCaseDataMap.get("ClientID"));

            testStep = "Functional Step 17: Validate the all updated data are saved correctly";
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
            wrapper.validateValueAttribute("CreateClient_FirstName_ED","ClientFirstNameUpdate");
            wrapper.validateValueAttribute("CreateClient_LastName_ED","ClientLastNameUpdate");
            wrapper.validateSelectedValueInSingleSelectionDropdown("CreateClient_Sex_DD","ClientGender2");
            wrapper.validateValueAttribute("CreateClient_StreetAddress_ED","AddressLine2");

            testStep = "Functional Step 18: Reverse  Client Last Name, First Name";
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
            wrapper.setTextBoxValue("CreateClient_FirstName_ED","ClientFirstName");
            wrapper.setTextBoxValue("CreateClient_LastName_ED","ClientLastName");

            testStep = "Functional Step 19: Reverse Gender for the client";
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
            wrapper.selectByVisibleText("CreateClient_Sex_DD","ClientGender1");

            testStep = "Functional Step 20: Reverse Address Line";
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
            wrapper.setTextBoxValue("CreateClient_StreetAddress_ED","AddressLine1");

            testStep = "Functional Step 21: Click Save button";
            editClientPage.saveClientSuccessfully();

            testStep = "Functional Step 22: Validate the message";
            notificationPage.validateMessage("V_Message");
    }
}
