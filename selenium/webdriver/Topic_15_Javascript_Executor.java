package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	Alert alert;
	
	String loginPageUrl, userID, userPassword, customerID;
	String customerName, gender, dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password;
	String editAddressInput, editAddressOutput, editCity, editState, editPin, editPhone, editEmail;
	
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
		
		
		loginPageUrl = "https://demo.guru99.com/v4/";
		customerName = "Brian Tracy";
		gender = "male";
		dateOfBirth = "1950-01-31";
		addressInput = "123 Los Angeles\nUnited States";
		addressOutput = "123 Los Angeles United States";
		city = "New York";
		state = "California";
		pinNumber = "632565";
		phoneNumber = "0987569584";
		email = "briantrary" + getRandomString(4) + "@mail.net";
		password = "123456";
		
		editAddressInput = "456 Tracy\nColombia";
		editAddressOutput = "456 Tracy Colombia";
		editCity = "Hawaii";
		editState = "New Yersey";
		editPin = "659853";
		editPhone = "0986333222";
		editEmail = "briantracy" + getRandomString(5) + "@mail.vn";

	}
	
	public static String getRandomString(int length){
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }
	
	// get random email
    String emailAccount = getRandomString(6) + "@podman.com";
	
	
	public void TC_01() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(4);
		
		//executeForBrowser("window.location= 'http://live.techpanda.org/'");
		
		String homePageDomain = (String)executeForBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.techpanda.org");
		
		String homePageUrl = (String)executeForBrowser("return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");
		
		
		String mobile_TextElement ="//a[text()='Mobile']";
		clickToElementByJS(mobile_TextElement);
		sleepInSecond(3);
		
		clickToElementByJS("//a[@title='IPhone']/parent::h2/following-sibling::div/button");
		sleepInSecond(3);
		
		String shoppingCartText = getInnerText();
		Assert.assertTrue(shoppingCartText.contains("IPhone was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);
		
		String customerPageTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerPageTitle, "Customer Service");
		
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(3);
		
		sendKeyToElementByJS("//input[@id='newsletter']", emailAccount);
		sleepInSecond(3);
		
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(20);
		
		Assert.assertTrue(areExpectedTextInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		String guru99Domain = (String)executeForBrowser("return document.domain;");
		Assert.assertEquals(guru99Domain, "demo.guru99.com");
	}
	
	public void TC_02_HTML05_Validation_Message() {
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		
		By firstNameBy = By.id("user_first_name");
		By emailBy = By.id("user_email");
		By createButton = By.xpath("//button[contains(text(), 'Tạo tài khoản mới')]");
		
		driver.findElement(createButton).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage("#user_first_name"), "Please fill out this field.");
	}
	
	public void TC_03_Remove_Attribute_In_DOM() {
		driver.get(loginPageUrl);
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3> td")).getText(), "Manger Id : "+userID);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		removeAttributeInDOM("//input[@name='dob']", "type");
		driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}
	
	@Test
	public void TC_04_Image_uploaded() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		Assert.assertTrue(isImageLoaded("//img[@alt='broken_05']"));
		
		Assert.assertFalse(isImageLoaded("//img[@alt='broken_04']"));
	}
	
	
	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}
	
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	public boolean areExpectedTextInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('"+ textExpected +"')[0];");
		return textActual.equalsIgnoreCase(textExpected);
	}
	
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText");
	}
	
	public void sendKeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value','"+ value + "')", getElement(locator));
	}
	
	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}
	
	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void clickToElementByJS(String locator) {
			jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}
	
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location= '"+ url + "'");
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
