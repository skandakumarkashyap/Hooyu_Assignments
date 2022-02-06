@UITEst
Feature: Unsuccessful registration
Description: The purpose of this test is to register a new user into the Take a lot e-commerce application


Scenario: Unsuccessful registration when password mismatch
    Given user is on application home page
  	When user click on register link and navigates to registration page 
  	And user fills the following details  and clicks register now button
  	 |firstname|Sam|
  	 |lastname| Bob|
  	 |email | tester1234@gmail.com|
  	 |retype email|tester1234@gmail.com|
  	 |password|sambob1234|
  	 |retype password|sambob12345|
  	 |mobile number|9876543210|
  	Then user is redirected to registration page with "Passwords don't match" error message
  		

Scenario: Unsuccessful registration when email mismatch
    Given user is on application home page
  	When user click on register link and navigates to registration page 
  	And user fills the following details  and clicks register now button
  	 |firstname|Tom|
  	 |lastname| Harry|
  	 |email | tester@gmail.com|
  	 |retype email|tester12@gmail.com|
  	 |password|test1234|
  	 |retype password|test1234|
  	 |mobile number|9876543210|
  	Then user is redirected to registration page with "Email addresses don't match" error message
  	

@UITestsEnd
Scenario: Unsuccessful registration when mobile number is not specified
    Given user is on application home page
  	When user click on register link and navigates to registration page 
  	And user fills the following details  and clicks register now button
  	 |firstname|Mili|
  	 |lastname|Richard|
  	 |email | tester@gmail.com|
  	 |retype email|tester@gmail.com|
  	 |password|test1234|
  	 |retype password|test1234|
  	 |mobile number||
  	Then user is redirected to registration page with "No Mobile/Phone Number Specified" error message