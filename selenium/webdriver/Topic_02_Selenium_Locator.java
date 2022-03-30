package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;

public class Topic_02_Selenium_Locator {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}
	
	@Test
	public void Test_Case_01() {
		// find element by ID
		driver.findElement(By.id("email")).sendKeys("che@gmail.com");
		
		// find element by class
		driver.findElement(By.className("fb_logo"));
		
		// find element by Name
		driver.findElement(By.name("pass")).sendKeys("12345678s");
		
		// find element by Tagname
		driver.findElement(By.tagName("input"));
		
		// find element by Link text
		driver.findElement(By.linkText("Tiếng Việt"));
		
		// find element by partial Link Text
		driver.findElement(By.partialLinkText("Tiếng"));
		driver.findElement(By.partialLinkText("Việt"));
		driver.findElement(By.partialLinkText("ếng Việt"));
		
		// find element by Css
		driver.findElement(By.cssSelector("input#email"));
		driver.findElement(By.cssSelector("#email"));
		driver.findElement(By.cssSelector("input[id='pass']"));
		
		driver.findElement(By.cssSelector(".fb_logo"));
		driver.findElement(By.cssSelector(".fb_logo.img"));
		driver.findElement(By.cssSelector("img.fb_logo"));
		driver.findElement(By.cssSelector("img[alt='Facebook']"));
	
		driver.findElement(By.cssSelector("a"));
		
		// Css not working on text, so we can using attribute to solve
		driver.findElement(By.cssSelector("a[title='Vietnamese']"));
		driver.findElement(By.cssSelector("a[onClick*='vi_VN']")); // *= is contains
		driver.findElement(By.cssSelector("a[title*='Viet']"));
		
		
		// Xpath
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']")); // lấy chính xác các thành phần của class 
		driver.findElement(By.xpath("//img[contains(@class,'fb_logo')]")); // lấy 1 phần của class
		driver.findElement(By.xpath("//img[starts-with(@class,'fb_logo')]")); 
		driver.findElement(By.xpath("//img"));
		
		driver.findElement(By.xpath("//input[@id='pass']"));
		driver.findElement(By.xpath("//a[text()='Tiếng Việt']"));
		driver.findElement(By.xpath("//*[text()='Tiếng Việt']"));
		driver.findElement(By.xpath("//a[contains(text(),'Tiếng')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Việt')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Việt')]"));
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
