package com.qainfotech.automation.Junit_tatoc;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;



public class AppTest{
	static WebDriver driver;
	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\bhavyakhanna\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
	}
	
	@Test
	public void basic_selenium_automation() {
driver.findElement(By.tagName("a")).click();
		
		
		//grid gate
		driver.findElement(By.className("greenbox")).click();
		
		
		//frame dungeon
		driver.switchTo().frame(driver.findElement(By.id("main")));
		WebElement box1 = driver.findElement(By.id("answer"));
		String color1 = box1.getAttribute("class");
		driver.switchTo().frame(driver.findElement(By.id("child")));
		WebElement box2 = driver.findElement(By.id("answer"));
		String color2 = box2.getAttribute("class");
		while(!color1.equals(color2)) {
			 driver.switchTo().defaultContent();
			 driver.switchTo().frame(driver.findElement(By.id("main")));
			 driver.findElement(By.tagName("a")).click();
			 driver.switchTo().frame(driver.findElement(By.id("child")));
			 box2 = driver.findElement(By.id("answer"));
			 color2 = box2.getAttribute("class");
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("main")));
		List<WebElement> anchors = driver.findElements(By.tagName("a"));
		anchors.get(anchors.size()-1).click();
		
		
		
		// Drag around
		WebElement from = driver.findElement(By.id("dragbox"));
		WebElement to = driver.findElement(By.id("dropbox"));
        Actions act=new Actions(driver);
        act.dragAndDrop(from, to).build().perform();
        driver.findElement(By.tagName("a")).click();
		
        
        //popup windows
		
        List<WebElement> popup = driver.findElements(By.tagName("a"));
        popup.get(0).click();
        String originalwindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) 
        {
        driver.switchTo().window(handle); 
        
        }
    	driver.findElement(By.id("name")).sendKeys("Bhavya");
    	driver.findElement(By.id("submit")).click();
    	driver.switchTo().window(originalwindow);
        popup.get(1).click();
		
        
        //cookie handling
        List<WebElement> CookieHandlingLinks = driver.findElements(By.tagName("a"));
        CookieHandlingLinks.get(0).click();
        String token = driver.findElement(By.id("token")).getText();
        String newtoken = token.replace("Token: ","");
		Cookie tatoc_cookie = new Cookie("Token",newtoken);
        driver.manage().addCookie(tatoc_cookie);
        CookieHandlingLinks.get(1).click();
		
	}
	
	
	@AfterClass
	public static void closeBrowser(){
		driver.quit();
	}
	
}
