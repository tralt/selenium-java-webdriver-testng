package tech;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Multiple_URL {

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriver driver;
	
	By emailTextBox = By.xpath("//*[@id='email']");
	By passwordTextBox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@Parameters("browser")
	@BeforeClass
	public void beforClass(String browserName) {
		
		
		if (osName.startsWith("Window")) {
			switch (browserName) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
				
				
			case "edge":
				System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
				driver = new EdgeDriver();
				break;	
				
			default:
				System.out.println("Browname is not valid");
				break;
			}
			
		}
		else if (osName.startsWith("Mac")) {
			switch (browserName) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
				driver = new FirefoxDriver();
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
				driver = new ChromeDriver();
				break;
				
				
			case "edge":
				System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
				driver = new EdgeDriver();
				break;	
				
			default:
				System.out.println("Browname is not valid");
				break;
			}
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Parameters("server")
	@Test
	public void TC_01_Login_System(String serverName) {
		
		String serverURL = getServerUrl(serverName);
		
		driver.get(serverURL+"/index.php/customer/account/login/");
		
		driver.findElement(emailTextBox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextBox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	private String getServerUrl(@Optional("DEV") String serverName) {
		switch (serverName) {
		case "DEV":
			serverName = "http://live.techpanda.org";
			break;
			
		case "TESTING":
			serverName = "http://beta.techpanda.org";
			break;	
			
		case "PRODUCTION":
			serverName = "http://techpanda.org";
			break;		

		default:
			System.out.println("Server Name is not valid");
			break;
		}
		
		return serverName;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
