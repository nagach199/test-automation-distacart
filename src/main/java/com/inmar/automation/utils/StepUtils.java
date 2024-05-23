package com.inmar.automation.utils;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.Scenario;


public class StepUtils { 

	/**
	 * This method is used to get Map from the datatable passed from Cucumber 
	 * @param dataTable
	 * @return Map<String, String>  
	 */
	protected Map<String, String> getDataAsMap(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		return dataMap;
	} 
     
	   

	/**
	 * This method compares and validates the expected and actual data are equal or not 
	 * @param expectedString
	 * @param actualString
	 * @return
	 */
	public boolean validateStringData(String expectedString, String actualString) {
		boolean status = false;
		if(expectedString.equalsIgnoreCase(actualString)) {
			status = true;
		}
		return status;
	}
	
	
	
		
	
}
