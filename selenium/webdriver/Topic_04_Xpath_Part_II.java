package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_II {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By REGISTER_BUTTON = By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']");
	By FULL_NAME_FIELD = By.id("txtFirstname");
	By EMAIL_FIELD = By.id("txtEmail");
	By CONFIRM_EMAIL_FIELD = By.id("txtCEmail");
	By PASSWORD_FIELD = By.id("txtPassword");
	By CONFIRM_PASSWORD_FIELD = By.xpath("//input[@id='txtCPassword']");
	By PHONE_NUMBER_FIELD = By.xpath("//input[@id='txtPhone']");
	
	By INLINE_MESSAGE_OF_FULL_NAME_FIELD = By.xpath("//label[@id='txtFirstname-error']");
	By INLINE_MESSAGE_OF_EMAIL_FIELD = By.xpath("//label[@id='txtEmail-error']");
	By INLINE_MESSAGE_OF_CEMAIL_FIELD = By.xpath("//label[@id='txtCEmail-error']");
	By INLINE_MESSAGE_OF_PASSWORD_FIELD = By.xpath("//label[@id='txtPassword-error']");
	By INLINE_MESSAGE_OF_CPASSWORD_FIELD = By.xpath("//label[@id='txtCPassword-error']");
	By INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD = By.xpath("//label[@id='txtPhone-error']");
	

	
	@BeforeClass
	public void Get_The_Stage() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.manage().deleteAllCookies();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01_Register_With_Empty_Data() {
		
		 driver.findElement(FULL_NAME_FIELD).sendKeys("");
		 driver.findElement(EMAIL_FIELD).sendKeys("");
		 driver.findElement(CONFIRM_EMAIL_FIELD).sendKeys("");
		 driver.findElement(PASSWORD_FIELD).sendKeys("");
		 driver.findElement(CONFIRM_PASSWORD_FIELD).sendKeys("");
		 driver.findElement(PHONE_NUMBER_FIELD).sendKeys("");
		 driver.findElement(REGISTER_BUTTON).click();
		
		/*
		 * Verify error messages have been displayed in the Register Form
		 * */
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_FULL_NAME_FIELD).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_EMAIL_FIELD).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_CEMAIL_FIELD).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_PASSWORD_FIELD).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_CPASSWORD_FIELD).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD).getText(), "Vui lòng nhập số điện thoại.");
	}
	
	@Test
	public void TC_02_Register_With_Invalid_Email() {
		
		 driver.findElement(FULL_NAME_FIELD).sendKeys("Thanh Thanh Hoa");
		 driver.findElement(PASSWORD_FIELD).sendKeys("12345678a");
		 driver.findElement(EMAIL_FIELD).sendKeys("123@456@789");
		 driver.findElement(CONFIRM_EMAIL_FIELD).sendKeys("123@456@789");
		 driver.findElement(CONFIRM_PASSWORD_FIELD).sendKeys("12345678a");
		 driver.findElement(PHONE_NUMBER_FIELD).sendKeys("0989000989");
		 driver.findElement(REGISTER_BUTTON).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_FULL_NAME_FIELD).isEmpty());
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_EMAIL_FIELD).getText(),  "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_CEMAIL_FIELD).getText(), "Email nhập lại không đúng");
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_PASSWORD_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_CPASSWORD_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD).isEmpty());
	}
	
	@Test
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		
		 driver.findElement(FULL_NAME_FIELD).sendKeys("Thanh Thanh Hoa");
		 driver.findElement(EMAIL_FIELD).sendKeys("hoa@email.com");
		 driver.findElement(CONFIRM_EMAIL_FIELD).sendKeys("hoa01@email.com");
		 driver.findElement(PASSWORD_FIELD).sendKeys("12345678a");
		 driver.findElement(CONFIRM_PASSWORD_FIELD).sendKeys("12345678a");
		 driver.findElement(PHONE_NUMBER_FIELD).sendKeys("0989000989");
		 driver.findElement(REGISTER_BUTTON).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_FULL_NAME_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_EMAIL_FIELD).isEmpty());
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_CEMAIL_FIELD).getText(), "Email nhập lại không đúng");
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_PASSWORD_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_CPASSWORD_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD).isEmpty());
	}
	
	@Test
	public void TC_04_Register_With_Password_Less_Than_6_Characters() {
		
		 driver.findElement(FULL_NAME_FIELD).sendKeys("Thanh Thanh Hoa");
		 driver.findElement(EMAIL_FIELD).sendKeys("hoa@email.com");
		 driver.findElement(CONFIRM_EMAIL_FIELD).sendKeys("hoa@email.com");
		 driver.findElement(PASSWORD_FIELD).sendKeys("12345");
		 driver.findElement(CONFIRM_PASSWORD_FIELD).sendKeys("12345");
		 driver.findElement(PHONE_NUMBER_FIELD).sendKeys("0989000989");
		 driver.findElement(REGISTER_BUTTON).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_FULL_NAME_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_EMAIL_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_CEMAIL_FIELD).isEmpty());
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_PASSWORD_FIELD).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_CPASSWORD_FIELD).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD).isEmpty());
	}
	
	@Test
	public void TC_05_Register_With_Incorrect_Confirm_Password() {
		 
		 driver.findElement(FULL_NAME_FIELD).sendKeys("Thanh Thanh Hoa");
		 driver.findElement(EMAIL_FIELD).sendKeys("hoa@email.com");
		 driver.findElement(CONFIRM_EMAIL_FIELD).sendKeys("hoa@email.com");
		 driver.findElement(PASSWORD_FIELD).sendKeys("12345678a");
		 driver.findElement(CONFIRM_PASSWORD_FIELD).sendKeys("12345678b");
		 driver.findElement(PHONE_NUMBER_FIELD).sendKeys("0989000989");
		 driver.findElement(REGISTER_BUTTON).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_FULL_NAME_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_EMAIL_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_CEMAIL_FIELD).isEmpty());
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_PASSWORD_FIELD).isEmpty());
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_CPASSWORD_FIELD).getText(), "Mật khẩu bạn nhập không khớp");
		//Assert.assertTrue(driver.findElements(INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD).isEmpty());
	}
	
	@Test
	public void TC_06_Register_With_Invalid_Phone_Number() {
		 
		 driver.findElement(FULL_NAME_FIELD).sendKeys("Thanh Thanh Hoa");
		 driver.findElement(EMAIL_FIELD).sendKeys("hoa@email.com");
		 driver.findElement(CONFIRM_EMAIL_FIELD).sendKeys("hoa@email.com");
		 driver.findElement(PASSWORD_FIELD).sendKeys("12345678a");
		 driver.findElement(CONFIRM_PASSWORD_FIELD).sendKeys("12345678b");
		 driver.findElement(PHONE_NUMBER_FIELD).sendKeys("0987666776999");
		 driver.findElement(REGISTER_BUTTON).click();
		
		/*
		 * Verify error messages will be shown
		 * */
		 
		Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD).getText(), "Số điện thoại phải từ 10-11 số.");
		
		/*
		 * 
		 * */
		
		driver.findElement(PHONE_NUMBER_FIELD).clear();
		driver.findElement(PHONE_NUMBER_FIELD).sendKeys("123");
		 driver.findElement(REGISTER_BUTTON).click();
		 
		 /*
			 * Verify error messages will be shown
			 * */
			 
			Assert.assertEquals(driver.findElement(INLINE_MESSAGE_OF_PHONE_NUMBER_FIELD).getText()
					, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
