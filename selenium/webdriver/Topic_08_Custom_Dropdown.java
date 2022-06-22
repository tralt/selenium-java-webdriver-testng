package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	
		driver = new FirefoxDriver();
		
		// Khởi tạo sau khi driver này được sinh ra
		// JavascriptExecutor / WebDriverWait/ Actions ...
		jsExecutor = (JavascriptExecutor)driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public static String getRandomNumber(int length){
        return RandomStringUtils.randomNumeric(length);
    }
	
	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("#number-button", "ul#number-menu li div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "15");
		
		selectItemInCustomDropdown("#number-button", "ul#number-menu li div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "3");
	}
	
	
	@Test
	public void TC_04_Angular_Enter_To_DropDown() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("#number-button", "ul#number-menu li div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "15");
		
		selectItemInCustomDropdown("#number-button", "ul#number-menu li div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "3");
	}
	
	
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		// Step 1: click on a dropdown element
				driver.findElement(By.cssSelector(parentLocator)).click();
				sleepInSecond(2);
				
				// Step 2: Wait until the list present
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
				
				// Step 3: Find the element that you wanna click
				List<WebElement> lstItems = driver.findElements(By.cssSelector(childLocator));
				for (WebElement item : lstItems) {
					String actual_textString = item.getText();
					 if (actual_textString.equalsIgnoreCase(expectedTextItem)) {
						 
						 jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
						 item.click();
						 break;
					}
				}
	}
	
	public void enterToCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		// Step 1: click on a dropdown element
				driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
				sleepInSecond(2);
				
				// Step 2: Wait until the list present
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
				
				// Step 3: Find the element that you wanna click
				List<WebElement> lstItems = driver.findElements(By.cssSelector(childLocator));
				for (WebElement item : lstItems) {
					String actual_textString = item.getText();
					 if (actual_textString.equalsIgnoreCase(expectedTextItem)) {
						 
						 jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
						 item.click();
						 break;
					}
				}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
