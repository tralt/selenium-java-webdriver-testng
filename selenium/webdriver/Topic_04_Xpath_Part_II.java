package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_II {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	/*
	 WebElement REGISTER_BUTTON = driver.findElement(By.xpath("//div[@id='uldangky']//button[text()='ĐĂNG KÝ']"));
	 WebElement FULL_NAME_FIELD = driver.findElement(By.xpath(""));
	 WebElement INLINE_MESSAGE_OF_FULL_NAME_FIELD = driver.findElement(By.xpath("//label[@id='txtFirstname-error']"));
	 WebElement INLINE_MESSAGE_OF_EMAIL_FIELD = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
	 WebElement INLINE_MESSAGE_OF_CEMAIL_FIELD = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
	 WebElement INLINE_MESSAGE_OF_PASSWORD_FIELD = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
	 WebElement INLINE_MESSAGE_OF_CPASSWORD_FIELD = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
	 WebElement INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
	 */
	
	@BeforeClass
	public void Get_The_Stage() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_01_Register_With_Empty_Data() {
		
		/*
		 * Open the URL
		 * */
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		 /*
		  * User click on the Register button
		  * */
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		/*
		 * Verify error messages have been displayed in the Register Form
		 * */
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
	}
	
	//@Test
	public void TC_02_Register_With_Invalid_Email() {
		
		/*
		 * Open the URL, enter values
		 * */
		 driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		 driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Thanh Tra");
		 driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0989000989");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtFirstname-error']")).isEmpty());
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),  "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtPassword-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtCPassword-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtPhone-error']")).isEmpty());
	}
	
	//@Test
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		
		/*
		 * Open the URL, enter values
		 * */
		 driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		 driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Thanh Tra");
		 driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("tratra@email.com");
		 driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("tratra01@email.com");
		 driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0989000989");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtFirstname-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtEmail-error']")).isEmpty());
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtPassword-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtCPassword-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtPhone-error']")).isEmpty());
	}
	
	//@Test
	public void TC_04_Register_With_Password_Less_Than_6_Characters() {
		
		/*
		 * Open the URL, enter values
		 * */
		 driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		 driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Thanh Tra");
		 driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("tratra@email.com");
		 driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("tratra@email.com");
		 driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
		 driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
		 driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0989000989");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtFirstname-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtEmail-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtCEmail-error']")).isEmpty());
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtPhone-error']")).isEmpty());
	}
	
	//@Test
	public void TC_05_Register_With_Incorrect_Confirm_Password() {
		
		/*
		 * Open the URL, enter values
		 * */
		 driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		 driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Thanh Tra");
		 driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("tratra@email.com");
		 driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("tratra@email.com");
		 driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345678b");
		 driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0989000989");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtFirstname-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtEmail-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtCEmail-error']")).isEmpty());
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtPassword-error']")).isEmpty());
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
		Assert.assertTrue(driver.findElements(By.xpath("//label[@id='txtPhone-error']")).isEmpty());
	}
	
	@Test
	public void TC_06_Register_With_Invalid_Phone_Number() {
		
		/*
		 * Open the URL, enter values
		 * */
		 driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		 driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Thanh Tra");
		 driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("tratra@email.com");
		 driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("tratra@email.com");
		 driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987666776999");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		/*
		 * 
		 * */
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		 
		 /*
			 * Verify error messages will be shown
			 * */
			 
			Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
