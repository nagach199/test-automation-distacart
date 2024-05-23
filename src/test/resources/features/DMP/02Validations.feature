@SmokeTest
Feature: Check Validation Functionality

  Background: 
    Given User launch url and navigates to login screen

  #Used In Flow Check
  #Scenario Outline: verify the invoice number status by default it's open state
  #When user should enter username
  #When user should enter password
  #Then user click on sign in button to login into application
  #Then verify the invoice number status by default it's open state "<FileName>"
  #Then change the status open to awaiting approval
  #Examples:
  #|FileName|
  #| Automation_remittance_template|
  @OpenTOAwaiting
  Scenario: Verify invoice is open to awaiting approval and ensure invoice moved to awaiting approval section
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then Make any invoice which is open to awaiting approval and ensure invoice moved to awaiting approval section

  @Split @PhaseII
  Scenario: In Validation section by selecting single invoice and clicking split option means a confirmation pop up should comes up!
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then once declined popup warning message should not popped out for upcoming splits

  @SplitFunctionality @PhaseII
  Scenario Outline: validate the split functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file and upload "<FileName>"
    Then check the split functionality in validation tab

    Examples: 
      | FileName |
      | Split    |
      
      
   @BlukUpdate
  Scenario Outline: Add the debitdocument number and verify it.
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file and upload "<FileName>"
    Then add the debit document number using bulk update
    Then added debit memo should be impacted in changelog MemoPage
    Then get the list of approvers should visible in pop up
    And by selecting any one approvers the invoice approver should be changed to selected approver in bulk update
    |approverName|<approverName>|
    Then check the selected approver invoice in awiting approver section
    |approverName|<approverName>|
    Then reassign approvers in awaiting approval is selected means only approvers should be changed! Processing Result should be still Awaiting Approval
    |reAssignApproverName|<reAssignApproverName>|
    |approverName|<approverName>|
    And change the status Awaiting approvel to Approved
    And go to approved deduction
    Then change the approver name using reassign approver and invoice should be moved to awaiting approval
    |reAssignApproverName_Approved|<reAssignApproverName_Approved>|
    |reAssignApproverName|<reAssignApproverName>|
    |approverName|<approverName>|
    Then go to awaiting approval
    Then check the selected approver invoice in awiting approver section
    |reAssignApproverName_Approved|<reAssignApproverName_Approved>|
    Examples: 
      | FileName							 |approverName|reAssignApproverName|reAssignApproverName_Approved|
      | DebitDocumentNumber    |Unit Test|Inmar User|Unit Test|
      
      
      
      
      @BulkResolution
  Scenario Outline: In validation tab resolution to approve should be reacted accordingly!
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file and upload "<FileName>"
    Then resolution to approve should be reacted accordingly in validation tab
    And go to approved deduction
    Then validate the invoice number should be reacted accordingly
    Examples: 
      | FileName							 |
      | BulkResolutionFile    |
      
  # @AssignApprovers
  # Scenario Outline: Add the debitdocument number and verify it.
   #  When user should enter username
   #  When user should enter password
     #Then user click on sign in button to login into application
     #And create the new remittance file and upload "<FileName>"
     #Then get the list of approvers should visible in pop up
     #And by selecting any one approvers the invoice approver should be changed to selected approver in bulk update
     #|approverName|<approverName>|

    # Examples: 
     #  | FileName								|approverName|
      # | DebitDocumentNumber	    |Unit Test|
   
