package com.sandata.v8.functional.DataEntry.Employees;

import com.sandata.core.BaseDriver;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.sandata.utilities.XLReader.TestCaseDataMap;

public class Auto_V8_TC_63048_Employee_Verify_Required_Employee_Fields_when_Modification_Employee extends BaseTest {

	LoginPage login = new LoginPage();
	NavigationPage navigation =new NavigationPage();
	Wrapper wrapper = new Wrapper();
	DataEntryEmployeePage employee = new DataEntryEmployeePage();
	NotificationPage notification = new NotificationPage();

	@Test(dataProvider="td", groups = {"smoke"})
	public void Auto_V8_TC_63047_Employee_Verify_Required_Employee_Fields_when_creation_Employee(String dummyData) throws IOException, InterruptedException {
        BaseDriver.GetDriver();
        
        testStep = "Functional Step 1: Login with valid Agency value";
        login.login();

        testStep= "Functional Step 2: Click on Data Entry and go to Employee page";
	    navigation.navigateToEmployee();
	  
	    testStep= "Functional Step 3: Click on Create New Employee Button and Validate Create New Employee Page";
	    employee.createEmployee();
	    employee.validateCreateEmployeePage("Create Employee");

	    testStep= "Functional Step 4: Validate * sign on Top of First Name, Last Name, Santrax ID, Socaial Security, Email and Confirm Email fields";
	    employee.validateRequiredMessage("DE_TitleFname", "DE_Requird_Sign"); 
	    employee.validateRequiredMessage("DE_TitleLname", "DE_Requird_Sign"); 
	    employee.validateRequiredMessage("DE_TitleSanID", "DE_Requird_Sign");
	    employee.validateRequiredMessage("DE_TitleSSN", "DE_Requird_Sign");
	    employee.validateRequiredMessage("DE_TitleEmail", "DE_Requird_Sign"); 
	    employee.validateRequiredMessage("DE_TitleConfEmail", "DE_Requird_Sign");
	    
	    testStep= "Functional Step 5: Click on Save Button";
	    employee.save();
	    
	    testStep= "Functional Step 6: Validate The Error Message on Top of First Name, Last Name, Santrax ID, Socaial Security, Email and Confirm Email fields";
	    employee.validateRequiredMessage("DE_Fname_RequirdMsg", "DE_Error_Requird"); 
	    employee.validateRequiredMessage("DE_Lname_RequirdMsg", "DE_Error_Requird"); 
	    employee.validateRequiredMessage("DE_SanID_RequirdMsg", "DE_Error_Requird");
	    employee.validateRequiredMessage("DE_SSN_RequirdMsg", "DE_Error_Requird");
	    employee.validateRequiredMessage("DE_Email_RequirdMsg", "DE_Error_Requird"); 
	    employee.validateRequiredMessage("DE_ConfEmail_RequirdMsg", "DE_Error_Requird");
	    
	    testStep= "Functional Step 7: Enter Data in to First Name, Last Name, Santrax ID, Social Security, Email and Confirm Email field";
	    employee.enterRandomText("DE_CreateEmp_FirstName");
	    employee.enterRandomText("DE_CreateEmp_LastName");
	    employee.enterRandomSantraxID("DE_CreateEmp_SanID");
	    employee.enterRandomSSN("DE_CreateEmp_SSN");
	    employee.enterRandomEmail("DE_CreateEmp_Email","DE_CreateEmp_Conf_Email");
	    
	    
	    String empFName = wrapper.getTextBoxValue("DE_CreateEmp_FirstName");
	    String empLName = wrapper.getTextBoxValue("DE_CreateEmp_LastName");
	    String ssn = wrapper.getTextBoxValue("DE_CreateEmp_SSN");
	    String sanID = wrapper.getTextBoxValue("DE_CreateEmp_SanID");
	    String email = wrapper.getTextBoxValue("DE_CreateEmp_Email");
	    
	    testStep= "Functional Step 8: Click on Save Button";
	    employee.save();
	    
	    testStep="Functional Step 9 : Click OK to confirm";
	    notification.confirmOK();
	    
	    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_FirstName", empFName);
	    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_LastName", empLName);
	    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SSN", ssn);
	    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SanID", sanID);
	    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_Email", email);
	    
	    
	    
	    testStep= "Functional Step 7: Enter Data in to First Name, Last Name, Santrax ID, Social Security, Email and Confirm Email field";
	    employee.enterRandomText("DE_CreateEmp_FirstName");
	    employee.enterRandomText("DE_CreateEmp_LastName");
	    employee.enterRandomSantraxID("DE_CreateEmp_SanID");
	    employee.enterRandomSSN("DE_CreateEmp_SSN");
	    employee.enterRandomEmail("DE_CreateEmp_Email","DE_CreateEmp_Conf_Email");
	    
	    
	     empFName = wrapper.getTextBoxValue("DE_CreateEmp_FirstName");
	     empLName = wrapper.getTextBoxValue("DE_CreateEmp_LastName");
	     ssn = wrapper.getTextBoxValue("DE_CreateEmp_SSN");
	     sanID = wrapper.getTextBoxValue("DE_CreateEmp_SanID");
	     email = wrapper.getTextBoxValue("DE_CreateEmp_Email");
	     
	     testStep= "Functional Step 8: Click on Save Button";
		 employee.save();
		    
		  testStep="Functional Step 9 : Click OK to confirm";
		  notification.confirmOK();
		  
		  // open the same employee
		  
		 	wrapper.validateDynamicTextBoxValue("DE_CreateEmp_FirstName", empFName);
		    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_LastName", empLName);
		    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SSN", ssn);
		    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SanID", sanID);
		    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_Email", email);
		    
		    
		    testStep= "Functional Step 8: Click on Save Button";
			 employee.save();
			    
			  testStep="Functional Step 9 : Click OK to confirm";
			  notification.confirmOK();
			  
			  // open the same employee  
			  
			  wrapper.validateDynamicTextBoxValue("DE_CreateEmp_FirstName", empFName);
			    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_LastName", empLName);
			    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SSN", ssn);
			    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SanID", sanID);
			    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_Email", email);
			  
		  
			    employee.enterRandomText("DE_CreateEmp_FirstName");
			    
			    // Hit cancel
			    testStep= "Functional Step 8: Click on Save Button";
				 employee.cancel();
				 
				 
				  testStep="Functional Step 9 : Click OK to confirm";
				  notification.confirmOK();
				 
				 
				 
				// open the same employee  
				 wrapper.validateDynamicTextBoxValue("DE_CreateEmp_FirstName", empFName);
				    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_LastName", empLName);
				    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SSN", ssn);
				    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SanID", sanID);
				    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_Email", email);
				 
				 
				    // Hit cancel
				    testStep= "Functional Step 8: Click on Save Button";
					 employee.cancel();
					 
					 
					  testStep="Functional Step 9 : Click OK to confirm";
					  notification.cancel();
	    
	    
	    
					// open the same employee  
					  wrapper.validateDynamicTextBoxValue("DE_CreateEmp_FirstName", empFName);
					    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_LastName", empLName);
					    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SSN", ssn);
					    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_SanID", sanID);
					    wrapper.validateDynamicTextBoxValue("DE_CreateEmp_Email", email);
	    
    }
	
	
}

