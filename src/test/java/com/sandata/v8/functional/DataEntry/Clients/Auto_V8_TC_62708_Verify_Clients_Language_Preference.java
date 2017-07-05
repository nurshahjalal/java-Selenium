package com.sandata.v8.functional.DataEntry.Clients;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.AccountManagerPage;
import com.sandata.v8.pages.DataEntryClientPage;
import com.sandata.v8.pages.DataEntryCreateClientPage;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_62708_Verify_Clients_Language_Preference extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();
	DataEntryCreateClientPage createClientPage = new DataEntryCreateClientPage();
	Wrapper wrapper = new Wrapper();
	AccountManagerPage ac = new AccountManagerPage();

	@Test(dataProvider="td")
	public void Auto_V8_TC_62708_Verify_Clients_Language_Preference(String dummyData) throws IOException {
		
        BaseDriver.GetDriver();
        login.loginAccountManager();
        
        ac.goToSelectAccountPage(); 
        
        ac.selectAccount();
        
        ac.goToSetupPhone();
        
        wrapper.click("AM_Navigation_SetupPhone_Edit");
        
        wrapper.click("SetUp_MultiLanguage_Ed");
        ac.switchToBrowser("langBrowser");
        
        String  arr = ac.getCurrentLanguageOption();
        
        wrapper.click("Cancel_LanguageList");
        
        ac.switchToBrowser("AccBrow");
        
        wrapper.click("AccManager_LogOff");
        
        wrapper.closeCurrentWindow();
        
        BaseDriver.GetDriver();
        testStep = "Functional Step 1: Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2: Go to Client page";
        navigation.navigateToClient();

        testStep = "Functional Step 3: Create on Create New button on the top right of the page";
        clientPage.createClient();
        
        testStep = "Functional Step 4: Checking the Language Preferance Field ";
        createClientPage.CheckFieldMandatory("LanguagePreferanceText");
        
        testStep="Functional Step 4 : Enter the require field for the new client";
	    createClientPage.enterRequireFields();
	    
	    testStep="Functional Step 4 : Checking All Language Options";
	    createClientPage.verifyAllLanguageOptions(arr);
	    
	    testStep="Functional Step 5 : Enter the Address Information for the new client";
        createClientPage.enterPrimaryAddress();

        testStep="Functional Step 6 : Click on Create New";
        createClientPage.clickCreateNew();
        
        testStep="Functional Step 6 : Click on Create New";
        wrapper.click("SaveConfirmation");
        
        testStep="Functional Step 6 : Click on Create New";
        createClientPage.checkNotificationMsg("V_Client_Create_Success_Message");
        
        wrapper.holdOn(2000);
        testStep = "Functional Step 2: Go to Client page";
       // navigation.navigateToClient();
        
        wrapper.click("Navigation_Clients_ITM");
        
        
        wrapper.setTextBoxValue("Clients_SearchClientFirstName_ED", "CreateClient_FirstName_ED");
        
        wrapper.click("Clients_Search_BTN");
        
        wrapper.click("Client_Edit");
        
        createClientPage.verifyClientLanguage("CreateClient_LanguagePreference_DD", "ClientLanguage");
        
        wrapper.selectByVisibleText("CreateClient_LanguagePreference_DD", "ClientSecondLanguage");
        
        testStep="Functional Step 6 : Click on Create New";
        createClientPage.clickCreateNew();
        
        testStep="Functional Step 6 : Click on Create New";
        wrapper.click("SaveConfirmation");
        
        testStep="Functional Step 6 : Click on Create New";
        createClientPage.checkNotificationMsg("Clnt_UpdateMSG");
        
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 2: Go to Client page";
        wrapper.click("Navigation_Clients_ITM");
        //navigation.navigateToClient();
        
        wrapper.setTextBoxValue("Clients_SearchClientFirstName_ED", "CreateClient_FirstName_ED");
        
        wrapper.click("Clients_Search_BTN");
        
        wrapper.click("Client_Edit");
        
        createClientPage.verifyClientLanguage("CreateClient_LanguagePreference_DD", "ClientSecondLanguage");
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//
//        testStep = "Validate the value display on the Relationship dropdown";
//        wrapper.checkObjectNotExist("CreateClient_DOB_ED");
//
//        testStep = "Functional Step 4: Enter require fields";
//        navigation.browserToPage("Search Clients");
//
//        testStep = "Functional Step 5: Add new phone number";
//        clientPage.searchClientBy(TestCaseDataMap.get("ClientID"), "", "");
//        clientPage.search();
//
//        testStep = "Validate the client with ClientID displayed in the grid";
//            clientPage.validateClientExistBy(TestCaseDataMap.get("ClientID"),
//                TestCaseDataMap.get("ClientFirstName"),
//                TestCaseDataMap.get("ClientLastName"));
//
//        clientPage.editClientWithID(TestCaseDataMap.get("ClientID"));
//        wrapper.checkObjectNotExist("CreateClient_DOB_ED");
    }
}
