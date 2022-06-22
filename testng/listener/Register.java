package listener;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Register {
	
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
	
	@Test
	public void TC_01_Register() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(emailTextBox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextBox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
