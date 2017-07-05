package com.sandata.v8.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;

public class ReportPage {

	Wrapper baseObj = new Wrapper();

	public void setReportType(String value){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.selectByVisibleText("Report_ReportType_DD", value);
	}
	public void setFromDateRange(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("Report_FromDate_ED", "Report_FromDate_ED");
        baseObj.getElement("Report_FromDate_ED").sendKeys(Keys.TAB);

    }

    public void setFromDateRange(String fieldName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("Report_FromDate_ED", fieldName);
        baseObj.getElement("Report_FromDate_ED").sendKeys(Keys.TAB);
    }

    public void setToDateRange(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("Report_ToDate_ED", "Report_ToDate_ED");
        baseObj.getElement("Report_ToDate_ED").sendKeys(Keys.TAB);
	}

    public void setToDateRange(String fieldName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.setTextBoxValue("Report_ToDate_ED", fieldName);
        baseObj.getElement("Report_ToDate_ED").sendKeys(Keys.TAB);
    }

    public void clickRunReport(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.holdOn(2000);
        baseObj.click("Report_RunReport_BT");
        baseObj.waitForBrowserToLoadCompletely();
        baseObj.holdOn(5000);
	}
	public void setReportName(String value){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.selectByVisibleText("Report_ReportName_DD", value);
	}

	public void validateHeaderColumnNotPresenceOnTheReport(String columnName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.switchToFrameByLocator("Report_iFrame");
        Map<String, String> locatorDetailMap = XLReader.LocatorDataMap.get("Report_Header_Row");
        String locatorValue = locatorDetailMap.get("LocatorValue");
        String locatorType = locatorDetailMap.get("LocatorType");
        By locator = baseObj.getLocatorBy("Report_Header_Row", locatorType, locatorValue);
        boolean flag = true;
        List<WebElement> elements = baseObj.getDriver().findElements(locator);
        for (WebElement e : elements) {
            List<WebElement> cells = e.findElements(By.xpath("/td"));
            if (cells.size()>0 ){
                for (WebElement cell : cells){
                    String actualText = cell.getText().replaceAll("&nbsp;", " ").trim();
                    if (actualText.equalsIgnoreCase(columnName.trim())) {
                        Assert.fail(columnName + " is found on report header with the value " + actualText);
                        flag = false;
                        break;
                    }
                }
            } else {
                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> There is no result displayed on the report </b>");
            }

            if (!flag){
                break;
            }
        }

        if (flag) {
            Assert.assertEquals(columnName +" is not presence on report",columnName +" is not presence on report");
        }
        baseObj.getDriver().switchTo().defaultContent();
    }

	private void validateReportParameter(String ExpectedValue){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");

        baseObj.switchToFrameByLocator("Report_iFrame");
		Map<String, String> locatorDetailMap = XLReader.LocatorDataMap.get("Report_Center_Title_Cell");
		String locatorValue = locatorDetailMap.get("LocatorValue");
		String locatorType = locatorDetailMap.get("LocatorType");
		By locator = baseObj.getLocatorBy("Report_Center_Title_Cell", locatorType, locatorValue);
		boolean flag = false;
		List<WebElement> elements = baseObj.getDriver().findElements(locator);
		for (WebElement e : elements) {
			String actualText = e.getText().replaceAll("&nbsp;", " ").trim();
			if (actualText.contains(ExpectedValue.trim())) {
				Assert.assertTrue(true, ExpectedValue + " is found on report");
				flag = true;
				break;
			}
		}
		if (!flag) {
			Assert.fail(ExpectedValue + " is not found on the report");
		}
		baseObj.getDriver().switchTo().defaultContent();
	}
	public void validatePresenceOfSandataTelephonyImage(){
		baseObj.switchToFrameByLocator("Report_iFrame");
		baseObj.validateElementVisible("Report_SantraxTelephony_img");
		baseObj.getDriver().switchTo().defaultContent();
	}
	public void setDataAndValidateReport(String Report ,String ReportType , String ReportName){ 
	    BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		setReportType(ReportType);
		setReportName(ReportName);
        String ExpectedValue = baseObj.getFirstSelectedOption("Report_ReportName_DD").getText().trim();	
        setFromDateRange();
        if(Report.equalsIgnoreCase("Date Range Reports"))
        {
        	setToDateRange();
        }
        baseObj.getElement("Report_FromDate_ED").sendKeys(Keys.TAB);
        clickRunReport();
        validateReportParameter(ExpectedValue);
 	}

    public void validateReportNameExist(String fieldName) {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        for(int i=1;;i++){
            String reportName = XLReader.TestCaseDataMap.get(fieldName+i);
            if (reportName != null && !"".equals(reportName)){
                BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+"> Validate the available of the report template "+reportName+"</b>");
                baseObj.validateOptionExist("Report_ReportName_DD",reportName.trim());
                BaseTest.test.log(LogStatus.PASS,"", "<b style= "+'"'+"font-size: 15px;color:green"+'"'+"> "+reportName+" exist</b>");
            } else return;
        }
    }

    public void takeReportScreenshot(String validationID, String validateQuestion){
        BaseTest.test.log(LogStatus.INFO,"<b style= \"font-size: 15px;color:blue\">Validate the captured screen", "<b style= \"font-size: 15px;color:green\">"+validateQuestion+"</b>");
        baseObj.getScreenShot(validationID);
    }

    public void selectReportViewByClient(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.switchToFrameByLocator("Report_iFrame");
        baseObj.selectByValue("Report_ReportView_DD","2");
        baseObj.getDriver().switchTo().defaultContent();
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;c"+'"'+"> Report View By Client is selected: </b>" + baseObj.getFirstSelectedOption("Report_ReportView_DD"));
    }

    public void selectReportViewByEmployee(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.switchToFrameByLocator("Report_iFrame");
        baseObj.selectByValue("Report_ReportView_DD","1");
        baseObj.getDriver().switchTo().defaultContent();
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;c"+'"'+"> Report View By Client is selected: </b>" + baseObj.getFirstSelectedOption("Report_ReportView_DD"));
    }

    public void discardSelectedContracts() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.discardMultipleSelectedOptions("Report_Contract_DD");
    }
}
