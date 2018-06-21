package com.qainfotech.automation.Junit_tatoc;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class App {

	public static WebDriver setDriverProp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\bhavyakhanna\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public void launchBrowser(WebDriver driver) {
		
		driver.get("http://10.0.1.86/tatoc");
	}

	public static void openBasic(WebDriver driver) {

		driver.findElement(By.tagName("a")).click();
	}

	public static void gridGate(WebDriver driver) {
		driver.findElement(By.className("greenbox")).click();
	}

	public static void frameDungeon(WebDriver driver) {
		driver.switchTo().frame(driver.findElement(By.id("main")));
		WebElement box1 = driver.findElement(By.id("answer"));
		String color1 = box1.getAttribute("class");
		driver.switchTo().frame(driver.findElement(By.id("child")));
		WebElement box2 = driver.findElement(By.id("answer"));
		String color2 = box2.getAttribute("class");
		while (!color1.equals(color2)) {
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
		anchors.get(anchors.size() - 1).click();
	}

	public static void dragAround(WebDriver driver) {
		WebElement from = driver.findElement(By.id("dragbox"));
		WebElement to = driver.findElement(By.id("dropbox"));
		Actions act = new Actions(driver);
		act.dragAndDrop(from, to).build().perform();
		driver.findElement(By.tagName("a")).click();
	}

	public static void popupWindow(WebDriver driver) {
		List<WebElement> popup = driver.findElements(By.tagName("a"));
		popup.get(0).click();
		String originalwindow = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);

		}
		driver.findElement(By.id("name")).sendKeys("Bhavya");
		driver.findElement(By.id("submit")).click();
		driver.switchTo().window(originalwindow);
		popup.get(1).click();
	}

	public static void cookieHandling(WebDriver driver) {
		List<WebElement> CookieHandlingLinks = driver.findElements(By.tagName("a"));
		CookieHandlingLinks.get(0).click();
		String token = driver.findElement(By.id("token")).getText();
		String newtoken = token.replace("Token: ", "");
		Cookie tatoc_cookie = new Cookie("Token", newtoken);
		driver.manage().addCookie(tatoc_cookie);
		CookieHandlingLinks.get(1).click();
	}

}
