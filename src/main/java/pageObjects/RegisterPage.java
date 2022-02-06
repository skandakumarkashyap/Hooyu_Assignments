package pageObjects;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

	WebDriver driver;

	@FindBy(how = How.ID, using = "firstname")
	private WebElement inputFirstName;

	@FindBy(how = How.ID, using = "surname")
	private WebElement inputLastName;

	@FindBy(how = How.ID, using = "email")
	private WebElement inputEmail;

	@FindBy(how = How.ID, using = "email2")
	private WebElement inputRetypeEmail;

	@FindBy(how = How.ID, using = "password")
	private WebElement inputPassword;

	@FindBy(how = How.ID, using = "password2")
	private WebElement inputRetypePassword;

	@FindBy(how = How.ID, using = "telno1")
	private WebElement inputMobileNumber;

	@FindBy(how = How.NAME, using = "registerButton")
	private WebElement buttonRegisterNow;

	@FindBy(how = How.ID, using = "passwords-not-matching")
	public WebElement errorPasswordDontMatch;

	@FindBy(how = How.ID, using = "emails-not-matching")
	public WebElement errorEmailsDontMatch;

	@FindBy(how = How.ID, using = "phone-number-not-specified")
	public WebElement errorMobileNotSpecified;
	
	@FindBy(how = How.XPATH, using = "//li[@id='login-logout']/a")
	public WebElement linkLogOut;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForPageToLoad() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(4));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Register ']")));
	}

	public void fillRegistrationForm(String firstName,String lastName,String email,String retypeEmail,String password, String retypePassword, String mobileNumber)
	{
		Random rnd = new Random();
		int number;
		inputFirstName.sendKeys(firstName);
		inputLastName.sendKeys(lastName);
		
		if(email.equalsIgnoreCase(retypeEmail) && password.equalsIgnoreCase(retypePassword) && mobileNumber!=null)
		{
		number = rnd.nextInt(999999);
		email+="tester"+String.format("%06d", number)+"@gmail.com";
		retypeEmail = email;
		}
		inputEmail.sendKeys(email);
		inputRetypeEmail.sendKeys(retypeEmail);
		
		inputPassword.sendKeys(password);
		inputRetypePassword.sendKeys(retypePassword);
		
		if(mobileNumber == null)
		{
		inputMobileNumber.sendKeys("");
		}
		else
		{
	    number = rnd.nextInt(99999999);    
		mobileNumber = "07"+String.format("%08d", number)+"84";
	    inputMobileNumber.sendKeys(mobileNumber);
		}
	}

	public void clickOnRisterNow() {
		buttonRegisterNow.click();
	}

	public boolean isErrorTextPasswordDontMatchDisplayed() {
		return errorPasswordDontMatch.isDisplayed();
	}

	public boolean isErrorTextEmailsDontMatchDisplayed() {
		return errorEmailsDontMatch.isDisplayed();
	}

	public boolean isErrorTextMobileNotSpecifiedDisplayed() {
		return errorMobileNotSpecified.isDisplayed();
	}

	public String getErrorText(WebElement element) {
		return element.getText();
	}
	
	public void clickOnLogOut()
	{
		linkLogOut.click();
	}

}