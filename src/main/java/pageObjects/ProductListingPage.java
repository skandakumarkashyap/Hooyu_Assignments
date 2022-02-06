package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListingPage {

	public static List<String> expectedProductsInBasket = new ArrayList<String>();
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using = "//button[starts-with(@class,'button badge-button mini-cart-button')]")
	private WebElement miniCartButton;

	public ProductListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForPageToLoad() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchSortOrder_searchDrop")));
	}
	
	
	public void addProductToCart(String productName)
	{
		driver.findElement(By.xpath("//h4[text()='"+productName+"']/ancestor::div[contains(@class,'card-section')]/following-sibling::div //button[normalize-space(.)='Add to Cart']")).click();
		expectedProductsInBasket.add(productName);
	}
	
	public void clickOnMiniCartButton()
	{
		miniCartButton.click();
	}
}
