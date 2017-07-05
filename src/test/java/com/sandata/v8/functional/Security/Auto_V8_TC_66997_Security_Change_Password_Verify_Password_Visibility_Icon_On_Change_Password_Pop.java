package com.sandata.v8.functional.Security;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.ChangePasswordPage;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.NavigationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 5/26/2017.
 */
public class Auto_V8_TC_66997_Security_Change_Password_Verify_Password_Visibility_Icon_On_Change_Password_Pop extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    ChangePasswordPage changePwd = new ChangePasswordPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider = "td")
    public void Auto_V8_TC_66997_Security_Change_Password_Verify_Password_Visibility_Icon_On_Change_Password_Pop(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2 : Go to Security - Change Password page";
        navigation.navigateToSecurity().navigateToChangePassword();

        testStep = "Validate the Password Invisibility Icon display on old password";
        wrapper.CheckObjectExist("ChangePassword_OldPasswordInvisibility_BTN");

        testStep = "Validate the Password Invisibility Icon display on old password";
        wrapper.CheckObjectExist("ChangePassword_NewPasswordInvisibility_BTN");

        testStep = "Validate the Password Invisibility Icon display on old password";
        wrapper.CheckObjectExist("ChangePassword_ConfirmedNewPasswordInvisibility_BTN");

        testStep = "Functional Step 3 : Show Visibility On Old Password";
        changePwd.toggleShowOldPasssword(true);

        testStep = "Functional Step 4 : Show Visibility On New Password";
        changePwd.toggleShowNewPasssword(true);

        testStep = "Functional Step 5 : Show Visibility On Confirmed Password";
        changePwd.toggleShowConfirmPasssword(true);

        testStep = "Functional Step 6 : Enter a valid password into the Old Password field";
        changePwd.enterOldPassword("ValidPassword");

        testStep = "Functional Step 7 : Enter a valid password into the New Password filed";
        changePwd.enterNewPassword("ValidPassword");

        testStep = "Functional Step 8 : Enter a valid password into the New Password filed";
        changePwd.enterConfirmPassword("ValidPassword");

        testStep = "Validate user can read the valid password in Visibility Mode";
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        Assert.assertEquals(wrapper.getAttribute("ChangePassword_OldPassword_ED","type"),"text");
        Assert.assertEquals(wrapper.getAttribute("ChangePassword_NewPassword_ED","type"),"text");
        Assert.assertEquals(wrapper.getAttribute("ChangePassword_ConfirmPassword_ED","type"),"text");
        wrapper.validateValueAttribute("ChangePassword_OldPassword_ED","ValidPassword");
        wrapper.validateValueAttribute("ChangePassword_NewPassword_ED","ValidPassword");
        wrapper.validateValueAttribute("ChangePassword_ConfirmPassword_ED","ValidPassword");

        testStep = "Functional Step 9 : Turn off Show Visibility On Old Password";
        changePwd.toggleShowOldPasssword(false);

        testStep = "Functional Step 10 : Turn off Show Visibility On New Password";
        changePwd.toggleShowNewPasssword(false);

        testStep = "Functional Step 11 : Turn off Show Visibility On Confirmed Password";
        changePwd.toggleShowConfirmPasssword(false);

        testStep = "Validate all the password fields change to password type";
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        Assert.assertEquals(wrapper.getAttribute("ChangePassword_OldPassword_ED","type"),"password");
        Assert.assertEquals(wrapper.getAttribute("ChangePassword_NewPassword_ED","type"),"password");
        Assert.assertEquals(wrapper.getAttribute("ChangePassword_ConfirmPassword_ED","type"),"password");
    }

}
