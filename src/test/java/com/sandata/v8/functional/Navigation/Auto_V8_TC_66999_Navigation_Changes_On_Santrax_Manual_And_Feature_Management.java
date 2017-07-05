package com.sandata.v8.functional.Navigation;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by vumvo on 4/18/2017.
 */
public class Auto_V8_TC_66999_Navigation_Changes_On_Santrax_Manual_And_Feature_Management extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    VisitMaintenanceCreateCallPage vmCreateCall = new VisitMaintenanceCreateCallPage();
    NotificationPage errorPage = new NotificationPage();
    Wrapper wrapper = new Wrapper();

    @Test(dataProvider="td")
    public void Auto_V8_TC_66999_Navigation_Changes_On_Santrax_Manual_And_Feature_Management(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();

        testStep = "Functional Step 2 : Click to Expand the Security menu";
        navigation.navigateToSecurity();

        testStep = "Validate that the Feature Management Item is removed on the Hamburger menu";
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        wrapper.checkObjectNotExist("Navigation_FeatureManagement_ITM");

        testStep = "Functional Step 3 : Click on Santrax Manual item";
        navigation.navigateToSanTraxManualPage();

        testStep = "Validate User Guide is loading on the new tab";
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        Assert.assertTrue(wrapper.getDriver().getCurrentUrl().contains("User_Guide"));

        testStep = "Validate Guideline is loaded successfully on the tab";
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        wrapper.getScreenShot("V_66999_SantraxGuideline_load_successfully");
    }
}
