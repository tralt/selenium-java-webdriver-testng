package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Multiple_Browser {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@Test
	public void TC_01_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com/");
		
		driver.quit();
	}
	
	@Test
	public void TC_02_Firefox() {
		// Setting OS hiểu được geckodriver => Giao tiếp với Browser
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");
		
		driver.quit();
		
	}
	
	@Test
	public void TC_03_Chromium() {
		System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		driver = new EdgeDriver();
		
		driver.get("https://www.facebook.com/");
		
		driver.quit();
		
	}
	

}
