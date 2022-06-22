package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_11_Action_Part_II {
	
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
	
	public void TC_02_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		
		scrollToElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(doubleClickButton).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	
	public void TC_03_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// Click on "right click me" button
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		
		// Hover to "Paste"
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		
		// Verify the paste is hovering 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		
		// Click on the paste
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		
		// Accep alert
		explicitWait.until(ExpectedConditions.alertIsPresent()).accept();
	}
	
	public void TC_04_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement bigCircle = driver.findElement(By.cssSelector("#droptarget"));
		WebElement smallCirclElement = driver.findElement(By.cssSelector("#draggable"));
		
		action.dragAndDrop(smallCirclElement, bigCircle).perform();
		sleepInSecond(3);
		
		// Verify 
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
	}
	
	public void TC_05_Drag_And_Drop_HTML5() {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
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
