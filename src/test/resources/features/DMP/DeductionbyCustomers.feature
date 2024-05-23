@SmokeTest
Feature: Check Deduction By Customers Functionality

  Background: 
    Given User launch url and navigates to login screen

  @Months @PhaseII
  Scenario: validate the header columns for months in deduction by customes
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then go to deduction by customers tab
    Then verify the header columns for months in deduction by customes

  @Weekly @PhaseII
  Scenario: validate the header columns for weekly in deduction by customes
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then go to deduction by customers tab
    Then verify the header column for weeks in deduction by customes
    
    
    
    @Refresh @PhaseII
  Scenario: validate the refresh functionality and header names
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then go to deduction by customers tab
    Then verify the refresh functinality and header names
    
    
    @FileName @PhaseII
  Scenario Outline: validate the filename and file extension
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then go to deduction by customers tab
    Then export the file and check the filename and extension
    Then downloaded file path:-please verify the data
     |DownloadFilePath|<DownloadFilePath>| 
     
      Examples: 
      | DownloadFilePath|
      |C:\WorkSpace2\test_automation_inmar_dmp\src\main\resources\DownloadFiles|
