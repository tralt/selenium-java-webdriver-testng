package tech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
	
	WebDriver driver;

	@Test
	public void TC_01() {
		// Thư viện Assert kiểm tra tính đúng đắn của dữ liệu 
		
		String addressCity = "Sai Gon";
		
		// Kiểm tra 1 điều kiện luôn luôn đúng 
		Assert.assertTrue(addressCity.equalsIgnoreCase("Sai Gon"));
		
		// Kiểm tra 1 điều kiện mong đợi là sai 
		Assert.assertFalse(addressCity.contains("Ha Noi"), "Address khong chua du lieu mong doi");
		Assert.assertFalse(addressCity.contains("Ha"));
		
		// Kiểm tra đầu vào của dữ liệu và đầu ra như nhau 
		Assert.assertEquals(addressCity, "Sai Gon");
		
		
		// Kiểm tra điều kiện mong đợi là null/ not null (đã khởi )
		Object fullName = null;
		Assert.assertNull(fullName);
		
		Topic_02_Assert topic02 = null;
		Assert.assertNull(topic02);
		
		topic02 = new Topic_02_Assert();
		Assert.assertNotNull(topic02);
		
		// Kiểm tra tính đúng đắn của dữ liệu
		
				// 1 - Kiểm tra dữ liệu mình mong muốn là ĐÚNG
				Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
				
				// 2 - Kiểm tra dữ liệu mình mong muốn là SAI 
				Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());
				
				// 3 - Kiểm tra dữ liệu mình mong muốn và thực tế BẰNG NHAU
				Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
				
				// 4 - Kiểm tra dư liệu mình mong muốn và thực tế là TƯƠNG ĐỐI
				String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
				Assert.assertTrue(benefitText.contains("abc"));
				Assert.assertTrue(benefitText.contains("okkk"));
				Assert.assertTrue(benefitText.contains("NOT TODAY"));
		
	}
}
