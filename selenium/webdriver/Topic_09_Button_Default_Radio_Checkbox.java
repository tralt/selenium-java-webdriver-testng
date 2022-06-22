package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Default_Radio_Checkbox {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	
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
	
	//@Test
	public void TC_01() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By luggedCheckBox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By headtedCheckBox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By towarCheckBox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
		
		checkToCheckBox(luggedCheckBox);
		checkToCheckBox(headtedCheckBox);
		
		// Verify 
		Assert.assertTrue(isElementSelected(luggedCheckBox));
		Assert.assertTrue(isElementSelected(headtedCheckBox));
		
		Assert.assertFalse(isElementDisabled(towarCheckBox));
		
		System.out.println("Done [1] ");
		
		
		uncheckToCheckBox(luggedCheckBox);
		uncheckToCheckBox(headtedCheckBox);
		
		// Verify
		Assert.assertFalse(isElementSelected(luggedCheckBox));
		Assert.assertFalse(isElementSelected(headtedCheckBox));
		Assert.assertFalse(isElementSelected(towarCheckBox));
		
	}
	
	@Test
	public void TC_02_Custome_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By winterCheckBox = By.cssSelector("input[value='Winter']");
		
		clickByJavascript(winterCheckBox);
		sleepInSecond(2);
		
		Assert.assertTrue(isElementSelected(winterCheckBox));
	}
	
	@Test
	public void TC_03_Custome_Radio_Button_Google_Doc(){
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By haiPhongCity = By.xpath("//span[text() ='Hải Phòng']/ancestor::label/div/div[1]");
		By quangNinhCity =  By.xpath("//span[text() ='Quảng Ninh']/ancestor::label/div/div[1]");
		By daNangCity = By.xpath("//span[text() ='Đà Nẵng']/ancestor::label/div/div[1]");
		
		Assert.assertFalse(driver.findElement(haiPhongCity).isSelected());
		Assert.assertFalse(driver.findElement(quangNinhCity).isSelected());
		Assert.assertFalse(driver.findElement(daNangCity).isSelected());
		
		//scrollToElement(haiPhongCity);
		driver.findElement(haiPhongCity).click();
		sleepInSecond(2);
		
		driver.findElement(quangNinhCity).click();
		sleepInSecond(2);
		
		driver.findElement(daNangCity).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(haiPhongCity).isSelected());
		Assert.assertFalse(driver.findElement(quangNinhCity).isSelected());
		Assert.assertTrue(driver.findElement(daNangCity).isSelected());
	}
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}
	
	public void clickByJavascript(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	
	public void checkToCheckBox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckBox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementDisabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		}else {
			return false;
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
