package com.inmar.automation.utils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.inmar.automation.configs.BrowserConstants;
import com.inmar.automation.configs.DataConstants;


import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {
	public static WebDriver  driver = null;
	public static String applicationUrl = null;
	
	 static int totalScenarios = 0;
	 static int scenariosCompleted = 0; 
	 private static boolean hasInitialized = false;
	 
	 private static ConfigReader configReader; 
		static Properties prop;
	
	 private static void initializeSystemProperties() {
		 configReader = new ConfigReader();
			prop = configReader.init_prop();
			String os = prop.getProperty("OSNAME");
		 totalScenarios = Integer.parseInt(System.getProperty("totalScenarios", "0"));
		 System.setProperty("browser", System.getProperty("browser", "chrome"));
		 if(os.equals("Linux"))
		 {
		   System.setProperty("pathToDriver", System.getProperty("pathToDriver", "/app/linuxdriver/chromedriver"));	
				 
		 }
		 else
		 {
			 
			// System.setProperty("pathToDriver", System.getProperty("pathToDriver", "C:\\Myfiles\\Jars\\chromedriver.exe")); 
		 }
			
		    System.setProperty("foreground", System.getProperty("foreground", "false"));
			System.setProperty("target", System.getProperty("target", "ENG"));
			System.setProperty("buildName", System.getProperty("buildName", "FINTECH"));
			//System.setProperty("environment", System.getProperty("environment", "QA"));
			System.setProperty("application", System.getProperty("application", "DMP"));
			//System.setProperty("url", System.getProperty("url", "https://beta-deductionslink.inmar.com/"));
			System.setProperty("seleniumGridUrl", System.getProperty("seleniumGridUrl", "none"));
			//System.setProperty("seleniumGridUrl", System.getProperty("seleniumGridUrl", "http://localhost:4444/wd/hub"));
			//System.setProperty("headless", "true");
			
			
			
			System.setProperty("verbose", System.getProperty("verbose", "false"));

		    if (!hasInitialized) {
		        hasInitialized = true;
	           
	            System.out.printf(
	                    "[DEBUG] Using DMP documents path: %s\n",
	                   DataConstants.getDocumentPath("some-file.pdf"  )
	                   
	            );
	        }
	    }
	 
	
	 /**
	     * This method is used to return the browser instance
	     */
	    public static WebDriver getDriverInstance() {
	        if (driver == null) {
	            driver = launchBrowser(null);
	        }
	        return driver;
	    }

	@Before
	public static void launchUrl(Scenario scenario) {
		
		
		launchBrowser(scenario);
		applicationUrl = System.getProperty("url");
		getApplicationUrl();
		driver.get(applicationUrl);
		//driver.manage().window().maximize();
		Dimension dimension = new Dimension(1500, 1080);
		driver.manage().window().setSize(dimension);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Cookie cookie = new Cookie("zaleniumMessage", scenario.getName());
		driver.manage().addCookie(cookie);
	}
	
	 /**
	     * Get the remote WebDriver instance
	     * @param capabilities Desired capabilities
	     * @param scenario Scenario info
	     * @return the driver or null
	     */
	    private static WebDriver getRemoteDriver(MutableCapabilities capabilities, Scenario scenario) {
	        String buildName = System.getProperty("buildName");
	        capabilities.setCapability("build",
	                buildName != null ? buildName :
	                System.getProperty("url") + " - " + System.getProperty("application")
	                
	        );
	        capabilities.setCapability("--startTunnel",false);
	        System.out.println(System.getProperty("url") + " - " + System.getProperty("application"));
	        capabilities.setCapability("name", scenario.getName());
	        System.out.println(scenario.getName());
	        String seleniumGridUrl = System.getProperty("seleniumGridUrl");
	        System.out.println(System.getProperty("seleniumGridUrl"));
	        System.out.printf("Using Browser: %s\n", capabilities.getBrowserName());
			/*
			 * try {
			 * 
			 * return new RemoteWebDriver(new URL(seleniumGridUrl), capabilities);
			 * 
			 * } catch (UnreachableBrowserException e) {
			 * Assert.fail("UnreachableBrowserException: " + e.getMessage()); } catch
			 * (MalformedURLException e) { Assert.fail("MalformedURLException: " +
			 * seleniumGridUrl); } catch (WebDriverException e) {
			 * Assert.fail("WebDriverException: " + e.getMessage()); }
			 */
	        return null;
	    }
	    
	    /**
	     * This method is used to launch the browser instance
	     */
	    private static WebDriver launchBrowser(Scenario scenario) {
	        initializeSystemProperties();
	        String browser = System.getProperty("browser");
	      //  String driverPath = System.getProperty("pathToDriver");
	        boolean useGrid = !System.getProperty("seleniumGridUrl").equals("none");


	        switch (browser.toLowerCase()) {
	            case BrowserConstants.CHROME: {
	                Map<String, Object> prefs = new HashMap<>();
	                prefs.put("download.default_directory", DataConstants.getDownloadsPath_linux());
	                System.out.println("DataConstants.getDownloadsPath()::"+DataConstants.getDownloadsPath_linux());
	                prefs.put("profile.default_content_settings.popups", 0);
	                prefs.put("download.prompt_for_download", false);
	                prefs.put("download.directory_upgrade", true);
	                //WebDriverManager.chromedriver().linux().setup();
	                ChromeOptions options = new ChromeOptions();
	                //options.setExperimentalOption("prefs", prefs);
	                options.addArguments("--window-size=1366,768");
	            	options.addArguments("--disable-dev-shm-using");
			        options.addArguments("--disable-setuid-sandbox");
			         options.addArguments("--disable-dev-shm-usage");
			         options.addArguments("--no-sandbox");	
	            	
	            	//WebDriverManager.chromedriver().linux().setup();
	    			 /* WebDriverManager.chromedriver().win().setup();
	    		      ChromeOptions options = new ChromeOptions();
	    		      options.setPageLoadStrategy(PageLoadStrategy.NONE); 
	    		      options.addArguments("start-maximized");
	    		      options.setExperimentalOption("useAutomationExtension", false);
	    		      options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	    		      options.addArguments("--no-sandbox"); 
	    		      options.addArguments("--disable-infobars");
	    			  options.addArguments("--disable-dev-shm-usage");
	    		      options.addArguments("--disable-browser-side-navigation");
	    		      options.addArguments("--disable-gpu");*/
	    		      
	    		      
	    		      String downloadFilepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
	  						+ File.separator + "resources" + File.separator + "DownloadFiles";
	  		      HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	  		      chromePrefs.put("profile.default_content_settings.popups", 0);
	  		      chromePrefs.put("download.default_directory", downloadFilepath);
	  		       //options.setExperimentalOption("prefs", chromePrefs);
	  		    
	    		      //driver = new ChromeDriver(options); 
	    		     

	                if (useGrid) {
	                	  driver = getRemoteDriver(options, scenario);
	                } else {
	                   // options.setHeadless(System.getProperty("headless", "false").equals("true"));
	                   
	                    driver = new ChromeDriver(options);
	                }
	                break;
	            }
	            case BrowserConstants.FIREFOX: {
	                FirefoxOptions options = new FirefoxOptions();
	                FirefoxProfile profile = new FirefoxProfile();

	                // Reference: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
	                String[] allowedMimeTypes = new String[]{
	                        "application/vnd.ms-excel", // .xls
	                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xlsx
	                        "application/msword", // .doc
	                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .docx
	                        "application/vnd.ms-powerpoint", // .ppt
	                        "application/vnd.openxmlformats-officedocument.presentationml.presentation", // .pptx
	                        "text/csv", // .csv
	                        "application/pdf", // .pdf
	                        "text/plain" // .txt
	                };

	                profile.setPreference("browser.download.folderList", 2);
	                profile.setPreference("browser.download.manager.showWhenStarting", false);
	                profile.setPreference("browser.download.dir", DataConstants.getDownloadsPath());
	                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", String.join(";", allowedMimeTypes));
	                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	                profile.setPreference( "browser.download.manager.showWhenStarting", false );
	                profile.setPreference( "pdfjs.disabled", true );

	                options.setProfile(profile);

	                if (useGrid) {
	                	
	                  // driver = getRemoteDriver(options, scenario);
	                } else {
	                    options.addArguments("--headless");
	                   // options.setBinary(driverPath);
	                    driver = new FirefoxDriver(options);
	                }
	                break;
	            }
	           
	        }

	        assert driver != null;
	        driver.manage().deleteAllCookies();
	        return driver;
	    }
    
	/* public static WebDriver getDriverInstance() {
			if (driver == null) {
				driver = launchBrowser();
			}
			
			
			
			
			return driver;
		}
	 
	 @Before
		public static void launchUrl(){
			launchBrowser();
			getApplicationUrl();
	        driver.get(applicationUrl);
			driver.manage().window().maximize();
		 
	 }
	public static WebDriver launchBrowser() {
		//String browser = System.getProperty("browser");
		String browser = "chrome1";
		DesiredCapabilities dc = new DesiredCapabilities();
     if(browser.equalsIgnoreCase("chrome1")) {
			
			dc.setBrowserName("chrome");
			
			
		}
		//Chrome
		if (browser.equalsIgnoreCase(BrowserConstants.CHROME)) {
			String userdir = System.getProperty("pathToDriver");
					
			
			//WebDriverManager.chromedriver().linux().setup();
			WebDriverManager.chromedriver().win().setup();
		      ChromeOptions options = new ChromeOptions();
		      options.setPageLoadStrategy(PageLoadStrategy.NONE); 
		      options.addArguments("start-maximized");
		      options.setExperimentalOption("useAutomationExtension", false);
		      options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		      options.addArguments("--no-sandbox"); 
		      options.addArguments("--disable-infobars");
			// options.addArguments("--headless");
		     // options.setHeadless(System.getProperty("headless", "false").equals("true"));
		      options.addArguments("--disable-dev-shm-usage");
		      options.addArguments("--disable-browser-side-navigation");
		      options.addArguments("--disable-gpu");
		      driver = new ChromeDriver(options); 
		      driver.manage().deleteAllCookies();
		      
		      
		}
		if(browser.equalsIgnoreCase(BrowserConstants.EDGE))
		{
			//System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/driver/msedgedriver.exe");

			
			WebDriverManager.edgedriver().setup();
			
			 driver = new EdgeDriver();
			driver.manage().window().maximize();

			//Deleting all the cookies
			driver.manage().deleteAllCookies();
		}
		//Firefox
		if (browser.equalsIgnoreCase(BrowserConstants.FIREFOX)) {
            String userdir = System.getProperty("pathToDriver");
            System.setProperty("webdriver.gecko.driver",userdir);
           driver = new FirefoxDriver();
       }
		
		//
		
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return driver;
	}*/

	public static void getApplicationUrl() {
		/*String target = System.getProperty("target");
		String environment = System.getProperty("environment");
		String application = System.getProperty("application");*/
		//String environment = System.getProperty("ENVURL");
		String target ="ENG";
		String environment = "QA";
		String application = "DMP";

		if (target.equalsIgnoreCase(BrowserConstants.ENG)) {
			switch (application.toUpperCase()) {

			case "DMP":
				// applicationUrl = System.getProperty("url");
				switch (environment) {
				case "PRD":
					
					
					applicationUrl = BrowserConstants.DMP_PRD_URL;
					break;
				case "UAT":
					applicationUrl = BrowserConstants.DMP_UAT_URL;
					
					break;
				case "QA":
					applicationUrl = BrowserConstants.DMP_QA_URL;
					
					break;

				default:
					applicationUrl = BrowserConstants.DMP_PRD_URL;
					break;
				}
				break;
			
		}
				
				
			
				
			
		}
	}
	
	
}