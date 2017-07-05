
package com.sandata.v8.driver_script;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.sandata.utilities.XLReader;

public class TestNGDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		TestNG myTestNG = new TestNG();
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("Sandata Suite");
		XmlTest myTest = new XmlTest(mySuite);
		myTest.setName("SanData Test");
		List<XmlClass> testngClassesTag=null;
		XLReader.DataSheetPath=TestNGDriver.class.getName().split("\\.")[2].toUpperCase()+".xlsx";
		List<Map<String, String>> tcNamesMapList=XLReader.getDriverSheetInfo();
		testngClassesTag = new ArrayList<XmlClass> ();

		for(int i=0; i<=tcNamesMapList.size()-1;i++){

			Map<String, String> tcNamesMap=tcNamesMapList.get(i);
			XmlClass testngClassTag = new XmlClass("com.sandata.v8.functional."+tcNamesMap.get("Module_Name")+"."+tcNamesMap.get("TestCase_Name"));
			testngClassesTag.add(testngClassTag);
		}
		myTest.setXmlClasses(testngClassesTag);
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.add(myTest);
		mySuite.setTests(myTests);
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		myTestNG.setXmlSuites(mySuites);
		myTestNG.run(); 



	}


}