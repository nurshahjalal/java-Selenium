package com.sandata.v8.functional.DataEntry.Employees;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_68239_Employee_User_can_create_new_employee_then_delete_from_grid_result extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	Wrapper wrapper = new Wrapper();
	DataEntryEmployeePage employee = new DataEntryEmployeePage();
	NotificationPage notification = new NotificationPage();

	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_68239_Employee_User_can_create_new_employee_then_delete_from_grid_result(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1: Login with valid Agency value";
        login.login();

        testStep = "Functional Step 2: Click on Employee Tab";
        navigation.navigateToEmployee();
               
        testStep= "Functional Step 3: Click on Create New Employee Button";
	    employee.createEmployee();

	    testStep= "Functional Step 4: Enter Data in to First Name, Last Name and Santrax ID";
	    employee.enterData("DE_CreateEmp_FirstName", "Abir");
	    employee.enterData("DE_CreateEmp_LastName", "Barua");
	    employee.enterData("DE_CreateEmp_EmpID", "9873497889");
	    employee.enterData("DE_CreateEmp_SSN", "578-35-3978");
	    employee.enterRandomSantraxID("DE_CreateEmp_SanID");
     
	    testStep= "Functional Step 5: Enter a New Email in to Email field";
	    employee.enterRandomEmail("DE_CreateEmp_Email","DE_CreateEmp_Conf_Email");
	    
	    testStep= "Functional Step 6: Click on Save Button";
	    employee.save();
	    
	    testStep="Functional Step 7: Click OK to confirm";
	    notification.confirmOK();
	    
	    testStep="Functional Step 8: Validate Employee was successfully saved and close the pop up";
	    notification.validateMessage("Notification_Message_Success");
	    wrapper.click("Notification_Close_BTN");
	    
	    testStep= "Functional Step 9: Click on Cancel Button";
	    employee.cancel();
	    
	    testStep= "Functional Step 10: Search Employee";
	    employee.clear();
	    employee.searchEmployeeBy("9873497889", "", "");
	    
	    testStep = "Functional Step 11: Click on Search Button";
        employee.search();
        wrapper.holdOn(2000);
  
        testStep = "Functional Step 12: Delete the Employee by clicking the Trash icon";
        employee.deleteEmployeeWithID("9873497889");
        
        testStep="Functional Step 13: Click Delete to confirm";
	    wrapper.click("DE_CreateEmp_Delete");
 	    wrapper.click("Notification_Close_BTN");
        
	    testStep= "Functional Step 14: Search Employee";
	    employee.clear();
	    employee.searchEmployeeBy("9873497889", "", "");
	    
	    testStep = "Functional Step 15: Click on Search Button";
        employee.search();
        wrapper.holdOn(2000);
        
        testStep="Functional Step 16: Validate there are no Employee found with the searched Employee ID";
	    wrapper.validateInnerTextDirectly("DE_CreateEmp_NoData", "No Data Found!");
	    
        
          }
}
