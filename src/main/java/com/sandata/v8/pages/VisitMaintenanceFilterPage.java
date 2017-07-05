package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vumvo on 4/18/2017.
 */
public class VisitMaintenanceFilterPage {

    Wrapper baseObj = new Wrapper();

    public VisitMaintenanceFilterPage createCall(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("VisitMaintenance_CreateCall_BTN");
        baseObj.waitForBrowserToLoadCompletely();
        return this;
    }

    public VisitMaintenanceFilterPage enterFilterDateRangeFilter(String startDateTimeField, String endDateTimeField){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.holdOn(2000);
        baseObj.getElement("VisitMaintenance_StartDate_ED").clear();
        baseObj.setTextBoxValue("VisitMaintenance_StartDate_ED", startDateTimeField);
        baseObj.holdOn(1000);
        baseObj.getElement("VisitMaintenance_StartDate_ED").sendKeys(Keys.TAB);
        baseObj.holdOn(2000);
        baseObj.setTextBoxValue("VisitMaintenance_EndDate_ED", endDateTimeField);
        baseObj.holdOn(1000);
        baseObj.getElement("VisitMaintenance_EndDate_ED").sendKeys(Keys.TAB);
        return this;

    }
    
    public VisitMaintenanceFilterPage EnterFromDate(){
    	
    	baseObj.getElement("VisitMaintenance_StartDate_ED").clear();
    	baseObj.holdOn(1000);
    	baseObj.click("VisitMaintenance_StartDate_ED");
        baseObj.EnterDynamicData("VisitMaintenance_StartDate_ED", baseObj.getPrevious_dateFormatDDMMYYYY(30));
    	
    	return this;
    	
    	
    }
    
public VisitMaintenanceFilterPage EnterCustomFromDate(int customDate){
    	
    	baseObj.getElement("VisitMaintenance_StartDate_ED").clear();
    	baseObj.holdOn(1000);
    	baseObj.click("VisitMaintenance_StartDate_ED");
        baseObj.EnterDynamicData("VisitMaintenance_StartDate_ED", baseObj.getPrevious_dateFormatDDMMYYYY(customDate));
    	
    	return this;
    	
    	
    }
    
    
   public VisitMaintenanceFilterPage verifyVisitStatus(String expectedValue){
	    BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
    	baseObj.validateInnerText("VM_Visit_Status", expectedValue);
    	return this;
    	
    	
    }
   
   public VisitMaintenanceFilterPage optionalClick(){
	   
	   boolean checkForNoData = baseObj.CheckObjectExist("VM_NoData");
	   
	   while(checkForNoData){
		   baseObj.holdOn(2000);
		   clickOnSearch();
		   checkForNoData = baseObj.CheckObjectExist("VM_NoData");
	   }
	   
	   return this;
   }
    public VisitMaintenanceFilterPage enterFilterClient(String clientNameField){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("VisitMaintenance_Client_ED", clientNameField);
        return this;
    }
    
    public VisitMaintenanceFilterPage enterFilterEmployee(String employeeNameField){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("VisitMaintenance_Employee_ED", employeeNameField);

        return this;
    }

    public VisitMaintenanceFilterPage clickOnSearch(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("VisitMaintenance_Search_BTN");
       // baseObj.getElement("VisitMaintenance_PageNavigation_BAR"); // Wait for Page Navigation Bar display
        return this;
    }

    public VisitMaintenanceFilterPage showDisplayOptions(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("VisitMaintenance_ShowDisplayOptions_BTN");
        return this;
    }

    public VisitMaintenanceFilterPage showAdvanceFilter(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("VisitMaintenance_ShowHideAdvanceFilterOptions_BTN");
        return this;
    }

    public VisitMaintenanceFilterPage checkDisplayOptions(String optionName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement option = baseObj.getDriver().findElement(By.xpath("//div[@class='custom-checkbox']/label[text()='"+optionName+"']"));
        option.click();
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">Display Option: "+optionName+" is clicked</b>");
        return this;
    }
    public VisitMaintenanceFilterPage validateActualVisitExist(String visitDate, String actualTime){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement vmGrid = baseObj.getElement("VisitMaintenance_VisitGrid_TBL");
        List<WebElement> rows = vmGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
          if(row.findElements(By.cssSelector("[data-visit-date='"+visitDate+"']")).size() > 0){
              List<WebElement> cells = row.findElements(By.cssSelector("[data-vm-tab-show='call_details']"));
              for (WebElement cell : cells){
                  String visitTime = cell.getText().trim();
                  if (actualTime.equalsIgnoreCase(visitTime)){
                      Assert.assertEquals(actualTime.toUpperCase(), visitTime.toUpperCase());
                      isMatch = true;
                      break;
                  }
              }
          }
            if (isMatch){
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the given date " + visitDate + " at " + actualTime);
        }
        return this;
    }

    public VisitMaintenanceFilterPage validateManageVisitPageIsDisplayed(){
        String currentPageName = baseObj.getElement("Navigation_CurrentPage_LBL").getText().trim();
        Assert.assertEquals(currentPageName,"Manage Visits");
        return this;
    }
    
    public VisitMaintenanceFilterPage clickEveryColumn(String locatorName){
    	
    	WebElement element = baseObj.getElement(locatorName);
		//baseObj.element = getElement(locatorName);
		List<WebElement> rows=element.findElements(By.tagName("tr"));
		
		
		for (int col=0; col <12; col++){
				List<WebElement> columns=rows.get(1).findElements(By.tagName("td"));
				String txt = columns.get(col).getText();
				if (!txt.equalsIgnoreCase("N/A")){
					columns.get(col).click();
					baseObj.holdOn(1000);
					baseObj.CheckObjectExist("Visit_Details_Text");
					baseObj.click("Visit_Details_Cancel");
					baseObj.holdOn(1000);
				}
		}
    	return this;
    }
    public VisitMaintenanceFilterPage openVisitDetailBy(String clientName, String employeeName, String visitDate, String columnName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement vmGrid = baseObj.getElement("VisitMaintenance_VisitGrid_TBL");
        List<WebElement> rows = vmGrid.findElements(By.cssSelector("tr[data-row-id]"));

        boolean isMatch = false;
        for (WebElement row : rows){

            if(row.findElements(By.cssSelector("[data-visit-date='"+visitDate+"']")).size() > 0){
                WebElement clientCell = row.findElement(By.cssSelector("[data-vm-tab-show='client']"));
                WebElement employeeCell = row.findElement(By.cssSelector("[data-vm-tab-show='employee']"));
                if(clientCell.getText().trim().equals(clientName)
                        && employeeCell.getText().trim().equals(employeeName)) {
                    BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;"+'"'+"> Clicking on the column "+columnName+"</b>");
                    if("Visit Date".equalsIgnoreCase(columnName)){
                        WebElement visitDateCell = row.findElement(By.cssSelector("[data-visit-date='"+visitDate+"']"));
                        visitDateCell.click();
                    } else if ("Employee".equalsIgnoreCase(columnName)){
                        employeeCell.click();
                    } else if ("Client".equalsIgnoreCase(columnName)){
                        clientCell.click();
                    } else {

                        List<WebElement> headers = vmGrid.findElements(By.tagName("th"));
                        int i=0;
                        for (; i< headers.size();i++){
                            WebElement header = headers.get(i);
                            if(columnName.equalsIgnoreCase(header.getText())){
                                i=i+1;
                                String selector = "td:nth-of-type("+i+")";
                                WebElement cell = row.findElement(By.cssSelector(selector));
                                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;"+'"'+">"+columnName + " cell ("+ cell.getText() +") is clicked</b>");
                                cell.click();
                                return this;
                            }
                        }
                        BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;"+'"'+"> Input column name "+columnName + " is not exist or supported yet</b>");
                        Assert.fail("Input column name "+columnName + " is not exist or supported yet");
                    }
                    isMatch = true;

                    baseObj.getElement("VisitDetail_Modal_Title");//Wait for the modal title display
                    baseObj.holdOn(2000);
                    break;
                }
            }
            if (isMatch){
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the given date " + visitDate + "; clientName " + clientName+"; employee " + employeeName);
        }
        return this;
    }
    public VisitMaintenanceFilterPage validateActualInOutOnVisitDate(String visitDate, String actualIn, String actualOut){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement vmGrid = baseObj.getElement("VisitMaintenance_VisitGrid_TBL");
        List<WebElement> rows = vmGrid.findElements(By.cssSelector("tr[data-row-id]"));
        boolean isMatch = false;
        for (WebElement row : rows){
            if(row.findElements(By.cssSelector("[data-visit-date='"+visitDate+"']")).size() > 0){
                List<WebElement> cells = row.findElements(By.cssSelector("[data-vm-tab-show='call_details']"));
                String _actualIn, _actualOut;
                _actualIn = cells.get(0).getText().trim();

                if (cells.size() > 1) {
                    _actualOut = cells.get(1).getText().trim();
                } else {
                    _actualOut = "";
                }

                if (actualIn.equalsIgnoreCase(_actualIn) && _actualOut.equalsIgnoreCase(actualOut)){
                        Assert.assertEquals(_actualIn.toUpperCase(), _actualIn.toUpperCase());
                        Assert.assertEquals(_actualOut.toUpperCase(), _actualOut.toUpperCase());
                        isMatch = true;
                        break;
                    }
                }

            if (isMatch){
                break;
            }
        }
        if(!isMatch){
            Assert.fail("Cannot find the visit with the given date " + visitDate + " at " + actualIn + " - " + actualOut);
        }
        return this;
    }

    public VisitMaintenanceFilterPage checkDisplayOptionNotExisit(String optionName) {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        Assert.assertEquals(baseObj.getDriver().findElements(By.xpath("//*[text()='"+optionName+"']")).size(),0);
        return this;
    }

    public void validateCurrentDatePopulatedOnStartDate() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        String currentDate = baseObj.getCurrentDate_DDMMYYYY();
        String actualDate = baseObj.getAttribute("VisitMaintenance_StartDate_ED","value");
        Assert.assertEquals(actualDate.substring(0,10),currentDate);
        BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">Today [" + currentDate + "] is populated on Start Date</b>");
    }

    public void validateCurrentDatePopulatedOnEndDate() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        String currentDate = baseObj.getCurrentDate_DDMMYYYY();
        String actualDate = baseObj.getAttribute("VisitMaintenance_EndDate_ED","value");
        Assert.assertEquals(actualDate.substring(0,10),currentDate);
        BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">Today [" + currentDate + "] is populated on End Date</b>");
    }

    public void validateSelectedException(String exceptionName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        WebElement selectedOption = baseObj.getFirstSelectedOption("VisitMaintenance_ExceptionType_DD");
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;"+'"'+"> Current selection is "+ selectedOption.getText()+"</b>");
        Assert.assertEquals(selectedOption.getText().trim().toLowerCase(),exceptionName.toLowerCase());
        BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+"> Current selection is matched with expected value: "+ exceptionName+"</b>");
    }

    public void validateNumberVisit(int number){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        if (number == 0){
            Assert.assertNotNull(baseObj.getElement("VisitMaintenance_SearchNoResult_CELL"));
            BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+"> Expected Numbers: " + number + " - Actual: No result found</b>");
        } else {
            String text = baseObj.getInnerText("Grid_RightBarInfo_LBL");
            BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;"+'"'+"> Info on Right Bar: " + text +"</b>");
            Assert.assertEquals(Integer.parseInt(text.split(" ")[5]),number);
            BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">Expected value: " + number + " - Result on the Grid: "+ Integer.parseInt(text.split(" ")[5])+"</b>");
        }
    }

    
    public void VeriFy_AM_PM(String strAmlocator, String strPmLocator){
    	
    	String AmText = baseObj.getTextBoxValue(strAmlocator).split(" ")[2].trim();
    	
    	String PmText = baseObj.getTextBoxValue(strPmLocator).split(" ")[2].trim();
    	
    	if(AmText.equalsIgnoreCase("AM") & PmText.equalsIgnoreCase("PM")){
    		BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+">Expected value: AM And Pm Found in Start and End Date: </b>");
    		
    	}else{
    		BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+">Expected value: AM And Pm Not Found in Start and End Date: </b>");
    	}
    	
    	
    }
    
    public void CheckDelete(String locatorName){
    	
    	boolean strDelFound = baseObj.CheckObjectExist(locatorName);
    	
    	if (strDelFound){
    		BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Delete Button Found  </b>");
    	}else{
    		BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+"Delete Button Not Found </b>");
    	}
    	
    	
    }
    
    public void checkboxStatus(String locatorname,String attributeName){
    	boolean strAttribute = baseObj.getAttributeStatus(locatorname, attributeName);
    	
    	if (strAttribute){
    		BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Do Not Bill Checkbox is Editable  </b>");
    	}else{
			BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Do Not Bill Checkbox is Not Checked  </b>");
		}
    }
    
    public void checkDoNotBill_CheckBox(String locatorname, String expectStatus){
		
    	boolean checkedStatus = baseObj.isReadOnlyTextBox(locatorname);
    	
		if (checkedStatus==true & expectStatus.equalsIgnoreCase("Checked")){
			BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Do Not Bill Checkbox is Editable  </b>");
		}else if (checkedStatus==false & expectStatus.equalsIgnoreCase("Unchecked")){
			BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Do Not Bill Checkbox is Read Only  </b>");
		}else{
			BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
    				+" Do Not Bill Checkbox is Not Checked  </b>");
		}
	}
    
    public void re_Search(){
    	
    	String strDate =baseObj.getInnerText("Visit_Date").trim();
    	
    	baseObj.getElement("VisitMaintenance_StartDate_ED").clear();
    	baseObj.click("VisitMaintenance_StartDate_ED");
        baseObj.EnterDynamicData("VisitMaintenance_StartDate_ED", strDate);
        
        baseObj.getElement("VisitMaintenance_EndDate_ED").clear();
    	baseObj.click("VisitMaintenance_EndDate_ED");
        baseObj.EnterDynamicData("VisitMaintenance_EndDate_ED", strDate);
        
        String emp = baseObj.getInnerText("VM_Emloyee").trim();
        
        baseObj.EnterDynamicData("VisitMaintenance_Employee_ED", emp);
    	
    }
    
    public void clickOnClientName(){
    	baseObj.click("ClientName");;
    }
    
    public void verifyAllOptions(){
    	
    	   
        List<WebElement>allSuggestions = baseObj.getOptions("VisitMaintenance_Status_DD");
        String[] allOptions ={ "Select Status","In Process", "Incomplete", "Verified", "Processed","Omit"};
        for (int j = 1; j < allSuggestions.size(); j++) {
            if((allSuggestions.get(j).getText().trim()).equalsIgnoreCase(allOptions[j])){
            	BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
        				+" Status Option Found As " + allOptions[j] + "</b>");
    		}else{
    			BaseTest.test.log(LogStatus.FAIL,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'
        				+"  Status Option is Not Found As Expected  </b>");	
            };

        }
    	
    }
	
	    
 public VisitMaintenanceFilterPage EnterDate(String locatorName, String textToInput ){
    	
    	baseObj.clearEditBox(locatorName);
    	baseObj.holdOn(1000);
        baseObj.setTextBoxValue_Direct(locatorName, textToInput);
    	return this;
     }

 public VisitMaintenanceFilterPage selectFromDropDown(String locatorName, String value){
	 baseObj.click(locatorName);
     baseObj.selectByValue(locatorName, value); 
     return this;
 }
 
 public VisitMaintenanceFilterPage selectFromVisitStatus( String value){
     baseObj.selectByValue("VisitMaintenance_Status_DD", value); 
     return this;
 }
 
 
 public VisitMaintenanceFilterPage validateCurrentSlctnFrmDropDown(String locatorName, String validationName){
		baseObj.validateSelectedValueInSingleSelectionDropdown(locatorName, validationName);
		return this;
	}

}
