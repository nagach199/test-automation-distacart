@FlowCheck @SmokeTest
Feature: End to End WorkFlow
  from new remittance to Trackand Manage Disputes

  Background: 
    Given User launch url and navigates to login screen

  @uploadFile
  Scenario: upload the remittance file
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    And create the new remittance file
    Then upload the created remittance file

  @Validation
  Scenario Outline: Verify the status and change the status open to awaiting approval
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then verify the invoice number status by default it's open state "<FileName>"
    Then change the status open to awaiting approval

    Examples: 
      | FileName                       |
      | Automation_remittance_template |

  @Awaiting
  Scenario: Verify the status and change the status awaiting approval to dispute
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the invoice number status by default it's awaiting approval state
    Then change the status ap to dispute

  @Dispute
  Scenario: Verify the status in dispute
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the invoice number status by default it's dispute state

  @ThresholdCredit
  Scenario Outline: verify the invoice number as threshold credit state
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then create the new remittance file for threshold credit
    Then upload the created threshold credit remittance file
    Then verify the invoice number status by default it's open state "<FileName>"
    Then change the status open to threashold credit
    Then verify the invoice number as threshold credit state
    Then Threshold credit invoice memo page should be have threshold credit changelog in memo page

    Examples: 
      | FileName                      |
      | Automation_remittance_TCredit |

  @Memopages
  Scenario Outline: upload the remittance file
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then create the new remittance file and upload "<FileName>"
    Then verify the change logs in validation
    Then validate the invoice memo page should contain awaiting approval processing status
    Then verify the change logs in track and manage dispute
    Examples: 
      | FileName          |
      | MemoPage |
