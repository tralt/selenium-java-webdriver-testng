package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String appleFileName = "apple.png";
	String dellFileName = "dell.png";
	String lenovoFileName = "lenovo.png";
	
	// Support cho cac may window
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;
	String appleFilePath = uploadFileFolderPath + appleFileName;
	String dellFilePath = uploadFileFolderPath + dellFileName;
	String lenovoFilePath = uploadFileFolderPath + lenovoFileName;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
	
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();

	}
	
	@Test
	public void TC_01() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFile = By.xpath("//input[@type='file']");
		driver.findElement(uploadFile).sendKeys(appleFilePath);
		driver.findElement(uploadFile).sendKeys(dellFilePath);
		driver.findElement(uploadFile).sendKeys(lenovoFilePath);
		sleepInSecond(3);
		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ appleFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ dellFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ lenovoFileName +"']")).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
