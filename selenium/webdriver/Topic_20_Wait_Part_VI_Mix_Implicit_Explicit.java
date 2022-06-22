package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_20_Wait_Part_VI_Mix_Implicit_Explicit {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
	
		driver = new FirefoxDriver();
	}
	
	public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");
		
		By emailID = By.xpath("//input[@id='email']");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Start Time: "+ getTimeNow());
		driver.findElement(emailID).isDisplayed();
		System.out.println("End Time: "+ getTimeNow());
		
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start Time: "+ getTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(emailID));
		System.out.println("End Time: "+ getTimeNow());
		
		// Khi element có xuất hiện thì dùng cả 2 loại wait không ảnh hưởng gì đến script
	}
	
	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");
		
		By emailID = By.xpath("//input[@id='email01']");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Start Time: "+ getTimeNow());
		
		try {
			driver.findElement(emailID).isDisplayed();
		}catch (Exception e) {
			System.out.println("End Time: "+ getTimeNow());
		}
		
		// Chờ hết 30s thì fail 
	}
	
	public void TC_03_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");
		
		By emailID = By.xpath("//input[@id='email01']");
		
		// implicit = 0s 
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start Time: "+ getTimeNow());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
		}catch (Exception e) {
			System.out.println("End Time: "+ getTimeNow());
		}
		
		// Chờ hết 15s thì bắn ra exception
	}
	
	public void TC_04_Element_Not_Found_Mix() {
		driver.get("https://www.facebook.com/");
		
		By emailID = By.xpath("//input[@id='email01']");
		
		
		// - 1. Implicit < Explicit
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Start Time: "+ getTimeNow());
		
		try {
			driver.findElement(emailID).isDisplayed();
		}catch (Exception e) {
			System.out.println("End Time: "+ getTimeNow());
		}
		
		
		explicitWait = new WebDriverWait(driver, 10);
		System.out.println("Start Time: "+ getTimeNow());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(emailID));
		}catch (Exception e) {
			System.out.println("End Time: "+ getTimeNow());
		}
		
		// Implicit sẽ không bị ảnh hưởng bởi bất kỳ một loại wait nào khác
		// Explicit bị ảnh hưởng bởi implicit nhưng vì đang set thời gian nhỏ hơn nên chưa thấy rõ 
		// Implicit và Explicit chạy bất đồng bộ tức là khởi tạo song song 
		
		
		// - 2. Implicit > Explicit 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		System.out.println("Start Time: "+ getTimeNow());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(emailID));
		}catch (Exception e) {
			System.out.println("End Time: "+ getTimeNow());
		}
		
		// Khi nào đạt đủ timeout của cả 2 thằng thì dừng lại 
		// (lưu ý 2 loại wait này khởi tạo chạy cùng lúc nhưng trong hàm explicit thì lại có hàm find.element nên implicit chạy trước 1 chút)
	}
	
	public void TC_05_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");
		
		//By emailID = By.xpath("//input[@id='email01']");
		
		// Implicit = 0s
		WebElement emailID = driver.findElement(By.xpath("//input[@id='email01']"));
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start Time: "+ getTimeNow());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(emailID));
		}catch (Exception e) {
			System.out.println("End Time: "+ getTimeNow());
		}
		
		// Test case pass vì có try catch 
		// Bật ra exception: Fail tại dòng 141, chỗ driver.findElement
		// chạy mất 0s vì Implicit = 0s
	}
	
	public void TC_06_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");
		
		// Implicit = 0
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start Time: "+ getTimeNow());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='email01']"))));
		}catch (Exception e) {
			System.out.println("End Time: "+ getTimeNow());
		}
		
		// Test case pass vì có try catch 
		// Bật ra exception: Fail tại dòng 166, chỗ driver.findElement
		// chạy mất 0s vì Implicit = 0s
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}
