package com.inmar.automation.stepdefinations;




import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.inmar.automation.utils.BrowserUtils;
import com.inmar.automation.utils.StepUtils;



import com.inmar.automation.pages.LoginPage;

import io.cucumber.datatable.DataTable;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSteps extends StepUtils{

	WebDriver driver = BrowserUtils.getDriverInstance();   
	LoginPage loginpage = null;
	
	
	
	
	@Given("^I am on Home page of distacart$")
	public void i_am_on_Home_page_of_distacart() throws Throwable {
		loginpage = new LoginPage(driver);
		loginpage.launchURL();
	}
	
	
	@When("^launch the url$")
	public void i_can_launch_the_url() throws Throwable {
		
		
		
	}


	@When("^I login to Distacart with valid user credentials (.*) and (.*)$")
	public void i_login_to_distacart_with_valid_user_credentials(String userName, String passWord) throws InterruptedException {
	    // Your implementation here
	    loginpage.enterLoginDetails(userName, passWord);
	}

	@When("^I can select the country name username and gender1$")
	public void i_can_select_the_country_name_username_and_gender1(DataTable dataTable) throws Throwable {
		//Map<String, String> dataMap =dataTable
		//generalStoreAppPage.enterDetails(dataMap);
		
		
	}
	@When("^I can select the items and tap the shopping cart icon$")
	public void i_can_select_the_items_and_tap_the_shopping_cart_icon() throws Throwable {
		
		loginpage.selectItemsAndTapCartButton();
		
		
	}
	@Then("^I can validate that the total purchase amount is equal to the two prices added together$")
	public void i_can_validate_that_the_total_purchase_amount_is_equal_to_the_two_prices_added() throws Throwable {
		
		loginpage.calculateAmount();
		
		
	}

	
	

	
	
	
	
	
}
