package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_I {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void Get_The_Stage() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}
	
	@Test
	public void TC_01_ValidateCurrentUrl() {
		
		WebElement LOGIN_BUTTON = driver.findElement(By.cssSelector("button.submit"));
		
		LOGIN_BUTTON.isEnabled();
		LOGIN_BUTTON.click();
		LOGIN_BUTTON.isDisplayed();
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
