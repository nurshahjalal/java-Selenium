package com.sandata.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLReader {

	public  static String DataSheetPath;
	public static final String TestDataSheetName="TestData";
	public static final String LocatorsSheetName="Locators";
	public static final String DriverSheetName="TestDriverSheet";
	public static final String EnvSheetName="ENV";
	public static String groupFlag="Y";
	public static String groupName="Regression";
	public static List<Map<String, String>> TestCaseDataMapList;
	public static Map<String, String> TestCaseInfoMap;
	public static Map<String, String> TestCaseDataMap;
	public static Map<String, String> EnvironmentDataMap;
	public static Map<String, Map<String, String>> LocatorDataMap;
	
	

	public static void initializeData(Map<String, String> testCaseDataMap) throws IOException{
		
	}
	
	public static List<Map<String, String>>  getDriverSheetInfo() throws IOException{
           
			Workbook wbook=getDataBook(DataSheetPath);
			Sheet sheet=getDataSheet(wbook, DriverSheetName);
			Row firstRow= sheet.getRow(0);
			int columnCount=firstRow.getLastCellNum();
			String[] columnsArray=new String[columnCount];
			for(int i=0; i<=columnCount-1; i++){
				columnsArray[i]=firstRow.getCell(i).getStringCellValue();
			}
			
			List<Map<String, String>> testCaseMapList=new ArrayList<Map<String, String>>();
			int executeColumnNumber=getColumnNumberByColumnName(sheet, "Execute");
			int groupColumnNumber=getColumnNumberByColumnName(sheet, "Group");
			
			int rowCount=sheet.getLastRowNum();
			for(int rowNumber=1;rowNumber<=rowCount; rowNumber++){
				Map<String, String> testExecutionMap = new HashMap<String, String>();
				Row row=sheet.getRow(rowNumber);
				     
					   for(int i=0; i<=columnCount-1; i++){
							
							String xlGroupName=row.getCell(groupColumnNumber).getStringCellValue().trim();
							String xlExecuteCcndition=row.getCell(executeColumnNumber).getStringCellValue().trim();
							if(groupFlag.trim().equalsIgnoreCase("Y")){
							
								if(xlGroupName.contains(groupName) && xlExecuteCcndition.equals("Y")){
								  testExecutionMap.put(columnsArray[i], row.getCell(i).getStringCellValue()); 	
								}else{
									testExecutionMap=null;
									break;
								}
							
							}else{
								if(xlExecuteCcndition.equals("Y")){
									  testExecutionMap.put(columnsArray[i], row.getCell(i).getStringCellValue());	
								}else{
									testExecutionMap=null;
									break;
								}
							}
					    }
					   if(testExecutionMap!=null){
						   testCaseMapList.add(testExecutionMap);  
					   }
					 
						
			}
			
			return testCaseMapList;
	  }
		
		
	

	
	public static void getTestCaseInfo( String testCaseID) throws IOException{
		Workbook wbook=getDataBook(DataSheetPath);
		Sheet sheet=getDataSheet(wbook, TestDataSheetName);
        FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wbook);

        int rowNumber=getRowNumberByRowID(sheet, "TestCase_ID", testCaseID);
		int ColumnNumber=getColumnNumberByColumnName(sheet, "DataSheet");
		
		int envColumnNumber=getColumnNumberByColumnName(sheet, "ENV_ID");
		
		String DataInfo=sheet.getRow(rowNumber).getCell(ColumnNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();    
		
		String sheetName="";
		String rowID="";
		Row dataRow=null;
		if(DataInfo.trim().equals("")==false){
			String[] arrDataInfo=DataInfo.split(":");
			sheetName=arrDataInfo[0].trim();
			 rowID=arrDataInfo[1].trim();
			
		}
		 dataRow=sheet.getRow(rowNumber);
		 TestCaseInfoMap=new HashMap<String, String>();
			
		 TestCaseInfoMap.put("sheetName", sheetName); 
		 TestCaseInfoMap.put("rowID", rowID);
		 TestCaseInfoMap.put("ENV_ID",sheet.getRow(rowNumber).getCell(envColumnNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
		   
		  if(sheetName.trim().equals("")){
			  TestCaseDataMap=new HashedMap<String, String>();
			  int startColumnNumber=getColumnNumberByColumnName(sheet, "FieldName1");
			  int columnCount=dataRow.getLastCellNum();
			   for(int i=startColumnNumber; i<=columnCount-1; i=i+2){
					String dataKeyName=dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
					if(dataKeyName!=null && dataKeyName.trim().equals("")==false){
                        DataFormatter df = new DataFormatter();
                        Cell curCell = dataRow.getCell(i+1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (curCell.getCellTypeEnum()){
                            case NUMERIC:
                                TestCaseDataMap.put(dataKeyName, df.formatCellValue(curCell));
                                break;
                            case FORMULA:
                                String keyValue = df.formatCellValue(curCell, formulaEvaluator);
                                TestCaseDataMap.put(dataKeyName, df.formatCellValue(curCell, formulaEvaluator));
                                break;
                            case STRING:
                            default:
                                TestCaseDataMap.put(dataKeyName, curCell.getStringCellValue());
                                break;
                        }
                    }
                }
            }
	}
	
	public static void getTestCaseData(String sheetName, String rowID) throws IOException{
		Workbook wbook=getDataBook(DataSheetPath);
		Sheet sheet=getDataSheet(wbook, sheetName);
        FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wbook);
		MissingCellPolicy MCP=Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;
		int rowNumber=getRowNumberByRowID(sheet, "Row_ID", rowID);
			Row dataRow=sheet.getRow(rowNumber);
			Map<String, String> dataMap=new HashMap<String, String>();
			Row columnRow=sheet.getRow(0);
				for(int i=1; i<columnRow.getLastCellNum(); i++){
                    String dataKeyName=columnRow.getCell(i, MCP).getStringCellValue();
                    if(dataKeyName!=null && dataKeyName.trim().equals("")==false){
                        DataFormatter df = new DataFormatter();
                        Cell curCell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (curCell.getCellTypeEnum()) {
                            case NUMERIC:
                                dataMap.put(dataKeyName, df.formatCellValue(curCell));
                                break;
                            case FORMULA:
                                String keyValue = df.formatCellValue(curCell, formulaEvaluator);
                                dataMap.put(dataKeyName, df.formatCellValue(curCell, formulaEvaluator));
                                break;
                            case STRING:
                            default:
                                dataMap.put(dataKeyName, curCell.getStringCellValue());
                                break;
                        }
                    }
				}
				TestCaseDataMap=dataMap;
	}
	
	

	public static Map<String, String> getEnvironmentInfo(String envID) throws IOException{
		Workbook wbook=getDataBook(DataSheetPath);
		Sheet sheet=getDataSheet(wbook, EnvSheetName);
		Map<String, String> envMap= new HashMap<String, String>();
		Row firstRow= sheet.getRow(0);
		int columnCount=firstRow.getLastCellNum();
		int rowNumber=getRowNumberByRowID(sheet, "ENV_ID", envID);
		Row envRow=sheet.getRow(rowNumber);
		for(int i=0; i<=columnCount-1; i++){
			String envKeyName=firstRow.getCell(i).getStringCellValue();
			String envKeyValue=null;
			if(envKeyName!=null && envKeyName.trim().equals("")==false){
				envKeyValue=envRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
				envMap.put(envKeyName, envKeyValue);
			}
		}
		EnvironmentDataMap=envMap;
		return envMap;
	}

	public static Map<String, Map<String, String>> getLocatorInfo() throws IOException{
		Workbook wbook=getDataBook(DataSheetPath);
		Sheet sheet=getDataSheet(wbook, LocatorsSheetName);
		String locatorNameColumn="LocatorName";
		String LocatorValueColumn="LocatorValue";
		String LocatorTypeColumn="LocatorType";
		int locatorNameColumnNumber=getColumnNumberByColumnName(sheet,locatorNameColumn);
		int locatorValueColumnNumber=getColumnNumberByColumnName(sheet, LocatorValueColumn);
		int locatorTypeColumnNumber=getColumnNumberByColumnName(sheet, LocatorTypeColumn);
		Map<String, Map<String, String>> orMap= new HashMap<String, Map<String, String>>();
		
		for(int rowNumber = 1; rowNumber<= sheet.getLastRowNum(); rowNumber++){
			Row dataRow=sheet.getRow(rowNumber);
			String dataKeyName=dataRow.getCell(locatorNameColumnNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
			if(dataKeyName!=null && dataKeyName.trim().equals("")==false){
				String locatorValue=dataRow.getCell(locatorValueColumnNumber).getStringCellValue();
				String locatorType=dataRow.getCell(locatorTypeColumnNumber).getStringCellValue();
				Map<String, String> locatorDetailsMap=new HashedMap<String, String>();
				locatorDetailsMap.put(LocatorValueColumn, locatorValue);
				locatorDetailsMap.put(LocatorTypeColumn, locatorType);
				orMap.put(dataKeyName, locatorDetailsMap);
			}
		}
		LocatorDataMap=orMap;
		return orMap;
	}


	public static Row getRow(Sheet sheet, int rowNumber) throws IOException{
		return sheet.getRow(rowNumber);

	}

	public static int getColumnNumberByColumnName(Sheet sheet, String columnName) throws IOException{
		Row firstRow_Columns=sheet.getRow(0);
		int columnNumber=-1;
		int columnCount=firstRow_Columns.getLastCellNum();
		for(int i=0; i<=columnCount-1; i++){
			if(firstRow_Columns.getCell(i).getStringCellValue().toLowerCase().contains(columnName.toLowerCase())){
				columnNumber=i;
				break;
			}
		}
		return columnNumber;

	}

    public static int getColumnHeaderCount(Row firstRow_Columns){
		int columnHeaderCount=firstRow_Columns.getLastCellNum();
		return columnHeaderCount;
    }

	public static List<Integer> getRowNumberListByRowIDs(Sheet sheet, String columnName,String RowID) throws IOException{
		int rowCount=sheet.getLastRowNum();
		int columnNumber=getColumnNumberByColumnName(sheet, columnName);
		List<Integer> rowNumbersList=new ArrayList<Integer>();
		for(int i=1; i<=rowCount; i++){
			Cell cell=sheet.getRow(i).getCell(columnNumber);
			if(cell !=null && cell.getStringCellValue().equalsIgnoreCase(RowID)){
				rowNumbersList.add(i);
			}
		}
		return rowNumbersList;
	}
	public static int getRowNumberByRowID(Sheet sheet, String columnName,String RowID) throws IOException{
		int rowCount=sheet.getLastRowNum();
		int columnNumber=getColumnNumberByColumnName(sheet, columnName);
		int rowNumber=-1;
		for(int i=1; i<=rowCount; i++){
			Cell cell=sheet.getRow(i).getCell(columnNumber);
			if(cell !=null && cell.getStringCellValue().equalsIgnoreCase(RowID)){
				rowNumber=i;
				break;
			}
		}
		return rowNumber;
	}

	public static Sheet getDataSheet(Workbook workBook, String sheetName) throws IOException{
		return workBook.getSheet(sheetName);

	}
	public static Workbook getDataBook(String dataSheetPath) throws IOException{

		File xlFile=new File(dataSheetPath);
		FileInputStream fis=new FileInputStream(xlFile);
	
		String[] arrPath=dataSheetPath.split("\\.");
		String fileExt= arrPath[arrPath.length-1];
		if(fileExt.equalsIgnoreCase("xlsx")){
			return new XSSFWorkbook(fis);
		}else{
			return new HSSFWorkbook(fis);
		}
	}
}
