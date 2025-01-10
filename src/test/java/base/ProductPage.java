package base;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

	// Add/Remove Locators

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	WebElement backpackAddBtn;

	@FindBy(id = "remove-sauce-labs-backpack")
	WebElement backpackRemoveBtn;

	@FindBy(id = "add-to-cart-sauce-labs-bike-light")
	WebElement bikelightAddBtn;

	@FindBy(id = "remove-sauce-labs-bike-light")
	WebElement bikelightRemoveBtn;

	@FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
	WebElement shirtAddBtn;

	@FindBy(id = "remove-sauce-labs-bolt-t-shirt")
	WebElement shirtRemoveBtn;

	@FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
	WebElement jacketAddBtn;

	@FindBy(id = "remove-sauce-labs-fleece-jacket")
	WebElement jacketRemoveBtn;

	@FindBy(id = "add-to-cart-sauce-labs-onesie")
	WebElement onesieAddBtn;

	@FindBy(id = "remove-sauce-labs-onesie")
	WebElement onesieRemoveBtn;

	@FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
	WebElement redshirtAddBtn;

	@FindBy(id = "remove-test.allthethings()-t-shirt-(red)")
	WebElement redshirtRemoveBtn;

	// Header

	@FindBy(xpath = "//a[@data-test='shopping-cart-link']")
	WebElement linkCartBtn;

	@FindBy(className = "shopping_cart_badge")
	WebElement cartBadge;

	@FindBy(id = "react-burger-menu-btn")
	WebElement burgerMenuButton;

	@FindBy(id = "logout_sidebar_link")
	WebElement logoutBtn;

	@FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
	WebElement label;

	String Products = "Products";

	@FindBy(className = "inventory_item_price")
	List<WebElement> Price;

	@FindBy(className = "inventory_item_name")
	List<WebElement> ProductNames;

	@FindBy(className = "product_sort_container")
	WebElement sortDropdownBtn;

	// Image locators

	@FindBy(xpath = "//img[@alt='Sauce Labs Backpack']")
	WebElement backpackImage;

	@FindBy(xpath = "//img[@alt='Sauce Labs Bike Light']")
	WebElement bikeLightImage;

	@FindBy(xpath = "//img[@alt='Sauce Labs Bolt T-Shirt']")
	WebElement tshirtImage;

	@FindBy(xpath = "//img[@alt='Sauce Labs Fleece Jacket']")
	WebElement jacketImage;

	@FindBy(xpath = "//img[@alt='Sauce Labs Onesie']")
	WebElement onesieImage;

	@FindBy(xpath = "//img[@alt='Test.allTheThings() T-Shirt (Red)']")
	WebElement redshirtImage;

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	public boolean isProductPageDisplayed() {
		return label.isDisplayed() && label.getText().equals(Products);
	}

	// Getting Images SRC attribute

	public String getRedshirtImageSrc() {
		return redshirtImage.getAttribute("src");
	}

	public String getOnesieImageSrc() {
		return onesieImage.getAttribute("src");
	}

	public String getJacketImageSrc() {
		return jacketImage.getAttribute("src");
	}

	public String getTshirtImageSrc() {
		return tshirtImage.getAttribute("src");
	}

	public String getBackpackImageSrc() {
		return backpackImage.getAttribute("src");
	}

	public String getBikeLightImageSrc() {
		return bikeLightImage.getAttribute("src");
	}

	// Return Buttons
	public void addItemToCart(WebElement itemButton) {
		itemButton.click();
	}

	public String getCartBadgeCount() {
		return cartBadge.getText();
	}

	public WebElement getAddBackpackBtn() {
		return backpackAddBtn;
	}

	public WebElement getRemoveBackpackBtn() {
		return backpackRemoveBtn;
	}

	public WebElement getAddBikelightBtn() {
		return bikelightAddBtn;
	}

	public WebElement getRemoveBikelightBtn() {
		return bikelightRemoveBtn;
	}

	public WebElement getAddShirtBtn() {
		return shirtAddBtn;
	}

	public WebElement getRemoveShirtBtn() {
		return shirtRemoveBtn;
	}

	public WebElement getAddJacketBtn() {
		return jacketAddBtn;
	}

	public WebElement getRemoveJacketBtn() {
		return jacketRemoveBtn;
	}

	public WebElement getAddOnesieBtn() {
		return onesieAddBtn;
	}

	public WebElement getRemoveOnesieBtn() {
		return onesieRemoveBtn;
	}

	public WebElement getAddRedshirtBtn() {
		return redshirtAddBtn;
	}

	public WebElement getRemoveRedshirtBtn() {
		return redshirtRemoveBtn;
	}

	public List<WebElement> getPriceList() {
		return Price;
	}

	public List<WebElement> getProductNamesList() {
		return ProductNames;
	}

	public WebElement getSortDropdownBtn() {
		return sortDropdownBtn;
	}

	// Click Cart

	public ProductPage toCart() {
		linkCartBtn.click();
		return this;
	}

	public WebElement getCartBtn() {
		return linkCartBtn;
	}

	// to Logout

	public LoginPage toLogout() {
		burgerMenuButton.click();
		logoutBtn.click();
		return new LoginPage(driver);
	}

}
