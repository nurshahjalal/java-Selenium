package com.sandata.v8.functional.Security;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.ChangePasswordPage;
import com.sandata.v8.pages.LoginPage;
import com.sandata.v8.pages.ManagerUsersPage;
import com.sandata.v8.pages.NavigationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 5/26/2017.
 */
public class Auto_V8_TC_66998_Security_Error_when_user_create_new_user_with_invalid_confirmed_email extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    ManagerUsersPage managerUsersPage = new ManagerUsersPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider = "td")
    public void Auto_V8_TC_66998_Security_Error_when_user_create_new_user_with_invalid_confirmed_email(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login with invalid Agency value";
        login.login();

        testStep = "Functional Step 2 : Go to Security - Manager Users";
        navigation.navigationToManageUsers();

        testStep = "Functional Step 3 : Click on Create User button";
        managerUsersPage.createUser();

        testStep = "Functional Step 4 : Enter valid email address for Username/Email field";
        managerUsersPage.enterUsername("Valid_Email_Address");

        testStep = "Functional Step 5 : Click on Create User button";
        managerUsersPage.clickCreateUser();

        testStep = "Validate error message next to Confirmed Username/ Email label";
        managerUsersPage.validateErrorMessageOnConfirmedUsername("V_Confirm_Error_Message");

        testStep = "Functional Step 6 : Enter different valid email address, but not the same value with Username filed,  for Confirmed Username/Email field";
        managerUsersPage.enterConfirmUsername("Another_Email_Address");

        testStep = "Functional Step 7 : Click on Create User button";
        managerUsersPage.clickCreateUser();

        testStep = "Validate error message next to Confirmed Username/ Email label";
        managerUsersPage.validateErrorMessageOnConfirmedUsername("V_Confirm_Error_Message");
    }

}
