package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Action_Part_I {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	Alert alert;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
	
		driver = new FirefoxDriver();
		
		// Khởi tạo sau khi driver này được sinh ra
		// JavascriptExecutor / WebDriverWait/ Actions ...
		jsExecutor = (JavascriptExecutor)driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		WebElement yourAgeText = driver.findElement(By.id("age"));
		
		// Hover the mouse on the text box
		action.moveToElement(yourAgeText).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}
	
	public void TC_02_Click_And_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> lstNumbers = driver.findElements(By.cssSelector("ol#selectable li"));
		action.clickAndHold(lstNumbers.get(0)).moveToElement(lstNumbers.get(3)).release().perform(); // realse() la hanh dong nha chuot trai
		sleepInSecond(3);
		
		List<WebElement> lstNumberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(lstNumberSelected.size(), 4);
	}
	
	@Test
	public void TC_03_Click_And_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// 1, 5 va 11
		// Nhan phim Ctrl xuong (Chua nha ra)
		// Click vao 1
		// CLick vao 5
		// Click vao 11
		// Thuc thi cac cau lenh
		// Nha phim Ctrl
		
		List<WebElement> lstNumbers = driver.findElements(By.cssSelector("ol#selectable li"));
		
		Keys controlKey;
		if (osName.contains("windows") || osName.contains("nux")) {
			controlKey = Keys.CONTROL;
		}else {
			controlKey = Keys.COMMAND;
		}
		
		action.keyDown(controlKey).perform();
		
		action
			.click(lstNumbers.get(0))
			.click(lstNumbers.get(4))
			.click(lstNumbers.get(10)).perform();
		
		action.keyUp(controlKey).perform();
		
		sleepInSecond(3);
		
		List<WebElement> lstNumberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(lstNumberSelected.size(), 3);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
