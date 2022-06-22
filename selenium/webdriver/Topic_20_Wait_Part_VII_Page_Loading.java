package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_VII_Page_Loading {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
	
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
	}
	
	public void TC_01_Only_Explicit() {
		driver.get("https://api.orangehrm.com/");
		
		explicitWait = new WebDriverWait(driver, 120);
		
		// Wait cho spinner invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#project h1")).getText(), "OrangeHRM REST API Documentation");
		
	}
	
	public void TC_02_Only_Implicit() {
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		driver.get("https://api.orangehrm.com/");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#project h1")).getText(), "OrangeHRM REST API Documentation");
		
	}
	
	public void TC_03_API_Page_Ready() {
		driver.get("https://api.orangehrm.com/");
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#project h1")).getText(), "OrangeHRM REST API Documentation");
		
	}
	
	public void TC_04_UI_Page_Ready() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		
		//Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
	}
	
	@Test
	public void TC_05_Test_Project_Page_Ready() {
		driver.get("https://blog.testproject.io/");
		
		// Hover mouse to search text box
		action.moveToElement(driver.findElement(By.cssSelector("#search-2 input.search-field"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		// Handle Popup
		if (driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}

		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("selenium");
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> firstAllPostTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement postTitle : firstAllPostTitle) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
	}
	
	// Only JQuery
	public boolean isJQueryLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 120);
		jsExecutor = (JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		return explicitWait.until(jQueryLoad);
		
	}
	
	// JQuery + Javascript
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 120);
		jsExecutor = (JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean)jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean)jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
