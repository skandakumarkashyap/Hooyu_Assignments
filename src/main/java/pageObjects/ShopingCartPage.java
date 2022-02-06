package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopingCartPage {

	WebDriver driver;

	@FindBys({ @FindBy(xpath = "//h3[starts-with(@class,'cart-item-module_item-title')]") })
	public List<WebElement> itemsInCart;

	public ShopingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForPageToLoad() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Shopping Cart']")));
	}

	public List<String> getItemsInCart() {
		List<String> items = new ArrayList<String>();
		for (WebElement e : itemsInCart) {
			items.add(e.getText());
		}

		return items;
	}

	public boolean verifyActualItemsInCartWithExpected(List<String> expectedProductsInBasket) {
		List<String> actualProductsInBasket = getItemsInCart();
		return expectedProductsInBasket.size() == actualProductsInBasket.size()
				&& expectedProductsInBasket.containsAll(actualProductsInBasket);
	}

}
