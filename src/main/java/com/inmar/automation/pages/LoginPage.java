package com.inmar.automation.pages;


import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.inmar.automation.configs.BrowserConstants;
import com.inmar.automation.utils.PageUtils;

public class LoginPage {
	
	//private WebDriver driver;
	private PageUtils pageUtils;
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.pageUtils = PageUtils.getInstance();
		
		PageFactory.initElements(this.driver, this);
	}
	

	@FindBy(xpath="//*/text()[normalize-space(.)='Login']/parent::*")
	private  WebElement clkHamburgMenu;
	@FindBy(id="customer_login_link")
	private  WebElement loginLink;
	@FindBy(name="customer[email]")
	private  WebElement txtUserName;
	
	@FindBy(name="customer[password]")
	private  WebElement txtPassword;
	@FindBy(xpath="//input[@value='Sign in']")
	private  WebElement clkLoginButton;
	@FindBy(xpath="//button[@class='sda-modal-close-button']")
	private  WebElement crossIcon;
	
	
	@FindBy(xpath="//android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]")
	private  WebElement addCart2;
	@FindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private  WebElement clkButtonCart;
	
	@FindBy(xpath="//android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView")
	private  WebElement addCart1_Amount;
	@FindBy(xpath="//android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView")
	private  WebElement addCart2_Amount;
	
	@FindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private  WebElement totalPurchaseAmount;
	
	
	
	public void launchURL() throws InterruptedException
	{
		 driver.get("https://www.distacart.com/");
	}
	
	
	/**
	 * This method is used to select the country ,name,and gender
	 * @throws InterruptedException
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	public void enterLoginDetails(String userName,String pwd) throws InterruptedException
	{
	
		
		    
		     pageUtils.clickElement(driver, clkHamburgMenu);
	       // driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Menu'])[1]/preceding::div[1]")).click();
	        WebDriverWait wait =new WebDriverWait(driver, 30); // 10 seconds timeout
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customer[password]")));
	        element.click();
	        Thread.sleep(3000);
	        pageUtils.sendKeysToElement(driver, txtUserName, userName);
	        pageUtils.sendKeysToElement(driver, txtPassword, pwd);
	        Thread.sleep(2000);
//	        try {
//	        pageUtils.clickElement(driver, crossIcon);
//	        }
//	        catch(Exception e)
//	        {
//	        	
//	        }
	       
	        pageUtils.clickElement(driver, clkLoginButton);
	       
		
	}
	  
	/**
	 * This Method is used to Select the items and tap the shopping cart icon
	 * @throws InterruptedException 
	 */
	public void selectItemsAndTapCartButton() throws InterruptedException
	{
	//	pageUtils.clickElement(driver, addCart1);
		pageUtils.clickElement(driver, addCart2);
		pageUtils.clickElement(driver, clkButtonCart);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL);
	}
	
	/**
	 * This Method is used to Validate that the Total Purchase Amount is equal to the two prices added
           together.
	 */
	public boolean calculateAmount()
	{
		String FirstCartAmount=pageUtils.getTextOfElement(driver, addCart1_Amount);
		String SecondCartAmount=pageUtils.getTextOfElement(driver, addCart2_Amount);
		float fca= Float.parseFloat(FirstCartAmount.replace("$", ""));
		float sca= Float.parseFloat(SecondCartAmount.replace("$", ""));
		
		float totaltwoPrices=fca+sca;
		String twoPricesAmount=Float. toString(totaltwoPrices);
		System.out.println("Two prices Total Amount:"+twoPricesAmount);
		
		String TPA=pageUtils.getTextOfElement(driver, totalPurchaseAmount);
		String totalPurchase_Amount= TPA.replace("$", "").trim();
		System.out.println("Total Purchase Amount:"+totalPurchase_Amount);
		boolean status=false;
		if(twoPricesAmount.equals(totalPurchase_Amount))
		{
			System.out.println("TOTAL PURCHASE AMOUNT IS EQUAL TO THE TWO PRICES ADDED");
			status=true;
		}
		else
		{
			
			//Assert.assertTrue("ERROR MESSAGE :TOTAL PURCHASE AMOUNT IS NOT EQUAL TO THE TWO PRICES ADDED", status);
			status=false;
		}
		return status;
		
	}
	
	
}
