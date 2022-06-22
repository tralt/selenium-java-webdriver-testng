package tech;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_01_Annotation {
	
	
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }
  
  @BeforeMethod // 1 lần trước từng test case 
  public void beforeMethod() {
  }

  @AfterMethod // 1 lần sau từng test case 
  public void afterMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite // chỉ chạy 1 lần duy nhất trước tất cả testcase/ method
  public void beforeSuite() {
  }

  @AfterSuite // chỉ chạy 1 lần duy nhất sau tất cả testcase/ method
  public void afterSuite() {
  }

}
