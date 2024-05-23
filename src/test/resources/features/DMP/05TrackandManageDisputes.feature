@SmokeTest
Feature: Check Track&ManageDispute Functionality

  Background: 
    Given User launch url and navigates to login screen

  #Used In Flow Check
  # Scenario: verify the invoice number status by default dispute state
  #  When user should enter username
  # When user should enter password
  # Then user click on sign in button to login into application
  # Then verify the invoice number status by default it's dispute state
  @ValidateDisputeInvoice
  Scenario: verify the invoice number status by default dispute state
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then Change the invoice number awaiting approval to dispute and disputed invoice should only be available in track and manage dispute tab
