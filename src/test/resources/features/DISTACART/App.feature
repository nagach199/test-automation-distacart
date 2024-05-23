#src/test/resources/features/DISTACART/App.feature
Feature: user functionality on login to checkout
  I want to be able to Check The login to checkout Scenario
	
Background:
	Given I am on Home page of distacart

@DIS_healthcheck
Scenario Outline: Validate login  
     When I login to Distacart with valid user credentials <userName> and <passWord>
    
Examples:
| userName                    | passWord   |
| distatestbot@gmail.com     | DistaTest@123   |


@DIS_healthcheck1
Scenario Outline: Validate login1  
     When I login to Distacart with valid user credentials <userName> and <passWord>
    
Examples:
| userName                    | passWord   |
| distatestbot@gmail.com     | DistaTest@123   |


	
