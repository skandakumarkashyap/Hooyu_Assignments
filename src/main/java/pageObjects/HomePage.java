package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	@FindBy(how = How.XPATH,using = "//input[@name='search']")
	private WebElement inputSearchField;
	
	
	@FindBy(how = How.XPATH,using = "//button[@type='submit']")
	private WebElement submitSearch;
	
	@FindBy(how = How.XPATH,using = "//a[text()='Register']")
	private WebElement linkRegister;
	
	@FindBy(how= How.XPATH,using = "//a[@title='Home'] | //a/img[@alt='Takealot']")
	private WebElement takeALotLogo;
	
	WebDriverWait webDriverWait;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void waitForPageToLoad() {
		webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(15));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Register']")));
	}
	
	public void searchProduct(String productName) {
		inputSearchField.click();
		inputSearchField.clear();
		inputSearchField.sendKeys(productName);
		submitSearch.click();
	}
	
	public void clickOnRegisterLink()
	{
		linkRegister.click();
	}
	
	public void navigateBackToHomePage()
	{
		takeALotLogo.click();
	}
}

