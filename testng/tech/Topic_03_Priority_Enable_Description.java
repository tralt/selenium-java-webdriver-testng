package tech;

import org.testng.annotations.Test;

public class Topic_03_Priority_Enable_Description {
	
	// Nên đánh số test case để quản lý và sắp xếp ưu tiên 
	
	@Test 
	public void Buyer_01_Search_Product() {
		
	}
	
	@Test
	public void Buyer_02_Add_To_Cart() {
		
	}
	
	@Test(enabled = true, description = "Buyer add items into cart and then go to the cart page to choose the payment method")
	public void Buyer_03_Choose_Payment_Method() {
		
	}
	
	@Test (enabled = false, description = "link testcase, document, ... (Trello , #JIRA5648, ...)")
	public void Buyer_04_Checkout() {
		
	}

}
