package com.sandata.v8.functional.DataEntry.Employees;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_63513_Employee_Verify_UI_of_creation_modified_employee extends BaseTest {
	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	VisitMaintenanceFilterPage visitFilterPage = new VisitMaintenanceFilterPage();
	VisitDetailPage visitDetailPage = new VisitDetailPage();
	Wrapper wrapper = new Wrapper();
	DataEntryEmployeePage employee = new DataEntryEmployeePage();

	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_63513_Employee_Verify_UI_of_creation_modified_employee(String dummyData) throws IOException {
        BaseDriver.GetDriver();

        testStep = "Functional Step 1: Login with valid Agency value";
        login.login();

        testStep = "Functional Step 2: Click on Employee Tab";
        navigation.navigateToEmployee();
               
        testStep= "Functional Step 3: Click on Create New Employee Button";
	    employee.createEmployee();

	    
 /*      
        testStep = "Functional Step 3: Verify Employee ID, Employee FirstName, Employee LastName, Search, Clear are displying";
        wrapper.CheckObjectExist("Employees_SearchEmployeeID_ED");
        wrapper.CheckObjectExist("Employees_SearchFirstName_ED");
        wrapper.CheckObjectExist("Employees_SearchLastName_ED");
        wrapper.CheckObjectExist("Employees_Search_BTN");
        wrapper.CheckObjectExist("Employees_Clear_BTN");

        testStep = "Functional Step 4: Enter Data in Employee ID, Employee FirstName, Employee LastName field";
        employee.searchEmployeeBy("7984789478", "Jewelemp", "Barua");
        
        testStep = "Functional Step 5: Click on Clear Button";
        employee.clear();
        
        testStep = "Functional Step 6: Validate Employee ID, Employee FirstName, Employee LastName fields are Empty";
        employee.validateEmptyfield("Employees_SearchEmployeeID_ED", "");
        employee.validateEmptyfield("Employees_SearchFirstName_ED", "");
        employee.validateEmptyfield("Employees_SearchLastName_ED", "");
        
        testStep = "Functional Step 7: Enter Data in Employee ID field";
        employee.searchEmployeeBy("7984789478", "", "");
        
        testStep = "Functional Step 8: Click on Search Button";
        employee.search();
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 9: Validate Employee ID, Employee FirstName, Employee LastName column displays searched Employee";
        employee.validateClientExistBy("7984789478", "Jewelemp", "Barua");
        
        testStep = "Functional Step 10: Click on Clear Button";
        employee.clear();
        
        testStep = "Functional Step 11: Enter Data in Employee FirstName field";
        employee.searchEmployeeBy("", "Jewelemp", "");
        
        testStep = "Functional Step 12: Click on Search Button";
        employee.search();
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 13: Validate Employee ID, Employee FirstName, Employee LastName column displays searched Employee";
        employee.validateClientExistBy("7984789478", "Jewelemp", "Barua");
        
        testStep = "Functional Step 14: Click on Clear Button";
        employee.clear();
        
        testStep = "Functional Step 15: Enter Data in Employee LastName field";
        employee.searchEmployeeBy("", "", "Barua");
        
        testStep = "Functional Step 16: Click on Search Button";
        employee.search();
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 17: Validate Employee ID, Employee FirstName, Employee LastName column displays searched Employee";
        employee.validateClientExistBy("7984789478", "Jewelemp", "Barua");
        
        testStep = "Functional Step 18: Click on Clear Button";
        employee.clear();
        
        testStep = "Functional Step 19: Enter Data in Employee ID and Employee FirstName field";
        employee.searchEmployeeBy("7984789478", "Jewelemp", "");
        
        testStep = "Functional Step 20: Click on Search Button";
        employee.search();
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 21: Validate Employee ID, Employee FirstName, Employee LastName column displays searched Employee";
        employee.validateClientExistBy("7984789478", "Jewelemp", "Barua");
        
        testStep = "Functional Step 22: Click on Clear Button";
        employee.clear();
        
        testStep = "Functional Step 23: Enter Data in Employee ID, Employee FirstName, Employee LastName field";
        employee.searchEmployeeBy("7984789478", "Jewelemp", "Barua");
        
        testStep = "Functional Step 24: Click on Search Button";
        employee.search();
        wrapper.holdOn(2000);
        
        testStep = "Functional Step 25: Validate Employee ID, Employee FirstName, Employee LastName column displays searched Employee";
        employee.validateClientExistBy("7984789478", "Jewelemp", "Barua");
        
*/      
        
          }
}
