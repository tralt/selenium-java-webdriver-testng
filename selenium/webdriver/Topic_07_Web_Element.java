package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action = new Actions(driver);
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_01_Define_Element() {
		WebElement emailTextBox = driver.findElement(By.id("email"));
		emailTextBox.clear();
		emailTextBox.sendKeys("");
		emailTextBox.isDisplayed();
	}
	
	@Test
	public void TC_01_Define_Element_Method() {
		WebElement element = driver.findElement(By.id(""));
		
		// Xoá dữ liệu trong nhưng field cho phép nhập
		// Luôn luôn clear hết dữ liệu trước khi tương tác lên field đó
		// Textbox/ TextArea / Editable Dropdown 
		element.clear();
		
		// Nhập data trong những field cho phép nhập 
		element.sendKeys("");
		element.sendKeys(Keys.TAB);
		
		
		driver.findElement(By.className("footer")).findElement(By.cssSelector("a[title='My Account']"));
		driver.findElement(By.xpath(""));
		
		// GUI: Font Size/ Font Type/ Color/ Pixel...
		element.getCssValue("background-color");
		// rgb(149, 246, 6)
		element.getCssValue("front-size");
		
		// GUI: Position/ Location/ Size of element
		element.getLocation();
		element.getRect();
		element.getSize();
		
		
		// Framework: Attach screenshot to Report HTML
		element.getScreenshotAs(OutputType.FILE);
		
		
		// Mong muốn một element hiển thị
		// Hiển thị: Người dùng nhìn thấy được/ có kích thước cụ thể (Chiều rộng, chiều dài, chiều cao)
		// Áp dụng với tất cả các loại element 
		element.isDisplayed();
		
		// Mong muốn một element có thể thao tác được lên hoặc không
		element.isEnabled();
		
		// Mong muốn một element được CHỌN
		element.isSelected();
		
		// Click on the element
		element.click();
		
		// Giống hành vi ENTER
		element.submit();
		
		// Actions
		action.clickAndHold(element).moveToElement(element).perform();
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
