package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_II_FindElement {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	//WebDriverWait explicitWait;
	
	@BeforeClass
	public void Get_The_Stage() {
		
		if (osName.startsWith("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
	
		driver = new FirefoxDriver();
		
		//explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");

	}
	
	@Test
	public void TC_01_Find_Element() {
		
		// - Có duy nhất một element 
		// Nếu như element xuất hiện ngay -> thì trả về element đó không cần chờ hết timeout 
		// Nếu như element chưa xuất hiện -> thì sau 0.5s sẽ tìm lại cho đến khi hết timeout 
		driver.findElement(By.xpath("//input[@id='email']"));
		
		// - Không có element nào hết
		// Nó sẽ tìm đi tìm lại cho đến khi hết timeout 
		// Sau khi hết timeout thì fail test case này 
		// Không có chạy các step còn lại 
		// Throw / Log ra một exception 
		driver.findElement(By.xpath("//input[@name='automation']"));
		
		// - Có nhiều element 
		// Thao tác với element đầu tiên 
		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
		
	}
	
	public void TC_02_Find_Elements() {
		
		int elementNumber = 0;
		
		// - Có duy nhất một element 
		// - Có nhiều hơn một element 
		// Nếu như element xuất hiện ngay -> Thì trả về element đó không cần chờ hết timeout 
		// Nếu như element chưa xuất hiện -> Thì sau 0.5s sẽ tìm lại cho đến khi hết timeout thì thôi 
		
		elementNumber = driver.findElements(By.xpath("//input[@id='email']")).size();
		
		elementNumber = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
		
		// - Không có element nào hết 
		// Nó sẽ tìm đi tìm lại cho đến khi hết timeout
		// Sau khi hết timeout thì không đánh fail step nà mà chạy tiếp các step sau 
		elementNumber = driver.findElements(By.xpath("//input[@id='automation']")).size();
		
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
