package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;

public class ChangePasswordPage {
	Wrapper baseObj = new Wrapper();

	 public void enterOldPassword(String fieldName) {
         BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
         baseObj.setTextBoxValue("ChangePassword_OldPassword_ED", fieldName);
     }

    public void enterNewPassword(String fieldName) {
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.setTextBoxValue("ChangePassword_NewPassword_ED", fieldName);
    }

    public void enterConfirmPassword(String fieldName) {
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.setTextBoxValue("ChangePassword_ConfirmPassword_ED", fieldName);
    }

    public void toggleShowOldPasssword(Boolean state){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
	     String fieldType = baseObj.getAttribute("ChangePassword_OldPassword_ED","type");
	     if(state && fieldType.equals("password") ){
            baseObj.click("ChangePassword_OldPasswordInvisibility_BTN");
         } else if (!state && !fieldType.equals("password")) {
            baseObj.click("ChangePassword_OldPasswordInvisibility_BTN");
         }
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px" + '"' + "> Old Password has changed to mode " + baseObj.getAttribute("ChangePassword_OldPassword_ED","type") +" </b>");
    }

    public void toggleShowNewPasssword(Boolean state){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        String fieldType = baseObj.getAttribute("ChangePassword_NewPassword_ED","type");
        if(state && fieldType.equals("password") ){
            baseObj.click("ChangePassword_NewPasswordInvisibility_BTN");
        } else if (!state && !fieldType.equals("password")) {
            baseObj.click("ChangePassword_NewPasswordInvisibility_BTN");
        }
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px" + '"' + "> New Password has changed to mode " + baseObj.getAttribute("ChangePassword_NewPassword_ED","type") +" </b>");
    }

    public void toggleShowConfirmPasssword(Boolean state){
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        String fieldType = baseObj.getAttribute("ChangePassword_ConfirmPassword_ED","type");
        if(state && fieldType.equals("password") ){
            baseObj.click("ChangePassword_ConfirmedNewPasswordInvisibility_BTN");
        } else if (!state && !fieldType.equals("password")) {
            baseObj.click("ChangePassword_ConfirmedNewPasswordInvisibility_BTN");
        }
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px" + '"' + "> Confirmed Password has changed to mode " + baseObj.getAttribute("ChangePassword_ConfirmPassword_ED","type") +" </b>");
    }

}
