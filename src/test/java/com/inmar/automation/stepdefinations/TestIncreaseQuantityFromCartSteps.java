package com.inmar.automation.stepdefinations;

import org.openqa.selenium.WebDriver;

import com.inmar.automation.pages.LoginPage;
import com.inmar.automation.pages.TestIncreaseQuantityFromCartPage;
import com.inmar.automation.utils.BrowserUtils;
import com.inmar.automation.utils.StepUtils;

import io.cucumber.java.en.Given;

public class TestIncreaseQuantityFromCartSteps extends StepUtils{

	WebDriver driver = BrowserUtils.getDriverInstance();   
	TestIncreaseQuantityFromCartPage TIP = null;
	
	
	
	
	@Given("^add the products into the cart$")
	public void i_am_on_Home_page_of_distacart() throws Throwable {
		TIP = new TestIncreaseQuantityFromCartPage(driver);
		TIP.testIncreaseQuantityFromCartPage();
	}
}
