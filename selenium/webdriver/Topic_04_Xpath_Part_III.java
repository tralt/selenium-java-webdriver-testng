package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_III {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void Get_The_Stage() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public static String getRandomString(int length){
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }
	
	// get random email
    String emailAccount = getRandomString(6) + "@podman.com";
	
	//@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		
		/*
		 * Open the URL, click on My Account to direct to the Sign In page
		 * And then enter blank values for the User Name field and the Password field and click on the Login button
		 * */
		 driver.get("http://live.techpanda.org/");
		 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		 driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
		 driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		 driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		 driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		/*
		 * Verify error messages have been displayed in the Login Page
		 * */
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
	}
	
	//@Test
	public void TC_02_Login_With_Invalid_Email() {
			
			/*
			 * Open the URL, click on My Account to direct to the Sign In page
			 * And then enter an invalid email for the User Name field
			 * */
			 driver.get("http://live.techpanda.org/");
			 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
			 driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
			 driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@777bnn");
			 driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
			 driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			/*
			 * Verify error messages have been displayed in the Login Page
			 * */
			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText()
					, "Please enter a valid email address. For example johndoe@domain.com.");
		}
	
	//@Test
	public void TC_03_Login_With_Password_Less_Than_6_Characters() {
			
			/*
			 * Open the URL, click on My Account to direct to the Sign In page
			 * And then enter an invalid password for the Password field
			 * */
			 driver.get("http://live.techpanda.org/");
			 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
			 driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
			 driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
			 driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
			 driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			/*
			 * Verify error messages have been displayed in the Login Page
			 * */
			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText()
					, "Please enter 6 or more characters without leading or trailing spaces.");
		}
	
	//@Test
	public void TC_04_Login_With_Password_Less_Than_6_Characters() {
			
			/*
			 * Open the URL, click on My Account to direct to the Sign In page
			 * And then enter a correct email for the User Name field and an incorrect password
			 * */
			 driver.get("http://live.techpanda.org/");
			 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
			 driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
			 driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
			 driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
			 driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			/*
			 * Verify error messages have been displayed in the Login Page
			 * */
			Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages .error-msg span")).getText()
					, "Invalid login or password.");
		}
	
	@Test
	public void TC_05_Create_A_New_Account() {
			
			/*
			 * Open the URL, click on My Account to direct to the Sign In page
			 * And then the user clicks on the Create An Account button to register an account
			 * And then the user will enter correct value for all of the fields
			 * And she registers successfully
			 * */
			 driver.get("https://live.techpanda.org/");
			 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
			 driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
			 driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
			 driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Tra");
			 driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Thanh");
			 driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Le");
			 driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(this.emailAccount);
			 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678a");
			 driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("12345678a");
			 driver.findElement(By.xpath("//span[text()='Register']")).click();
			
			/*
			 * Register successfully
			 * */
			Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages li.success-msg span")).getText()
					, "Thank you for registering with Main Website Store.");
			
			Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
			
			
			Assert.assertEquals(driver.findElement(By.cssSelector(".welcome-msg .hello")).getText(), "Hello, Tra Thanh Le!");
			
			Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Change Password']//parent::p")).getText()
					, "Tra Thanh Le\n" + this.emailAccount+"\n" + "Change Password");
			
			/*
			 * She logout, and check the current page is the home page
			 * */
			
			driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
			 driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Log Out']")).click();
			 
			 // verify this is home page
			 Assert.assertTrue(driver.getCurrentUrl().contains("http://live.techpanda.org/index.php/"));
		}
	
	@Test
	public void TC_06_Login_With_Valid_Email_And_Password() {

		 driver.get("https://live.techpanda.org/");
		 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		 driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
		 driver.findElement(By.xpath("//input[@id='email']")).sendKeys(this.emailAccount);
		 driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345678a");
		 driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			/*
			 * Login successfully
			 * */
			
			Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
			
			Assert.assertEquals(driver.findElement(By.cssSelector(".welcome-msg .hello")).getText(), "Hello, Tra Thanh Le!");
			
			Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Change Password']//parent::p")).getText()
					, "Tra Thanh Le\n" + emailAccount+"\n" + "Change Password");
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
