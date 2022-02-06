@APITest
Feature: Tests to verify successful weather data requests to open weather API 
Description: The purpose of this test is to cover all happy paths of possible requests to the Open weather API

Scenario Outline: Authorized user is able to get current weather information with longitude and latitude
  	Given User has an API key
  	When user requests current weather data for a <latitude> and <longitude>
  	Then relevant weather data is retrieved
 Examples:
 	|latitude | longitude |
 	|35       | 139       |
 
 

Scenario Outline: Authorized user is able to get current weather information with a valid city name
    Given User has an API key
    When user requests current weather data for a "<city>"
    Then relevant weather data is retrieved matching the city
 Examples:
   |city|
   |London|
   


Scenario Outline: Authorized user is able to get current weather information with a valid city name and country code
    Given User has an API key
    When user requests current weather data for a "<city>" and "<country code>"
    Then relevant weather data is retrieved matching the city and country code
 Examples:
   |city  |country code|
   |London|GB		   |
   

Scenario Outline: Authorized user is able to get current weather information with a valid zip and country code
    Given User has an API key
    When user requests current weather data for a <zip code> and "<country code>"
    Then relevant weather data is retrieved matching the zip and country code
 Examples:
   |zip code  |country code|
   |560079    |IN		   |
   
   
Scenario Outline: Authorized user is able to get current weather information with a valid city ID
    Given User has an API key
    When user requests current weather data for a <city id>
    Then relevant weather data is retrieved matching the city id
 Examples:
   |city id|
   |2172797|

     