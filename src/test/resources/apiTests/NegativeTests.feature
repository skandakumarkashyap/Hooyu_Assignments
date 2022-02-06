@APITest
Feature: Tests to verify unsuccessful weather data requests to open weather API 
Description: The purpose of this test is to cover all possible negative scenarios when a user performs requests to the Open weather API


Scenario Outline: Unauthorized user is restricted from getting weather information with longitude and latitude
  	Given User has an invalid or no API key
  	When user requests current weather data for a <latitude> and <longitude>
  	Then error message "Invalid API key" with error code 401  is retrieved
 Examples:
 	|latitude | longitude |
 	|35       | 139       |
 	


Scenario Outline: Authorized user is restricted from getting weather information with only latitude
  	Given User has an API key
  	When user requests current weather data by passing only <latitude> 
  	Then error message "Nothing to geocode" with error code 400  is retrieved
 Examples:
 	|latitude |
 	|35       |
 	

Scenario Outline: Authorized user is restricted from getting weather information with an invalid zip or country code
    Given User has an API key
    When user requests current weather data for a <zip code> and "<country code>"
    Then error message "city not found" with error code 404  is retrieved
 Examples:
   |zip code  |country code|
   |909090    |IN		   |

  
Scenario Outline: Authorized user is restricted from getting weather information with an invalid city name
    Given User has an API key
    When user requests current weather data for a "<city>"
    Then error message "city not found" with error code 404  is retrieved
 Examples:
   |city|
   |Invalid city|
   
   
Scenario Outline: Authorized user is restricted from getting weather information with an invalid city ID
    Given User has an API key
    When user requests current weather data for a <city id>
    Then error message "city not found" with error code 404  is retrieved
 Examples:
   |city id|
   |010|
   


 	
