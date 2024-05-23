@SmokeTest
Feature: Check Approved Deduction Functionality

  Background: 
    Given User launch url and navigates to login screen

  @OpenToThreshold
  Scenario: verify the Make any invoice directly to threshold credit (Write off) and the invoice should be moved to approved deduction tab
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then invoice is moved open to threshold credit
    Then Any invoice directly to threshold credit and the invoice should be moved to approved deduction tab
    
    
    @BulkResolutionApproved
  Scenario Outline: In Approved deduction tab resolution to approve should be reacted accordingly!
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file and upload "<FileName>"
    And go to validation Tab
    Then change the status open to Approved
    And go to approved deduction
    Then resolution to approve should be reacted accordingly in approved deduction tab
    Examples: 
      | FileName							 |
      | BulkResolutionFile    |
    
     @CreditReport1
  Scenario Outline: verify credit report details in approved deduction
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then create the new remittance file and upload "<FileName>"
    Then go to validation Tab
    Then change the status open to Approved
    Then go to approved deduction
    Then validate the credit report details
    Then download the file and check the filename and extension
    
    Examples: 
      | FileName |
      | CreditReport    |
      
      
      
      
    @CreditReport_MultipleInvoices
  Scenario Outline: verify credit report details in approved deduction
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then create the new remittance file and upload "<File1>"
   Then resolution to approve should be reacted accordingly in validation tab
    
    Then go to remittance Tab
    Then create the new remittance file and upload "<File2>"
    Then resolution to approve should be reacted accordingly in validation tab
    
    Then go to remittance Tab
    Then create the new remittance file and upload "<File3>"
    Then resolution to approve should be reacted accordingly in validation tab
    
    Then go to approved deduction
    Then select the multiple invoices and validate the credit report details
     |File1|<File1>|
     |File2|<File2>|
     |File3|<File3>|
   
    
    Examples: 
      | File1 |File2|File3|
      | CreditReportFile1    |CreditReportFile2|CreditReportFile3|
