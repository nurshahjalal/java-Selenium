package com.sandata.v8.functional.DataEntry.Employees;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_62470_Modify_email_address_for_employee extends BaseTest {

	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	Wrapper wrapper = new Wrapper();
	DataEntryEmployeePage employee = new DataEntryEmployeePage();
	NotificationPage notification = new NotificationPage();
	DataEntryClientPage clientPage = new DataEntryClientPage();

	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_62470_Modify_email_address_for_employee(String dummyData) throws IOException, InterruptedException {
        BaseDriver.GetDriver();
        
        testStep = "Functional Step 1: Login with valid Agency value";
        login.login();

        testStep= "Functional Step 2: Click on Data Entry and go to Employee page and Validate Employee Page";
	    navigation.navigateToEmployee();
	    employee.validateCreateEmployeePage("Employees");
	    
	    testStep= "Functional Step 3: Enter an existing Employee ID in Employee ID field";
        employee.searchEmployeeBy("2839403945", "", "");
        
        testStep= "Functional Step 4: Click on Search Button";
        employee.search();
	    
        testStep="Functional Step 5: Click on the Edit button on the visit grid";
        wrapper.click("DE_Grid_Edit_Icon");

	    testStep= "Functional Step 6: Enter an existing Email in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.barua@sandata.com");

	    testStep= "Functional Step 7: Click on Save Button";
	    employee.save();
	    
	    testStep="Functional Step 8: Click OK to confirm";
	    notification.confirmOK();
	    
	    testStep="Functional Step 9: Validate the Error Message pop up and Close the Error Message";
	    notification.validateMessage("Notification_Message_LBL");
	    wrapper.click("Notification_Close_BTN");
 
	    //Step #4 The pop up should display existing email address or something.
	    
/*	    
	    testStep= "Functional Step 10: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.barua@sandata");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.barua@sandata");
	    
	    testStep="Functional Step 11: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
    
	    testStep= "Functional Step 12: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.baruasandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.baruasandata.com");
	    
	    testStep="Functional Step 13: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 14: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "123cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "123cathy.barua@sandata.com");
	    
	    testStep="Functional Step 15: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 16: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "_cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "_cathy.barua@sandata.com");
	    
	    testStep="Functional Step 17: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	  
	    testStep= "Functional Step 18: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "-cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "-cathy.barua@sandata.com");
	    
	    testStep="Functional Step 19: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 20: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", ".cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", ".cathy.barua@sandata.com");
	    
	    testStep="Functional Step 21: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 22: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.barua @sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.barua @sandata.com");
	    
	    testStep="Functional Step 23: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
   
	    testStep= "Functional Step 24: Enter a New Email in to Email field";
	    employee.enterRandomEmail("DE_CreateEmp_Email");
	    employee.enterRandomEmail("DE_CreateEmp_Conf_Email");	    
	    
	    testStep= "Functional Step 25: Click on Save Button";
	    employee.save();
	    
	    testStep="Functional Step 26: Click OK to confirm";
	    notification.confirmOK();
	    
	    testStep="Functional Step 27: Validate Employee was successfully saved and close the pop up";
	    notification.validateMessage("Notification_Message_Success");
	    wrapper.click("Notification_Close_BTN");
*/
	
    }	
	
	
}

