package com.inmar.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class HookUtils {
	private ConfigReader configReader;
	static Properties prop;

	@Before
	public void logInfo(Scenario scenario) {
		if (BrowserUtils.totalScenarios > 0) {
			System.out.printf("[STARTED][%d/%d] %s\n", BrowserUtils.scenariosCompleted, BrowserUtils.totalScenarios,
					scenario.getName());
		} else {
			System.out.printf("[STARTED] %s\n", scenario.getName());
		}
	}

	/**
	 * This cucumber hook closes the browser after each scenario
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@After
	public void cleanUp(Scenario scenario) throws IOException {
		Cookie cookie = new Cookie("zaleniumTestPassed", scenario.isFailed() ? "false" : "true");
		BrowserUtils.driver.manage().addCookie(cookie);

		

		// if (scenario.isFailed()) {
	//	scenario.embed(((TakesScreenshot) BrowserUtils.driver).getScreenshotAs(OutputType.BYTES), "image/png");
		byte[] screenshotBytes = ((TakesScreenshot) BrowserUtils.driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshotBytes, "image/png", "Screenshot");
		// }

		String timeStamp;
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		String screenshotName = (timeStamp + scenario.getName().replaceAll(" ", "_"));

		if (scenario.isFailed()) {
			File sourcePath = ((TakesScreenshot) BrowserUtils.driver).getScreenshotAs(OutputType.FILE);

			File destinationDir = new File("./target/cucumber-reports/extent-report/ScreenShots");
			if (!destinationDir.exists()) {
				destinationDir.mkdir();
			}
			File destinationPath = new File(destinationDir + "/" + screenshotName + ".png");

			try {
				FileUtils.copyFile(sourcePath, new File(destinationPath + "/"));
				//Reporter.addScreenCaptureFromPath("ScreenShots/"+screenshotName + ".png");
			} catch (IOException e) {
			}

		}
		//Reporter.setSystemInfo("Browser", "chrome");

		// WebDriver driver = null;
		// driver.findElement(By.cssSelector("img.dropdown-toggle")).click();
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("=///text()[normalize-space(.)='LOGOUT']/parent::")).click();
		// Thread.sleep(1000);

		BrowserUtils.driver.quit();

	}

}