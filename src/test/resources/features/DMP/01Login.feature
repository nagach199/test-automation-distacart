@SmokeTest
Feature: Check Login Functionality

  Background: 
    Given User launch url and navigates to login screen

  @login
  Scenario Outline: Verify login Functionality
    When user should enter username
    When user should enter password
    Then user click on sign in button to login into application

    Examples: 
      | username | password |
      |          |          |
      
 
