package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	@FindBy(id = "continue-shopping")
	WebElement continueShopBtn;

	// Added Items

	@FindBy(xpath = "//div[normalize-space()='Sauce Labs Backpack']")
	WebElement addedBackpack;

	@FindBy(xpath = "//div[normalize-space()='Sauce Labs Bike Light']")
	WebElement addedBikelight;

	@FindBy(xpath = "//div[normalize-space()='Sauce Labs Onesie']")
	WebElement addedOnesie;

	@FindBy(xpath = "//div[normalize-space()='Sauce Labs Bolt T-Shirt']")
	WebElement addedShirt;

	@FindBy(xpath = "//div[normalize-space()='Sauce Labs Fleece Jacket']")
	WebElement addedJacket;

	@FindBy(xpath = "//div[normalize-space()='Test.allTheThings() T-Shirt (Red)']")
	WebElement addedRedshirt;

	// Remove Button locators

	@FindBy(id = "remove-sauce-labs-backpack")
	WebElement backpackRemoveBtn;

	@FindBy(id = "remove-sauce-labs-bike-light")
	WebElement bikelightRemoveBtn;

	@FindBy(id = "remove-sauce-labs-bolt-t-shirt")
	WebElement shirtRemoveBtn;

	@FindBy(id = "remove-sauce-labs-fleece-jacket")
	WebElement jacketRemoveBtn;

	@FindBy(id = "remove-sauce-labs-onesie")
	WebElement onesieRemoveBtn;

	@FindBy(id = "remove-test.allthethings()-t-shirt-(red)")
	WebElement redshirtRemoveBtn;

	public CartPage(WebDriver driver) {
		super(driver);
	}

	// Get Remove Buttons
	
	public WebElement removeBackpack() {
		return backpackRemoveBtn;
	}

	public WebElement removeBikelight() {
		return bikelightRemoveBtn;
	}

	public WebElement removeOnesie() {
		return onesieRemoveBtn;
	}

	public WebElement removeShirt() {

		return shirtRemoveBtn;
	}

	public WebElement removeJacket() {

		return jacketRemoveBtn;
	}

	public WebElement removeRedshirt() {

		return redshirtRemoveBtn;
	}
	// Get Add Buttons

	public WebElement addedBackpack() {
		return addedBackpack;
	}

	public WebElement addedBikelight() {
		return addedBikelight;
	}

	public WebElement addedOnesie() {
		return addedOnesie;
	}

	public WebElement addedShirt() {

		return addedShirt;
	}

	public WebElement addedJacket() {

		return addedJacket;
	}

	public WebElement addedRedshirt() {

		return addedRedshirt;
	}

	public WebElement getCheckout() {
		return checkoutBtn;
	}

	public void removeItemToCart(WebElement itemButton) {
		itemButton.click();
	}

	public CheckoutPage toCheckout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}

	public ProductPage toContinueShop() {
		continueShopBtn.click();
		return new ProductPage(driver);
	}

}
