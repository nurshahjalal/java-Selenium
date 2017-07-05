package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;
import com.sandata.utilities.XLReader;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage {

	Wrapper baseObj = new Wrapper();

	public NavigationPage navigateToClient() {
		BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		navigateTo(ModuleName.DATAENTRY).navigateTo(ModuleName.CLIENT);
		return this;
	}

	public NavigationPage navigateToEmployee() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
		navigateTo(ModuleName.DATAENTRY).navigateTo(ModuleName.EMPLOYEE);
		return this;
	}

    public NavigationPage navigationToManageUsers(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        navigateTo(ModuleName.SECURITY).navigateTo(ModuleName.MANAGEUSERS);
        return this;
    }
    public NavigationPage navigationToManageRoles(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        navigateTo(ModuleName.SECURITY).navigateTo(ModuleName.MANAGEUSEROLES);
        return this;

    }


    public NavigationPage navigateToVisitMaintenance(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        navigateTo(ModuleName.VISTMAINTENANCE);
        return this;
    }
    public NavigationPage navigateToDashboard(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        navigateTo(ModuleName.DASHBOARD);
        return this;
    }
	private NavigationPage navigateTo(ModuleName moduleName){
        baseObj.getElement(moduleName.getLocatorName()).click();
        baseObj.waitForBrowserToLoadCompletely();
        return this;
	}

    public NavigationPage navigateToSecurity(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
	    navigateTo(ModuleName.SECURITY);
	    return this;
    }

    public NavigationPage navigateToChangePassword(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        navigateTo(ModuleName.CHANGEPASSWORD);
        return this;
    }

    public NavigationPage navigateToSanTraxManualPage(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        navigateTo(ModuleName.MANUAL);
        baseObj.waitForBrowserToLoadCompletely();
        baseObj.switchFocusOnWindowTabByPartialURL("User_Guide");
        return this;
    }

	public NavigationPage browserToPage(String page){
	    if ("Search Clients".equalsIgnoreCase(page)) {
            baseObj.getDriver().navigate().to(XLReader.EnvironmentDataMap.get("Application_URL")+"/VM/DataEntry/Clients");
        } else if ("Search Employees".equalsIgnoreCase(page)){
            baseObj.getDriver().navigate().to(XLReader.EnvironmentDataMap.get("Application_URL")+"/VM/DataEntry/Employees");
        }
        baseObj.waitForBrowserToLoadCompletely();
	    return this;
    }

    public void navigateToDataManagement() {
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        navigateTo(ModuleName.DATAMANAGEMENT);
    }

    public enum ModuleName {
        DASHBOARD("Dashboard","Navigation_Dashboard_ITM"),
        DATAENTRY("Data Entry", "Navigation_DataEntry_ITM"),
		CLIENT("Clients","Navigation_Clients_ITM"),
		EMPLOYEE("Employees","Navigation_Employees_ITM"),
        VISTMAINTENANCE("Visit Maintenance","Navigation_VisitMaintenance_ITM"),
        DATAMANAGEMENT("Data Management","Navigation_DataManagement_ITM"),
        SCHEDULES("Schedules","Navigation_Schedules_ITM"),
        SCHEDULING("Scheduling","Navigation_Scheduling_ITM"),
        REPORTnEXPORT("Report and Export","Navigation_ReportAndExport_ITM"),
        REPORTS("Reports","Navigation_Reports_LNK"),
        EXPORTS("Exports","Navigation_Exports_LNK"),
        EXPORTMAINTENANCE("Export Maintenance","Navigation_ExportsMaintenance_LNK"),
        EXPORTPREVIEW("Export Preview","Navigation_ExportsRun_LNK"),
        SECURITY("Security","Navigation_Security_ITM"),
        FEATUREMANAGEMENT("Feature Management","Navigation_FeatureManagement_ITM"),
        MANUAL("Santrax Manual","Navigation_SantraxManual_ITM"),
        CHANGEPASSWORD("Change Password","Navigation_ChangePassword_ITM"),
        MANAGEUSERS("Manage Users","Navigation_ManageUsers_ITM"),
        MANAGEUSEROLES("Manager Users Roles","Navigation_ManageUserRoles_ITM")
        ;

		private final String label;
		private final String locatorName;

		ModuleName(String label, String locatorName){
			this.label = label; this.locatorName = locatorName;
		}

		public String getLabel(){
			return this.label;
		}
		public String getLocatorName() { return this.locatorName;}

	}
	
	public NavigationPage navigateToDashBoard(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Navigation_Dashboard_SJM");
        return this;
    }
	
	public void expandReportAndExportTab(){
		 baseObj.click("Navigation_Repor&Export_SJM");
	}
	public NavigationPage navigateToReport(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.click("Navigation_Report_SJM");
        return this;
    }

}
