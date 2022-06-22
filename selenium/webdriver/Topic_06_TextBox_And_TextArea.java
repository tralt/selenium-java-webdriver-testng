package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_TextBox_And_TextArea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	String first_name, last_name, edit_first_name, edit_last_name;

	By USER_NAME_FIELD_ON_LOGIN_FORM = By.id("txtUsername");
	By PASSWORD_FIELD_ON_LOGIN_FORM = By.id("txtPassword");
	By LOGIN_BUTTON = By.id("btnLogin");

	By FIRST_NAME_FIELD = By.xpath("//input[@name='personal[txtEmpFirstName]']");
	By LAST_NAME_FIELD = By.xpath("//input[@name='personal[txtEmpLastName]']");
	By EMPLOYEE_ID_FIELD = By.xpath("//input[@name='personal[txtEmployeeId]']");
	
	By IMMIGRATION_SUB_MENU = By.xpath("//a[text()='Immigration']");

	@BeforeClass
	public void Get_The_Stage() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Add_Employee() {
		
		first_name = "Miriam";
		last_name = "Le";
		edit_first_name = "Mina";
		edit_last_name = "Lestranger";
		
		driver.get("https://opensource-demo.orangehrmlive.com/");

		// Textbox
		driver.findElement(USER_NAME_FIELD_ON_LOGIN_FORM).sendKeys("Admin");
		driver.findElement(PASSWORD_FIELD_ON_LOGIN_FORM).sendKeys("admin123");

		// Button
		driver.findElement(LOGIN_BUTTON).click();
		sleepInSecond(5);

		// Open 'Add Employee' page
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		sleepInSecond(2);

		// The sub-menu "Add Employee" is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("#menu_pim_addEmployee")).isDisplayed());

		// Enter to FirstName/ LastName
		driver.findElement(By.id("firstName")).sendKeys(first_name);
		driver.findElement(By.id("lastName")).sendKeys(last_name);

		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");

		// Click on Save button
		driver.findElement(By.cssSelector("#content #btnSave")).click();
		sleepInSecond(3);

		// Verify First Name/ Last Name/ Employee ID is disabled
		Assert.assertFalse(driver.findElement(FIRST_NAME_FIELD).isEnabled());
		Assert.assertFalse(driver.findElement(LAST_NAME_FIELD).isEnabled());
		Assert.assertFalse(driver.findElement(EMPLOYEE_ID_FIELD).isEnabled());
		
		Assert.assertEquals(driver.findElement(FIRST_NAME_FIELD).getAttribute("value"), first_name);
		Assert.assertEquals(driver.findElement(LAST_NAME_FIELD).getAttribute("value"), last_name);
		Assert.assertEquals(driver.findElement(EMPLOYEE_ID_FIELD).getAttribute("value"), employeeID);
		
		// Edit First Name/ Last Name field
		driver.findElement(By.cssSelector("#btnSave")).click(); // click on the Edit Button to edit first_name and last_name
		
		// Verify First Name/ Last Name/ Employee ID is disabled
		Assert.assertTrue(driver.findElement(FIRST_NAME_FIELD).isEnabled());
		Assert.assertTrue(driver.findElement(LAST_NAME_FIELD).isEnabled());
		Assert.assertTrue(driver.findElement(EMPLOYEE_ID_FIELD).isEnabled());

		driver.findElement(FIRST_NAME_FIELD).clear();
		driver.findElement(FIRST_NAME_FIELD).sendKeys(edit_first_name);

		driver.findElement(LAST_NAME_FIELD).clear();
		driver.findElement(LAST_NAME_FIELD).sendKeys(edit_last_name);

		driver.findElement(By.cssSelector("#btnSave")).click(); // click on the Save button to save information just have changed
		
		// Verify information after edit
		Assert.assertEquals(driver.findElement(FIRST_NAME_FIELD).getAttribute("value"), edit_first_name);
		Assert.assertEquals(driver.findElement(LAST_NAME_FIELD).getAttribute("value"), edit_last_name);
		Assert.assertEquals(driver.findElement(EMPLOYEE_ID_FIELD).getAttribute("value"), employeeID);
		
		// Click on Immigration Sub Menu
		driver.findElement(IMMIGRATION_SUB_MENU).click();
		sleepInSecond(3);
		
		// Click on Add button
		driver.findElement(By.cssSelector("div.inner #btnAdd")).click();
		
		// Enter to 'Immigaration' number textbox
		driver.findElement(By.cssSelector("#immigration_number")).sendKeys("582-06-2514");
		driver.findElement(By.cssSelector("#immigration_comments")).sendKeys("This is my SSN ID = Passport ID. \n I used to mockaro to generate it");
		
		// Verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("#immigration_number")).getAttribute("value"), "582-06-2514");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#immigration_comments")).getAttribute("value"), "This is my SSN ID = Passport ID. \n I used to mockaro to generate it");
		
		
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
