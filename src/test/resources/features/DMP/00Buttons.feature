@SmokeTest
Feature: Check the Tab Functionality

  Background: 
    Given User launch url and navigates to login screen

  Scenario: Verify tab's Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the ar documents button
    Then verify the new remittance button
    Then verify the validations button
    Then verify the awaiting approval button
    Then verify the approved deductions button
    Then verify the track and manage disputes button

  Scenario: Verify AR Document button Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the ar documents button

  Scenario: Verify New Remittance button Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the new remittance button

  Scenario: Verify Validations button Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the validations button

  Scenario: Verify Awaiting Approval button Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the awaiting approval button

  Scenario: Verify Approved Deductions button Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the approved deductions button

  Scenario: Verify Tarck and manage disputes button Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application
    Then verify the track and manage disputes button
