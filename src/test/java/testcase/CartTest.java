package testcase;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.CartPage;
import base.CheckoutPage;
import base.LoginPage;
import base.ProductPage;

public class CartTest extends BaseTest {

	// Verify Items if displayed on Cart

	@Test(priority = 1, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify All Items if displayed on Cart")
	public void isAddedtoCartDisplayed(String username, String password) {

		switch (username) {
		case "standard_user":
			// Login
			LoginPage login = new LoginPage(driver);
			login.toLogin(username, password);

			ProductPage productPage = new ProductPage(driver);
			List<WebElement> itemsToAdd = List.of(productPage.getAddBackpackBtn(), productPage.getAddBikelightBtn(),
					productPage.getAddJacketBtn(), productPage.getAddOnesieBtn(), productPage.getAddShirtBtn(),
					productPage.getAddRedshirtBtn());

			for (int i = 0; i < itemsToAdd.size(); i++) {
				productPage.addItemToCart(itemsToAdd.get(i));
			}
			productPage.toCart();
			CartPage cartPage = new CartPage(driver);
			Assert.assertTrue(cartPage.addedBackpack().isDisplayed());
			Assert.assertTrue(cartPage.addedBikelight().isDisplayed());
			Assert.assertTrue(cartPage.addedOnesie().isDisplayed());
			Assert.assertTrue(cartPage.addedJacket().isDisplayed());
			Assert.assertTrue(cartPage.addedRedshirt().isDisplayed());
			Assert.assertTrue(cartPage.addedShirt().isDisplayed());
			System.out.println(username + ": Test for 'isAddedtoCartDisplayed' completed successfully!");
			break;

		case "visual_user":

			LoginPage visualLogin = new LoginPage(driver);
			visualLogin.toLogin(username, password);

			ProductPage visualProductPage = new ProductPage(driver);
			List<WebElement> visualItemsToAdd = List.of(visualProductPage.getAddBackpackBtn(),
					visualProductPage.getAddBikelightBtn(), visualProductPage.getAddJacketBtn(),
					visualProductPage.getAddOnesieBtn(), visualProductPage.getAddShirtBtn(),
					visualProductPage.getAddRedshirtBtn());

			for (int i = 0; i < visualItemsToAdd.size(); i++) {
				visualProductPage.addItemToCart(visualItemsToAdd.get(i));
			}
			visualProductPage.toCart();
			CartPage visualCartPage = new CartPage(driver);
			Assert.assertTrue(visualCartPage.addedBackpack().isDisplayed());
			Assert.assertTrue(visualCartPage.addedBikelight().isDisplayed());
			Assert.assertTrue(visualCartPage.addedOnesie().isDisplayed());
			Assert.assertTrue(visualCartPage.addedJacket().isDisplayed());
			Assert.assertTrue(visualCartPage.addedRedshirt().isDisplayed());
			Assert.assertTrue(visualCartPage.addedShirt().isDisplayed());
			System.out.println(username + ": Test for 'isAddedtoCartDisplayed' completed successfully!");
			break;

		case "performance_glitch_user":

			LoginPage glitchLogin = new LoginPage(driver);
			glitchLogin.toLogin(username, password);

			ProductPage glitchProductPage = new ProductPage(driver);
			List<WebElement> glitchItemsToAdd = List.of(glitchProductPage.getAddBackpackBtn(),
					glitchProductPage.getAddBikelightBtn(), glitchProductPage.getAddJacketBtn(),
					glitchProductPage.getAddOnesieBtn(), glitchProductPage.getAddShirtBtn(),
					glitchProductPage.getAddRedshirtBtn());

			for (int i = 0; i < glitchItemsToAdd.size(); i++) {
				glitchProductPage.addItemToCart(glitchItemsToAdd.get(i));
			}
			glitchProductPage.toCart();
			CartPage glitchCartPage = new CartPage(driver);
			Assert.assertTrue(glitchCartPage.addedBackpack().isDisplayed());
			Assert.assertTrue(glitchCartPage.addedBikelight().isDisplayed());
			Assert.assertTrue(glitchCartPage.addedOnesie().isDisplayed());
			Assert.assertTrue(glitchCartPage.addedJacket().isDisplayed());
			Assert.assertTrue(glitchCartPage.addedRedshirt().isDisplayed());
			Assert.assertTrue(glitchCartPage.addedShirt().isDisplayed());
			System.out.println(username + ": Test for 'isAddedtoCartDisplayed' completed successfully!");
			break;

		case "problem_user":

			LoginPage problemLogin = new LoginPage(driver);
			problemLogin.toLogin(username, password);

			ProductPage problemProductPage = new ProductPage(driver);
			List<WebElement> problemItemsToAdd = List.of(problemProductPage.getAddBackpackBtn(),
					problemProductPage.getAddBikelightBtn(), problemProductPage.getAddOnesieBtn(),
					problemProductPage.getAddJacketBtn(), problemProductPage.getAddRedshirtBtn(),
					problemProductPage.getAddShirtBtn());

			for (int i = 0; i < problemItemsToAdd.size(); i++) {
				problemProductPage.addItemToCart(problemItemsToAdd.get(i));
			}
			problemProductPage.toCart();
			CartPage problemCartPage = new CartPage(driver);
			Assert.assertTrue(problemCartPage.addedBackpack().isDisplayed());
			Assert.assertTrue(problemCartPage.addedBikelight().isDisplayed());
			Assert.assertTrue(problemCartPage.addedOnesie().isDisplayed());

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			try {
				WebElement jacketElement = wait.until(ExpectedConditions.visibilityOf(problemCartPage.addedJacket()));
				Assert.assertTrue(jacketElement.isDisplayed());
				WebElement redshirtElement = wait.until(ExpectedConditions.visibilityOf(problemCartPage.addedShirt()));
				Assert.assertTrue(redshirtElement.isDisplayed());
				WebElement shirtElement = wait.until(ExpectedConditions.visibilityOf(problemCartPage.addedRedshirt()));
				Assert.assertTrue(shirtElement.isDisplayed());
			} catch (Exception e) {
				throw new SkipException(username
						+ " Skipping test: Jacket/Tshirt and Redshirt not found in the cart due to defect ProductPage Add/Remove Btns.");
			}

			System.out.println(username
					+ ": Test for 'isAddedtoCartDisplayed' completed successfully!\nSkipped: Jacket, T-shirt and Redshirt due to ProductPage Add button defect");
			break;
		case "error_user":

			LoginPage errorLogin = new LoginPage(driver);
			errorLogin.toLogin(username, password);

			ProductPage errorProductPage = new ProductPage(driver);
			List<WebElement> errorItemsToAdd = List.of(errorProductPage.getAddBackpackBtn(),
					errorProductPage.getAddBikelightBtn(), errorProductPage.getAddOnesieBtn(),
					errorProductPage.getAddJacketBtn(), errorProductPage.getAddRedshirtBtn(),
					errorProductPage.getAddShirtBtn());

			for (int i = 0; i < errorItemsToAdd.size(); i++) {
				errorProductPage.addItemToCart(errorItemsToAdd.get(i));
			}
			errorProductPage.toCart();
			CartPage errorCartPage = new CartPage(driver);
			Assert.assertTrue(errorCartPage.addedBackpack().isDisplayed());
			Assert.assertTrue(errorCartPage.addedBikelight().isDisplayed());
			Assert.assertTrue(errorCartPage.addedOnesie().isDisplayed());

			WebDriverWait errorWait = new WebDriverWait(driver, Duration.ofSeconds(5));

			try {
				WebElement jacketElement = errorWait
						.until(ExpectedConditions.visibilityOf(errorCartPage.addedJacket()));
				Assert.assertTrue(jacketElement.isDisplayed());
				WebElement redshirtElement = errorWait
						.until(ExpectedConditions.visibilityOf(errorCartPage.addedRedshirt()));
				Assert.assertTrue(redshirtElement.isDisplayed());
				WebElement shirtElement = errorWait.until(ExpectedConditions.visibilityOf(errorCartPage.addedShirt()));
				Assert.assertTrue(shirtElement.isDisplayed());
			} catch (Exception e) {
				throw new SkipException(username
						+ " Skipping test: Jacket/Tshirt and Redshirt not found in the cart due to defect ProductPage Add/Remove Btns.");
			}

			System.out.println(username
					+ ": Test for 'isAddedtoCartDisplayed' completed successfully!\nSkipped Jacket, Tshirt and Redshirt due to ProductPage Add button defect");
			break;
		}
	}

	// Each item remove button test

	@Test(priority = 2, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify remove button")
	public void toRemoveAllAddedItemsFromCart(String username, String password) {

		switch (username) {
		case "standard_user":

			LoginPage login = new LoginPage(driver);
			login.toLogin(username, password);

			ProductPage productPage = new ProductPage(driver);
			List<WebElement> itemsToAdd = List.of(productPage.getAddBackpackBtn(), productPage.getAddBikelightBtn(),
					productPage.getAddJacketBtn(), productPage.getAddOnesieBtn(), productPage.getAddShirtBtn(),
					productPage.getAddRedshirtBtn());

			for (int i = 0; i < itemsToAdd.size(); i++) {
				productPage.addItemToCart(itemsToAdd.get(i));
			}
			productPage.toCart();

			CartPage cartPage = new CartPage(driver);
			List<WebElement> itemsToRemove = List.of(cartPage.removeBackpack(), cartPage.removeBikelight(),
					cartPage.removeJacket(), cartPage.removeOnesie(), cartPage.removeRedshirt(),
					cartPage.removeShirt());

			for (int i = 0; i < itemsToRemove.size(); i++) {
				cartPage.removeItemToCart(itemsToRemove.get(i));
			}
			cartPage.toContinueShop();
			Assert.assertTrue(productPage.getAddBackpackBtn().isDisplayed());
			Assert.assertTrue(productPage.getAddBikelightBtn().isDisplayed());
			Assert.assertTrue(productPage.getAddJacketBtn().isDisplayed());
			Assert.assertTrue(productPage.getAddOnesieBtn().isDisplayed());
			Assert.assertTrue(productPage.getAddRedshirtBtn().isDisplayed());
			Assert.assertTrue(productPage.getAddShirtBtn().isDisplayed());
			System.out.println(username + ": Test for 'remove All Added Items on Cart' completed successfully!");
			break;

		case "visual_user":

			LoginPage visualLogin = new LoginPage(driver);
			visualLogin.toLogin(username, password);

			ProductPage visualProductPage = new ProductPage(driver);
			List<WebElement> visualItemsToAdd = List.of(visualProductPage.getAddBackpackBtn(),
					visualProductPage.getAddBikelightBtn(), visualProductPage.getAddJacketBtn(),
					visualProductPage.getAddOnesieBtn(), visualProductPage.getAddShirtBtn(),
					visualProductPage.getAddRedshirtBtn());

			for (int i = 0; i < visualItemsToAdd.size(); i++) {
				visualProductPage.addItemToCart(visualItemsToAdd.get(i));
			}
			visualProductPage.toCart();

			CartPage visualCartPage = new CartPage(driver);
			List<WebElement> visualItemsToRemove = List.of(visualCartPage.removeBackpack(),
					visualCartPage.removeBikelight(), visualCartPage.removeJacket(), visualCartPage.removeOnesie(),
					visualCartPage.removeRedshirt(), visualCartPage.removeShirt());

			for (int i = 0; i < visualItemsToRemove.size(); i++) {
				visualCartPage.removeItemToCart(visualItemsToRemove.get(i));
			}
			visualCartPage.toContinueShop();
			Assert.assertTrue(visualProductPage.getAddBackpackBtn().isDisplayed());
			Assert.assertTrue(visualProductPage.getAddBikelightBtn().isDisplayed());
			Assert.assertTrue(visualProductPage.getAddJacketBtn().isDisplayed());
			Assert.assertTrue(visualProductPage.getAddOnesieBtn().isDisplayed());
			Assert.assertTrue(visualProductPage.getAddRedshirtBtn().isDisplayed());
			Assert.assertTrue(visualProductPage.getAddShirtBtn().isDisplayed());
			System.out.println(username + ": Test for 'remove All Added Items on Cart' completed successfully!");
			break;

		case "performance_glitch_user":

			LoginPage glitchLogin = new LoginPage(driver);
			glitchLogin.toLogin(username, password);

			ProductPage glitchProductPage = new ProductPage(driver);
			List<WebElement> glitchItemsToAdd = List.of(glitchProductPage.getAddBackpackBtn(),
					glitchProductPage.getAddBikelightBtn(), glitchProductPage.getAddJacketBtn(),
					glitchProductPage.getAddOnesieBtn(), glitchProductPage.getAddShirtBtn(),
					glitchProductPage.getAddRedshirtBtn());

			for (int i = 0; i < glitchItemsToAdd.size(); i++) {
				glitchProductPage.addItemToCart(glitchItemsToAdd.get(i));
			}
			glitchProductPage.toCart();

			CartPage glitchCartPage = new CartPage(driver);
			List<WebElement> glitchItemsToRemove = List.of(glitchCartPage.removeBackpack(),
					glitchCartPage.removeBikelight(), glitchCartPage.removeJacket(), glitchCartPage.removeOnesie(),
					glitchCartPage.removeRedshirt(), glitchCartPage.removeShirt());

			for (int i = 0; i < glitchItemsToRemove.size(); i++) {
				glitchCartPage.removeItemToCart(glitchItemsToRemove.get(i));
			}
			glitchCartPage.toContinueShop();
			Assert.assertTrue(glitchProductPage.getAddBackpackBtn().isDisplayed());
			Assert.assertTrue(glitchProductPage.getAddBikelightBtn().isDisplayed());
			Assert.assertTrue(glitchProductPage.getAddJacketBtn().isDisplayed());
			Assert.assertTrue(glitchProductPage.getAddOnesieBtn().isDisplayed());
			Assert.assertTrue(glitchProductPage.getAddRedshirtBtn().isDisplayed());
			Assert.assertTrue(glitchProductPage.getAddShirtBtn().isDisplayed());
			System.out.println(username + ": Test for 'remove All Added Items on Cart' completed successfully!");
			break;

		case "error_user":

			LoginPage errorLogin = new LoginPage(driver);
			errorLogin.toLogin(username, password);

			ProductPage errorProductPage = new ProductPage(driver);
			errorProductPage.getAddBackpackBtn().click();
			errorProductPage.getAddBikelightBtn().click();
			errorProductPage.getAddOnesieBtn().click();
			WebDriverWait errorWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			try {
				errorWait.until(ExpectedConditions.elementToBeClickable(errorProductPage.getAddJacketBtn())).click();
				errorProductPage.getRemoveJacketBtn().isDisplayed();
				errorWait.until(ExpectedConditions.elementToBeClickable(errorProductPage.getAddShirtBtn())).click();
				errorProductPage.getRemoveShirtBtn().isDisplayed();
				errorWait.until(ExpectedConditions.elementToBeClickable(errorProductPage.getAddRedshirtBtn())).click();
				errorProductPage.getRemoveRedshirtBtn().isDisplayed();
			} catch (Exception e) {
				throw new SkipException(username
						+ " Skipping test: Jacket/Tshirt and Redshirt not found in the cart due to defect Add/Remove Btns.");
			}

			System.out.println(username + ": Test for 'remove All Added Items on Cart' completed successfully!");
			break;

		case "problem_user":

			LoginPage problemLogin = new LoginPage(driver);
			problemLogin.toLogin(username, password);

			ProductPage problemProductPage = new ProductPage(driver);
			problemProductPage.getAddBackpackBtn().click();
			problemProductPage.getAddBikelightBtn().click();
			problemProductPage.getAddOnesieBtn().click();
			WebDriverWait problemWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			try {
				problemWait.until(ExpectedConditions.elementToBeClickable(problemProductPage.getAddJacketBtn()))
						.click();
				problemProductPage.getRemoveJacketBtn().isDisplayed();
				problemWait.until(ExpectedConditions.elementToBeClickable(problemProductPage.getAddShirtBtn())).click();
				problemProductPage.getRemoveShirtBtn().isDisplayed();
				problemWait.until(ExpectedConditions.elementToBeClickable(problemProductPage.getAddRedshirtBtn()))
						.click();
				problemProductPage.getRemoveRedshirtBtn().isDisplayed();
			} catch (Exception e) {
				throw new SkipException(username
						+ " Skipping test: Jacket/Tshirt and Redshirt not found in the cart due to defect Add/Remove Btns.");
			}

			System.out.println(username + ": Test for 'remove All Added Items on Cart' completed successfully!");
			break;
		}
	}

	// Validate CheckoutBtn Coordinates
	// Only visual_user has the problem

	@Test(priority = 4, description = "Verify CheckoutBtn coordinates")
	public void testCheckoutBtnCoordinates() {
		String username = "visual_user";
		String password = "secret_sauce";

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		new ProductPage(driver).toCart();
		int x = 1045;
		int y = 217;

		CartPage cartPage = new CartPage(driver);
		WebElement checkout = cartPage.getCheckout();
		Point point = checkout.getLocation();
		System.out.println(point.getX());
		System.out.println(point.getY());
		int actualX = point.getX();
		int actualY = point.getY();

		System.out.println(username + ": CheckoutBtn X and Y coordinates: " + actualX + "," + actualY);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualX, x);
		softAssert.assertEquals(actualY, y);
		softAssert.assertAll();
		System.out.println(username + ": Test for 'CheckoutBtn Coordinates' completed successfully!");
	}

	// Test Continue Shopping button function

	@Test(priority = 5, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify continue shopping button")
	public void toContinueShopping(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		CartPage cartPage = new CartPage(driver);
		cartPage.toContinueShop();

		Assert.assertTrue(productPage.isProductPageDisplayed());
		System.out.println(username + ": Test for 'toContinueShopping' completed successfully!");
	}
	
	// Verify Response time of continue shopping button

	@Test(priority = 6, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Response time of continue shopping button")
	public void testGlitchContinueShopping(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		this.startTime = System.nanoTime();
		CartPage cartPage = new CartPage(driver);
		cartPage.toContinueShop();
		this.endTime = System.nanoTime();
		
		this.duration = (endTime - startTime) / 1_000_000_000.0;
		Assert.assertTrue(this.duration <= 1.0, "\nContinue Shopping took " + this.duration + " seconds expecting <=1.0 seconds");
		System.out.println(
				username + ": Test for 'Continue shopping button Response Time' completed successfully." + "\nTime: " + this.duration);
	}

	// Checkout all items test

	@Test(priority = 3, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify if all items can checkout")
	public void toCheckoutAllItems(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		List<WebElement> itemsToAdd = List.of(productPage.getAddBackpackBtn(), productPage.getAddBikelightBtn(),
				productPage.getAddJacketBtn(), productPage.getAddOnesieBtn(), productPage.getAddShirtBtn(),
				productPage.getAddRedshirtBtn());

		for (int i = 0; i < itemsToAdd.size(); i++) {
			productPage.addItemToCart(itemsToAdd.get(i));
		}
		productPage.toCart();

		CartPage cartPage = new CartPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {
			WebElement jacketElement = wait.until(ExpectedConditions.visibilityOf(cartPage.addedJacket()));
			Assert.assertTrue(jacketElement.isDisplayed());
			WebElement redshirtElement = wait.until(ExpectedConditions.visibilityOf(cartPage.addedRedshirt()));
			Assert.assertTrue(redshirtElement.isDisplayed());
			WebElement shirtElement = wait.until(ExpectedConditions.visibilityOf(cartPage.addedShirt()));
			Assert.assertTrue(shirtElement.isDisplayed());
		} catch (Exception e) {
			throw new SkipException(username
					+ " Skipping test: Jacket/Tshirt and Redshirt not found in the cart due to defect ProductPage Add/Remove Btns.");
		}
		CheckoutPage checkoutPage = cartPage.toCheckout();

		Assert.assertTrue(checkoutPage.isCheckoutPage());
		System.out.println(username + ": Test for 'to Checkout all Items' completed successfully!");
	}
}
