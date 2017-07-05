package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AccountManagerPage {
	Wrapper baseObj = new Wrapper();

	 public void goToSelectAccountPage(){
	     BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
         baseObj.click("AM_Navigation_SantraxAccount_ITM");
         baseObj.click("AM_Navigation_SelectAccount_ITM");
         baseObj.waitForBrowserToLoadCompletely();
    }

    public void goToSetupVisitMaintenance(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("AM_Navigation_SantraxSetup_ITM");
        baseObj.click("AM_Navigation_SetupVisitMaintenance_ITM");
        baseObj.waitForBrowserToLoadCompletely();
    }
    
    public void goToSetupPhone(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("AM_Navigation_SantraxSetup_ITM");
        baseObj.click("AM_Navigation_SetupPhone_ITM");
        baseObj.waitForBrowserToLoadCompletely();
    }

    public void selectAccount(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.selectByValue("AM_SelectAccount_DD",XLReader.EnvironmentDataMap.get("AccountManager_Account"));
        baseObj.click("AM_SelectAccount_BTN");
    }

    public void disableScheduleModule(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        boolean isDisabled = baseObj.isCheckboxChecked("AM_SetupVM_DisableScheduling_CHK");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> Is Schedule module disabled ? "+isDisabled+"</b>");
        if (isDisabled == false){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> Click to disable the Schedule module the </b>");
            baseObj.click("AM_SetupVM_DisableScheduling_CHK");
            saveSetup();

        }
    }

    public void enableSecheduleModule(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        boolean isDisabled = baseObj.isCheckboxChecked("AM_SetupVM_DisableScheduling_CHK");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> Is Schedule module disabled ? "+isDisabled+"</b>");

        if (isDisabled == true){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> Click to enable the Schedule module the </b>");
            baseObj.click("AM_SetupVM_DisableScheduling_CHK");
            saveSetup();
        }

    }
    public void saveSetup(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("AM_SetupVM_Save_BTN");
        baseObj.holdOn(5000);
        baseObj.alertAccept();
        baseObj.waitForBrowserToLoadCompletely();
    }
    
    public String getCurrentLanguageOption(){
    	List<WebElement>allSuggestions = baseObj.getOptions("Current_LanguageList");
    	String [] allOptions = new String [allSuggestions.size()];
    	String options = "";
    	for(int i = 0; i <allSuggestions.size(); i++){
    		 options = options + allSuggestions.get(i).getText()+  ";" ;
    		
    		//options= options + ";";
    		}
		return options;
    	
    }
    
    public void switchToBrowser(String strBrow){
    	String url  = baseObj.retrieveLocatorValue(strBrow);
    	baseObj.switchFocusOnWindowTabByURL(url);
    	
    }

}
