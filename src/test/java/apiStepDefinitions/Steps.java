package apiStepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {

	private static final String BASE_URL = "https://api.openweathermap.org";
	private String API_KEY = "b17adaa0cb0b0919bb8a64c8bd2d0cc8";
	private String NO_API_KEY = "";

	private Integer expectedLatitude;
	private Integer expectedLongitude;
	private String expectedCityName;
	private String expectedCountryCode;
	private Integer expectedZipCode;
	private Integer expectedCityID;

	private static Response response;

	@Given("User has an API key")
	public void userHasAnAPIKey() {
		Assert.assertNotNull("The API Key is null", API_KEY);
	}

	@When("user requests current weather data for a {int} and {int}")
	public void requestsCurrentWeatherDataForALatitudeAndLongitude(Integer latitude, Integer longitude) {
		expectedLatitude = latitude;
		expectedLongitude = longitude;
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.get("/data/2.5/weather" + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY);
	}

	@Then("relevant weather data is retrieved")
	public void relevantWeatherDataIsRetrieved() {
		String jsonString = response.asString();

		Integer actualLatitude = JsonPath.from(jsonString).get("coord.lat");
		Integer actualLongitude = JsonPath.from(jsonString).get("coord.lon");
		Assert.assertTrue("Actual latitude is not as expected", actualLatitude.equals(expectedLatitude));
		Assert.assertTrue("Actual longitude is not as expected", actualLongitude.equals(expectedLongitude));
	}

	@When("user requests current weather data for a {string}")
	public void requestsCurrentWeatherDataForACity(String cityName) {
		expectedCityName = cityName;
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.get("/data/2.5/weather" + "?q=" + cityName + "&appid=" + API_KEY);
	}

	@Then("relevant weather data is retrieved matching the city")
	public void relevantWeatherDataIsRetrievedMatchingTheCity() {
		String jsonString = response.asString();

		String actualCityName = JsonPath.from(jsonString).get("name");
		Assert.assertTrue("Actual city name is not as expected", actualCityName.equalsIgnoreCase(expectedCityName));
	}

	
	@When("user requests current weather data for a {string} and {string}")
	public void requestsCurrentWeatherDataForACityAndCountryCode(String cityName, String countryCode) {
		expectedCityName = cityName;
		expectedCountryCode = countryCode;
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.get("/data/2.5/weather" + "?q=" + cityName + "," + countryCode + "&appid=" + API_KEY);

	}

	@Then("relevant weather data is retrieved matching the city and country code")
	public void relevantWeatherDataIsRetrievedMatchingTheCityAndCountryCode() {
		String jsonString = response.asString();

		String actualCityName = JsonPath.from(jsonString).get("name");
		String actualCountryCode = JsonPath.from(jsonString).get("sys.country");
		Assert.assertTrue("Actual city name is not as expected", actualCityName.equalsIgnoreCase(expectedCityName));
		Assert.assertTrue("Actual country code is not as expected",
				actualCountryCode.equalsIgnoreCase(expectedCountryCode));
	}

	@When("user requests current weather data for a {int} and {string}")
	public void requestsCurrentWeatherDataForAZipCodeAndCountryCode(Integer zipCode, String countryCode) {
		expectedZipCode = zipCode;
		expectedCountryCode = countryCode;
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.get("/data/2.5/weather" + "?q=" + zipCode + "," + countryCode + "&appid=" + API_KEY);
	}

	@Then("relevant weather data is retrieved matching the zip and country code")
	public void relevantWeatherDataIsRetrievedMatchingTheZipAndCountryCode() {
		String jsonString = response.asString();

		String actualCountryCode = JsonPath.from(jsonString).get("sys.country");
		Assert.assertTrue("Actual country code is not as expected",
				actualCountryCode.equalsIgnoreCase(expectedCountryCode));
	}
	
	@When("user requests current weather data for a {int}")
	public void requestsCurrentWeatherDataForACity(Integer cityID) {
		expectedCityID = cityID;
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.get("/data/2.5/weather" + "?id=" + cityID + "&appid=" + API_KEY);

	}

	@Then("relevant weather data is retrieved matching the city id")
	public void relevantWeatherDataIsRetrievedMatchingTheCityId() {
		String jsonString = response.asString();

		Integer actualCityID = JsonPath.from(jsonString).get("id");
		Assert.assertTrue("Actual city ID is not as expected", actualCityID.equals(expectedCityID));
	}

	@Given("User has an invalid or no API key")
	public void userHasAnInvalidOrNoAPIKey() {
		Assert.assertTrue("API key is not empty", NO_API_KEY.isEmpty());
		API_KEY = NO_API_KEY;
	}
		

	@Then("error message {string} with error code {int}  is retrieved")
	public void error_messageWithErrorCodeIsRetrieved(String errMessage, Integer errCode) {
		String jsonString = response.asString();
		String errorMessage = JsonPath.from(jsonString).get("message");

		String errorCode = JsonPath.from(jsonString).get("cod").toString();
		
		Assert.assertTrue("Invalid error code",errorCode.equals(errCode.toString()));
		Assert.assertTrue("API key is not empty", errorMessage.contains(errMessage));
	}


	@When("user requests current weather data by passing only {int}")
	public void requestsCurrentWeatherDataByPassingOnlyLatitude(Integer latitude) {
		expectedLatitude = latitude;
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.get("/data/2.5/weather" + "?lat=" + latitude + "&appid=" + API_KEY);
	}

}
