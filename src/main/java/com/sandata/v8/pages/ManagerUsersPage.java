package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;

public class ManagerUsersPage {
	Wrapper baseObj = new Wrapper();

	public void createUser(){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.click("ManageUsers_CreateUser_BTN");
    }

    public void enterUsername(String fieldName){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.setTextBoxValue("CreateUser_Username_ED", fieldName);
    }

    public void enterConfirmUsername(String fieldName){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.setTextBoxValue("CreateUser_ConfirmedUsername_ED", fieldName);
    }

    public void clickCreateUser(){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.click("CreateUser_BTN");
    }

    public void validateErrorMessageOnConfirmedUsername(String fieldName){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.validateInnerText("CreateUser_ConfirmedError_LBL", fieldName);
    }

    public void validateErrorMessageOnUsername(String fieldName){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.validateInnerText("CreateUser_UsernameError_LBL", fieldName);
    }


}
