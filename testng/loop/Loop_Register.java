package loop;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Loop_Register {

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriver driver;
	
	By emailTextBox = By.xpath("//*[@id='email']");
	By passwordTextBox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	String loginURL, userID, userPassword, email;
	
	@BeforeMethod
	public void beforMethod() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver(); 
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		loginURL = "https://demo.guru99.com/v4/";
		email = "miriamthanh"+ getRandomNumber(3) + "@live.com";
		
	}
	
	// invocationCount = số lần chạy lại của test case và chỉ có thể áp dụng cho test case 
	@Test (invocationCount = 3)
	public void Register() {
		driver.get(loginURL);
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		System.out.println("User ID: "+userID);
		System.out.println("Password: "+userPassword);
	}
	
	public static String getRandomNumber(int length){
        return RandomStringUtils.randomNumeric(length);
    }
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
