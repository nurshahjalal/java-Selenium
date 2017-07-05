package com.sandata.v8.functional.DataEntry.Employees;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_62458_Adding_email_address_for_employee_during_create_new_employee extends BaseTest {

	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	Wrapper wrapper = new Wrapper();
	DataEntryEmployeePage employee = new DataEntryEmployeePage();
	NotificationPage notification = new NotificationPage();

	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_62458_Adding_email_address_for_employee_during_create_new_employee(String dummyData) throws IOException, InterruptedException {
        BaseDriver.GetDriver();
        
        testStep = "Functional Step 1: Login with valid Agency value";
        login.login();

        testStep= "Functional Step 2: Click on Data Entry and go to Employee page";
	    navigation.navigateToEmployee();
	  
	    testStep= "Functional Step 3: Click on Create New Employee Button and Validate Create New Employee Page";
	    employee.createEmployee();
	    employee.validateCreateEmployeePage("Create Employee");
	    
	    testStep= "Functional Step 4: Enter Data in to First Name, Last Name and Santrax ID";
	    employee.enterRandomText("DE_CreateEmp_FirstName");
	    employee.enterRandomText("DE_CreateEmp_LastName");
	    employee.enterRandomSantraxID("DE_CreateEmp_EmpID");
	    employee.enterRandomSantraxID("DE_CreateEmp_SanID");
	    
	    testStep= "Functional Step 5: Enter an existing Email in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.barua@sandata.com");
	    
	    testStep= "Functional Step 6: Click on Save Button";
	    employee.save();
	    
	    testStep="Functional Step 7: Click OK to confirm";
	    notification.confirmOK();
	        
	    testStep="Functional Step 8: Validate the Error Message pop up and Close the Error Message";
	    notification.validateMessage("Notification_Message_LBL");
	    wrapper.click("Notification_Close_BTN");
	    
	    testStep= "Functional Step 9: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.barua@sandata");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.barua@sandata");
	    
	    testStep="Functional Step 10: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
    
	    testStep= "Functional Step 11: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.baruasandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.baruasandata.com");
	    
	    testStep="Functional Step 12: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 13: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "123cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "123cathy.barua@sandata.com");
	    
	    testStep="Functional Step 14: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 15: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "_cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "_cathy.barua@sandata.com");
	    
	    testStep="Functional Step 16: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	  
	    testStep= "Functional Step 17: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "-cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "-cathy.barua@sandata.com");
	    
	    testStep="Functional Step 18: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 19: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", ".cathy.barua@sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", ".cathy.barua@sandata.com");
	    
	    testStep="Functional Step 20: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 21: Enter Data in to Email field";
	    employee.enterData("DE_CreateEmp_Email", "cathy.barua @sandata.com");
	    employee.enterData("DE_CreateEmp_Conf_Email", "cathy.barua @sandata.com");
	    
	    testStep="Functional Step 22: Validate the invalid Email format Error Message ";
	    employee.validateToolTipMessage("DE_CreateEmployee_Invalid_Email_Err");
	    
	    testStep= "Functional Step 23: Enter a New Email in to Email field";
	   employee.enterRandomEmail("DE_CreateEmp_Email","DE_CreateEmp_Conf_Email");
	    
	
	    testStep= "Functional Step 24: Click on Save Button";
	    employee.save();
	    
	    testStep="Functional Step 25: Click OK to confirm";
	    notification.confirmOK();
	    
	    testStep="Functional Step 26: Validate Employee was successfully saved and close the pop up";
	    notification.validateMessage("Notification_Message_Success");
	    wrapper.click("Notification_Close_BTN");
	    

    }
	
	
}

