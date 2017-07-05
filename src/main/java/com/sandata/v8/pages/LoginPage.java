package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;


public class LoginPage {
	
	Wrapper baseobj = new Wrapper();

	public void goToLoginPage(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseobj.navigate(XLReader.EnvironmentDataMap.get("Application_URL"));
        baseobj.waitForBrowserToLoadCompletely();
    }

	public void login(){
		BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		baseobj.getElement("Login_Agency_ED").sendKeys(
				BaseDriver.GlobalVariables.getProperty("Agency",XLReader.EnvironmentDataMap.get("Agency")));
		baseobj.getElement("Login_UserName_ED").sendKeys(
				BaseDriver.GlobalVariables.getProperty("UserName",XLReader.EnvironmentDataMap.get("UserName")));
		String password = XLReader.EnvironmentDataMap.get("Password");
		if(password.endsWith("=")){ // Password has been encoded
            password = new String(Base64.decodeBase64(password));
            System.out.println(password);
        }
		baseobj.getElement("Login_Password_ED").sendKeys(
				password);
		baseobj.click("Login_Login_BT");
		baseobj.waitForBrowserToLoadCompletely();
		baseobj.waitForElementPresence("Navigation_VisitMaintenance_ITM");
	}

	public void loginAccountManager(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseobj.navigate(XLReader.EnvironmentDataMap.get("AccountManager_URL"));
        baseobj.getElement("AM_Login_Username_ED").sendKeys(XLReader.EnvironmentDataMap.get("AccountManager_Username"));
        baseobj.getElement("AM_Login_Password_ED").sendKeys(XLReader.EnvironmentDataMap.get("AccountManager_Password"));
        baseobj.click("AM_Login_OK_BTN");
        baseobj.waitForBrowserToLoadCompletely();
    }


	public void validateAlertHeader(){
		BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		baseobj.validateInnerText("Login_Alert_Header_LBL", "V_Login_Alert_Header_LBL");
	}

	public void loginWithInvalidCredential(){
		BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		baseobj.setTextBoxValue("Login_Agency_ED", "Login_Agency_ED");
		baseobj.setTextBoxValue("Login_UserName_ED", "Login_UserName_ED");
		baseobj.setTextBoxValue("Login_Password_ED","Login_Password_ED");
		baseobj.click("Login_Login_BT");
	}

	public void validateOnLoginPage(){
		BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		Assert.assertTrue(baseobj.getElement("Login_Login_BT").isDisplayed());
	}

	public void login_Provider(){
		BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		baseobj.getElement("Login_Provider_Agency_ED").sendKeys(XLReader.EnvironmentDataMap.get("Agency"));;
		baseobj.getElement("Login_Provider_User_ED").sendKeys(XLReader.EnvironmentDataMap.get("UserName"));;
		baseobj.getElement("Login_Provider_Password_ED").sendKeys(XLReader.EnvironmentDataMap.get("Password"));
		baseobj.click("Login_Provider_Sign_In_BT");
	}

	public void loginWithValidCredential(String username, String password){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseobj.getElement("Login_Provider_Agency_ED").sendKeys(XLReader.EnvironmentDataMap.get("Agency"));;
        baseobj.setTextBoxValue("Login_UserName_ED", username);
        baseobj.setTextBoxValue("Login_Password_ED",password);
        baseobj.click("Login_Login_BT");
    }

}
