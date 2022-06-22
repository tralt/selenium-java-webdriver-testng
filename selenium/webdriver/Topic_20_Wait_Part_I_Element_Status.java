package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_I_Element_Status {
	

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
	
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");

	}
	
	public void TC_01_Visible() {
		// Visible là trạng thái có trong DOM và có trong UI
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))); // Pass
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed()); // Pass
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login_source']"))); // Fail vi cos trong DOM nhung khong co trong UI
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='login_source']")).isDisplayed()); // Fail
	} 
	
	public void TC_02_Invisible_In_DOM() {
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		// Invisible: Không có trong UI và Có trong DOM (optional)
		// Kết quả chạy như nhau nhưng thời gian chạy mỗi case khác nhau
		// ~1s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		// không hiển thị -> Pass -> ~1s
		
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
	}
	
	public void TC_03_Invisible_Not_In_DOM() {
		// Không có trên UI và KHÔNG có trong DOM (optional)
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		// Chạy mất 15s => chạy mất nhiều thời gian vì element không có trong DOM nên máy tự động tìm đi tìm lại element cho đến hết 15s 
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		// Không hiển thị -> Faild -> 20s => Chạy mất 20s ở đoạn này vì findElement phụ thuộc vào implicitlyWait (20s) 
		// Case này faild vì element không có trong DOM (do đã close) nên không thể check được isDisplayed() hay isVisible()
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
	}
	
	public void TC_04_Presence() {
		// Bắt buộc có trong DOM , có trên UI => pass 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		// Bắt buộc có trong DOM , khong có trên UI => pass 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	@Test
	public void TC_04_Staleness() {
		// Bật registration form 
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		// Tại thời điểm này element này đang có trong DOM
		WebElement confirmationEmailAddressTextBox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		// Đóng Registration form 
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		// Wait cho đến khi confirmationEmailAddressTextBox không còn trong DOM nữa 
		// Vì một lí do nào đó mình cần wait sao cho element không còn tồn tại trong DOM nữa 
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailAddressTextBox));
		
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
