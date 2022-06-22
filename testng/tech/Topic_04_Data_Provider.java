package tech;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_04_Data_Provider {
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	WebDriver driver;
	
	By emailTextBox = By.xpath("//*[@id='email']");
	By passwordTextBox = By.xpath("//*[@id='password']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@BeforeClass
	public void beforClass() {
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test (dataProvider = "user_pass")
	public void TC_01_Login_System(String userName, String password) throws InterruptedException {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(emailTextBox).sendKeys(userName);
		driver.findElement(passwordTextBox).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	@DataProvider (name = "user_pass")
	public Object[][] UserAndPassworData() {
	    return new Object[][] {
	    	{"selenium_11_01@gmail.com", "111111"},
	    	{"selenium_11_02@gmail.com", "111111"},
	    	{"selenium_11_03@gmail.com", "111111"}
	    };
	  }
	
	// Sinh ra 3 test case
	// Co the khai bao nhieu Data Provider
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
