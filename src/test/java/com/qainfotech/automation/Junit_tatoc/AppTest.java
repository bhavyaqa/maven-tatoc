package com.qainfotech.automation.Junit_tatoc;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public  class AppTest extends App {
	static WebDriver driver = App.setDriverProp();
	@BeforeClass
	
	public void launchBrowser() {
		driver.get("http://10.0.1.86/tatoc");
	}

	@Test
	public void basic_selenium_automation() {
		App.openBasic(driver);
		App.gridGate(driver);
		App.frameDungeon(driver);
		App.dragAround(driver);
		App.popupWindow(driver);
		App.cookieHandling(driver);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}

}
