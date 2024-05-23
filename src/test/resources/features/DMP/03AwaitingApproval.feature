@SmokeTest
Feature: Check AwaitingApproval Functionality

  Background: 
    Given User launch url and navigates to login screen

  #Used In Flow Check
  #Scenario: verify the invoice number status by default it's awaiting approval state
  # When user should enter username
  # When user should enter password
  # Then user click on sign in button to login into application
  #Then verify the invoice number status by default it's awaiting approval state
  # Then change the status ap to dispute
  @OpenTOAwaitingA
  Scenario: Filter the invoice in approval section which made open to awaiting approval and ensure filter is working good! and the invoice should contain changelog
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then Filter the invoice in approval section which made open to awaiting approval and ensure filter is working good! and the invoice should contain changelog
    And validate invoice memo page should contain awaiting approval processing status

  @AwaitingToDispute
  Scenario: validate the invoice in approval section which made awaiting approval to dispute
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And change the status awaiting approval to dispute

  @SplitFunctionalityAwaiting @PhaseII
  Scenario Outline: validate the split functionality in Awaiting Approval
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file and upload "<FileName>"
    And change the status for awaiting approval
    Then check the split functionality in awaiting approval

    Examples: 
      | FileName                |
      | Split_awaiting_approval |

   @BulkResolutionAW
  Scenario Outline: In Awaiting Approval tab resolution to approve should be reacted accordingly!
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file and upload "<FileName>"
    And change the status for awaiting approval
    Then go to awaiting approval
    Then resolution to approve should be reacted accordingly in awaiting approval tab
    And go to approved deduction
    Then validate the invoice number should be reacted accordingly
    Examples: 
      | FileName							 |
      | BulkResolutionFile    |
      
      
   @BulkResolutionError
  Scenario Outline: In awaiting approval if invoice is getting moved to approved and if deduction type is not assigned for an invoice error message should be thrown
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file and upload "<FileName>"
    And go to validation Tab
    Then change the status open to awaiting approval with filter
    Then go to awaiting approval
    Then in awaiting approval if invoice is getting moved to approved and if deduction type is not assigned for an invoice error message should be thrown
   Examples: 
      | FileName							 |
      | BulkResolutionFile    |
  
      