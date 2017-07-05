package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vumvo on 3/20/2017.
 */
public class DataEntryCreateClientPage {
    Wrapper baseObj = new Wrapper();
    NavigationPage navigation =new NavigationPage();
    DataEntryClientPage clientPage = new DataEntryClientPage();
    NotificationPage notificationPage = new NotificationPage();
    
    public DataEntryCreateClientPage validateOptionsOnRelationshipDropdown(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        List<WebElement> options = baseObj.getOptions("CreateClient_Relationship_DD");
        for (int i=0;i<options.size();i++){
            String expectedValue = XLReader.TestCaseDataMap.get("CreateClient_Relationship_DD_"+i);
            if (expectedValue != null && !"".equals(expectedValue)){
                String actualValue = options.get(i).getText();
                Assert.assertEquals(actualValue, expectedValue);
            }
        }
        baseObj.holdOn(5000);
        return this;
    }
    
    
    public String createAutoNewClient(){
    	//EnterDynamicData(String locatorName, String strValue)
    	 BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	 navigation.navigateToClient();
         clientPage.createClient();
    	 String fName = "Automation" + String.valueOf(baseObj.intRandom(3));
    	 baseObj.EnterDynamicData("CreateClient_FirstName_ED",fName);
    	 String LName = "Test" + String.valueOf(baseObj.intRandom(3));
         baseObj.EnterDynamicData("CreateClient_LastName_ED",LName);
         baseObj.EnterDynamicData("CreateClient_ClientID_ED", baseObj.GenerateRandomNumber(6));
         baseObj.EnterDynamicData("CreateClient_MedicaidID_ED", baseObj.GenerateRandomNumber( 9));
         baseObj.selectByDynamicText("CreateClient_LanguagePreference_DD", "English");
         baseObj.selectByDynamicText("CreateClient_AddressType_ED","Home");
         baseObj.EnterDynamicData("CreateClient_StreetAddress_ED","8636 LittleNeck");
         baseObj.EnterDynamicData("CreateClient_City_ED","Floral Park");
         baseObj.selectByDynamicText("CreateClient_State_DD","NY");
         baseObj.EnterDynamicData("CreateClient_ZipCode_ED","11001");
         baseObj.getElement("CreateClient_ZipCode_ED").sendKeys(Keys.TAB);
         clickCreateNew();
         notificationPage.confirmOK();
         baseObj.holdOn(1000);
    	return fName + ";" + LName;
    }
    
    public String createAnotherNewClient(){
    	//EnterDynamicData(String locatorName, String strValue)
    	 BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	 navigation.navigateToClient();
         clientPage.createClient();
    	 String fName = "Another" + String.valueOf(baseObj.intRandom(3));
    	 baseObj.EnterDynamicData("CreateClient_FirstName_ED",fName);
    	 String LName = "Test" + String.valueOf(baseObj.intRandom(3));
         baseObj.EnterDynamicData("CreateClient_LastName_ED",LName);
         baseObj.EnterDynamicData("CreateClient_ClientID_ED", baseObj.GenerateRandomNumber(6));
         baseObj.EnterDynamicData("CreateClient_MedicaidID_ED", baseObj.GenerateRandomNumber( 9));
         baseObj.selectByDynamicText("CreateClient_LanguagePreference_DD", "English");
         baseObj.selectByDynamicText("CreateClient_AddressType_ED","Home");
         baseObj.EnterDynamicData("CreateClient_StreetAddress_ED","8636 LittleNeck");
         baseObj.EnterDynamicData("CreateClient_City_ED","Floral Park");
         baseObj.selectByDynamicText("CreateClient_State_DD","NY");
         baseObj.EnterDynamicData("CreateClient_ZipCode_ED","11001");
         baseObj.getElement("CreateClient_ZipCode_ED").sendKeys(Keys.TAB);
         clickCreateNew();
         notificationPage.confirmOK();
         baseObj.holdOn(1000);
    	return fName + ";" + LName;
    }

    public DataEntryCreateClientPage enterRequireFields(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("CreateClient_FirstName_ED","CreateClient_FirstName_ED");
        baseObj.setTextBoxValue("CreateClient_LastName_ED","CreateClient_LastName_ED");
       // baseObj.setTextBoxValue("CreateClient_ClientID_ED","CreateClient_ClientID_ED");
        
        baseObj.EnterDynamicData("CreateClient_ClientID_ED", baseObj.GenerateRandomNumber(6));
        //Nur Changed because of unique nature of Medicaid id
        baseObj.EnterDynamicData("CreateClient_MedicaidID_ED", baseObj.GenerateRandomNumber( 9));
        baseObj.selectByVisibleText("CreateClient_LanguagePreference_DD", "ClientLanguage");
        return this;
    }
    
    public DataEntryCreateClientPage checkNotificationMsg( String validationName){
    	
    	String getText = baseObj.getInnerText("Notification_Success_PNL");
    	
    	String expectedText = XLReader.TestCaseDataMap.get(validationName);
    	
    	if (getText.contains(expectedText)){
    		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+" Text Validation Passed </b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+" Text Validation Failed </b>]");
		
	}
    	
    	return this;
    }

    public DataEntryCreateClientPage addNewPhoneNumber(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("CreateClient_NewPhoneNumber_ED","CreateClient_NewPhoneNumber_ED");
        if("true".equalsIgnoreCase(XLReader.TestCaseDataMap.get("CreateClient_PrimaryPhone_CHK"))){
            baseObj.getElement("CreateClient_PrimaryPhone_CHK").click();
        }
        baseObj.click("CreateClient_AddNewPhone_BTN");
        baseObj.holdOn(5000);
        return this;
    }

    public DataEntryCreateClientPage clickCreateNew(){
        baseObj.click("CreateClient_Create_BTN");
        baseObj.waitForBrowserToLoadCompletely();
        return this;
    }

    public DataEntryCreateClientPage enterPrimaryAddress(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.selectByVisibleText("CreateClient_AddressType_ED","CreateClient_AddressType_ED");
        baseObj.setTextBoxValue("CreateClient_StreetAddress_ED","CreateClient_StreetAddress_ED");
        baseObj.setTextBoxValue("CreateClient_City_ED","CreateClient_City_ED");
        baseObj.selectByValue("CreateClient_State_DD",XLReader.TestCaseDataMap.get("CreateClient_State_DD"));
        baseObj.setTextBoxValue("CreateClient_ZipCode_ED","CreateClient_ZipCode_ED");
        baseObj.getElement("CreateClient_ZipCode_ED").sendKeys(Keys.TAB);
        return this;
    }

    public DataEntryCreateClientPage clickOnManageAddress() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.holdOn(10000);
        baseObj.click("CreateClient_ManageAddress_LNK");
        baseObj.getElement("AdditionalAddress_Save_BTN");
        return this;
    }

    public DataEntryCreateClientPage addNewAdditionalAddress(String addressType, String addressLine,
                                                             String addressLine2, String city,
                                                             String state, String zipCode){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.selectByValue("AdditionalAddress_AddressType_DD", addressType);
        baseObj.getElement("AdditionalAddress_AddressLine_ED").sendKeys(addressLine);

        if (addressLine2 != null) {
            baseObj.getElement("AdditionalAddress_AddressLine2_ED").sendKeys(addressLine2);
        }
        baseObj.getElement("AdditionalAddress_AddressCity_ED").sendKeys(city);
        baseObj.selectByValue("AdditionalAddress_AddressState_DD", state);
        baseObj.getElement("AdditionalAddress_AddressZipCode_DD").sendKeys(zipCode);
        baseObj.click("AdditionalAddress_AddAddress_BTN");
        baseObj.holdOn(2000);
        return this;
    }

    public DataEntryCreateClientPage clickToAddAdditionAddress(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("AdditionalAddress_AddAddress_BTN");
        baseObj.getElement("AdditionalAddress_AddressGrid_TBL");
        return this;
    }

    public DataEntryCreateClientPage enterEmergencyContact() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.selectByVisibleText("ClientEmergency_Relationship_DD", "Emergency_Relationship");
        baseObj.setTextBoxValue("ClientEmergency_FirstName_ED", "Emergency_FirstName");
        baseObj.setTextBoxValue("ClientEmergency_LastName_ED", "Emergency_LastName");
        baseObj.selectByVisibleText("ClientEmergency_PhoneType_DD", "Emergency_PhoneType");
        baseObj.setTextBoxValue("ClientEmergency_PhoneNumber_ED", "Emergency_PhoneNumber");
        baseObj.setTextBoxValue("ClientEmergency_EmailAddress_ED", "Emergency_Email");
        baseObj.setTextBoxValue("ClientEmergency_AddressLine1_ED", "Emergency_AddressLine1");
        baseObj.setTextBoxValue("ClientEmergency_AddressLine2_ED", "Emergency_AddressLine2");
        baseObj.setTextBoxValue("ClientEmergency_City_ED", "Emergency_City");
        baseObj.selectByVisibleText("ClientEmergency_State_DD", "Emergency_State");
        baseObj.setTextBoxValue("ClientEmergency_ZipCode_ED", "Emergency_ZipCode");
        return this;
    }

    public DataEntryCreateClientPage clickToSaveAdditionalAddress() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("AdditionalAddress_Save_BTN");
        return this;
    }
    
    public DataEntryCreateClientPage CheckFieldMandatory(String locatorname) {
    	
    	String strText = baseObj.getInnerText(locatorname);
    	if (strText.contains("*")){
    		 BaseTest.test.log(LogStatus.PASS, "", "<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + strText + " Field Is Found as Mandatory</b>");
    	}else{
    		BaseTest.test.log(LogStatus.FAIL, "", "<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + strText + " Field Is Not Found as Mandatory</b>");
    	}
    		 
    		 return this;
    }
   

    public DataEntryCreateClientPage verifyLanguageOptionsExist(String fieldName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        for (int i=1;;i++){
            String currentOption = XLReader.TestCaseDataMap.get(fieldName+"_"+i);
            if (currentOption != null) {
                BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;" + '"' + ">" + currentOption + " must exist on the Languages dropdown</b>");
                baseObj.validateOptionExist("CreateClient_LanguagePreferenceError_Tooltip", currentOption);
                BaseTest.test.log(LogStatus.PASS, "", "<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + currentOption + " existed</b>");
            } else {
                break;
            }
        }
        return this;
    }
    
    
    public void verifyAllLanguageOptions(String expectedOptions){
    	
 	   
        List<WebElement>allSuggestions = baseObj.getOptions("CreateClient_LanguagePreference_DD");
        String[] allOptions =expectedOptions.split(";");
        
        //String [] allAvailOptions = new String [allSuggestions.size()];
    	String options = "";
    	for(int i = 1; i <allSuggestions.size(); i++){
    		 options = options + allSuggestions.get(i).getText()+  ";" ;
    		}
    	String [] allAvailOptions = options.split(";");
    	Arrays.sort(allAvailOptions);
    	Arrays.sort(allOptions);
    	
        for (int j = 0; j < allAvailOptions.length; j++) {
        	
            if(allAvailOptions[j].equalsIgnoreCase(allOptions[j])){
            	BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
        				+" Language Option Found As " + allOptions[j] + "</b>");
    		}else{
    			BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
        				+"  Language Option is Not Found As Expected  </b>");	
            };

        }
    	
    }
    
    public DataEntryCreateClientPage verifyClientLanguage(String locatorName,String validationName){
    	
    	WebElement dd = baseObj.getFirstSelectedOption(locatorName);
    	String ds = dd.getText();
    	String expectedText = XLReader.TestCaseDataMap.get(validationName);
    	if(ds.equalsIgnoreCase(expectedText)){
    		BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Language Option Found As " + expectedText + "</b>");
    	}else{
    		BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Language Option Not Found As " + expectedText + "</b>");
    	}
    	return this;
    }
    
   
}
