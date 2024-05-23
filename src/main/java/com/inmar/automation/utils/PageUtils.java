package com.inmar.automation.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.inmar.automation.configs.BrowserConstants;

import cucumber.api.Scenario;



public class PageUtils {
	WebElement we; 
	String count;
	String parent;
	String child;
	private static PageUtils pageUtilsInstance = new PageUtils();

	private PageUtils() { 
	} 

	public static PageUtils getInstance() {
		if (pageUtilsInstance == null) {
			pageUtilsInstance = new PageUtils();
		}
		return pageUtilsInstance;
	}

	/**
	 * This method gives the text of the specified WebElement
	 * 
	 * @param WebElement
	 * @return String
	 */
	public String getTextOfElement(WebDriver driver, WebElement element) {
		waitForElementToLoad(driver, element);
		String elementText = element.getText();
		return elementText;
	}

	/**
	 * This method clicks the specified WebElement
	 *  
	 * @param WebElement  
	 */
	public void clickElement(WebDriver driver, WebElement element) {
		long timeoutInSeconds = 30;
		WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds );
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		waitForElementToLoad(driver, element);
		element.click();
		waitForPageLoad(driver);
	}

	public void doubleClickElement(WebDriver driver, WebElement element) {
		long timeoutInSeconds = 10;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		waitForElementToLoad(driver, element);
		Actions action1 = new Actions(driver);
		// Double click
		action1.doubleClick(element).perform();
		waitForPageLoad(driver);
	}

	/**
	 * This method clicks the specified WebElement
	 * 
	 * @param WebElement
	 */
	public void clickFirstElementFromList(WebDriver driver, List<WebElement> lstElement) {
		long timeoutInSeconds = 10;
		try {
			WebElement element = lstElement.get(0);
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			waitForElementToLoad(driver, element);
			element.click();
			waitForPageLoad(driver);
		} catch (Exception e) {
			System.out.println("EXCEPTION OCCURRED: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * This method clicks the specified WebElement
	 * 
	 * @param WebElement
	 * @throws InterruptedException
	 */
	public void clickLinkForNavigation(WebDriver driver, WebElement element, String oldUrl)
			throws InterruptedException {
		long timeoutInSeconds = 10;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		waitForElementToLoad(driver, element);
		element.click();
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
		System.out.println("new url:" + getCurrentUrlOfPage(driver));
	}

	/**
	 * This method gets the current url of page
	 * 
	 * @param WebDriver
	 * @return String
	 * @throws InterruptedException
	 */
	public String getCurrentUrlOfPage(WebDriver driver) throws InterruptedException {
		waitForFixedTime(BrowserConstants.WAIT_MEDIUM);
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	/**
	 * This method sends the text to the specified WebElement after clearing it
	 * 
	 * @param driver
	 * @param element
	 * @param text
	 */
	public void sendKeysAfterClearingElement(WebDriver driver, WebElement element, String text) {
		waitForElementToLoad(driver, element);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * This method sends the text to the specified WebElement
	 * 
	 * @param driver
	 * @param element
	 * @param text
	 */
	public void sendKeysToElement(WebDriver driver, WebElement element, String text) {
		waitForElementToLoad(driver, element);
		element.sendKeys(text);

	}

	/**
	 * This method waits for the specified element to load
	 * 
	 * @param element
	 */
	public void waitForElementToLoad(WebDriver driver, WebElement element) {
		long timeoutInSeconds = 30;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method waits for the specified element to load
	 * 
	 * @param element
	 */
	public void waitForElementToLoadByLocator(WebDriver driver, By by) {
		long timeoutInSeconds = 60;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * This method waits for the list of elements to load
	 * 
	 * @param element
	 */
	public void waitForAllElementsToLoad(WebDriver driver, List<WebElement> elementList) {
		try {
			long timeoutInSeconds = 10;
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to click the coordinates of the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void clickElementCoordinates(WebDriver driver, WebElement element) {
		waitForElementToLoad(driver, element);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method is used to click the element using actions class
	 * 
	 * @param driver
	 * @param element
	 */
	/*
	 * public void clickElementUsingActions(WebDriver driver, WebElement element) {
	 * waitForElementToLoad(driver, element); Actions actions = new Actions(driver);
	 * actions.moveToElement(element).click().build().perform(); }
	 */
	
	public void clickElementUsingActions(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}


	/**
	 * Wait for page load
	 * 
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	/**
	 * This method is used to check if the element is displayed
	 * 
	 * @param driver
	 * @param element
	 * @return boolean
	 */
	public boolean isElementDisplayed(WebDriver driver, WebElement element) {
		boolean status = false;
		waitForElementToLoad(driver, element);
		status = element.isDisplayed();
		return status;
	}

	/**
	 * This method is used to check if the element is displayed without waiting for
	 * it
	 * 
	 * @param driver
	 * @param element
	 * @return boolean
	 */
	public boolean isElementLocated(WebDriver driver, By by) {
		boolean status = false;
		try {
			waitForElementToLoadByLocator(driver, by);
			status = driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	/**
	 * This method is used to check if the element is selected
	 * 
	 * @param driver
	 * @param element
	 * @return boolean
	 */
	public boolean isElementSelected(WebDriver driver, WebElement element) {
		boolean status = false;
		status = element.isSelected();
		return status;
		/*
		 * boolean status = false; if (!element.isSelected()) element.click(); return
		 * status;
		 */
	}

	/**
	 * This method refreshes the current page
	 * 
	 * @param driver
	 */
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * This method is used to wait for fixed time
	 * 
	 * @throws InterruptedException
	 */
	public void waitForFixedTime(String waitTime) throws InterruptedException {
		if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_VERY_SMALL_DMP)) {
			Thread.sleep(1000);
		} else if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_SMALL_DMP)) {
			Thread.sleep(2000);
		} else if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_VERY_SMALL)) {
			Thread.sleep(3000);
		} else if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_SMALL)) {
			Thread.sleep(5000);
		} else if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_MEDIUM)) {
			Thread.sleep(10000);
		} else if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_LONG)) {
			Thread.sleep(15000);
		} else if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_VERY_LONG)) {
			Thread.sleep(25000);
		} else if (waitTime.equalsIgnoreCase(BrowserConstants.WAIT_EXCEPTIONAL_CASES)) {
			Thread.sleep(45000);
		}
	}

	public void selectTab(WebDriver driver, WebElement element) throws InterruptedException {

		element.sendKeys(Keys.TAB);

	}

	
	/**
	 * This method is used to check if the element has disappeared from the page
	 * 
	 * @param driver
	 * @param element
	 */
	public boolean waitForElementToDisappear(WebDriver driver, WebElement element) {
		boolean status = false;
		WebDriverWait wait =new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.invisibilityOf(element));
		return status;
	}
	/**
	 * This method is used to check if the element has disappeared from the page
	 * 
	 * @param driver
	 * @param element
	 */
	public boolean waitForLocatorToDisappear(WebDriver driver, By byelement) {
		boolean status = false;
		WebDriverWait wait = new WebDriverWait(driver,180);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byelement));
		return status;
	}
	public boolean waitForLocatorToappear(WebDriver driver, By byelement) {
		boolean status = false;
		WebDriverWait wait = new WebDriverWait(driver,180);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byelement));
		return status;
	}
	/**
	 * This method is used to fetch the specified attribute value from the element
	 * 
	 * @param element
	 * @param attributeName
	 * @return String
	 */
	public String getAttributeValue(WebElement element, String attributeName) {
		String attributeValue = element.getAttribute(attributeName);
		return attributeValue;
	}

	/**
	 * This method is used to fetch the rows from table
	 * 
	 * @return List<WebElement>
	 */
	public List<WebElement> fetchRowsFromTable(WebElement element) {
		List<WebElement> lstTableRows = new ArrayList<>();
		lstTableRows = element.findElements(By.tagName("tr"));
		return lstTableRows;
	}

	/**
	 * This method is used to wait till element attribute changes
	 */
	public void waitForElementAttributeToChange(WebDriver driver, WebElement element, String attributeName,
			String attributeValue) {
		long timeoutInSeconds = 3;
		String attribute = element.getAttribute(attributeName);
		if (attribute != null && attribute.length() > 0) {
			if (!attribute.contains(attributeValue)) {
				WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
				wait.until(ExpectedConditions.attributeContains(element, attributeName, attributeValue));
			}
		}
	}

	/**
	 * This method is used to validate the text of the success message
	 * 
	 * @param driver
	 * @param dataMap
	 * @param element
	 * @return boolean
	 */
	public boolean validateSuccessMessage(WebDriver driver, WebElement element, Map<String, String> dataMap) {
		boolean status = false;
		String expectedMessage = dataMap.get("successMessage");
		String actualMessage = getTextOfElement(driver, element);
		System.out.println("expectedMessage:" + expectedMessage);
		System.out.println("actualMessage:" + actualMessage);
		if (expectedMessage.equalsIgnoreCase(actualMessage)) {
			status = true;
		}
		return status;
	}

	/**
	 * This method clicks the specified WebElement on Engine
	 * 
	 * @param driver
	 * @param WebElement
	 */
	public void clickButtonEngine(WebDriver driver, WebElement element) {
		long timeoutInSeconds = 5;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				String attribute = element.getAttribute("disabled");
				if (attribute == null) {
					return true;
				} else {
					return false;
				}
			}
		});
		element.click();
	}

	/**
	 * This method is used to select the option by visible text
	 * 
	 * @param element
	 * @param visibleText
	 */
	public void selectByVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	public void selectByVisibleTextNationality(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	public void selectByIndexNumber(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectCountry(List<WebElement> element, String Country) throws Exception {

		List<WebElement> we = element;

		for (WebElement e1 : we) {
			

			String cc = e1.getText();
			if (cc.equals(Country)) {
				System.out.println("Select Country:"+e1.getText());
				e1.click();
				break;
			}
		}

	}

	/**
	 * This method waits for the specified text to be present in the element
	 * 
	 * @param driver
	 * @param element
	 * @param textToAppear
	 */
	public void waitForElementTextToBe(WebDriver driver, WebElement element, String textToAppear) {
		long timeoutInSeconds = 50;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
	}

	/**
	 * This method waits for the specified text to be present in the element
	 * 
	 * @param driver
	 * @param element
	 * @param textToAppear
	 */
	public void waitForElementListToAppear(WebDriver driver, List<WebElement> lstElement) {
		long timeoutInSeconds = 10;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(sizeOfElementListGreaterThanZero(lstElement));
	}

	/**
	 * This method is used to get the focus to a particular element
	 * 
	 * @param driver
	 * @param element
	 */
	public void getFocusOnElement(WebDriver driver, WebElement element, WebElement subelement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		actions.moveToElement(subelement).build().perform();
		subelement.click();
	}

	public static ExpectedCondition<List<WebElement>> sizeOfElementListGreaterThanZero(
			final List<WebElement> lstElement) {
		return new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver) {

				// KEY is here - we are "failing" the expected condition
				// if there are less than elementsCount elements
				if (lstElement == null || lstElement.size() == 0) {
					return null;
				}
				System.out.println("Element list size from page utils: " + lstElement.size());
				return lstElement;
			}
		};
	}

	/**
	 * This method is used to check if the specified chart elements are present
	 * 
	 * @return boolean
	 */
	public boolean arePageElementsPresent(WebDriver driver, List<WebElement> lstElement, int size) {
		boolean status = false;
		waitForAllElementsToLoad(driver, lstElement);
		System.out.println("chart size:" + lstElement.size());
		if (lstElement != null) {
			if (size != 0) {
				if (lstElement.size() == size) {
					status = true;
				}
			} else {
				if (lstElement.size() > size) {
					status = true;
				}
			}
		}
		return status;
	}

	/**
	 * This method is used to switch to another window to perform required operation
	 * 
	 * @param driver
	 */
	public void switchWindow(WebDriver driver) {
		String currentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equalsIgnoreCase(currentWindow)) {
				driver.switchTo().window(windowHandle);
			}
		}
	}

	public void switchWindows(WebDriver driver) {
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> win1 = set1.iterator();
		parent = win1.next();
		child = win1.next();
		driver.switchTo().window(child);
	}

	public void switchToParentWindow(WebDriver driver) {
		driver.switchTo().window(parent);
	}

	/**
	 * This method waits for the specified element to load
	 * 
	 * @param element
	 */
	public void waitForUrlToChange(WebDriver driver, WebElement element) {
		long timeoutInSeconds = 15;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	

	/**
	 * This method is used to make the element visible on the page using
	 * JavaScriptExecutor
	 * 
	 * @param driver
	 * @param element
	 */
	public void makeElementVisible(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		jse.executeScript(js, element);
	}

	/**
	 * This method is used to fetch the element list from within another webelement
	 * using tagname
	 * 
	 * @return List<WebElement>
	 */
	public List<WebElement> fetchElementListUsingTagNames(WebElement element, String tagName) {
		List<WebElement> lstTableRows = new ArrayList<>();
		lstTableRows = element.findElements(By.tagName(tagName));
		return lstTableRows;
	}

	/**
	 * This method is used to fetch the element from within another webelement using
	 * tagname
	 * 
	 * @return WebElement
	 */
	public WebElement fetchElementUsingTagNames(WebElement element, String tagName) {
		WebElement subElement = null;
		try {
			subElement = element.findElement(By.tagName(tagName));
		} catch (Exception e) {
		}
		return subElement;
	}

	/**
	 * This method is used to wait till element attribute changes
	 */
	public void waitForElementAttributeNotToBe(WebDriver driver, WebElement element, String attributeName,
			String attributeValue) {
		long timeoutInSeconds = 3;
		String attribute = element.getAttribute(attributeName);
		if (attribute != null && attribute.length() > 0) {
			if (!attribute.contains(attributeValue)) {
				WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
				wait.until(ExpectedConditions
						.not(ExpectedConditions.attributeContains(element, attributeName, attributeValue)));
			}
		}
	}

	/**
	 * This method perform mouseOver the specified WebElement
	 * 
	 * @param WebElement
	 */
	public void mouseAction(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);

		actions.build().perform();
	}
	
	public String currentData() {
		Date mDate = new Date();

		DateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");

		String Date = date.format(mDate);
		System.out.println(Date);
		return Date;
	}

	public static String getDateAfterMonths(int months) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, months); // +months
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");  
		String Date = sdf.format(cal.getTime()); 
		
		return Date;
		}
	
	public static String getDateBeforeMonths(int months) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -months); // +months
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");  
		String Date = sdf.format(cal.getTime()); 
		
		return Date;
		}
	public void scrollDown(WebDriver driver) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
	}
	public void scrollDown2(WebDriver driver) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,200)", "");
	}
	public void scrollUp(WebDriver driver) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-400)");
	}
	public static String timestamp() {
		   return new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
		}
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.MONTH, months); // +months
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");  
		String Date = sdf.format(cal.getTime()); 
		
		return Date;
		}
	
	public void passScreenCapture(WebDriver driver) throws IOException, InterruptedException, AWTException
    {
    	//Current Date and Time
		DateFormat format = new SimpleDateFormat("dd_MMM_yyyy");
	    Date date = new Date();
	      String currentDateTime = format.format(date);
	       String[] getDetails = currentDateTime.split("_");
	        System.out.println("Split the Date::" + getDetails[1]);
	       String all=""+currentDateTime;
	       String month=""+getDetails[1];
	       //String year=""+getDetails[2];
	      //File file = new File("D:/Screenshots/"+year);
	        File file1 = new File("D:/Screenshots/"+month);
	        File file2 = new File(file1+"/"+all);
	         //file.mkdir();
	         file1.mkdir();
	         file2.mkdir();
        //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		//Date dt = new Date();
		//System.out.println(dateFormat.format(dt));

        //Screenshot
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(file2+"/Screenshot_"+timestamp()+".png"));
        
		//Full Screenshot Page scrolls
		/*final Screenshot screenshot = new AShot().shootingStrategy(
        new ViewportPastingStrategy(1500)).takeScreenshot(driver);
        final BufferedImage image = screenshot.getImage();
        ImageIO.write(image, "PNG", new File("D:\\Folder\\"+dateFormat.format(dt)+".png"));*/
        
        /*Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screenShot, "JPG", new File("D:\\Screenshots\\"+dateFormat.format(dt)+".png"));*/
               
        Thread.sleep(2000);
		
    }
	public static String TodayName() {
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, 0);
		  return new SimpleDateFormat("EEEE").format(cal.getTime());
		 
		}
	public  void waitForVisibilityOf(WebDriver driver,WebElement webElement) {
		WebDriverWait webDriverWait = new WebDriverWait(driver,30);
		webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public static String firstOne(String str) {

		return str.length() < 1 ? str : str.substring(0, 1);

	}

	public static String firstTwo(String str) {

		return str.length() < 2 ? str : str.substring(0, 2);

	}
	
	
	 public void selectDropDownOption(String value,List<WebElement> optionsList) throws InterruptedException
	 {
		
		List<WebElement> options = optionsList;
		System.out.println(options.size());
	

		for (WebElement e1 : options) {
			if (e1.getText().equals(value)) {
				Thread.sleep(1000);
				e1.click();
				break;

			}
		}
	     	
	 }
	 
	 public void clickOnMegaMenu(WebDriver driver,String menu, String mainMenu) {
	        Actions actions = new Actions(driver);
	        
	        // Format the XPath for the main menu
	        String mainCategoryXpath = Locators.megaMenuXpath(mainMenu);
	        
	        // Wait until the main category is clickable
	        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(mainCategoryXpath)));
	        
	        // Move to the main category element
	        WebElement mainCategoryElement = driver.findElement(By.xpath(mainCategoryXpath));
	        actions.moveToElement(mainCategoryElement).perform();
	        
	        // Format the XPath for the submenu
	        String subMenuXpath = Locators.productMainMenuXpath(menu);
	        
	        // Wait until the submenu is clickable
	        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath(subMenuXpath)));
	        
	        // Move to the submenu element and click it
	        WebElement subMenuElement = driver.findElement(By.xpath(subMenuXpath));
	        actions.moveToElement(subMenuElement).click().perform();
	    }
	 
	 
	 public void handlePopup(WebDriver driver) {
	        // Find elements matching the iframe locator
	        List<WebElement> popups = driver.findElements(By.xpath("//*[@id='cmessage_form_iframe']"));
	        if (popups.size() > 0) {
	            // Switch to the iframe
	            driver.switchTo().frame("cmessage_form_iframe");
	            System.out.println(driver.findElements(By.xpath("//*[@class='popup_header']//*[@cx='10']")).size());

	            // If the close button is found, click it
	            if (driver.findElements(By.xpath("//*[@class='popup_header']//*[@cx='10']")).size() > 0) {
	                System.out.println(driver.findElements(By.xpath("//*[@class='popup_header']//*[@cx='10']")).size());
	                driver.findElement(By.xpath("//*[@class='popup_header']//*[@cx='10']/../..")).click();
	                // Switch back to the default content
	                driver.switchTo().defaultContent();
	            }
	        }
	        // Ensure to switch back to the default content
	        driver.switchTo().defaultContent();
	    }
	 
	 public void moveToElement(WebDriver driver,WebElement element) {
	        // Use JavaScript to scroll the element into view
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	    }
	 
	}


class Locators {
    public static String megaMenuXpath(String mainMenu) {
        // Provide the appropriate XPath format for the main menu
        return String.format("//xpath-to-main-menu[contains(text(), '%s')]", mainMenu);
    }

    public static String productMainMenuXpath(String menu) {
        // Provide the appropriate XPath format for the submenu
        return String.format("//xpath-to-sub-menu[contains(text(), '%s')]", menu);
    }
}

	



