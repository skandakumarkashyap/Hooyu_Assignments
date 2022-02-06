package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegistrationSuccessAlert {
	
	WebDriver driver;
	
	@FindBy(how= How.XPATH,using = "//h3[text()='Welcome to the TAKEALOT.com family!']")
	private WebElement registrationSuccessfulAlert;
	
	@FindBy(how = How.XPATH,using="//a[@title='Close']")
	private WebElement buttonClose;
	
	public RegistrationSuccessAlert(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyWelcomePopUpDisplayed()
	{
		return registrationSuccessfulAlert.isDisplayed();
	}
	
	public void clickOnCloseButton()
	{
		buttonClose.click();
	}

}
