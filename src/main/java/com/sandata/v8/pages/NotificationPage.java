package com.sandata.v8.pages;

import com.relevantcodes.extentreports.LogStatus;
import com.sandata.core.BaseTest;
import com.sandata.core.Wrapper;

public class NotificationPage {

	Wrapper baseObj = new Wrapper();

	public NotificationPage validateMessage(String messageFieldName){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.validateInnerText("Notification_Message_LBL", messageFieldName);
        baseObj.holdOn(3000);
        return this;
	}

	public NotificationPage confirmOK(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.holdOn(3000);
        baseObj.click("Confirmation_OK_BTN");
	    return this;
    }
	
	public NotificationPage cancel(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.holdOn(3000);
        baseObj.click("Confirmation_Cancel_BTN");
	    return this;
    }
	
    public NotificationPage confirmDelete(){
        BaseTest.test.log(LogStatus.INFO,"", "<b style= "+'"'+"font-size: 15px;color:blue"+'"'+">"+BaseTest.testStep+"</b>");
        baseObj.holdOn(3000);
        baseObj.click("Notification_Delete_BTN");
        baseObj.waitForBrowserToLoadCompletely();
        baseObj.holdOn(5000);
        return this;
    }

}
