package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
	}
	
	@Test
	public void TC_01_Method() {
		// chờ cho findElement/ findElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Chờ cho 1 page load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Chờ cho một đoạn code Javascript được thực thi thành công
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		
		driver.manage().window().fullscreen(); // tương ứng với việc ấn phím F11
		driver.manage().window().maximize(); // tương ứng với việc ấn phím maximize window
		
		
		// Set vị trí của browser so với độ phân giải màn hình
		driver.manage().window().setPosition(new Point(100, 250));
		driver.manage().window().getPosition();
		
		// Mở browser với kích thước bao nhiêu
		// Test responsive 
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().window().getSize();
		
		// Tracking history tốt hơn
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		
		// Alert
		driver.switchTo().alert();
		
		// Frame/ iFrame
		driver.switchTo().frame(0);
		
		// Window va Tab
		driver.switchTo().window("");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
