@UITests
Feature: Add product to basket and validate 
Description: The purpose of this test is to add the products into basket and validate them

Scenario: Customer searches for a product and adds it to basket
  	Given user is on application home page
  	When user searches for "Garmin Forerunner 45S Sports Smartwatch (39mm) - Black" and adds to basket
  	And user searches for "Garmin QUICKFIT 22 Black Silicone" and adds to basket
  	Then "Garmin Forerunner 45S Sports Smartwatch (39mm) - Black" and "Garmin QUICKFIT 22 Black Silicone" should be available in basket
 