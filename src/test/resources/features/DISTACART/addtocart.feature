#src/test/resources/features/DISTACART/addtocart.feature
Feature: Test Increase Quantity from Cart Page

Background:
	Given I am on Home page of distacart
	
	@DIS_healthcheck
  Scenario Outline: Increase Quantity of Products in Cart
   #When I login to Distacart with valid user credentials <userName> and <passWord>
    When add the products into the cart
    
 Examples:
| userName                    | passWord   |
| distatestbot@gmail.com     | DistaTest@123   |
