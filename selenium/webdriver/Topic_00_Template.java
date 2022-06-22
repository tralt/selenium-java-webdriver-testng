package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01() {
		
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		// Get Text
		System.out.println(driver.findElement(By.cssSelector("div.registered-users h2")));
		
		
		// isDisplayed
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Already registered?']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='ALREADY REGISTERED?']")).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
