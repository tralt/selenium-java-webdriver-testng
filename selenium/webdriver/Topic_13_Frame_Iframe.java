package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Iframe {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	Alert alert;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
	
		driver = new FirefoxDriver();
		
		// Khởi tạo sau khi driver này được sinh ra
		// JavascriptExecutor / WebDriverWait/ Actions ...
		jsExecutor = (JavascriptExecutor)driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Kyna() {
		driver.get("https://kyna.vn/");
		
		// Switch to frame/ Iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage >div.face-content >iframe")));
		
		// Verify the quantity of like of the fanpage = 166k
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div/following-sibling::div")).getText(), "166K likes");
		
		// Turn to the main screen
		driver.switchTo().defaultContent();
		
		// Swith to the box chat
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_ViewStack input.input_name")).sendKeys("Tra");
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_ViewStack input.input_phone")).sendKeys("0988999878");
		
		driver.switchTo().defaultContent();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
