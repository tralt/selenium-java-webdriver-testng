package webdriver;

import java.util.Set;
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

public class Topic_14_Window_Tab {
	
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
		//driver.manage().window().maximize();
	}
	
	
	public void TC_01_Naukri_Tab_ID() {
		driver.get("https://www.naukri.com/");
		String homePageWindowID = driver.getWindowHandle();
		System.out.println("Tab A: "+ homePageWindowID);
		
		// Click on 'Jobs' and link to the B page
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);
		System.out.println("Tab A: "+ driver.getCurrentUrl());
		
		// Hành vi của website là sẽ tự nhảy qua trang Jobs 
		// Nhưng driver thì vẫn ở trang trước đó
		
		// Trong trường hợp chỉ có duy nhất 2 tabs/ windows thì có thể dùng ID của tab/ window để switch qua 
		// Dùng 1 biến tạm để  duyệt qua các phần tử trong Set<String>
		switchToWindowByID(homePageWindowID);
		
		// Đã switch qua trang Jobs để selenium có thể tương tác 
		System.out.println("Tab B: "+ driver.getCurrentUrl());
		
		String jobsPageWindowID = driver.getWindowHandle();
		
		switchToWindowByID(jobsPageWindowID);
		
		// Đã switch qua trang Home để selenium có thể tương tác 
		System.out.println("Tab A: "+ driver.getCurrentUrl());
	}
	
	public void TC_02_Naukri_Title_Page() {
		driver.get("https://www.naukri.com/");
		String homePageWindowID = driver.getWindowHandle();
		System.out.println("Tab A: "+ homePageWindowID);
		
		// Click on 'Jobs' and link to the B page
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);
		System.out.println("Tab A: "+ driver.getCurrentUrl());
		
		// Hành vi của website là sẽ tự nhảy qua trang Jobs 
		// Nhưng driver thì vẫn ở trang trước đó
		
		// Trong trường hợp chỉ có duy nhất 2 tabs/ windows thì có thể dùng ID của tab/ window để switch qua 
		// Dùng 1 biến tạm để  duyệt qua các phần tử trong Set<String>
		switchToWindowByTitlePage("Browse Jobs by Company, Location, Skills, Designation & Industry - Naukri.com");
		
		// Đã switch qua trang Jobs để selenium có thể tương tác 
		System.out.println("Tab B: "+ driver.getCurrentUrl());
		
		switchToWindowByTitlePage("Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com");
		
		// Đã switch qua trang Home để selenium có thể tương tác 
		System.out.println("Tab A: "+ driver.getCurrentUrl());
	}
	
	@Test
	public void TC_03_Cambridge_Dictionary_Title_Page() {
		
		driver.get("https://dictionary.cambridge.org/vi/");
		String homePageWindowID = driver.getWindowHandle();
		System.out.println("Tab A: "+ homePageWindowID);
		
		// Click on 'Dang Nhap' and link to the B page
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitlePage("Login");
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		//Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']/following-sibling::span")).getText(), "abc");
		
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']")).sendKeys("Automation000***");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		switchToWindowByTitlePage("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		
		// Verify login thanh cong
		Assert.assertEquals(driver.findElement(By.cssSelector("header#header span.cdo-username")).getText(), "Automation FC");
	
	}
	
	
	
	// Có thể viết thành 1 method trong trường hợp chỉ có 2 tab/window 
	public void switchToWindowByID (String currentWindowID) {
		// Lấy hết tất cả ID đang có ra
		Set<String> allWindowsIDs = driver.getWindowHandles();
		
		// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
		for (String id : allWindowsIDs) {
			// nếu như ID nào mà khác với ID của home page thì switch đến
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
			}
			
		}
	}
	
	// Dùng được cho trường hợp có nhiều tab
	public void switchToWindowByTitlePage(String expectedTitle) {
		// Lấy hết tất cả ID đang có ra
				Set<String> allWindowsIDs = driver.getWindowHandles();
				
				// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
				for (String id : allWindowsIDs) {
					// Switch vào trước rồi mới checkt
					driver.switchTo().window(id);
					
					// Lấy ra title của page đó
					String actualTitleString = driver.getTitle();
					
					if (actualTitleString.equals(expectedTitle)) {
						// Thoả mãn điều kiện
						break;
					}
				}
	}
	
	public void switchToWindowByLink(String expectedRelativeLink) {
		// Lấy hết tất cả ID đang có ra
				Set<String> allWindowsIDs = driver.getWindowHandles();
				
				// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
				for (String id : allWindowsIDs) {
					// Switch vào trước rồi mới checkt
					driver.switchTo().window(id);
					
					// Lấy ra title của page đó
					String actualLink = driver.getCurrentUrl();
					
					if (actualLink.contains(expectedRelativeLink)) {
						// Thoả mãn điều kiện
						break;
					}
				}
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
