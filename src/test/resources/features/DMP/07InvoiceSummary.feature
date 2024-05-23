@SmokeTest
Feature: Check Invoice Summary Functionality

  Background: 
    Given User launch url and navigates to login screen

  @InvoiceSummaryThresholdCredit
  Scenario: Verify any invoice directly to threshold credit and the invoice should be moved to approved deduction tab
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    And create the new remittance file for Threshold credit
    When upload new remittance file for Threshold credit
    Then Make any invoice directly to threshold credit and the invoice should be moved to approved deduction tab

  @InvoiceSummaryCredit
  Scenario: Verify In approved deduction invoice changed to credit and the invoice should be moved to invoice summary tab
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then In approved deduction invoice changed to credit
    When In approved deduction invoice changed to credit and the invoice should be moved to invoice summary tab
