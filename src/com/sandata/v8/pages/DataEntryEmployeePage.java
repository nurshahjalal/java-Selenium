package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by vumvo on 3/20/2017.
 */
public class DataEntryEmployeePage {
    Wrapper baseObj = new Wrapper();
    NotificationPage notification =new NotificationPage();
    NavigationPage navigation =new NavigationPage();
    
    
    public String createAutoNewEmployee(){

  	   // navigation.navigateToEmployee();
  	    baseObj.click("Navigation_Employees_ITM");
    	createEmployee();
    	String f_EName = "AutoEmployer" + String.valueOf(baseObj.intRandom(3));
   	 	baseObj.EnterDynamicData("DE_CreateEmp_FirstName",f_EName);
   	 	String L_EName = "TestEmployer" + String.valueOf(baseObj.intRandom(3));
        baseObj.EnterDynamicData("DE_CreateEmp_LastName",L_EName);
        String ssn = "090" + "-" + baseObj.GenerateRandomNumber(2) + "-" + baseObj.GenerateRandomNumber(4);
        baseObj.EnterDynamicData("DE_CreateEmp_SSN", ssn);
       // baseObj.EnterDynamicData("DE_CreateEmp_SanID", baseObj.GenerateRandomNumber(6));
        enterRandomEmail("DE_CreateEmp_Email","DE_CreateEmp_Conf_Email");
        save();
        notification.confirmOK();
        
       return f_EName + ";" + L_EName;
    }
    
    public String createAnotherNewEmployee(){

   	   // navigation.navigateToEmployee();
   	    baseObj.click("Navigation_Employees_ITM");
     	createEmployee();
     	String f_EName = "AnotherEmployer" + String.valueOf(baseObj.intRandom(3));
    	 	baseObj.EnterDynamicData("DE_CreateEmp_FirstName",f_EName);
    	 	String L_EName = "OtherEmployer" + String.valueOf(baseObj.intRandom(3));
         baseObj.EnterDynamicData("DE_CreateEmp_LastName",L_EName);
         String ssn = "090" + "-" + baseObj.GenerateRandomNumber(2) + "-" + baseObj.GenerateRandomNumber(4);
         baseObj.EnterDynamicData("DE_CreateEmp_SSN", ssn);
        // baseObj.EnterDynamicData("DE_CreateEmp_SanID", baseObj.GenerateRandomNumber(6));
         enterRandomEmail("DE_CreateEmp_Email","DE_CreateEmp_Conf_Email");
         save();
         notification.confirmOK();
         
        return f_EName + ";" + L_EName;
     }
    
    public String createNewEmployee(){

    	   // navigation.navigateToEmployee();
    	    baseObj.click("Navigation_Employees_ITM");
      	createEmployee();
      	String f_EName = "AnotherEmployer" + String.valueOf(baseObj.intRandom(3));
     	 	baseObj.EnterDynamicData("DE_CreateEmp_FirstName",f_EName);
     	 	String L_EName = "NoExceptions" + String.valueOf(baseObj.intRandom(3));
          baseObj.EnterDynamicData("DE_CreateEmp_LastName",L_EName);
          String ssn = "090" + "-" + baseObj.GenerateRandomNumber(2) + "-" + baseObj.GenerateRandomNumber(4);
          baseObj.EnterDynamicData("DE_CreateEmp_SSN", ssn);
         // baseObj.EnterDynamicData("DE_CreateEmp_SanID", baseObj.GenerateRandomNumber(6));
          enterRandomEmail("DE_CreateEmp_Email","DE_CreateEmp_Conf_Email");
          save();
          notification.confirmOK();
          
         return f_EName + ";" + L_EName;
      }
    public DataEntryEmployeePage createEmployee(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Employees_Create_New_BTN");
        return this;
    }

    public DataEntryEmployeePage searchEmployeeBy(String employeeID, String employeeFirstName, String employeeLastName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        if (employeeID != null){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> by Client ID: "+ employeeID+"</b>");
            baseObj.getElement("Employees_SearchEmployeeID_ED").sendKeys(employeeID);
        }

        if (employeeFirstName != null){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> by Client First Name: "+ employeeFirstName+"</b>");
            baseObj.getElement("Employees_SearchFirstName_ED").sendKeys(employeeFirstName);
        }

        if (employeeLastName != null){
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> by Client First Name: "+ employeeLastName+"</b>");
            baseObj.getElement("Employees_SearchLastName_ED").sendKeys(employeeLastName);
        }
        return this;
    }

    public DataEntryEmployeePage search(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Employees_Search_BTN");
        return this;
    }

    public DataEntryEmployeePage clear(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Employees_Clear_BTN");
        return this;
    }

    public DataEntryEmployeePage validateClientExistBy(String employeeID, String firstName, String lastName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement clientGrid = baseObj.getElement("Employees_EmployeeGrid_TBL");
        List<WebElement> rows = clientGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
            if(row.findElements(By.xpath("td[.='"+ employeeID + "']")).size() > 0){

                List<WebElement> cells = row.findElements(By.cssSelector("td"));
                String actualClientID = cells.get(0).getText().trim();
                Assert.assertEquals(actualClientID,employeeID);

                if (firstName != null){
                    String actualFirstName = cells.get(1).getText().trim();
                    Assert.assertEquals(actualFirstName, firstName);
                }

                if (lastName != null){
                    String actualLastName = cells.get(2).getText().trim();
                    Assert.assertEquals(actualLastName, lastName.trim());
                }
                isMatch = true;
            }
            if (isMatch){
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the employee ID " + employeeID);
        }
        return this;
    }

    public DataEntryEmployeePage editEmployeeWithID(String clientID){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement employeeGrid = baseObj.getElement("Employees_EmployeeGrid_TBL");
        List<WebElement> rows = employeeGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
            if(row.findElements(By.xpath("td[.='"+ clientID + "']")).size() > 0){
                WebElement btnEdit = row.findElement(By.cssSelector(".edit-employee"));
                btnEdit.click();
                isMatch = true;
            }
            if (isMatch){
                baseObj.waitForBrowserToLoadCompletely();
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the client ID " + clientID);
        }
        return this;
    }

    public DataEntryEmployeePage deleteEmployeeWithID(String employeeID){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement clientGrid = baseObj.getElement("Employees_EmployeeGrid_TBL");
        List<WebElement> rows = clientGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
            if(row.findElements(By.xpath("td[.='"+ employeeID + "']")).size() > 0){
                WebElement btnEdit = row.findElement(By.cssSelector(".delete-employee"));
                btnEdit.click();
                isMatch = true;
            }
            if (isMatch){
                baseObj.waitForBrowserToLoadCompletely();
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the employee ID " + employeeID);
        }
        return this;
    }
	
	public DataEntryEmployeePage validateEmptyfield(String locatorName, String expectedValue){
		 BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		 baseObj.validateInnerTextDirectly(locatorName, expectedValue); 
		 return this;
	}

	public DataEntryEmployeePage enterRandomText(String locatorName){
	  	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	  	baseObj.setTextBoxValue_Direct(locatorName, baseObj.generateRandomString(5));
		return this;
	}
	
	public DataEntryEmployeePage enterData(String locatorName, String textToInput){
	  	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	  	baseObj.setTextBoxValue_Direct(locatorName, textToInput);
		return this;
	}
	
	 public DataEntryEmployeePage enterRandomSantraxID(String locatorName){
		  	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		  	int number = baseObj.intRandom(5);
		  	baseObj.setTextBoxValue_Direct(locatorName, String.valueOf(number));
			return this;
		}
	
	  public DataEntryEmployeePage enterRandomEmail(String locatorName,String anotherLocator){
		  	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		  	String email = getRandomEmail();
		  	baseObj.setTextBoxValue_Direct(locatorName, email); 
		  	baseObj.setTextBoxValue_Direct(anotherLocator, email);
			return this;
		}
	    
	    private String getRandomEmail(){
	    	
	    	String randomEmail=baseObj.generateRandomString(5) + "@sandata.com";
		  	return  randomEmail ; 
	    }
	
	    public DataEntryEmployeePage save(){
			BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
			baseObj.click("DE_CreateEmp_Save");
	    return this;
	}
    
	    public DataEntryEmployeePage cancel(){
	 			BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	 			baseObj.click("DE_CreateEmp_Cancel");
	 	    return this;
	 	}
	    
	    public DataEntryEmployeePage validateCreateEmployeePage(String expectedValue){
	        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	        baseObj.validateInnerTextDirectly("DE_CreateEmployee_Page", expectedValue);
	        return this;
	    }
	    
	    public DataEntryEmployeePage validateToolTipMessage(String messageFieldName){
	        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	        baseObj.validateInnerText("DE_CreateEmployee_toolTip", messageFieldName);
	        baseObj.holdOn(3000);
	        return this;
		}
		
		public void validateRequiredMessage(String locatorName, String validationName){
	        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	        baseObj.validateInnerTextContains(locatorName, validationName);
	        }
	    
	    public DataEntryEmployeePage enterRandomSSN(String locatorName){
		  	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		  	int number = baseObj.intRandom(9);
		  	baseObj.setTextBoxValue_Direct(locatorName, String.valueOf(number));
			return this;
		}
}
