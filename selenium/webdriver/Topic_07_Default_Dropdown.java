package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
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

public class Topic_07_Default_Dropdown {
	
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
	public void TC_01_Rode() {
		driver.get("https://rode.com/en/support/where-to-buy");
		
		select = new Select(driver.findElement(By.cssSelector("#country")));
		
		// Ko support multiple select
		Assert.assertFalse(select.isMultiple());
		
		// Select gia tri Vietnam
		select.selectByVisibleText("Vietnam");		
		
		// Verify Vietnam selected success
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		driver.findElement(By.cssSelector("#page-container #map button.btn.btn-default")).click();
		sleepInSecond(3);
		
		// Assert the quantity of saler
		Assert.assertEquals(driver.findElements(By.cssSelector("div.dealer_branch")).size(), 35);
	}
	
	@Test
	public void TC_02_NopCommerce() {
		
		String firstname = "Miriam";
		String lastname = "Le";
		String day = "10";
		String month = "December";
		String year = "2000";
		String email = "miriamthanh"+ getRandomNumber(3) + "@live.com";
		String password = "12345678a";
	
		
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		
		// Date
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText(day);
		
		// Month
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		
		// Year
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
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
