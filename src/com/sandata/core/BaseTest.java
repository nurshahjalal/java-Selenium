
package com.sandata.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sandata.utilities.XLReader;

public class BaseTest {

	protected static ExtentReports extent;
	String startClassTime, endClassTime, duration;
	public static String testcaseName;
	static Wrapper baseobj = new Wrapper();
	static public ExtentTest test;
	public static String testCaseName;
	public static String classResult = "Pass";

	public static int iterationCounter;
	   public  static String testStep="";
	   static String testMethodName;
	   
	@DataProvider(name="td")
	public Object[][] testData(Method testMethod) throws IOException{
		
		iterationCounter=0;
		 testMethodName=testMethod.getDeclaringClass().getName();
		 String[] clsNameArr=testMethodName.split("\\.");
		 testMethodName=clsNameArr[clsNameArr.length-1];
		XLReader.DataSheetPath=System.getProperty("user.dir")+"\\"+testMethodName.split("_")[1]+".xlsx";
		Pattern p = Pattern.compile("TC_\\d{1,5}");
	    Matcher m = p.matcher(testMethodName);
	    String  testCaseID="";
	    
	    if(m.find()){
	    	testCaseID=m.group(0);
	    }
        
		XLReader.getTestCaseInfo(testCaseID);
		
		 Wrapper.StepNumber=1;
		
		String sheetName=XLReader.TestCaseInfoMap.get("sheetName");
		String rowID=XLReader.TestCaseInfoMap.get("rowID");
		
		if(sheetName.trim().equals("")==false){
		 
			XLReader.getTestCaseData(sheetName, rowID);
		}
		XLReader.getEnvironmentInfo(XLReader.TestCaseInfoMap.get("ENV_ID"));
		
		Object[][] dummyArray=new Object[1][1];
		    for(int i=0; i<=dummyArray.length-1; i++){
		    	dummyArray[i][0]="dummy";
		    }
		return dummyArray;
	}
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		
		if(XLReader.DataSheetPath==null){
			File testcaseFolder=new File("src/test/java/com/sandata");
			XLReader.DataSheetPath=testcaseFolder.list()[0].trim().toUpperCase()+".xlsx";
		}
		
		XLReader.getLocatorInfo();
		String ts=Wrapper.getTimeStamp();
		String resultFoler="Automation_Reports";
		String resultFilePath=System.getProperty("user.home") + "\\"+resultFoler;
		resultFoler=createFolder(resultFilePath);
		folderPath=resultFoler+"\\"+ts+"_ExecutionReport";
		folderPath=createFolder(folderPath);
		String extentReportPath=folderPath+"\\"+"Automation_Report.html";
		extent = new ExtentReports(extentReportPath, true);
		extent.loadConfig(new File("extent-config.xml"));
        
		System.out.println("------------------@BeforeSuite fired------------------------");
	
	}
	
	
	
	public static String folderPath;
	
	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.out.println("------------------ @BeforeMethod fired------------------------");
		startClassTime = baseobj.getCurrentTime();
		
		
		Map<String, String> sysInfo = new HashMap<String, String>();
		sysInfo.put("Selenium Version", "3.0");
		sysInfo.put("Environment", "NST-Internal");
		extent.addSystemInfo(sysInfo);
		test = extent.startTest(testMethodName).assignCategory(this.getClass().getSimpleName());
	
		
	}
	
	public String createFolder(String folderPath){
		File file= new File(folderPath);
		if(file.exists()==false){
			file.mkdir();
		}
		return folderPath;
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("------------------ @BeforeClass fired------------------------");
	}
	
	@AfterClass
	public void afterClass(){
			
	}
	
	@BeforeTest
	public void beforeTest(){

	}
	@AfterTest
	public void afterTest(){
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("------------------ @AfterMethod fired------------------------");
		if (!result.isSuccess()) {
			System.out.println("ITestResult");
			String methodName = result.getName().toString().trim();
			baseobj.takeScreenShot(folderPath, methodName);
		} else {
			test.log(LogStatus.PASS, result.getMethod().getMethodName());
		}
		
		extent.flush();
		BaseDriver.StopDriver();
		baseobj.holdOn(3000);
	   
	}

	@AfterSuite
	protected void afterSuite() {
		System.out.println("------------------	@AfterSuite fired -----------------------");
        try {
            FileUtils.copyFile(new File(folderPath+"\\"+"Automation_Report.html"), new File(System.getProperty("user.dir") + "\\"+"Automation_Report.html"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
		// mailReport.sendEmail();
	}
	
	
	
}

