package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vumvo on 4/18/2017.
 */
public class VisitDetailPage {

    Wrapper baseObj = new Wrapper();

    public VisitDetailPage validateVisitDetailDisplayWithActiveTab(String tabName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.CheckObjectExist("VisitDetail_Modal_Title");
        baseObj.holdOn(2000);
        BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">Visit Detail modal exist</b>");
        String status;
        switch (tabName.toLowerCase()){
                case "general":
                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px"+'"'+">Verifying GENERAL tab is active</b>");
                status = baseObj.getAttribute("VisitDetail_GeneralTab_BTN","aria-expanded");
                Assert.assertEquals(status,"true",tabName + " IS NOT ACTIVE");
                BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">GENERAL tab is active</b>");
                break;
          case "client":
                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px"+'"'+">Verifying CLIENT tab is active</b>");
                status = baseObj.getAttribute("VisitDetail_ClientTab_BTN","aria-expanded");
                Assert.assertEquals(status,"true",tabName + " IS NOT ACTIVE");
                BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">CLIENT tab is active</b>");
                break;
            case "employee":
                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px"+'"'+">Verifying EMPLOYEE tab is active</b>");
                status = baseObj.getAttribute("VisitDetail_EmployeeTab_BTN","aria-expanded");
                Assert.assertEquals(status,"true",tabName + " IS NOT ACTIVE");
                BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">EMPLOYEE tab is active</b>");
                break;
            case "call log":
                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px"+'"'+">Verifying CALL LOG tab is active</b>");
                status = baseObj.getAttribute("VisitDetail_CallTab_BTN","aria-expanded");
                Assert.assertEquals(status,"true",tabName + " IS NOT ACTIVE");
                BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">CALL LOG tab is active</b>");
                break;
            case "memo":
                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px"+'"'+">Verifying MEMO tab is active</b>");
                status = baseObj.getAttribute("VisitDetail_MemoTab_BTN","aria-expanded");
                Assert.assertEquals(status,"true",tabName + " IS NOT ACTIVE");
                BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">MEMO tab is active</b>");
                break;
        }
        return this;
    }

    public VisitDetailPage validateSelectEmployeeSectionDisplayed(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeLastName_ED");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeFirstName_ED");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeId_ED");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeDescipline_ED");
        baseObj.CheckObjectExist("VisitDetail_SearchEmployee_BTN");
        return this;
    }
    public VisitDetailPage ClickOnGeneralTab(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("VisitDetail_GeneralTab_BTN");
    	 return this;
    }
    
    public VisitDetailPage ClickOnExceptionTab(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("VisitDetail_ExceptionTab_BTN");
    	 return this;
    }
    
    public VisitDetailPage ClickOnCallLogTab(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("VisitDetail_CallTab_BTN");
    	 return this;
    }
    
    public VisitDetailPage EnterCallDate(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	 baseObj.EnterDynamicData("VD_Call_Log_Date", baseObj.getCurrentDate_DDMMYYYY());
    	 return this;
    }
    
    public VisitDetailPage EnterCustomCallDate( int customDay){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	 baseObj.EnterDynamicData("VD_Call_Log_Date", baseObj.getPrevious_dateFormatDDMMYYYY( customDay));
    	 return this;
    }
   
    public VisitDetailPage EnterCallTime(String strValue){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.setTextBoxValue("VD_Confirmation_Time",strValue );
    	return this;
    }
    
    public VisitDetailPage EnterAdjustInTime(String strValue){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.setTextBoxValue("VD_General_AdjustedIn",strValue );
    	return this;
    }
    public VisitDetailPage EnterAdjustOutTime(String strValue){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.setTextBoxValue("VD_General_AdjustedOut",strValue );
    	return this;
    }
    
    public VisitDetailPage EnterBillHours(String strValue){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.setTextBoxValue("VD_General_BillHours",strValue );
    	return this;
    }
    
    public VisitDetailPage ClickDoNot_bill(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("General_DoNotBill");
    	return this;
    }
    
    public VisitDetailPage SelectReasonCode(String locatorname){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.selectByIndex(locatorname, 4);
    	return this;
    }
    
    public VisitDetailPage SelectService(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.selectByIndex("VM_Detail_Service_DD", 4);
    	return this;
    }
    
    public VisitDetailPage ManualCallAdd(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("VD_Manual_Add");
    	 return this;
    }
    
    public VisitDetailPage AcknowledgeException(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("VD_Acknowledge_Exception");
    	 return this;
    }
    
    public VisitDetailPage SaveException(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("VD_Save_Exception");
    	 return this;
    }
    
    public VisitDetailPage SaveGeneral(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("VD_General_Save");
    	 return this;
    }
    
    
    public VisitDetailPage verifyVisitStaus(String expectedStatus){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.validateTextBoxValue("Visit_Status_ED",expectedStatus );
    	return this;
    }
    
    public VisitDetailPage verifyCallInStaus(String expectedStatus){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.validateTextBoxValue("Visit_callIn_ED",expectedStatus );
    	return this;
    }
    
    public VisitDetailPage verifyCallOutStaus(String expectedStatus){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.validateTextBoxValue("Visit_CallOut_ED",expectedStatus );
    	return this;
    }
    
    public VisitDetailPage verifyWithoutCallException(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.validateDynamicText("VD_Visit_Without_Call","Visits without out-calls" );
    	return this;
    }
    
    public VisitDetailPage verifyCallOutTime(String strValue){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.validateTextBoxValue("Visit_CallOut_ED",strValue );
    	return this;
    }
    
    
    public VisitDetailPage Check_Visit_Disabality(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.Check_EditBox_ReadOnly("Visit_St_DT_ED");
        baseObj.Check_EditBox_ReadOnly("Visit_End_DT_ED");
        baseObj.Check_EditBox_ReadOnly("Visit_TZ_ED");
        baseObj.Check_EditBox_ReadOnly("Visit_Status_ED");
        baseObj.Check_EditBox_ReadOnly("Visit_callIn_ED");
        baseObj.Check_EditBox_ReadOnly("Visit_CallOut_ED");
        baseObj.Check_EditBox_ReadOnly("Visit_CllHours_ED");
     
        
        return this;
    }
    public VisitDetailPage CheckExceptions(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.CheckObjectExist("NonFixable_Exception_1");
        baseObj.CheckObjectExist("NonFixable_Exception_2");
        baseObj.CheckObjectExist("Fixable_Exception_3");
        baseObj.CheckObjectExist("Fixable_Exception_4");
        return this;
    }
    public VisitDetailPage GeneralTab_VisitItem(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.validateDynamicText("GeneralTab_VisitStartDate", "VISIT START DATE");
        baseObj.validateDynamicText("GeneralTab_VisitEndDate", "VISIT END DATE");
        baseObj.validateDynamicText("GeneralTab_VisitTimeZone", "VISIT TIME ZONE"); 
        baseObj.validateDynamicText("GeneralTab_VisitStatus", "VISIT STATUS");
        baseObj.validateDynamicText("GeneralTab_CallIn", "CALL IN");
        baseObj.validateDynamicText("GeneralTab_CallOut", "CALL OUT");
        baseObj.validateDynamicText("GeneralTab_CallHours", "CALL HOURS");
        return this;
    }
    
    public VisitDetailPage GeneralTab_AgencyItem(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.validateDynamicText("GeneralTab_AgencyID", "AGENCY ID");
        baseObj.validateDynamicText("GeneralTab_AgencyName", "AGENCY NAME");
        baseObj.validateDynamicText("GeneralTab_AgencyPayer", "PAYER");
        baseObj.validateDynamicText("GeneralTab_AgencyProgram", "PROGRAM");
        baseObj.validateDynamicText("GeneralTab_AgencyService", "SERVICE");
        baseObj.validateDynamicText("GeneralTab_AgencyBillCode", "BILL CODE");
        
        return this;
    }
    
    public VisitDetailPage GeneralTab_VisitVerification(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.validateDynamicText("GeneralTab_ClientTime", "CLIENT VERIFIED TIME");
        baseObj.validateDynamicText("GeneralTab_ClientService", "CLIENT VERIFIED SERVICE");
        baseObj.validateDynamicText("GeneralTab_ClientSignature", "CLIENT SIGNATURE");
        baseObj.validateDynamicText("GeneralTab_VisitSource", "VISIT SOURCE");
        baseObj.validateDynamicText("GeneralTab_ThirdParty", "3RD PARTY VISIT ID");

        
        
        return this;
    }
    
    public VisitDetailPage VerifyVisitUpdateMessage(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.validateTextBoxValue("Status_Msg", "Visit is successfully updated.");
        return this;
    	
    }
    
    public VisitDetailPage Check_VisitVerification_Disabality(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.Check_EditBox_ReadOnly("VD_ClientVerifiedTime");
        baseObj.Check_EditBox_ReadOnly("Visit_End_DT_ED");
        baseObj.Check_EditBox_ReadOnly("VD_ClientVerifiedService");
        baseObj.Check_EditBox_ReadOnly("VD_vv_modal_client_signature");
        baseObj.Check_EditBox_ReadOnly("VD_GeneralVisitSource");
        baseObj.Check_EditBox_ReadOnly("VD_ThirdPartyVisitID");
     
        
        return this;
    }
    
    public VisitDetailPage Check_Agency_Disabality(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.Check_EditBox_ReadOnly("GeneralAgencyID_ED");
        baseObj.Check_EditBox_ReadOnly("GeneralAgencyName_ED");
        baseObj.Check_EditBox_ReadOnly("General_ContractName_ED");
        baseObj.Check_EditBox_ReadOnly("General_Program_ED");
        baseObj.Check_EditBox_ReadOnly("General_BillCode_ED");
        baseObj.Check_EditBox_ReadOnly("Visit_CallOut_ED");

     
        
        return this;
    }
    
    public VisitDetailPage validateSelectClientSectionDisplayed(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeLastName_ED");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeFirstName_ED");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeId_ED");
        baseObj.CheckObjectExist("VisitDetail_SelectEmployeeDescipline_ED");
        baseObj.CheckObjectExist("VisitDetail_SearchEmployee_BTN");
        return this;
    }


    public VisitDetailPage validateHeaderClientName(String clientNameField){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.validateInnerText("VisitDetail_HeaderClientName_LBL", clientNameField);
        return this;
    }

    public VisitDetailPage validateHeaderEmployeeName(String employeeNameField){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.validateInnerText("VisitDetail_HeaderEmployeeName_LBL", employeeNameField);
        return this;
    }

    public VisitDetailPage validateManualTabNotExist(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        Assert.assertEquals(baseObj.getDriver().findElements(By.xpath("//a[.='MANUAL CALL']")).size(),0);
        return this;
    }
    
  //Use the Same field name (AdjustInTime) whenever this function need to be used for General Tab
    public VisitDetailPage General_updateAdjustedIn(){
    	baseObj.setTextBoxValue("VD_General_AdjustedIn", "AdjustInTime");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        return this;
    }
    
  //Use the Same field name (AdjustInTime) whenever this function need to be used for General Tab
    public VisitDetailPage General_updateAdjustedOut(){
    	baseObj.setTextBoxValue("VD_General_AdjustedOut", "AdjustOutTime");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        return this;
    }

    
    
   

    
    public VisitDetailPage General_Save(){
    	baseObj.click("VD_General_Save");;
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        return this;
    }

    public VisitDetailPage validateLabelNotExist(String labelName) {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        Assert.assertEquals(baseObj.getDriver().findElements(By.xpath("//*[text()='"+labelName+"']")).size(),0);
        return this;
    }

    public VisitDetailPage close() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.holdOn(2000);
        baseObj.click("VisitDetail_Close_BTN");
        baseObj.waitForElementNotPresence("VisitDetail_Close_BTN");
        baseObj.holdOn(1000);
        return this;
    }

    
    public VisitDetailPage count_Character(String locatorName, String expectedCount){
    	
    	String count = baseObj.getInnerText(locatorName).split(" ")[0];
    	if (count.equalsIgnoreCase(expectedCount)){
    		 BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+" Character Remaining Found As "
    		 		+count + "</b>");
    	}else{
    		
    		 BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+" Character Remaining Not Found As Expected "
     		 		 + "</b>");
    	}
    	return this;
    }
    
    public VisitDetailPage Update_General_AdjustedTime(){
    	General_updateAdjustedIn();
        General_updateAdjustedOut();
        updateBillHours();
    	return this;
    }
    
    public VisitDetailPage updateBillHours(){
    	baseObj.setTextBoxValue("VD_General_BillHours", "BillHours");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        return this;
    }
    
    public VisitDetailPage closeVisitDetail(){
    	BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.click("Visit_Details_Cancel");
    	return this;
    }

}
