@UITEst
Feature: Register a new user into application
Description: The purpose of this test is to register a new user into the Take a lot e-commerce application


Scenario: Create a new user
    Given user is on application home page
  	When user click on register link and navigates to registration page 
  	And user fills the following details  and clicks register now button
  	 |firstname| John |
  	 |lastname| doe |
  	 |password| test12345|
  	 |retype password|test12345|
  	Then the user should be registered successfully
  	And user logs out of application 