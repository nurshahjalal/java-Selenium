package com.sandata.v8.pages;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;

public class DashBoardPage {
	Wrapper baseObj = new Wrapper();
	
	 public void validateAbsenceOfField(String locator){
	        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");

	        try {
	   		 baseObj.getDriver().findElement(baseObj.getLocatorBy(locator, XLReader.LocatorDataMap.get(locator).get("LocatorType"), XLReader.LocatorDataMap.get(locator).get("LocatorValue"))).isDisplayed();
	   		BaseTest.test.log(LogStatus.FAIL,
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>Step No - "
							+ Wrapper.StepNumber++ + "</b>" + "</b>",
							"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">"+locator + "</b> "
									+ "</b>" + "is Visible ");
			Assert.assertTrue(false, "Field is Visible");
				   		   		
	   	} catch (Exception e) {
	   		BaseTest.test.log(LogStatus.PASS,
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>Step No - "
							+ Wrapper.StepNumber++ + "</b>" + "</b>",
							"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">"+locator + "</b> "
									+ "</b>" + " is Invisible");   	}           
	    }
	 

	 public void validatePresenceOfField_UnknownClients_UnknownEmployees(){
	        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	       baseObj.validateElementVisible("Dasboard_UnknownClients_LBL");
	       baseObj.validateElementVisible("Dasboard_UnknownEmployees_LBL");       
	    }
	 
	 public void validatePresenceOfField_NoShow_UnsheduledVisit(){
	        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	       baseObj.validateElementVisible("Dasboard_NoShow_LBL");
	       baseObj.validateElementVisible("Dasboard_UnscheduledVisits_LBL");       
	    }
	 
	 public void clickOnDashBoardLinks(String locator){
	        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	
		 baseObj.click(locator);
	 }
	 public void validateNavigationPage(String exceptionType){
		 baseObj.click("VisitMaintenance_ShowFilter");
		 baseObj.validateSelectedValueInSingleSelectionDropdown("VisitMaintenance_ExceptionType_DD", exceptionType);
//	    String ExpectedDate = baseObj.getCurrentDateWithFormat(null);
//		 baseObj.validatePartialValueAttribute_Direct("VisitMaintenance_StartDate_ED", ExpectedDate);
//		 baseObj.validatePartialValueAttribute_Direct("VisitMaintenance_EndDate_ED", ExpectedDate);
	 }


    public int getNumberUnknownClientsVisit(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        String numberOfVisits =  baseObj.getInnerText("Dashboard_UnknownClients_LBL");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;"+'"'+"> Number of Unknown Clients Visit="+numberOfVisits+"</b>");
        return Integer.parseInt(numberOfVisits);
    }

    public int getNumberUnknownEmployeesVisit(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        String numberOfVisits =  baseObj.getInnerText("Dashboard_UnknownEmployees_LBL");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;"+'"'+"> Number of Unknown Employees Visit="+numberOfVisits+"</b>");
        return Integer.parseInt(numberOfVisits);
    }
    
    public void checkForNoObject(String locatorName){
    	
    }
    
    public void verifyObjectExistence(String locatorName){
		if (baseObj.getLocatorValue(locatorName)){
			BaseTest.test.log(LogStatus.FAIL,"<b>Step No - "+baseObj.StepNumber+++" </b> " + locatorName +" Section Is Available");
		}else {
			BaseTest.test.log(LogStatus.PASS,"<b>Step No - "+baseObj.StepNumber+++" </b> " + locatorName +" Section Is Not Available");
		
	}
    }
}
