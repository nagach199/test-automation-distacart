package com.inmar.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.inmar.automation.utils.PageUtils;

import java.util.List;
import java.util.Random;

public class TestIncreaseQuantityFromCartPage {
    
	private PageUtils pageUtils;
	private WebDriver driver;
	public TestIncreaseQuantityFromCartPage(WebDriver driver) {
		this.driver = driver;
		this.pageUtils = PageUtils.getInstance();
		
		PageFactory.initElements(this.driver, this);
	}
	@FindBy(xpath="//*/text()[normalize-space(.)='Wellness']/parent::*")
	private  WebElement mainCategory;
	@FindBy(xpath="//*/text()[normalize-space(.)='Ayurveda']/parent::*")
	private  WebElement category;
	@FindBy(xpath="//*[text()='Ayurvedic Products Online']")
	private  WebElement productTitle;
	@FindBy(xpath="//*[@class='snize-overhidden']//*[contains(text(),'Patanjali')]")
	private  WebElement productAvailable;
	@FindBy(xpath="//body/div/main[@role='main']/div/div/div/form[@method='post']/div/button")
	private  WebElement addToCartButton;
	@FindBy(xpath="//div//div//div[6]//span[1]")
	private  WebElement productPrice;
	@FindBy(xpath="//span[@class='minicart__close']/img")
	private  WebElement miniCartCloser;
	@FindBy(xpath="//label[@for='minicart__button--header--default']//span[@class='item__count']")
	private  WebElement cartCountValue;
	@FindBy(xpath="//div[@class='col-12 col-md-7 cart__quantity center']//button[@class='quantity--input__button quantity--input__decr']")
	private  WebElement cartQuantityPlusButton;
	
	
    public void testIncreaseQuantityFromCartPage() throws InterruptedException {
       
        String category = "Ayurveda";
        String link = driver.getCurrentUrl();
        
        // Navigate to the specified category
        pageUtils.getFocusOnElement(driver, mainCategory,addToCartButton);
        //pageUtils.clickElement(driver, addToCartButton);
       // pageUtils.clickElementUsingActions(driver, addToCartButton);
        System.out.println("INFO: Verifying category page title... Ayurvedic Products Online");
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Ayurvedic Products Online']")));
        pageUtils.waitForElementTextToBe(driver, productTitle, "Ayurvedic Products Online");
        // Set category and number of items to add to the cart
        category = "Patanjali";
        int noOfItems = 1;

        // Iterate until the desired number of items are added to the cart
        while (noOfItems <= 1) {
        	List<WebElement> availableItems =driver.findElements(By.xpath("//*[@class='snize-overhidden']//*[contains(text(),'Patanjali')]"));
         
            System.out.println("INFO: Available items found on the 1st page of " + category + " category: " + availableItems.size());

            // Randomly select an available product
            WebElement selectedElement = availableItems.get(new Random().nextInt(availableItems.size()));
            String product = selectedElement.getText();
            System.out.println("INFO: Randomly selected available product: " + product);
            pageUtils.moveToElement(driver,selectedElement);
            pageUtils.clickElement(driver, selectedElement);
            
            System.out.println("INFO: Click on add to cart button: " + product);
            WebElement productItem = driver.findElement(By.xpath("//body/div/main[@role='main']/div/div/div/form[@method='post']/div/button"));
            pageUtils.moveToElement(driver,productItem);
            pageUtils.waitForLocatorToappear(driver, By.xpath("//body/div/main[@role='main']/div/div/div/form[@method='post']/div/button"));

            // Verify product price and add to cart
            String price = productPrice.getText();
            System.out.println("INFO: Verify price of " + product + " is " + price);
            pageUtils.clickElement(driver, productItem);
            pageUtils.handlePopup(driver);
            pageUtils.clickElement(driver, miniCartCloser);
           

            String cartCount = cartCountValue.getText();
            System.out.println("INFO: Total items displayed in cart: " + cartCount);
            assert "1".equals(cartCount);
            Thread.sleep(6000);
             driver.navigate().to(link + "/cart");
             pageUtils.clickElement(driver, cartQuantityPlusButton);
            

            // Verify the cart count and updated price
            cartCount =cartCountValue.getText();
            
            
            
            
            
			/*
			 * String cartItemPrice =
			 * browser.findElement(By.xpath(locators.increasedCartItemPriceXpath)).getText()
			 * ; System.out.
			 * println("INFO: Total items displayed in cart after increasing the quantity by 1: "
			 * + cartCount); assert "2".equals(cartCount);
			 * System.out.println("INFO: Price displayed in cart page for product " +
			 * product + " is " + cartItemPrice); assert !cartItemPrice.equals(price);
			 * 
			 * // Verify the total price in the cart page String cartPageTotalPrice =
			 * browser.findElement(By.xpath(locators.cartPageTotalCurrencyCodeXpath)).
			 * getText(); assert cartItemPrice.equals(cartPageTotalPrice);
			 * 
			 * // Click on checkout button in cart page
			 * browser.findElement(By.name(locators.checkoutButtonId)).click(); new
			 * WebDriverWait(browser,
			 * 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.
			 * checkoutPageTotalPriceXpath))); helper.wait();
			 * 
			 * // Verify items in the checkout page String itemsInCheckoutPage =
			 * browser.findElement(By.xpath(locators.checkoutItemCountXpath)).getText();
			 * System.out.println("INFO: Total items displayed in checkout page: " +
			 * itemsInCheckoutPage); assert "2".equals(itemsInCheckoutPage);
			 * 
			 * String checkoutTotalPrice =
			 * browser.findElement(By.xpath(locators.checkoutPageTotalPriceXpath)).getText()
			 * ; String checkoutTotalCurrency =
			 * browser.findElement(By.xpath(locators.checkoutPageCurrencyXpath)).getText();
			 * System.out.
			 * println("INFO: Total price displayed in checkout page for product " + product
			 * + " is " + checkoutTotalPrice); assert
			 * cartItemPrice.equals(checkoutTotalPrice.trim() + " " +
			 * checkoutTotalCurrency);
			 */
            noOfItems++;
            availableItems.clear();
        }
    }
}
