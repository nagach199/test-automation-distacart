package com.inmar.automation.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.inmar.automation.utils.SendMail;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)


@CucumberOptions(features = "src/test/resources/features/DISTACART/addtocart.feature",
glue = { "com.inmar.automation.stepdefinations","com.inmar.automation.utils" },

tags = "@DIS_healthcheck", 


//plugin = { "pretty", "html:target/cucumber-report-html", "json:target/cucumber-reports/cucumber.json",
plugin = {"pretty", "html:Reports/cukes/report.html", "json:Reports/cukes/cucumber.json",       
       // "com.cucumber.listener.ExtentCucumberFormatter:Reports/extent-report/AutomationReport.html",
        //"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/extent-report/index.html",
        
"junit:Reports/cukes/junit.xml" 
		})


	 public class Runner {
	
	
	/*@AfterClass
	public static void SendingMail()
	{
		Runtime r=Runtime.getRuntime();
		r.addShutdownHook(new Thread()
				{
			public void run(){
				SendMail sm=new SendMail();
				try{
					sm.mail();
					System.out.println("Report has been sent");
				}
				catch(IOException e){
					e.printStackTrace();
				}
				
			}
				});
		
		
	}
	*/
	 @AfterClass
	    public static void executeIndexJS() throws IOException, InterruptedException {
	        ProcessBuilder processBuilder = new ProcessBuilder("node", System.getProperty("user.dir") + "/index.js");
	        processBuilder.directory(new java.io.File(System.getProperty("user.dir")));
	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();
	        System.out.println("'index.js' process exited with code " + exitCode);
	    }
}
