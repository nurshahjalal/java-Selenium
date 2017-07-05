package com.sandata.core;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetElementText;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sandata.utilities.XLReader;
import com.sandata.v8.pages.VisitDetailPage;


public class Wrapper {

	 public static int StepNumber;
	static public WebDriver driver = null;
	WebElement element;
	//public String xmlelementvalue = null;

	
	
	public void settDriver(WebDriver driver) {
		Wrapper.driver = driver;
	}

	public WebDriver getDriver() {
		return Wrapper.driver;
	}

	public String getAttribute_ButtonName( String locatorName, String attribute) {
		element = getElement(locatorName);
		return element.getAttribute(attribute);
	}

	// ---------------------------------- Start Text
	// Box-----------------------------------
	public String getTextBoxValue(String locatorName) {
		this.element = getElement(locatorName);
		return element.getAttribute("value");
	}




	// ----------------- Start Click button-----------------------------------
	
	
	
	public String xmlReader(String NodeName, String PropertyValue) {
		Element element = null;
		String Value = null;
		try {

			File Prop = new File(System.getProperty("user.dir") + "/src/test/resources/resource/testdata.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(Prop);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			NodeList nodes = doc.getElementsByTagName(NodeName);

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) node;
				}
			}
			Value = getValue(PropertyValue, element);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Value;
	}

	private static String getValue(String tag, Element element) {

		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();

	}

	public boolean isReadOnlyTextBox(String locatorName) {
		this.element = getElement(locatorName);
		if ((element.getAttribute("readonly") != null) || (element.getAttribute("disabled") != null)) {
			BaseTest.test.log(LogStatus.INFO, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++""
					+ " The Element Found as Read Only</b>");
					
			
			return true;
		} else {
			return false;
			
		}
	}
	
	public void Check_EditBox_ReadOnly(String locatorname){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
       boolean r_Only = isReadOnlyTextBox(locatorname);
       if (r_Only){
    	   BaseTest.test.log(LogStatus.PASS, "<b>Step No - "+StepNumber+++"</b>","<b>  Element has been Found As Read Only </b> ");
    	   
       }else{
    	   BaseTest.test.log(LogStatus.FAIL, "<b>Step No - "+StepNumber+++"</b>","<b>  Element NOT Found As Read Only </b> ");
    	   
       }
        
    }
	
	public boolean getAttributeStatus(String locatorName, String attributeName){
		this.element = getElement(locatorName);
		if (element.getAttribute(attributeName) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	// ------------------------ End Text Box----------------------------

	public void validateInnerText(String locatorName, String validationName) {
		
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>",
					"<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		String actualValue=element.getText();
		String expectedValue=XLReader.TestCaseDataMap.get(validationName);
		if(expectedValue==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		if(expectedValue.trim().equalsIgnoreCase(actualValue.trim())){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			Assert.assertEquals(expectedValue.trim(), actualValue.trim());
		}
	
	}
	
public void validateDynamicText(String locatorName, String validationName) {
		
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		String actualValue=element.getText();
		String expectedValue=validationName;
		if(expectedValue==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		if(expectedValue.trim().equalsIgnoreCase(actualValue.trim())){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			Assert.assertEquals(expectedValue.trim(), actualValue.trim());
		}
	
	}
	
	
	
	public void isSelectDropdown(String locatorName) {
		
		if(select(locatorName)!=null){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element Type Validation Passed. Element Type is Dropdown"+"</b>]");
		}
	}
	
	public void validateElementVisible(String locatorName){
		
		if(isVisible(locatorName)){
			  BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is Visible. Validation Passed"+".  actual value:<b>Visible</b>  AND  expected value: [<b>Visible</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is not Visible. Validation Failed"+".  actual value:<b>Not Visible</b>  AND  expected value: [<b>Visible</b>]");
			Assert.assertTrue(false);
		}
	
    }
	
	public void validateElementEnabled(String locatorName){
		
			if(isEnabled(locatorName)){
				  BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is Enabled. Validation Passed"+".  actual value:<b>ENABLED</b>  AND  expected value: [<b>ENABLED</b>]");
			}else{
				BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is Disabled. Validation Failed"+".  actual value:<b>DISABLED</b>  AND  expected value: [<b>ENABLED</b>]");
				Assert.assertTrue(false);
			}
		
	}
	
	private boolean isEnabled(String locatorName){
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		
		return this.element.isEnabled();
	}
	
	private boolean isVisible(String locatorName){
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		
		return this.element.isDisplayed();
	}
	
	public void validateAllDropdownItems_WithOrder(String locatorName, String validationName){
		String allDropDownValues_SeperatedBySemicolon=null;
		allDropDownValues_SeperatedBySemicolon=XLReader.TestCaseDataMap.get(validationName);
		if(allDropDownValues_SeperatedBySemicolon==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		String[] dropDownValuesArr=allDropDownValues_SeperatedBySemicolon.split(";");
		List<WebElement> listOptions=getOptions(locatorName);
		for(int i=0; i<=dropDownValuesArr.length-1;i++){
			String actualDDValue=listOptions.get(i).getText().trim();
			String expDDValue=dropDownValuesArr[i].trim();
			if(actualDDValue.equalsIgnoreCase(expDDValue)){
				  BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Dropdown Values is correct. Validation Passed"+".  actual value:"+(i+1)+"- [<b>"+actualDDValue+"</b>]  AND  expected value:"+(i+1)+" [<b>"+expDDValue+"</b>]");
			}else{
				BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Dropdown Values is in-correct. Validation Failed"+".  actual value:"+(i+1)+"- [<b>"+actualDDValue+"</b>]  AND  expected value:"+(i+1)+" [<b>"+expDDValue+"</b>]");
				Assert.assertEquals(actualDDValue.trim(), expDDValue.trim());
			}
		}
	}
	
	public void validateElementIsTextBox(String locatorName) { 
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		
		if(element.getTagName().equals("input") && element.getAttribute("type").equals("text")){
			  BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is Text Box. Validation Passed"+".  actual value:<b>TextBox</b>  AND  expected value: [<b>TextBox</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is not Text Box. Validation Failed"+".  actual value:<b>Not Textbox</b>  AND  expected value: [<b>TextBox</b>]");
			Assert.assertTrue(false);
		}
	}
	public void validateAllDropdownItems_WithoutOrder(String locatorName, String validationName){
		String allDropDownValues_SeperatedBySemicolon=null;
		allDropDownValues_SeperatedBySemicolon=XLReader.TestCaseDataMap.get(validationName);
		if(allDropDownValues_SeperatedBySemicolon==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		String[] dropDownValuesArr=allDropDownValues_SeperatedBySemicolon.split(";");
		String actualDDValue=getInnerText(locatorName).trim().toLowerCase();
		for(int i=0; i<=dropDownValuesArr.length-1;i++){
			
			String expDDValue=dropDownValuesArr[i].trim().toLowerCase();
			
			if(actualDDValue.contains(expDDValue)){
				  BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Dropdown Values are Validation Passed"+".  actual value: - [<b>"+actualDDValue+"</b>]  AND  expected value:"+(i+1)+" [<b>"+expDDValue+"</b>]");
			}else{
				BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Dropdown Values are Validation Failed"+".  actual value:"+(i+1)+"- [<b>"+actualDDValue+"</b>]  AND  expected value:"+(i+1)+" [<b>"+expDDValue+"</b>]");
				Assert.assertEquals(actualDDValue.trim(), expDDValue.trim());
			}
		}
	}
	
	
	
	public void validateInnerTextContains(String locatorName, String validationName) {
		
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		String actualValue=element.getText();
		String expectedValue=null;
			  expectedValue=XLReader.TestCaseDataMap.get(validationName);
			  
			if(expectedValue==null){
				BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
				Assert.assertTrue(false);
			}
			if(actualValue.trim().toLowerCase().contains(expectedValue.trim().toLowerCase())){
				   BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
				}else{
					BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
					Assert.assertEquals(expectedValue.trim(), actualValue.trim());
			 }
		
		
	
	}
	
	
	public void validateSelectedValueInSingleSelectionDropdown(String locatorName, String validationName) {
		String actualValue=getFirstSelectedOption(locatorName).getText().trim();
		String expectedValue=null;
		  expectedValue=XLReader.TestCaseDataMap.get(validationName);
		  
		if(expectedValue==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Dropdown Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		if(actualValue.equalsIgnoreCase(expectedValue)){
			   BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Selected value is correct. Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			}else{
				BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Selected value is incorrect. Validation Failed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
				Assert.assertEquals(expectedValue.trim(), actualValue.trim());
				Assert.assertEquals(actualValue.trim(), expectedValue.trim() );

		 }
	}
	
	public void validateInnerTextContainsDirectly(String locatorName, String expectedValue) {
		
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		String actualValue=element.getText();
		
		if(actualValue.trim().toLowerCase().contains(expectedValue.trim().toLowerCase())){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			
			Assert.assertEquals(expectedValue.trim(), actualValue.trim());
		}
	
	}
	
   public void validateInnerText_AllNumbers(String locatorName, String validationName) {
		
	   try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		String actualValue=element.getText();
		String expectedValue=XLReader.TestCaseDataMap.get(validationName);
		if(expectedValue==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		expectedValue=expectedValue.trim().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll(" ", "");
		actualValue=actualValue.trim().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll(" ", "");
		if(expectedValue.equalsIgnoreCase(actualValue)){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			Assert.assertEquals(expectedValue.trim(), actualValue.trim());
		}
	
	}
	
   
   
   public void validateInnerTextDirectly(String locatorName, String expectedValue) {
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		
		String actualValue=element.getText();
	
		if(expectedValue.trim().toLowerCase().contains(actualValue.trim().toLowerCase())){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			Assert.assertEquals(expectedValue.trim(), actualValue.trim());
		}
	
	}

   
	
	
	public WebElement getElement(String locatorName) {
		
		try {
			Map<String, String> locatorDetailMap=XLReader.LocatorDataMap.get(locatorName);
			String locatorValue=locatorDetailMap.get("LocatorValue");
			String locatorType=locatorDetailMap.get("LocatorType");
			By locator=getLocatorBy(locatorName, locatorType, locatorValue);
			int count = 1;
			System.out.println(
					"Waiting upto " + 60000 + "ms for element with locator: \"" + locator + "\" to appear on page.");
			while (driver.findElements(locator).size() == 0 && count <= 60) {
				Thread.sleep(1000);
				System.out.println("Waiting " + 1 + "000 ms for count " + count);
				count++;
			}
			//  LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>"
			return (driver.findElement(locator));
		} catch (NoSuchElementException e) {
			BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>"+"</b>","<b>"+locatorName+"</b> "+"</b> "+"  is not found on Page Source. Test Failed");
			
			//Assert.fail(locator + " NoSuchElementException NOT FOUND. TEST FAILED.");
		} catch (NullPointerException e) {
			BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>"+"</b>", "NullPointerException for "+"<b>"+locatorName+"</b> "+"</b> "+"  Please Match Script's locator Name with OR Locator Name. Test Failed");
			
			//Assert.fail(locator + "NullPointerException NOT FOUND. TEST FAILED.");
		} catch (StaleElementReferenceException e) {
			
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"  is not attached to the page document. Test Failed");
			
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable " + e.getStackTrace());
		}
		return null;
	}
	
   
	public void click(String locatorName) {
		
	
		try{
			this.element = getElement(locatorName);
			element.click();
			
			BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has been clicked successfully");
		}catch(ElementNotVisibleException e){
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform click.");
		    
		}catch(StaleElementReferenceException e){
			try{
				this.element = getElement(locatorName);
				element.click();	
				
			}catch(StaleElementReferenceException ne){
				BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" Web page's HTM has been refreshed, changed or updated since it was looked up");
				
			}
		}catch(WebDriverException e){
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
				
		}
		
		
	}
	
	public void jsClick(String locatorName) {
		this.element = getElement(locatorName);
		
		try{
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
			BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has been clicked successfully");
		}catch(ElementNotVisibleException e){
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform click.");
		    
		}
	}
	
	public void mouseClick(String locatorName) {
		
	
		try{
			this.element = getElement(locatorName);
			new Actions(driver).click(element).build().perform();
			BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has been clicked successfully");
		}catch(ElementNotVisibleException e){
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform click.");
		    
		}catch(StaleElementReferenceException e){
			try{
				this.element = getElement(locatorName);
				element.click();	
				
			}catch(StaleElementReferenceException ne){
				BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" Web page's HTM has been refreshed, changed or updated since it was looked up");
				
			}
		}
		
		
	}
	
	
	public void mouseDoubleClick(String locatorName) {
		
	
		try{
			this.element = getElement(locatorName);
			new Actions(driver).doubleClick(element).build().perform();
			BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has been clicked successfully");
		}catch(ElementNotVisibleException e){
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform click.");
		    
		}catch(StaleElementReferenceException e){
			try{
				this.element = getElement(locatorName);
				element.click();	
				
			}catch(StaleElementReferenceException ne){
				BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" Web page's HTM has been refreshed, changed or updated since it was looked up");
				
			}
		}			
	}
	
	
	public void setTextBoxValue(String locatorName, String fieldName) {
		String textToInput="";
		try{
			this.element = getElement(locatorName);
			
			 textToInput=XLReader.TestCaseDataMap.get(fieldName);
			element.clear();
			element.sendKeys(textToInput);
			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", textToInput+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");
		}catch(ElementNotVisibleException e){
			BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>",  textToInput+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ " because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform click.");
		}catch(StaleElementReferenceException e){
			try{
				this.element = getElement(locatorName);
				element.clear();
				element.sendKeys(textToInput);
				BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", textToInput+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");
				
			}catch(StaleElementReferenceException ne){
				BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" Web page's HTM has been refreshed, changed or updated since it was looked up");
				
			}
		}catch(InvalidElementStateException e){
			//driver.executeScript("arguments[0].setAttribute('value', '" + longstring +"')", inputField);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + textToInput +"')", element);
		}
		
		
	}
	
	
	

	
	public By getLocatorBy(String locatorName, String locatorType, String locatorValue){
		By locatorBy=null;
		try{
		
			if(locatorType.trim().equalsIgnoreCase("xpath")){
				locatorBy=By.xpath(locatorValue);
			}else if(locatorType.trim().equalsIgnoreCase("css")){
				locatorBy=By.cssSelector(locatorValue);
			}else if(locatorType.trim().equalsIgnoreCase("id")){
				locatorBy=By.id(locatorValue);
			}else if(locatorType.trim().equalsIgnoreCase("name")){
				locatorBy=By.name(locatorValue);
			}else if(locatorType.trim().equalsIgnoreCase("linkText")){
				locatorBy=By.linkText(locatorValue);
			}else if(locatorType.trim().equalsIgnoreCase("class")){
				locatorBy=By.className(locatorValue);
			}else if(locatorType.trim().equalsIgnoreCase("tag")){
				locatorBy=By.tagName(locatorValue);
			}else if(locatorType.trim().equalsIgnoreCase("plt")){
				locatorBy=By.tagName(locatorValue);
			}else {
				locatorBy=By.tagName(locatorValue);
			}
			
		}catch(Exception e){
			
		}
		
		return locatorBy;
	}
	
	

	// ----------------- Start Click button-----------------------------------
	

	// ------------------------ Start Drop Down-----------------------------------
	private Select select(String locatorName) {
		Select selectElement = null;
		this.element = getElement(locatorName);
		try {
			selectElement = new Select(element);
		} catch (UnexpectedTagNameException e) {
			Assert.fail(
					"Element " + locatorName + " was not with select tag name   Error Message UnexpectedTagNameException  -->"
							+ e.getStackTrace());
		}
		return selectElement;
	}

	public void deselectAll(String locatorName) {
		select(locatorName).deselectAll();
	}

	public void deselectByIndex(String locatorName, int index) {
		select(locatorName).deselectByIndex(index);
	}

	public void deselectByValue(String locatorName, String value) {
		select(locatorName).deselectByValue(value);
	}

	public void deselectByVisibleText(String locatorName, String text) {
		select(locatorName).deselectByVisibleText(text);
	}

	public List<WebElement> getAllSelectedOptions(String locatorName) {
		return (select(locatorName).getAllSelectedOptions());
	}

	public WebElement getFirstSelectedOption(String locatorName) {
		return select(locatorName).getFirstSelectedOption();
	}

	// All options belonging to this select tag
	public List<WebElement> getOptions(String locatorName) {
		return select(locatorName).getOptions();
	}

	public boolean isMultiple(String locatorName) {
		return select(locatorName).isMultiple();
	}

	// index start from 0 to n-1
	public void selectByIndex(String locatorName, int index) {
		
		select(locatorName).selectByIndex(index);
	}

	public void selectByValue(String locatorName, String value) {
		select(locatorName).selectByValue(value);
	}

	public void selectByVisibleText(String locatorName, String fieldName) {
		     String textToSelect="";
			 this.element = getElement(locatorName);
			 textToSelect=XLReader.TestCaseDataMap.get(fieldName);
			 select(locatorName).selectByVisibleText(textToSelect);
			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", textToSelect+" has been selected in DropDown Box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");
		
	}
	
	public void selectByDynamicText(String locatorName, String value) {
	    // String textToSelect="";
		 this.element = getElement(locatorName);
		 //textToSelect=XLReader.TestCaseDataMap.get(fieldName);
		 select(locatorName).selectByVisibleText(value);
		BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", value+" has been selected in DropDown Box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");
	
}

    /**
     * Validate if an option exist in the dropdown
     * @author: vumvo
     */
	public void validateOptionExist(String locatorName, String visibleText){
	    for (WebElement option : getOptions(locatorName)){
	        String actualValue = option.getText().trim();
	        if (actualValue.equals(visibleText)){
	            Assert.assertEquals(actualValue,visibleText);
	            return;
            }
        }
        Assert.fail(visibleText + " is not exist on the given locator " + locatorName);
    }

	// ---------------------- End Drop Down-----------------------------------

	public void browseURL(String url) {
		driver.get(url);
	}

	public void navigate(String url) {
		driver.navigate().to(url);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void maximizeWindowwithGivenDimension(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public WebDriver openFirefoxBrowser() {
		return (Wrapper.driver = new FirefoxDriver());
	}

	public WebDriver openFirefoxBrowserWithProfile(String profileName) {
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile(profileName);
		return (Wrapper.driver = new FirefoxDriver(myprofile));
	}

	public WebDriver openChromeBrowser() {
		return (driver);
	}

	public WebDriver openIEBrowser() {
		return (driver);
	}

	
	public void validateTableColumnsText(String tablelocatorName, String validationName) {
		String columnActualText="";
		int colNum=-1;
		
		this.element = getElement(tablelocatorName);
		List<WebElement> listColumns=element.findElements(By.tagName("th"));
		for(int i=0; i<=listColumns.size()-1; i++){
			 columnActualText=listColumns.get(i).getText().trim();
			if(columnActualText.equals("")==false){
			  	String expectedColumnText=XLReader.TestCaseDataMap.get(validationName+"_Column"+(i+1));
			  	
			  	    if(expectedColumnText!=null){
			  	    	if(expectedColumnText.trim().equalsIgnoreCase(columnActualText.trim())){
							BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"Step No - "+StepNumber+++"</b>"+"</b>", "Passed. Text Validation for "+tablelocatorName+"</b> "+".  actual text: ["+columnActualText+"]  AND  expected text: ["+expectedColumnText+"]");
						}else{
							BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"Step No - "+StepNumber+++"</b>"+"</b>", "Failed. Text Validation for "+tablelocatorName+"</b> "+".  actual text: ["+columnActualText+"]  AND  expected text: ["+expectedColumnText+"]");
							Assert.assertEquals(expectedColumnText.trim(), columnActualText.trim());
						}	
			  	    }else{
			  	    	BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"Step No - "+StepNumber+++"</b>"+"</b>","Text Validation failed for "+tablelocatorName+"</b> "+", Because ExpectedValue Not found for:"+validationName+"_Column"+(i+1));
			  	    	Assert.assertEquals("", columnActualText.trim());
						
			  	    }
				  	
			}
			
			
		}
		
	}
	

	public void validateTableColumnValueByColumnNameDirectly(String tablelocatorName, String columnName, int rowNumber,  String expectedColumnValue) {
		
		this.element = getElement(tablelocatorName);
		List<WebElement> listRows=element.findElements(By.tagName("tr"));
		
		   int columnNumber=getTableColumnNumberByColumnName(tablelocatorName, columnName);
			String columnValueActualText=listRows.get(rowNumber).findElements(By.tagName("td")).get(columnNumber).getText().trim();
			if(columnValueActualText.trim().equalsIgnoreCase(columnValueActualText.trim())==false){ 	    
			  	    	if(expectedColumnValue.trim().equalsIgnoreCase(columnValueActualText.trim())){
							BaseTest.test.log(LogStatus.PASS, "Passed. Text Validation for "+tablelocatorName+"</b> "+".  actual text: ["+columnValueActualText+"]  AND  expected text: ["+expectedColumnValue+"]");
						}else{
							BaseTest.test.log(LogStatus.FAIL, "Text Validation failed for "+tablelocatorName+"</b> "+".  actual text: ["+columnValueActualText+"]  AND  expected text: ["+expectedColumnValue+"]");
							Assert.assertEquals( columnValueActualText.trim(), expectedColumnValue.trim());
						}	
				  	
			}
			
			
		
		
	}
	
	
	private int getTableColumnNumberByColumnName(String tablelocatorName, String columnName) {
		
		this.element = getElement(tablelocatorName);
		List<WebElement> listColumns=element.findElements(By.tagName("th"));
		int columnNumber=-1;
		for(int i=0; i<=listColumns.size()-1; i++){
			String columnActualText=listColumns.get(i).getText().trim();
			if(columnActualText.equals("")==false){
			  	    if(columnName!=null && columnName.trim().equals("")==false){
			  	    	if(columnName.trim().equalsIgnoreCase(columnActualText.trim())){
							columnNumber=i+1;
							
			  	    	}	
			  	     }else{
			  	    	
			  	    }
				  	
			}
			
			
		}
		return columnNumber;
	}
	
	
	public static void extentValidation(ExtentTest extent, String status, String expected, String actual){
	  	if(status.equalsIgnoreCase("failed")){
	  		extent.log(LogStatus.FAIL, "<b style="+'"'+"color:red"+'"'+">Step No-1</b>", "<b>ACTUAL:</b>"+"<b style="+'"' +"font-size:15px"+'"'+">"+actual+"</b>"+"  ,  <b>EXPECTED: </b>"+"<b style="+'"'+"font-size:14px"+'"'+">"+expected+"</b>"  );
	  	}else{
			extent.log(LogStatus.PASS, "<b color="+'"'+"green"+'"'+">Step No-1</b>", "Expected: "+expected+"  , Actual:"+actual);
			  
	  	}
	}
	
	

	
	public void doubleClick(String locatorName) {
		this.element = getElement(locatorName);
		Actions action = new Actions(driver).doubleClick(element);
		action.build().perform();
	}

	// -------------------------- Start Click button----------------------------------

	// ---------------------------------- Start table-----------------------------------
	public void table(String locatorName) {
		WebElement htmltable = getElement(locatorName);
		List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
		for (int rnum = 0; rnum < rows.size(); rnum++) {
			List<WebElement> columns = rows.get(rnum).findElements(By.tagName("td"));
			for (int cnum = 0; cnum < columns.size(); cnum++) {

			}
		}

	}
	// ---------------------------------- End Table----------------------------------

	// ------------------------- Start Close browser  -------------------
	public void closeCurrentWindow() {
		driver.close();
	}

	public void closeAllWindow() {
		driver.quit();
	}

	public void closeChromeInstance() {
		try {
			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			Runtime.getRuntime().exec("taskkill /im chrome.exe /f");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// ---------------------------------- End Close browser -------------------

	public void refresh() {
		driver.navigate().refresh();
	}

	public void refreshByPhysicalKey() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void back() {
		driver.navigate().back();
	}

	// ------------------------------- Start Cookie---------------------------

	public void addCookie(String cookieName, String cookieValue) {
		Cookie name = new Cookie(cookieName, cookieValue);
		driver.manage().addCookie(name);
	}

	public void deleteCookieByName(String cookieName) {
		driver.manage().deleteCookieNamed(cookieName);
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public Cookie getCookieByName(String cookieName) {
		return (driver.manage().getCookieNamed(cookieName));
	}

	public Set<Cookie> getAllCookies() {
		return (driver.manage().getCookies());
	}

	// ------------------------------- End Cookie ----------------------------
	// ------------------------------- Read Property File --------------------
	public String readPropertyFile(File file, String key) {
		Properties prop = new Properties();
		InputStream input = null;
		String result;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get the property value and print it out
		result = prop.getProperty(key);
		try {
			input.close();
		} catch (Exception e) {
		}

		return result;

	}

	public List<WebElement> getAnchorTagsList() {
		return (driver.findElements(By.tagName("a")));
	}
	
	
	public void holdOn(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void waitForBrowserToLoadCompletely() {
		String state = null;
		String oldstate = null;
		try {
			System.out.print("Waiting for browser loading to complete");
			int i = 0;
			while (i < 5) {
				Thread.sleep(1000);
				state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
				System.out.print("." + Character.toUpperCase(state.charAt(0)) + ".");
				if (state.equals("interactive") || state.equals("loading"))
					break;
				/*
				 * If browser in 'complete' state since last X seconds. Return.
				 */

				if (i == 1 && state.equals("complete")) {
					System.out.println();
					return;
				}
				i++;
			}
			i = 0;
			oldstate = null;
			Thread.sleep(2000);

			/*
			 * Now wait for state to become complete
			 */
			while (true) {
				state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
				System.out.print("." + state.charAt(0) + ".");
				if (state.equals("complete"))
					break;

				if (state.equals(oldstate))
					i++;
				else
					i = 0;
				/*
				 * If browser state is same (loading/interactive) since last 60
				 * secs. Refresh the page.
				 */
				if (i == 15 && state.equals("loading")) {
					System.out.println("\nBrowser in " + state + " state since last 60 secs. So refreshing browser.");
					driver.navigate().refresh();
					System.out.print("Waiting for browser loading to complete");
					i = 0;
				} else if (i == 6 && state.equals("interactive")) {
					System.out.println(
							"\nBrowser in " + state + " state since last 30 secs. So starting with execution.");
					return;
				}

				Thread.sleep(4000);
				oldstate = state;

			}
			System.out.println();

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

    /**
     **************************************************************************************************************
     * @Function Name: waitForElementNotPresence
     * @Description: This function will take the locator name and wait until that locator not presents on the page.
     * @Param: String locatorName
     * @Return: void
     * @Date: 05/26/2017
     * @Author:  Vu Vo
     **************************************************************************************************************
     */
    public void waitForElementNotPresence(String locatorName){
	    WebDriverWait wait = new WebDriverWait(driver, 60);
        Map<String, String> locatorDetailMap=XLReader.LocatorDataMap.get(locatorName);
        String locatorValue=locatorDetailMap.get("LocatorValue");
        String locatorType=locatorDetailMap.get("LocatorType");
        By locator=getLocatorBy(locatorName, locatorType, locatorValue);

	    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     **************************************************************************************************************
     * @Function Name: waitForElementPresence
     * @Description: This function will take the locator name and wait until that locator presents on the page.
     * @Param: String locatorName
     * @Return: void
     * @Date: 05/26/2017
     * @Author:  Vu Vo
     **************************************************************************************************************
     */
    public void waitForElementPresence(String locatorName){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        Map<String, String> locatorDetailMap=XLReader.LocatorDataMap.get(locatorName);
        String locatorValue=locatorDetailMap.get("LocatorValue");
        String locatorType=locatorDetailMap.get("LocatorType");
        By locator=getLocatorBy(locatorName, locatorType, locatorValue);

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
	// ---------------------------------------------- Start Scrolling---------------------------------------------------
	public void scrollingToBottomofAPageUsingJS() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollingToBottomofAPageUsingAction() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	}

	public void scrollingToElementofAPage(String locatorName) {
		this.element = getElement(locatorName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollingByCoordinatesofAPage(int xAxis, int yAxis) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(" + xAxis + "," + yAxis + ")");
	}

	// ------------------------------- End Scrolling-------------------------------------------------

	public void dragAndDrop(String sourcelocatorName ,String destinationlocatorName) {
		(new Actions(driver)).dragAndDrop(getElement(sourcelocatorName), getElement(destinationlocatorName)).perform();
	}

	// ------------------------------- Start Alert---------------------------

	public boolean checkIsAlertPresent() {
		WebDriverWait wait = new WebDriverWait(driver, 300);

		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			Assert.fail("alert was not present");
			return false ;
		} else {
			System.out.println("Alert is present");
			return true;
		}
		

	}
	/**
	**************************************************************************************************************
	* @Function Name: getAlertText
	* @Description: This function will take the locator name from excel and validate
	* @Param: String date
	* @Param : 
	* @Return: alertMsg
	* @Date: 03/23/2017
	* @Author:  Nur
	**************************************************************************************************************
	*/
	public String getAlertText(){
		
		String alertMsg = "";
		if (checkIsAlertPresent()){
			Alert alert = driver.switchTo().alert();
			alertMsg = alert.getText();
			alertAccept();
			
		}
		return alertMsg;
	}
	
	
    public void validateTextOnAlert(String expectedText){
        checkIsAlertPresent();
        Alert alert = driver.switchTo().alert();

        Assert.assertTrue(alert.getText().contains(expectedText));
    }
	public void alertAccept() {
		checkIsAlertPresent();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void switchFocusOnWindowTabByTitle(String windowTabTitle) {
		Set<String> setHndlValues=driver.getWindowHandles();
		for(String handleValue : setHndlValues){
			driver.switchTo().window(handleValue);
			String title=driver.getTitle();
			if(title.equalsIgnoreCase(windowTabTitle)){
				break;
			}
		}
		
		
	}
	
	public void switchFocusOnWindowTabByPartialTitle(String windowTabPartialTitle) {
		Set<String> setHndlValues=driver.getWindowHandles();
		for(String handleValue : setHndlValues){
			driver.switchTo().window(handleValue);
			String title=driver.getTitle();
			if(title.trim().toLowerCase().contains(windowTabPartialTitle.trim().toLowerCase())){
				break;
			}
		}
		
		
	}
	
	public void switchFocusOnWindowTabByPartialURL(String partialURL) {
		Set<String> setHndlValues=driver.getWindowHandles();
		for(String handleValue : setHndlValues){
			driver.switchTo().window(handleValue);
			String url=driver.getCurrentUrl();
			if(url.trim().toLowerCase().contains(partialURL.trim().toLowerCase())){
				break;
			}
		}
	}
	
	public void switchFocusOnWindowTabByURL(String URL) {
		Set<String> setHndlValues=driver.getWindowHandles();
		for(String handleValue : setHndlValues){
			driver.switchTo().window(handleValue);
			String url=driver.getCurrentUrl();
			if(url.trim().toLowerCase().equalsIgnoreCase(URL.trim().toLowerCase())){
				break;
			}
		}		
	}
	
	/**
	**************************************************************************************************************
	* @Function Name: validateTextBoxValue
	* @Description: This function will validate  the text box value
	* @Param:  locatorName
	* @Param : validationName
	* @Return: alertMsg
	* @Date: 03/27/2017
	* @Author:  Shailendra
	**************************************************************************************************************
	*/
public void validateTextBoxValue(String locatorName, String validationName) {
		
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		String actualValue=element.getAttribute("value");
		String expectedValue=XLReader.TestCaseDataMap.get(validationName);
		if(expectedValue==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		if(expectedValue.equalsIgnoreCase(actualValue)){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual textbox value: [<b>"+actualValue+"</b>]  AND  expected text box value: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text text box value: [<b>"+actualValue+"</b>]  AND  expected text box value: [<b>"+expectedValue+"</b>]");
			Assert.assertEquals(actualValue, expectedValue);
		}
	
	}

public void validateDynamicTextBoxValue(String locatorName, String expectedValue) {
	
	try{
		this.element = getElement(locatorName);
	}catch(Exception e){
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
		
	}
	String actualValue=element.getAttribute("value");
	//String expectedValue=XLReader.TestCaseDataMap.get(validationName);
	if(expectedValue==null){
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
		Assert.assertTrue(false);
	}
	if(expectedValue.equalsIgnoreCase(actualValue)){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual textbox value: [<b>"+actualValue+"</b>]  AND  expected text box value: [<b>"+expectedValue+"</b>]");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text text box value: [<b>"+actualValue+"</b>]  AND  expected text box value: [<b>"+expectedValue+"</b>]");
		Assert.assertEquals(actualValue, expectedValue);
	}

}

	public void alertDismiss() {
		checkIsAlertPresent();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void alertSendKeys(String value) {
		checkIsAlertPresent();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}

	/*
	 * public String alertGetText() { Alert alert = driver.switchTo().alert();
	 * return alert.getText(); }
	 */

	// ------------------------------- End Alert ----------------------------

	// ------------------------------- Start Date ----------------------------
	public int getCurrentDate() {
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.DATE));
	}

	public int getCurrentMonth() {
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.MONTH)+1);
	}

	public int getCurrentYear() {
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.YEAR));
	}

	// ------------------------------- End Date ----------------------------

	// ------------------------------- Start Screen Short
	// ----------------------------
	public void getscreenshot() throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		FileUtils.copyFile(scrFile, new File("D:\\aaaaaaaaaaaaa.png"));
	}
	// ------------------------------- End Screen Shot --------------------------------


	public String getCurrentTime() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		String currentTime = df.format(dt);
		return currentTime;

	}

	public String duration(String st, String et) {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date d1 = null, d2 = null;
		try {
			d1 = format.parse(st);
			d2 = format.parse(et);
		} catch (ParseException e) {
		}

		long diff = d2.getTime() - d1.getTime();
		return Long.toString(diff);
	}

	public String getInnerText(String locatorName) {
		element = getElement(locatorName);
		if (element != null) {
			return element.getText();
		}
		return null;
	}

	public int getXcor(String locatorName) {
		return getElement(locatorName).getLocation().getX();
	}

	public int getYcor(String locatorName) {
		return getElement(locatorName).getLocation().getY();
	}
	
	//-----------------------------Jitendra Kumar--------------------------------
	
	//For taking ScreenShot
	public void takeScreenShot(String folderPath, String methodName) { 
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			File file=new File(folderPath+"//snapshots");
			if(file.exists()==false){
				
				file.mkdir();
			}
			folderPath=folderPath+"//snapshots";
			methodName=getTimeStamp()+methodName;
			FileUtils.copyFile(scrFile,new File(folderPath+"\\" + methodName + ".png"));
			System.out.println("***Placed screen shot ***");
			BaseTest.test.log(LogStatus.FAIL, "Screencast below: "
					+ BaseTest.test.addScreenCapture("snapshots"+"\\" + methodName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**** Capture the screenshot and add it into the Extent report. Capture screen shoot can be used to manually check
	 @Author: Vu Vo
     @Params: methodName - Name of the captured file.
	 */
    public void getScreenShot(String methodName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String folderPath = BaseTest.folderPath;
        try {
            File file=new File(folderPath+"//snapshots");
            if(file.exists()==false){
                file.mkdir();
            }
            folderPath=folderPath+"//snapshots";
            methodName=getTimeStamp()+methodName;
            FileUtils.copyFile(scrFile,new File(folderPath+"\\" + methodName + ".png"));
            System.out.println("***Placed screen shot ***");
            BaseTest.test.log(LogStatus.PASS, methodName,
                    BaseTest.test.addScreenCapture("snapshots"+"\\" + methodName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String GenerateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }
    
    public static String generateRandomString(int length) {
		StringBuilder str = new StringBuilder(
				RandomStringUtils.randomAlphabetic(length));
		int idx = str.length()-8;

		while (idx > 0) {
			str.insert(idx, " ");
			idx = idx-8;
		}
		return str.toString();
	}
	
	public static String getTimeStamp(){
		SimpleDateFormat sdf=new SimpleDateFormat("MM_dd_yy-HH_mm_ss");
		return sdf.format(new Date());
	}
	public static void main(String[] args){
		
		String x="<b style= "+'"'+"font-size: 15px"+'"'+">"+"abcd"+"</b>";
		System.out.println(x);
		
	}
	// this method converts RGBA values to hex value. [color]
	
	public String colorToHexValue(String color){ 
		String hex=null;;
		if(!color.equals("Transparent")){
		String s1 = color.substring(5);
		StringTokenizer st = new StringTokenizer(s1);
		int r = Integer.parseInt(st.nextToken(",").trim());
		int g = Integer.parseInt(st.nextToken(",").trim());
		int b = Integer.parseInt(st.nextToken(",").trim());
		Color c = new Color(r, g, b);
		hex = "#"+Integer.toHexString(c.getRGB()).substring(2);
		}else{
			System.out.println("Color is transparent.");
			hex="transparent";
		}
		return hex;
	}
	
	public void validateValueAttribute(String locatorName, String validationName) {

		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");

		}
		String actualValue=element.getAttribute("value");
		String expectedValue=XLReader.TestCaseDataMap.get(validationName);
		if(expectedValue==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		if(expectedValue.trim().equalsIgnoreCase(actualValue.trim())){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			Assert.assertEquals(expectedValue.trim(), actualValue.trim());
		}

	}

	// It generates random number between min and max
	public int generateRandom(int min,int max){ 
		int randomNo=0;
		if(max==min){
			randomNo=min;
		}else if(max<min){
			System.out.println("Invalid Range for Random Numbers, Max should be greater than min");
		}
		else{
		Random rn = new Random();		
		randomNo=rn.nextInt(max) + min;
		}
		return randomNo;
	}
	//This method is used to perform hover on specific elememnt.
	public void hoverElement(WebElement element) { 
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void switchToFrameByLocator(String locatorName) {
		// TODO Auto-generated method stub
		
		driver.switchTo().frame(getElement(locatorName));
	}



	//SPAL: Method will be used to Generate todays\Yesterdays/Tomorrow dates in desired format.

		public static String generateDate(String DATE_FORMAT, String whatDay) {
		String Date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		if(whatDay.equalsIgnoreCase("todays")) {
		    c1 = Calendar.getInstance(); // today
		    Date = sdf.format(c1.getTime());
		    System.out.println("Today is " + Date);
		} else if (whatDay.equalsIgnoreCase("yesterdays")) {
		    c1.add(Calendar.DATE, -1);
		    Date = sdf.format(c1.getTime());
		    System.out.println("Today is " + Date);
		} else if (whatDay.equalsIgnoreCase("tomorrow")) {
		    c1.add(Calendar.DATE, 1);
		    Date = sdf.format(c1.getTime());
		    System.out.println("Today is " + Date);
		}

		return Date;
		}
	
		/**
		**************************************************************************************************************
		* @Function Name: getPrevious_dateFormatDDMMYYYY
		* @Description: This function Will return a previous date based on the parameter in mm/dd/yyyy format
		* @Param: Number of days
		* @Param : N/A
		* @Return: Returns a String
		* @Date: 03/23/2017
		* @Author:  Nur
		**************************************************************************************************************
		*/

		public static String getPrevious_dateFormatDDMMYYYY(int noOfDays){
			
			//String date = null;
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	        Date date = new Date();
	        String todate = dateFormat.format(date);

	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -noOfDays);
	        Date todate1 = cal.getTime();    
	        String fromdate = dateFormat.format(todate1);
			return fromdate;
		}
		
		/**
		**************************************************************************************************************
		* @Function Name: getFuture_dateFormatDDMMYYYY
		* @Description: This function Will return a previous date based on the parameter in mm/dd/yyyy format
		* @Param: Number of days
		* @Param : N/A
		* @Return: Returns a String
		* @Date: 03/23/2017
		* @Author:  Nur
		**************************************************************************************************************
		*/

		public static String getFuture_dateFormatDDMMYYYY(int noOfDays){
			
			//String date = null;
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	        Date date = new Date();
	        String todate = dateFormat.format(date);

	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, + noOfDays);
	        Date todate1 = cal.getTime();    
	        String fromdate = dateFormat.format(todate1);
			return fromdate;
		}
		/**
		**************************************************************************************************************
		* @Function Name: getCurrentDate_DDMMYYYY()
		* @Description: This function Will return current date in mm/dd/yyyy format
		* @Param: N/A
		* @Param : N/A
		* @Return: Returns a String
		* @Date: 03/23/2017
		* @Author:  Nur
		**************************************************************************************************************
		*/

		public static String getCurrentDate_DDMMYYYY(){
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	        Date date = new Date();
	        String todate = dateFormat.format(date);
			return todate;
		}
		
		/**
		**************************************************************************************************************
		* @Function Name: EnterDynamicData
		* @Description: This function was introduced if anyone wants to enter any dynamic data in a text field such as yesterdays date, 10 days earlier date
		* @Param: locatorName
		* @Param : strValue
		* @Return: N/A
		* @Date: 03/23/2017
		* @Author:  Nur
		**************************************************************************************************************
		*/
		public void EnterDynamicData(String locatorName, String strValue) {
			try{
				this.element = getElement(locatorName);
				element.clear();
				element.sendKeys(strValue);
				BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", strValue+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");
			}catch(ElementNotVisibleException e){
				BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>",  strValue+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ " because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform click.");
			}catch(StaleElementReferenceException e){
				try{
					this.element = getElement(locatorName);
					element.clear();
					element.sendKeys(strValue);
					BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", strValue+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");
					
				}catch(StaleElementReferenceException ne){
					BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" Web page's HTM has been refreshed, changed or updated since it was looked up");
					
				}
			}catch(InvalidElementStateException e){
				//driver.executeScript("arguments[0].setAttribute('value', '" + longstring +"')", inputField);
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + strValue +"')", element);
			}	
			
		}
	
	
	/**
	**************************************************************************************************************
	* @Function Name: extractCellData
	* @Description: This function will fetch the cell data from the given 2nd parameter (row no) and compare the date range
	* @Param: locatorName
	* @Param : startRow
	* @Return: N/A
	* @Date: 03/23/2017
	* @Author:  Nur
	**************************************************************************************************************
	*/
	public void extractCellDataAndCompare(String locatorName, int startRow,String minDate, String maxDate){

		Map<String, String> locatorDetailMap=XLReader.LocatorDataMap.get(locatorName);
		String locatorValue=locatorDetailMap.get("LocatorValue");
		String finalXpath = "";
		String rowData = "";
		if (locatorValue.contains(";")){
			String loc[] =locatorValue.split(";");
			for (int r = startRow; r < 20; r ++ ){
				 finalXpath = loc[0] + r + loc[1];
					String cellValue = driver.findElement(By.xpath(finalXpath)).getText();
					if (!cellValue.isEmpty()){
					rowData = driver.findElement(By.xpath(finalXpath)).getText().split(" ")[1]+ "/2017";
					Date rowDate = convertString_To_Date(rowData);
					Date first_Date = convertString_To_Date(minDate);
					Date SecondDate = convertString_To_Date(maxDate); 
					if (rowDate.compareTo(first_Date)>=0 && rowDate.compareTo(SecondDate)<=0){
						BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", rowDate+" has been Checked And Found bEtween"
								+ " : "+"<b>"+first_Date + " and  " + SecondDate +"</b> "+"</b> "+ "   successfully.");
					}else{
						
						BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", rowDate+" has been Checked And Found unexpected date btween"
								+ " : "+"<b>"+first_Date + " and  " + SecondDate +"</b> "+"</b>");
					}
					
				}else{
					break;
			}		
					
		  }
		
		}
	}
	
	
	
	public void compareDate(String origianalDate, String compareDate){
		
		Date first_Date = convertString_To_Date(origianalDate);
		Date Second_Date = convertString_To_Date(compareDate);
		
		if (first_Date.compareTo(Second_Date)==0){
			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", origianalDate+" has been compared with"
					+ " : "+"<b>"+compareDate +"</b> "+ "   successfully.");
			
		}else{
			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", origianalDate+" has been compared And Did not match With"
					+ " : "+"<b>"+compareDate +"</b>");
			
		}
	}
	
	/**
	**************************************************************************************************************
	* @Function Name: convertString_To_Date
	* @Description: This function will convert string to date format
	* @Param: String date
	* @Param : 
	* @Return: N/A
	* @Date: 03/23/2017
	* @Author:  Nur
	**************************************************************************************************************
	*/
	public Date convertString_To_Date(String strValue){
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date startDate = null;
	    try {
	        startDate = df.parse(strValue);
	        
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		return startDate;
	}
	
	 public void validateNoDropdownInParentSection(String parentSectionLocatorName) {
			try{
				this.element = getElement(parentSectionLocatorName);
			}catch(Exception e){
				BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+parentSectionLocatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
				
			}
			int listBoxCount=element.findElements(By.tagName("select")).size();
			if(listBoxCount==0){
				BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"</b>"+" Validation Passed. There is no dropdown in <b style= "+'"'+"font-size:color:green"+'"'+">"+parentSectionLocatorName+"</b> section");
			}else{
				BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"</b>"+" Validation Failed. There is dropdowns in <b style= "+'"'+"font-size:color:red"+'"'+">"+parentSectionLocatorName+"</b> section");
				Assert.assertEquals(listBoxCount, 0);	
			}
			
		}
	   
	public void validateNoTextboxInParentSection(String parentSectionLocatorName) {
		try{
			this.element = getElement(parentSectionLocatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+parentSectionLocatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		int textBoxCount=element.findElements(By.xpath("input[@type='textbox']")).size();
		if(textBoxCount==0){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"</b>"+" Validation Passed. There is no text box in <b style= "+'"'+"font-size:color:green"+'"'+">"+parentSectionLocatorName+"</b> section");
		}else{
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"</b>"+" Validation Failed. There is textboxes in <b style= "+'"'+"font-size:color:red"+'"'+">"+parentSectionLocatorName+"</b> section");
			Assert.assertEquals(textBoxCount, 0);	
		}
		
	}
	/**
	**************************************************************************************************************
	* @Function Name: retrieveLocatorValue
	* @Description: This function will return the locator value by providing the locator name
	* @Param: String date
	* @Param : 
	* @Return: N/A
	* @Date: 03/23/2017
	* @Author:  Nur
	**************************************************************************************************************
	*/
	
	public String retrieveLocatorValue(String fieldName){
		
		String locatorValue = "";
		locatorValue=XLReader.TestCaseDataMap.get(fieldName);;
		return locatorValue;
		
	}
public String retrieveObjectLocatorValue(String locatorName){
		
		String locatorValue = "";
		Map<String, String> locatorDetailMap=XLReader.LocatorDataMap.get(locatorName);
		locatorValue=locatorDetailMap.get("LocatorValue");
		return locatorValue;
		
	}
	
	/**
	**************************************************************************************************************
	* @Function Name: datePickFromCalendar
	* @Description: This function will pick a day from calendar from given day parameter
	* @Param: locatorName
	* @Param : dayFromCalendar
	* @Return: N/A
	* @Date: 03/27/2017
	* @Author:  Nur
	**************************************************************************************************************
	*/
	public void datePickFromCalendar(String locatorName, String dayFromCalendar){
		try{
			
			this.element = getElement(locatorName);
	        List<WebElement> columns=element.findElements(By.tagName("td"));
	        dayFromCalendar = retrieveLocatorValue(dayFromCalendar);
	        for (WebElement cell: columns){
	           if (cell.getText().equals(dayFromCalendar)){
	              cell.findElement(By.linkText(dayFromCalendar)).click();
	              break;
	         }
	      }
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
		
		}
	}
	

	/**
	**************************************************************************************************************
	* @Function Name: clearEditBox
	* @Description: This function will Any value from Edit box
	* @Param: locatorName
	* @Param :
	* @Return: N/A
	* @Date: 03/27/2017
	* @Author: Amit
	**************************************************************************************************************
	*/
	public void clearEditBox(String locatorName) {

		try{
			this.element = getElement(locatorName);
			element.clear();
			BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has been cleared successfully");
		}catch(ElementNotVisibleException e){
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+" has not been cleared because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform clear.");
			    
			}
		}

	/**
	**************************************************************************************************************
	* @Function Name: CheckObjectExist
	* @Description: This function will verify the object is displayed in the app or not
	* @Param: locatorName
	* @Param :
	* @Return: True or false
	* @Date: 03/27/2017
	* @Author: Nur
	**************************************************************************************************************
	*/
	public boolean CheckObjectExist(String locatorName) {
		//boolean objectFound = false;
		//try{
			
			String locatorvalue = retrieveObjectLocatorValue(locatorName);
			Boolean isPresent = driver.findElements(By.xpath(locatorvalue)).size() > 0;
//			driver.findElement(By.xpath(locatorvalue)).isDisplayed(){
//				BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>  Element has been Found successfully </b> ");
//				objectFound = true;
//			
//				//objectFound = false;
//				//BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>  Element has Not been Found </b> ");
//				
//			}
//		}catch(ElementNotVisibleException e){
//			objectFound = false;
//			    
//			}
		return isPresent;
		}
	
	public boolean getLocatorValue(String locatorName){
		
		boolean exist = false;
		Map<String, String> locatorDetailMap=XLReader.LocatorDataMap.get(locatorName);
		String locatorValue=locatorDetailMap.get("LocatorValue");
		String locatorType=locatorDetailMap.get("LocatorType");
		By locator=getLocatorBy(locatorName, locatorType, locatorValue);
		
		if(driver.findElements(locator).size() != 0){
			exist = true;
		}
		
		return exist;
		
	}
	public void validateTextBoxValueNotEquals(String locatorName, String validationName) {
		
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
			
		}
		String actualValue=element.getAttribute("value");
		String expectedValue=XLReader.TestCaseDataMap.get(validationName);
		if(expectedValue==null){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		if(expectedValue.equalsIgnoreCase(actualValue)){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed. Actual And Expected Values are  matching"+".  actual textbox value: [<b>"+actualValue+"</b>]  AND  expected textbox value: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed. Actual And Expected Values are not matching "+".  actual textbox value: [<b>"+actualValue+"</b>]  AND  expected textbox value: [<b>"+expectedValue+"</b>]");
			Assert.assertNotEquals(actualValue, expectedValue);
		}

	}

/**
	**************************************************************************************************************
	* @Function Name: HoverAndClick
	* @Description: This function will Hover over and click on menu options 
	* @Param: Hoverlocator
	* @Param: ClickLocator
	* @Return: N/A
	* @Date: 03/30/2017
	* @Author:  Amit
	**************************************************************************************************************
	*/
	
	public void HoverAndClick(String Hoverlocator, String ClickLocator) throws Exception{
			  
		WebElement menuElement = getElement(Hoverlocator); 
		Actions builder = new Actions(driver);
		builder.moveToElement(menuElement).build().perform(); 
		BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>"+Hoverlocator+"</b> "+"</b> "+" has been clicked successfully");
		WebElement subElement = getElement(ClickLocator);	
		builder.moveToElement(subElement).click().perform();
		BaseTest.test.log(LogStatus.INFO, "<b>Step No - "+StepNumber+++"</b>","<b>"+ClickLocator+"</b> "+"</b> "+" has been clicked successfully");
		}
	
	public void validateSelectedValueInSingleSelectionDropdown_NotEqual(String locatorName, String validationName) {
		String actualValue=getFirstSelectedOption(locatorName).getText().trim();
		String expectedValue=null;
		  expectedValue=XLReader.TestCaseDataMap.get(validationName);
		  
		if(expectedValue==null){
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Dropdown Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
		Assert.assertTrue(false);
		}
		if(actualValue.equalsIgnoreCase(expectedValue)){
		   BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 12pt;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 12pt;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Selected value is discarded. Validation Failed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		   Assert.assertEquals(expectedValue.trim(), actualValue.trim()); 
		}else{
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Selected value is not discarded. Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]"); 
		}
		}
	


public void validateAttribute(String locatorName, String attributeName, String validationName) {
	   try{
	    this.element = getElement(locatorName);
	   }catch(Exception e){
	    BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
	    
	   }
	   String actualAttributeValue=element.getAttribute(attributeName);
	   String expAttributeValue=XLReader.TestCaseDataMap.get(validationName);
	    
	   if(actualAttributeValue.equals(expAttributeValue)){
	    BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 12pt;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 12pt;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" "+" Passed. Attribute Value of "+attributeName+" is matching. actual: [<b>"+actualAttributeValue+"</b>]  AND  expected: [<b>"+expAttributeValue+"</b>]");
	   }else{
	    BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 12pt;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Failed. Attribute Value of "+attributeName+" is not matching.actual[<b>"+actualAttributeValue+"</b>]  AND  expected: [<b>"+expAttributeValue+"</b>]");
	    Assert.assertEquals( actualAttributeValue, expAttributeValue);
	   }
	}

	
public void hitEnter(){
	this.element.sendKeys(Keys.RETURN);
}


    /**
     **************************************************************************************************************
     * @Function Name: Select an item by visible text on the auto complete dropdown list
     * @Description: Select an item by visible text on the auto complete dropdown list
     * @Param: locatorName
     * @Param: fieldName
     * @Return: N/A
     * @Date: 04/07/2017
     * @Author:  Vu Vo
     **************************************************************************************************************
     */
    public void selectAutoCompleteItemByVisibleText(String locatorName, String fieldName){
	    WebElement autoCompletedDropdown = getElement(locatorName);
	    String visibleText = XLReader.TestCaseDataMap.get(fieldName).trim();
	    List<WebElement> items = autoCompletedDropdown.findElements(By.cssSelector("li"));
	    for (WebElement item : items){
	        String itemText = item.getText().trim();
	        if(visibleText.equals(itemText)) {
                BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 12pt;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>",
                        "<b style= "+'"'+"font-size: 12pt;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" "+" selected with [<b>"+itemText+"</b>]");
                item.click();
	            break;
	        }
        }
    }

    /**
     **************************************************************************************************************
     * @Function Name: switchToDefaultContent
     * @Description: Switch To the Top (default) frame of the current page
     * @Param: N/A
     * @Return: N/A
     * @Date: 04/07/2017
     * @Author:  Vu Vo
     ***/

    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    /**
     **************************************************************************************************************
     * @Function Name: compareDateOnColumnName
     * @Description: This function will compare the value from a Date Column in a given table locator with the minDate and maxDate
     * @Param: locatorName
     * @Param : startRow
     * @Return: N/A
     * @Date: 03/23/2017
     * @Author:  Nur
     **************************************************************************************************************
     */
    public void compareDateValueOnColumnName(String locatorName, String columnName, int startRow,String minDate, String maxDate) {

        WebElement table = getElement(locatorName);
        List<WebElement> headers = table.findElements(By.cssSelector("tr .GridView-HeaderFooter"));
        int i = 0;
        for (; i < headers.size(); i++) {
            if (columnName.trim().equalsIgnoreCase(headers.get(i).getText().trim())) {
                break;
            }
        }
        if (i == 0) {
            BaseTest.test.log(LogStatus.FAIL, "<b style= " + '"' + "font-size: 12pt;color:red" + '"' + ">" + "<b>Step No - " + StepNumber++ + "</b>" + "</b>", "<b style= " + '"' + "font-size: 12pt;color:red" + '"' + ">" + "<b>" + locatorName + "</b> " + "</b> " + " Column Name is not found in the table " + columnName);
            Assert.fail(columnName + " is not exists on the table locator " + locatorName);
        }
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));
        int j = startRow;
        for (; j < rows.size(); j++) {
            WebElement row = rows.get(j);
            // Find the cell at the given column name
            WebElement cell = row.findElements(By.cssSelector("td")).get(i);
            String cellValue = cell.getText().trim();
            if (!cellValue.isEmpty()) {
                Date rowDate = convertString_To_Date(cellValue);
                Date min = convertString_To_Date(minDate);
                Date max = convertString_To_Date(maxDate);
                if (rowDate.compareTo(min) >= 0 && rowDate.compareTo(max) <= 0) {
                    BaseTest.test.log(LogStatus.INFO, "<b>Step No - " + StepNumber++ + "</b>", rowDate + " has been Checked And Found bEtween"
                            + " : " + "<b>" + min + " and  " + max + "</b> " + "</b> " + "   successfully.");
                } else {

                    BaseTest.test.log(LogStatus.INFO, "<b>Step No - " + StepNumber++ + "</b>", rowDate + " has been Checked And Found unexpected date btween"
                            + " : " + "<b>" + min + " and  " + max + "</b> " + "</b>");
                }
            }
        }
    }
    
public boolean validateActualAndExpectedText(String Actual , String Expected){
		
		if (Expected.trim().equalsIgnoreCase(Actual.trim())) {
			BaseTest.test.log(LogStatus.PASS,
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>Step No - " + StepNumber++
							+ "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>" +  "</b> "
							+ "</b> " + "</b>" + " Text Validation Passed" + ".  actual text: [<b>" + Actual
							+ "</b>]  AND  expected: [<b>" + Expected + "</b>]");
		} else {
			BaseTest.test.log(LogStatus.FAIL,
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>Step No - " + StepNumber++
							+ "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>" +  "</b> "
							+ "</b> " + "</b>" + " Text Validation Failed " + ".  actual text: [<b>" + Actual
							+ "</b>]  AND  expected: [<b>" + Expected + "</b>]");
			
		}
		Assert.assertEquals(Actual, Expected);
		return true;
		
	}

public boolean getAlertStatus() {
	boolean foundAlert = false;
	WebDriverWait wait = new WebDriverWait(driver,
			0 /* timeout in seconds */);
	try {
		wait.until(ExpectedConditions.alertIsPresent());
		foundAlert = true;
	} catch (TimeoutException eTO) {
		foundAlert = false;
	}
	return foundAlert;
}

/**
**************************************************************************************************************
* @Function Name: getFirstDate_DDMMYYYY
* @Description: This function Will return First day of current month in mm/01/yyyy format
* @Param: 
* @Return: todate
* @Date: 04/07/2017
* @Author:  Amit
**************************************************************************************************************
*/

public static String getFirstDate_DDMMYYYY(){
	
	DateFormat dateFormat = new SimpleDateFormat("MM/01/yyyy");
    Date date = new Date();
    String todate = dateFormat.format(date); 
	return todate;
}

/**
 **************************************************************************************************************
 * @Function Name: validateWindowTabTitle
 * @Description: This function will verify that Actual & Expected Window Title is Matching or not
 * @Param: titleFieldName
 * @Param :
 * @Return: 
 * @Date: 03/30/2017
 * @Author: Rahul
 **************************************************************************************************************
 */
public void validateWindowTab_PartialTitle(String titleFieldName) {
	// TODO Auto-generated method stub
	String expectedPartialTitle = XLReader.TestCaseDataMap.get(titleFieldName);
	String actualTitle=driver.getTitle();
	if(actualTitle.trim().toUpperCase().contains(expectedPartialTitle.trim().toUpperCase())){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+titleFieldName+"</b> "+"</b> "+"</b>"+" Window Tab Validation Passed. Title is matching"+".  actual title: [<b>"+actualTitle+"</b>]  AND  eexpected title: [<b>"+expectedPartialTitle+"</b>]");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+titleFieldName+"</b> "+"</b> "+"</b>"+" Window Tab Validation Failed "+".  Title is not matching. actual title: [<b>"+actualTitle+"</b>]  AND  expected title: [<b>"+expectedPartialTitle+"</b>]");
		Assert.assertTrue( false);
	}
}

/**
 **************************************************************************************************************
 * @Function Name: validateWindowTab_PartialTitle_NoMatch
 * @Description: This function will verify that Actual & Expected Window Title is not Matching
 * @Param: titleFieldName
 * @Param :
 * @Return: 
 * @Date: 03/30/2017
 * @Author: Rahul
 **************************************************************************************************************
 */
public void validateWindowTab_PartialTitle_NoMatch(String titleFieldName) {
	// TODO Auto-generated method stub
	String expectedPartialTitle = XLReader.TestCaseDataMap.get(titleFieldName);
	String actualTitle=driver.getTitle();
	if(actualTitle.trim().toUpperCase().contains(expectedPartialTitle.trim().toUpperCase())==false){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+titleFieldName+"</b> "+"</b> "+"</b>"+" Window Tab Validation Passed. Title is not matching"+".  actual title: [<b>"+actualTitle+"</b>]  AND  eexpected title: [<b>"+expectedPartialTitle+"</b>]");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+titleFieldName+"</b> "+"</b> "+"</b>"+" Window Tab Validation Failed "+".  Title is matching. actual title: [<b>"+actualTitle+"</b>]  AND  expected title: [<b>"+expectedPartialTitle+"</b>]");
		Assert.assertTrue( false);
	}
}

/**
 **************************************************************************************************************
 * @Function Name: validateTextDirectly
 * @Description: This function will verify that Actual & Expected Window Title is Matching without test from excel
 * @Param: actualText
 * @Param :expectedText
 * @Param :validationMessage
 * @Return: 
 * @Date: 03/30/2017
 * @Author: Rahul
 **************************************************************************************************************
 */
public void validateTextDirectly(String validationMsg, String actualText, String expectedText) {
	// TODO Auto-generated method stub
	if(actualText.equals(expectedText)){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+validationMsg+"</b> "+"</b> "+"</b>"+" Passed. Title is not matching"+".  actual: [<b>"+actualText+"</b>]  AND  expected: [<b>"+expectedText+"</b>]");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+validationMsg+"</b> "+"</b> "+"</b>"+" Window Tab Validation Failed "+".  Title is matching. actual: [<b>"+actualText+"</b>]  AND  expected: [<b>"+expectedText+"</b>]");
		Assert.assertEquals( actualText, expectedText);
	}
}

/**
 **************************************************************************************************************
 * @Function Name: validateColor
 * @Description: This function will verify that Actual & Expected color of font of any text on page
 * @Param: locator
 * @Param :expColorValidationName
 * @Return: 
 * @Date: 03/30/2017
 * @Author: Rahul
 **************************************************************************************************************
 */
public void validateColor(String locator, String expColorValidationName) {
	// TODO Auto-generated method stub
	String actualColor=getColor(locator);
	String expColor=XLReader.TestCaseDataMap.get(expColorValidationName);

	if(actualColor.equals(expColor)){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locator+"</b> "+"</b> "+"</b>"+" "+".Color is Matching. actual: [<b>"+actualColor+"</b>]  AND  expected: [<b>"+expColor+"</b>]");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locator+"</b> "+"</b> "+"</b>"+" Color is not Matching. actual[<b>"+actualColor+"</b>]  AND  expected: [<b>"+expColor+"</b>]");
		Assert.assertEquals( actualColor, actualColor);
	}
}

/**
 **************************************************************************************************************
 * @Function Name: getColor
 * @Description: This function will fetch color of any font through getCSSValue Method of WebElement
 * @Param: locatorName
 * @Param :
 * @Return: 
 * @Date: 03/30/2017
 * @Author: Rahul
 **************************************************************************************************************
 */
public String getColor(String locatorName) {
	// TODO Auto-generated method stub
	try{
		this.element = getElement(locatorName);
	}catch(Exception e){
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");

	}
	String color=element.getCssValue("color");
	if(color==null){
		color=""; 
	}
	return color;

}



/**
 **************************************************************************************************************
 *there is another method with same purpose. Please look for the method before create.
 * @Function Name: setTextBoxValue_Direct
 * @Description: This function will input value in excel directly not from test data excel
 * @Param: locatorName
 * @Param :textToInput
 * @Return: 
 * @Date: 03/30/2017
 * @Author: Rahul
 **************************************************************************************************************
 */
public void setTextBoxValue_Direct(String locatorName, String textToInput){

	try{
		this.element = getElement(locatorName);

		element.clear();
		element.sendKeys(textToInput);
		BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", textToInput+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");
	}catch(ElementNotVisibleException e){
		BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>",  textToInput+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ " because "+"<b>"+locatorName+"</b> "+"</b> "+" is not visible on Page. So Selenium is not able to perform click.");
	}catch(StaleElementReferenceException e){
		try{
			this.element = getElement(locatorName);
			element.clear();
			element.sendKeys(textToInput);
			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", textToInput+" has been entered in text box : "+"<b>"+locatorName+"</b> "+"</b> "+ "   successfully.");

		}catch(StaleElementReferenceException ne){
			BaseTest.test.log(LogStatus.ERROR,"<b style="+'"'+"color:red"+'"'+">Step No - "+StepNumber+++"</b>","<b>"+locatorName+"</b> "+"</b> "+" has not been clicked because "+"<b>"+locatorName+"</b> "+"</b> "+" Web page's HTM has been refreshed, changed or updated since it was looked up");

		}
	}catch(InvalidElementStateException e){
		//driver.executeScript("arguments[0].setAttribute('value', '" + longstring +"')", inputField);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + textToInput +"')", element);
	}

}

/**
 **************************************************************************************************************
 * @Function Name: validateValueAttribute_Direct
 * @Description: This function will take expected values directly not from test data excel
 * @Param: locatorName
 * @Param :expectedValue
 * @Return: 
 * @Date: 03/30/2017
 * @Author: Rahul
 **************************************************************************************************************
 */
public void validateValueAttribute_Direct(String locatorName, String expectedValue) {
	// TODO Auto-generated method stub
	try{
		this.element = getElement(locatorName);
	}catch(Exception e){
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");

	}
	String actualValue=element.getAttribute("value");

	if(expectedValue==null){
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed"+". Expected Value is Null Please Match Test Data Column Name from script's field Name");
		Assert.assertTrue(false);
	}
	if(expectedValue.trim().equalsIgnoreCase(actualValue.trim())){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Passed"+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualValue+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		Assert.assertEquals(actualValue.trim(), expectedValue.trim());
	}
}

public String getAttribute( String locatorName, String attribute) {
	element = getElement(locatorName);
	return element.getAttribute(attribute);
}

public List<WebElement> getElementsList( String locatorName) {
	String locatorValue = XLReader.LocatorDataMap.get(locatorName).get("LocatorValue");
	String locatorType = XLReader.LocatorDataMap.get(locatorName).get("LocatorType");;
	return driver.findElements(getLocatorBy(locatorName, locatorType, locatorValue));
}

public int getElementsCount( String locatorName) {
	return getElementsList(locatorName).size();
}

public void validateNumbers(int actualNumber, int expNumber) {
	// TODO Auto-generated method stub

	if(actualNumber==expNumber){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">Number or Count Validation"+"</b> "+"</b>"+" Number or Count Validation Passed.  actual number: [<b>"+actualNumber+"</b>]  AND  expected: [<b>"+expNumber+"</b>]");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">Number or Count Validation</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualNumber+"</b>]  AND  expected: [<b>"+expNumber+"</b>]");
		Assert.assertEquals(actualNumber, expNumber);
	}
}

public void validateCheckBoxChecked(String locatorName){
	boolean status=isCheckboxChecked(locatorName);
	if(status==true){
		BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">Checkbox Validation"+"</b> "+"</b>"+" Validation Passed. Checkbox is Checked as expected.");
	}else{
		BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">Checkbox Validation</b> "+"</b>"+" Validation Failed. Checkbox is Actually UnChecked. Expected-Checked.");
		Assert.assertTrue(false);
	}
}

public boolean isCheckboxChecked(String locatorName){
	element = getElement(locatorName);
	return element.isSelected();
}

public boolean isChecked(String locatorName){
	boolean status = false;
	element = getElement(locatorName);
	if(element.isSelected()){
		status = true;
		
	}else{
		status = false;
	}
	
	return status;
}
    /****************************************************************************************************************
     * @Function Name: getBodyText
     * @Description: This function Will return text content of the page body. Can be used to check the body empty
     * @Param: N/A
     *@Return: Text of the body
     * @Date: 04/14/2017
     * @Author:  Vu
     *
     *****************************************/
public void switchToNewTab()
{
	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
}
    public String getBodyText() {
        return driver.findElement(By.tagName("body")).getText();
    }
    
    public int getWindowTabCount()
	{
		return driver.getWindowHandles().size();
	}
    
    
    public void validateWindowTabCount(int expectedWindowCount){
		int actualWindowCount=getWindowTabCount();
       if(expectedWindowCount==actualWindowCount){
    	   BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"</b>"+" window or tab count validation passed"+".  actual count: [<b>"+actualWindowCount+"</b>]  AND  expected: [<b>"+expectedWindowCount+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+"</b>"+" window or tab count validation passed"+".  actual count: [<b>"+actualWindowCount+"</b>]  AND  expected: [<b>"+expectedWindowCount+"</b>]");
			Assert.assertEquals(expectedWindowCount, actualWindowCount);
		}
	}
    
	public void validateValueInDropDown(String locatorName, String fieldName) {
		// TODO Auto-generated method stub
		List<WebElement> listOptions=getOptions(locatorName);
		String expectedValue=XLReader.TestCaseDataMap.get(fieldName).trim();
		String actualText=null;
		boolean status=false;
		for(int i=0; i<=listOptions.size()-1; i++){
			actualText=listOptions.get(i).getText().trim();
			if(actualText.equalsIgnoreCase(expectedValue.trim())){
				status=true;
				break;
			}
		}
		if(status==true){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Dropdown Value Validation Passed"+".  actual text: [<b>"+actualText+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Text Validation Failed "+".  actual text: [<b>"+actualText+"</b>]  AND  expected: [<b>"+expectedValue+"</b>]");
			Assert.assertEquals(expectedValue.trim(), actualText.trim());
		}

	}

public void validateElementNotVisible(String locatorName) {
		// TODO Auto-generated method stub	
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");
		}	

		if(element.isDisplayed()==false){
			BaseTest.test.log(LogStatus.PASS, "<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is not Visible. Validation Passed"+".  actual value:<b>Hidden</b>  AND  expected value: [<b>Hidden</b>]");
		}else{
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:green"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+"</b>"+" Element is Visible. Validation Failed"+".  actual value:<b>Visible</b>  AND  expected value: [<b>Hidden</b>]");
			Assert.assertTrue(false);
		}

	}
	
	
	public void compareGreaterOrSmallerDate(String locatorName , String validationName ){
		try{
			this.element = getElement(locatorName);
		}catch(Exception e){
			BaseTest.test.log(LogStatus.FAIL, "<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>Step No - "+StepNumber+++"</b>"+"</b>","<b style= "+'"'+"font-size: 15px;color:red"+'"'+">"+"<b>"+locatorName+"</b> "+"</b> "+" Element Not Found in Page Source");

		}
		String actualValue=element.getText();
		String expectedValue =XLReader.TestCaseDataMap.get(validationName);

		Date Expected_Date = convertString_To_Date(expectedValue);
		Date Actual_Date = convertString_To_Date(actualValue);

		if (Expected_Date.before(Actual_Date)==true){

			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>","<b>"+ actualValue +"</b>" + " has been checked and found  greater than"
					+ " : "+"<b>"+expectedValue +"</b> ");

		}

		else if(Expected_Date.after(Actual_Date)==true){
			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>","<b>"+ actualValue +"</b>" + " has been checked and found smaller than"
					+ " : "+"<b>"+expectedValue +"</b> ");

		}

		else{
			BaseTest.test.log(LogStatus.INFO,"<b>Step No - "+StepNumber+++"</b>", actualValue+" has been checked and found equals to "
					+ " : "+"<b>"+expectedValue +"</b>");
		}

	}
	
	public void validateActualAndExpectedText_NotEqual(String Actual , String Expected){

		if (Expected.trim().equalsIgnoreCase(Actual.trim())) {
			BaseTest.test.log(LogStatus.FAIL,
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>Step No - " + StepNumber++
					+ "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>" +  "</b> "
							+ "</b> " + "</b>" + " Text Validation Failed " + ".  actual text: [<b>" + Actual
							+ "</b>]  AND  expected: [<b>" + Expected + "</b>]");
			Assert.assertEquals(Actual, Expected);
		} else {
			BaseTest.test.log(LogStatus.PASS,
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>Step No - " + StepNumber++
					+ "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>" +  "</b> "
							+ "</b> " + "</b>" + " Text Validation Passed" + ".  actual text: [<b>" + Actual
							+ "</b>] is not equal to expected text: [<b>" + Expected + "</b>]");

		}
	}
	
	
	public void validateTextOnAlert_ExtData(String validationName) {
		checkIsAlertPresent();
		Alert alert = driver.switchTo().alert();
        
		String actualValue = alert.getText();
		String expectedValue = XLReader.TestCaseDataMap.get(validationName);
		if (expectedValue == null) {
			BaseTest.test.log(LogStatus.FAIL,
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>Step No - " + StepNumber++
							+ "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>" 
							+ "</b> " + "</b>" + " Text Validation Failed"
							+ ". Expected Value is Null Please Match Test Data Column Name from script's field Name");
			Assert.assertTrue(false);
		}
		if (expectedValue.trim().contains(actualValue.trim())) {
			BaseTest.test.log(LogStatus.PASS,
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>Step No - " + StepNumber++
							+ "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>" 
							+ "</b> " + "</b>" + " Text Validation Passed" + ".  actual text: [<b>" + actualValue
							+ "</b>]  AND  expected: [<b>" + expectedValue + "</b>]");
		} else {
			BaseTest.test.log(LogStatus.FAIL,
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>Step No - " + StepNumber++
							+ "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>" 
							+ "</b> " + "</b>" + " Text Validation Failed " + ".  actual text: [<b>" + actualValue
							+ "</b>]  AND  expected: [<b>" + expectedValue + "</b>]");
			Assert.assertEquals(expectedValue.trim(), actualValue.trim());
		}
					
	}    

	public void checkObjectNotExist(String locatorName){
        Map<String, String> locatorDetailMap = XLReader.LocatorDataMap.get(locatorName);
        String locatorValue = locatorDetailMap.get("LocatorValue");
        String locatorType = locatorDetailMap.get("LocatorType");
        By locator = getLocatorBy(locatorName, locatorType, locatorValue);
        holdOn(2000);
        List<WebElement> noOfElements = driver.findElements(locator);
        if(noOfElements.size() > 0){
            WebElement element = noOfElements.get(0);
            Assert.assertFalse(element.isDisplayed());
        } else {
            Assert.assertTrue(noOfElements.size()==0, "Object with locator: "+locatorValue + " exists.");
        }
    }

    public void discardMultipleSelectedOptions(String locatorName) {
        Map<String, String> locatorDetailMap = XLReader.LocatorDataMap.get(locatorName);
        String locatorValue = locatorDetailMap.get("LocatorValue");
        String locatorType = locatorDetailMap.get("LocatorType");
        By locator = getLocatorBy(locatorName, locatorType, locatorValue);
        WebElement selectElement = driver.findElement(locator);
        // Click on the datalist element to expand the dropdown list
        selectElement.findElement(By.xpath("following::datalist")).click();
        Select select = new Select(selectElement);
        List<WebElement> selectOptions = select.getAllSelectedOptions();
        BaseTest.test.log(LogStatus.INFO,"",selectOptions.size() + " options are selected");
        for (WebElement option : selectOptions){
            String value = option.getAttribute("value");
            BaseTest.test.log(LogStatus.INFO,"","Discard option with value " + value);
            WebElement checkbox = selectElement.findElement(By.xpath("following::input[@type='checkbox' and @value='"+value+"']"));
            checkbox.click();
            BaseTest.test.log(LogStatus.INFO,"", value + " is discarded successfully.");
        }
    }

    /****************************************************************************************************************
     * @Function Name: Focus
     * @Description: Focus on the element defined by the locatorName
     * @Param: locatorName
     *@Return: Wrapper so we can call another method after focusing.
     * @Date: 06/02/2017
     * @Author:  Vu
     *
     *****************************************/
    public Wrapper focus(String locatorName){
        Map<String, String> locatorDetailMap = XLReader.LocatorDataMap.get(locatorName);
        String locatorValue = locatorDetailMap.get("LocatorValue");
        String locatorType = locatorDetailMap.get("LocatorType");
        By locator = getLocatorBy(locatorName, locatorType, locatorValue);
        WebElement selectElement = driver.findElement(locator);
        new Actions(driver).moveToElement(selectElement).perform();
        return this;
    }
	
	 public int intRandom(int digit){
		  
		  Random rand = new Random(); 
		  int randomNo =0;
		  String strRandom="";
		  
		  for(int i=0;i<digit;i++){
		   randomNo = rand.nextInt(100);
		   strRandom = strRandom + randomNo;
		  }
		  if(strRandom.length() > digit){		   
			  strRandom = strRandom.substring(0, digit);
		  }		  
		  return Integer.parseInt(String.valueOf(strRandom));
	 }

		
}



