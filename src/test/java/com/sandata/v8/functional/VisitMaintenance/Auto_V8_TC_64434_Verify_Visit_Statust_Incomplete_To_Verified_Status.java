package com.sandata.v8.functional.VisitMaintenance;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.v8.pages.*;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nur on 06/02/2017.
 */
public class Auto_V8_TC_64434_Verify_Visit_Statust_Incomplete_To_Verified_Status extends BaseTest {
    LoginPage login = new LoginPage();
    NavigationPage navigation =new NavigationPage();
    VisitMaintenanceFilterPage vmFilter = new VisitMaintenanceFilterPage();
    DataEntryCreateClientPage dcc = new DataEntryCreateClientPage();
    Wrapper wrapper = new Wrapper();
    VisitDetailPage vd = new VisitDetailPage();
    DataEntryClientPage clientPage = new DataEntryClientPage();
    DataEntryEmployeePage dep = new DataEntryEmployeePage();
    VisitMaintenanceCreateCallPage vmcp = new VisitMaintenanceCreateCallPage();

    @Test(dataProvider="td")
    
    public void Auto_V8_TC_63053_VM_Verify_User_Is_Able_To_Filter_Visits_Status(String dummyData) throws IOException {
    	
    	 BaseDriver.GetDriver();
    	 
    	testStep = "Functional Step 1 : Login into Santrax V8 with valid Agency value";
        login.login();
       
        testStep = "Functional Step 2 : Creat a Client";
        String clientName = dcc.createAutoNewClient();
        wrapper.holdOn(1500);
        
        testStep = "Functional Step 3 :Create an Employee";
        String empName = dep.createAutoNewEmployee();
       
       String e_FullName = empName.split(";")[1]+", "+ empName.split(";")[0];
      
       testStep = "Functional Step 4 : Navigate To Visit Maintenance Menu";
       wrapper.click("Navigation_VisitMaintenance_ITM");
       wrapper.holdOn(2000);
       
       String clntID = vmcp.createNewCall(clientName.split(";")[0],clientName.split(";")[1],empName.split(";")[0],
    		   empName.split(";")[1],"Call_In_Time","G0156");
        
        testStep = "Functional Step 5 : Enter Client Name";
        wrapper.EnterDynamicData("VisitMaintenance_Client_ED", clntID);
       
        wrapper.holdOn(4000);
        
        testStep = "Functional Step 6 : Click On Search";
        vmFilter.clickOnSearch();
        vmFilter.optionalClick();
        wrapper.holdOn(1500);
        
        testStep = "Functional Step 7 : Click on Visit Mintenance Grid";
        vmFilter.clickOnClientName();
        
        wrapper.holdOn(1500);
        
        testStep = "Functional Step 8 : Click On Visit Detail General Tab";
        vd.ClickOnGeneralTab();
        
        testStep = "Functional Step 9 : Verify Visit Status";
        vd.verifyVisitStaus("V_Status");
        
        testStep = "Functional Step 10 : Verify Call in Status";
        vd.verifyCallInStaus("CallIn_Status");
        
        testStep = "Functional Step 11 : Verify Call Out Status";
        vd.verifyCallOutStaus("CallOut_Status");
        
        testStep = "Functional Step 12 : Click On Visit Detail Exception Tab";
        vd.ClickOnExceptionTab();
        
        testStep = "Functional Step 13 : Verify Without Call Exception";
        vd.verifyWithoutCallException();
        
        testStep = "Functional Step 14 : Verify Acknowledge Exception";
        vd.AcknowledgeException();
        
        testStep = "Functional Step 15 : Select Reason Code";
        vd.SelectReasonCode("VD_Exception_ReasonCode");
        
        testStep = "Functional Step 16 : Save Exception";
        vd.SaveException();
        
        testStep = "Functional Step 17 : Click On Call Log Tab";
        vd.ClickOnCallLogTab();
        
        testStep = "Functional Step 18 : Enter Call Out Date";
        vd.EnterCallDate();
        
        testStep = "Functional Step 18 : Enter Call Out Time";
        vd.EnterCallTime("CallOut_Time");
        
        testStep = "Functional Step 19 : Select Reason Code";
        vd.SelectReasonCode("VD_Manual_CallReason_Code");
        
        testStep = "Functional Step 20 : Click On Add Button";
        vd.ManualCallAdd();
        wrapper.holdOn(1000);
        
        testStep = "Functional Step 21 : Click On Visit Detail General Tab";
        vd.ClickOnGeneralTab();
        
        testStep = "Functional Step 22 :Verify Updated Visit Status";
        vd.verifyVisitStaus("V_Update_Status");
        
        testStep = "Functional Step 23 :Verify Updated Call Out Time";
        vd.verifyCallOutTime("CallOut_Time");
        
        testStep = "Functional Step 24 :Close Visit Detail";
        vd.closeVisitDetail();
        
        testStep = "Functional Step 8 :Verify Updated Visit Status in Visit Maintenance Grid";
        vmFilter.verifyVisitStatus("V_Update_Status");
        

    }
}
