package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by vumvo on 4/18/2017.
 */
public class VisitMaintenanceCreateCallPage {
    Wrapper baseObj = new Wrapper();
    NotificationPage notification =new NotificationPage();
    
    public VisitMaintenanceCreateCallPage enterClientFilter(String clientName, String category, String supervisor, String contact, String isActive){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        String inputClientName = XLReader.TestCaseDataMap.get(clientName);
        if (inputClientName != null) {
            baseObj.setTextBoxValue("VM_CreateCall_ClientName_ED", clientName);
        }
        String inputCategory = XLReader.TestCaseDataMap.get(category);
        if (inputCategory != null) {
            baseObj.selectByVisibleText("VM_CreateCall_Category_DD", inputCategory);
        }
        String inputSupervisor = XLReader.TestCaseDataMap.get(supervisor);
        if (inputSupervisor != null) {
            baseObj.selectByVisibleText("VM_CreateCall_Supervisor_DD", inputSupervisor);
        }
        String inputContract = XLReader.TestCaseDataMap.get(contact);
        if (inputContract != null){
            baseObj.selectByVisibleText("VM_CreateCall_Contract_DD", inputContract);
        }
        String inputIsActive = XLReader.TestCaseDataMap.get(isActive);
        if (inputIsActive != null){
            baseObj.click("VM_CreateCall_IsActiveClient_CHK");
        }

        return this;
    }
    
    public String createNewCall(String c_Fname, String c_Lname,String e_Fname, String e_Laname, String callInTime,String Service){
    	
    	baseObj.click("VisitMaintenance_CreateCall_BTN");
    	baseObj.EnterDynamicData("Clients_SearchClientFirstName_ED", c_Fname);
    	baseObj.EnterDynamicData("Clients_SearchClientLastName_ED", c_Lname);
    	baseObj.click("VM_CreateCall_SearchClient_BTN");
   
    	baseObj.click("Client_Action");
    	String clientID = baseObj.getInnerText("Client_ID_text");
    	next();
    	baseObj.EnterDynamicData("Employees_SearchFirstName_ED", e_Fname);
    	baseObj.EnterDynamicData("Employees_SearchLastName_ED", e_Laname);
    	baseObj.click("VM_CreateCall_SearchEmployee_BTN");
    	baseObj.click("Employee_Action");
    	next();
    	//String empID = baseObj.getInnerText("Employee_ID_text");
    	baseObj.EnterDynamicData("Call_Date", baseObj.getCurrentDate_DDMMYYYY());
    	baseObj.setTextBoxValue("Call_Time", callInTime);
    	
    	if (Service!= ""){
    		baseObj.selectByDynamicText("Select_Service",Service);
    	}
    	
    	baseObj.click("VM_CreateCall_Finish_BTN");
    	notification.confirmOK();
    	
    	return clientID ;
    	
    }

    public VisitMaintenanceCreateCallPage enterEmployeeFilter(String employeeName, String isActive){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        String inputClientName = XLReader.TestCaseDataMap.get(employeeName);
        if (inputClientName != null) {
            baseObj.setTextBoxValue("VM_CreateCall_EmployeeName_ED", employeeName);
        }

        String inputIsActive = XLReader.TestCaseDataMap.get(isActive);
        if (inputIsActive != null){
            baseObj.click("VM_CreateCall_IsActiveEmployee_CHK");
        }
        return this;
    }

    public VisitMaintenanceCreateCallPage enterNewCallInformation(String callDate, String callTime, String timeZone ){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("VM_CreateCall_CallDate_ED",callDate);
        baseObj.setTextBoxValue("VM_CreateCall_CallTime_ED", callTime);
        String inputTimeZone = XLReader.TestCaseDataMap.get(timeZone);
        if (inputTimeZone != null){
            baseObj.selectByVisibleText("VM_CreateCall_TimeZone_DD",timeZone);
        }
        return this;
    }

    public VisitMaintenanceCreateCallPage next(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("VM_CreateCall_Next_BTN");
        return this;
    }

    public VisitMaintenanceCreateCallPage finishNewCall(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("VM_CreateCall_Finish_BTN");
        return this;
    }

    public VisitMaintenanceCreateCallPage selectClientByClientId(String fieldName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement clientGrid = baseObj.getElement("VM_CreateCall_ClientGrid_TBL");
        WebElement dataCell = clientGrid.findElement(By.cssSelector("[for='"+XLReader.TestCaseDataMap.get(fieldName)+"']"));

        if (dataCell != null){
            dataCell.click();
        } else {
            BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:red"+'"'+"> No client with the given id "
                    + XLReader.TestCaseDataMap.get(fieldName)+" displays on the client grid </b>");
            Assert.fail("No client with the given id "
                    + XLReader.TestCaseDataMap.get(fieldName)+" displays on the client grid");
        }
        return this;
    }

    public VisitMaintenanceCreateCallPage selectEmployeeById(String fieldName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement clientGrid = baseObj.getElement("VM_CreateCall_EmployeeGrid_TBL");
        WebElement dataCell = clientGrid.findElement(By.cssSelector("[for='"+XLReader.TestCaseDataMap.get(fieldName)+"']"));

        if (dataCell != null){
            dataCell.click();
        } else {
            BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:red"+'"'+"> No employee with the given id "
                    + XLReader.TestCaseDataMap.get(fieldName)+" displays on the employee grid </b>");
            Assert.fail("No employee with the given id "
                    + XLReader.TestCaseDataMap.get(fieldName)+" displays on the employee grid");
        }
        return this;
    }

    public VisitMaintenanceCreateCallPage searchClient() {
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.click("VM_CreateCall_SearchClient_BTN");
        return this;
    }
    public VisitMaintenanceCreateCallPage searchEmployee() {
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.click("VM_CreateCall_SearchEmployee_BTN");
        return this;
    }

    public VisitMaintenanceCreateCallPage CancelAndConfirm() {
        BaseTest.test.log(LogStatus.INFO, "", "<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
        baseObj.click("VM_CreateCall_Cancel_BTN");
        baseObj.holdOn(1000);
        baseObj.click("Confirmation_OK_BTN");
        baseObj.waitForBrowserToLoadCompletely();
        return this;
    }
}
