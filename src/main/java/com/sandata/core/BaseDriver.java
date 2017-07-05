package com.sandata.core;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sandata.utilities.XLReader;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseDriver extends Wrapper {
	public static Properties GlobalVariables;

	public static void GetDriver() {

		try {
			GlobalVariables = new Properties();
			GlobalVariables.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			String browserName = GlobalVariables.getProperty("Browser",XLReader.EnvironmentDataMap.get("Browser"));

			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\BrowserDrivers\\chromedriver.exe");

				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				options.addArguments("test-type");

				driver = new ChromeDriver(options);

				driver.manage().window().maximize();
				
			}

			else if (browserName.equalsIgnoreCase("ie")) {

				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "//BrowserDrivers//IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();
				
			}

			else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//BrowserDrivers//geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				
			}

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.get(GlobalVariables.getProperty("Application_URL",XLReader.EnvironmentDataMap.get("Application_URL")));
		// return driver;
	}

	public static void StopDriver() {

		try {
			if (driver != null) {
				driver.close();
				driver.quit();
				driver = null;
			
			}
		} catch (Exception ignore) {
			System.out.println("Getting Exception while closing the driver: " + ignore);
			
		}

	}
}
