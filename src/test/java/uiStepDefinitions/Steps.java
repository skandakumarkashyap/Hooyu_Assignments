package uiStepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import pageObjects.ProductListingPage;
import pageObjects.RegisterPage;
import pageObjects.RegistrationSuccessAlert;
import pageObjects.ShopingCartPage;

public class Steps {

	static WebDriver driver;
	String scenarioName;

	@Before
	public void getScenarios(Scenario scenario) {
		scenarioName = scenario.getName();
	}

	@Before(value = "@UITests")
	public void launchBrowser() {
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.takealot.com");
	}

	@Given("user is on application home page")
	public void userIsOnApplicationHomePage() {
		HomePage page = new HomePage(driver);
		page.waitForPageToLoad();

		String actualTitle = driver.getTitle();
		Assert.assertEquals("The application home page is not matching", actualTitle,
				"Takealot.com: Online Shopping | SA's leading online store");

	}

	@When("user searches for {string} and adds to basket")
	public void userSearchesForAndAddsToBasket(String productName) {
		HomePage homePage = new HomePage(driver);
		homePage.waitForPageToLoad();
		homePage.searchProduct(productName);

		ProductListingPage productListingPage = new ProductListingPage(driver);
		productListingPage.waitForPageToLoad();

		productListingPage.addProductToCart(productName);
	}

	@When("user click on register link and navigates to registration page")
	public void userClickOnRegisterLinkAndNavigatesToRegistrationPage() {
		HomePage homePage = new HomePage(driver);
		homePage.waitForPageToLoad();
		homePage.clickOnRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.waitForPageToLoad();
	}

	@When("user fills the following details  and clicks register now button")
	public void userFillsTheFollowingDetailsAndClicksRegisterNowButton(
			io.cucumber.datatable.DataTable registrationDetails) {
		RegisterPage registerPage = new RegisterPage(driver);
		List<List<String>> data = registrationDetails.asLists();
		if (scenarioName.equalsIgnoreCase("Create a new user")) {
			String firstName = data.get(0).get(1);
			String lastName = data.get(1).get(1);
			String email = "";
			String retypeEmail = "";
			String password = data.get(2).get(1);
			String retypePassword = data.get(3).get(1);
			String mobileNumber = "";

			registerPage.fillRegistrationForm(firstName, lastName, email, retypeEmail, password, retypePassword,
					mobileNumber);
		} else {
			String firstName = data.get(0).get(1);
			String lastName = data.get(1).get(1);
			String email = data.get(2).get(1);
			String retypeEmail = data.get(3).get(1);
			String password = data.get(4).get(1);
			String retypePassword = data.get(5).get(1);
			String mobileNumber = data.get(6).get(1);

			registerPage.fillRegistrationForm(firstName, lastName, email, retypeEmail, password, retypePassword,
					mobileNumber);
		}

		registerPage.clickOnRisterNow();
	}

	@Then("the user should be registered successfully")
	public void theUserShouldBeRegisteredSuccessfully() {
		RegistrationSuccessAlert registrationSuccessAlert = new RegistrationSuccessAlert(driver);

		Assert.assertTrue("Registration failed", registrationSuccessAlert.verifyWelcomePopUpDisplayed());

		registrationSuccessAlert.clickOnCloseButton();
	}

	@And("user logs out of application")
	public void theUserLogsOutFromTheApplication() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnLogOut();
	}

	@Then("user is redirected to registration page with {string} error message")
	public void userIsRedirectedToRegistrationPageWithErrorMessage(String errorMessage) {

		RegisterPage registerPage = new RegisterPage(driver);
		String actualErrorText;

		switch (errorMessage) {
		case "Email addresses don't match":
			actualErrorText = registerPage.errorEmailsDontMatch.getText();
			Assert.assertTrue("Error text is improper",
					actualErrorText.equalsIgnoreCase("Email addresses don't match"));
			break;
		case "No Mobile/Phone Number Specified":
			actualErrorText = registerPage.errorMobileNotSpecified.getText();
			Assert.assertTrue("Error text is improper",
					actualErrorText.equalsIgnoreCase("No Mobile/Phone Number Specified"));
			break;
		case "Passwords don't match":
			actualErrorText = registerPage.errorPasswordDontMatch.getText();
			Assert.assertTrue("Error text is improper", actualErrorText.equalsIgnoreCase("Passwords don't match"));
			break;
		default:
			System.out.println("None of the error messages matched");
		}
	}
	
	@Then("{string} and {string} should be available in basket")
	public void and_should_be_available_in_basket(String productOne, String productTwo) {
		List<String> expectedProductsInBasket = new ArrayList<String>();
		expectedProductsInBasket.add(productOne);
		expectedProductsInBasket.add(productTwo);
		
		ProductListingPage productListingPage = new ProductListingPage(driver);
		productListingPage.clickOnMiniCartButton();

		ShopingCartPage shopingCartPage = new ShopingCartPage(driver);
		shopingCartPage.waitForPageToLoad();

		Assert.assertTrue("Products added in cart is not matching with actual products",
				shopingCartPage.verifyActualItemsInCartWithExpected(expectedProductsInBasket));
	}


	@After("not @UITestsEnd")
	public void navigateToHomePage() {
		HomePage homePage = new HomePage(driver);
		homePage.waitForPageToLoad();
		homePage.navigateBackToHomePage();
	}

	@After("@UITestsEnd")
	public void closeBrowser(Scenario scenario) {
		driver.quit();
	}

}
