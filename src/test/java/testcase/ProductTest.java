package testcase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.LoginPage;
import base.ProductPage;

public class ProductTest extends BaseTest {

	// Test for All Product images

	@Test(priority = 8, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify All product images")
	private void validateProductImages(String username, String password) {
		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		SoftAssert softAssert = new SoftAssert();

		String backpackActualSrc = productPage.getBackpackImageSrc();
		String backpackExpectedSrc = prop.getProperty("backpackImage");
		softAssert.assertEquals(backpackActualSrc, backpackExpectedSrc, "Backpack Image for " + username + " FAILED!");

		String bikeLightImageActualSrc = productPage.getBikeLightImageSrc();
		String bikeLightImageExpectedSrc = prop.getProperty("bikeLightImage");
		softAssert.assertEquals(bikeLightImageActualSrc, bikeLightImageExpectedSrc,
				"BikeLight Image for " + username + " FAILED!");

		String tshirtImageActualSrc = productPage.getTshirtImageSrc();
		String tshirtImageExpectedSrc = prop.getProperty("tshirtImage");
		softAssert.assertEquals(tshirtImageActualSrc, tshirtImageExpectedSrc,
				"Tshirt Image for " + username + " FAILED!");

		String jacketImageActualSrc = productPage.getJacketImageSrc();
		String jacketImageExpectedSrc = prop.getProperty("jacketImage");
		softAssert.assertEquals(jacketImageActualSrc, jacketImageExpectedSrc,
				"Jacket Image for " + username + " FAILED!");

		String onesieImageActualSrc = productPage.getOnesieImageSrc();
		String onesieImageExpectedSrc = prop.getProperty("onesieImage");
		softAssert.assertEquals(onesieImageActualSrc, onesieImageExpectedSrc,
				"Onesie Image for " + username + " FAILED!");

		String redshirtImageActualSrc = productPage.getRedshirtImageSrc();
		String redshirtImageExpectedSrc = prop.getProperty("redshirtImage");
		softAssert.assertEquals(redshirtImageActualSrc, redshirtImageExpectedSrc,
				"Redshirt Image for " + username + " FAILED!");
		softAssert.assertAll();
		System.out.println(username + ": Tests for 'All Images' completed successfully!");
	}

	// Add and Remove button verification

	@Test(priority = 9, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Add and Remove Buttons")
	public void testAddRemoveButtons(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Add and Remove button verification for Backpack

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddBackpackBtn())).click();

			softAssert.assertTrue(productPage.getRemoveBackpackBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Backpack Add button: Failed! Cannot able to add to cart!");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getRemoveBackpackBtn())).click();

			softAssert.assertTrue(productPage.getAddBackpackBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Backpack Remove button: Failed!");
		}

		// Add and Remove button verification for Bike Light button

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddBikelightBtn())).click();

			softAssert.assertTrue(productPage.getRemoveBikelightBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Bike Light Add button: Failed! Cannot able to add to cart!");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getRemoveBikelightBtn())).click();

			softAssert.assertTrue(productPage.getAddBikelightBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Bike Light Remove button: Failed!");
		}

		// Add and Remove button verification for T-Shirt

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddShirtBtn())).click();

			softAssert.assertTrue(productPage.getRemoveShirtBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("T-Shirt Add button: Failed! Cannot able to add to cart!");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getRemoveShirtBtn())).click();

			softAssert.assertTrue(productPage.getAddShirtBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("T-Shirt Remove button: Failed!");
		}

		// Add and Remove button verification for Jacket

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddJacketBtn())).click();

			softAssert.assertTrue(productPage.getRemoveJacketBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Jacket Add button: Failed! Cannot able to add to cart!");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getRemoveJacketBtn())).click();

			softAssert.assertTrue(productPage.getAddJacketBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Jacket Remove button: Failed!");
		}

		// Add and Remove button verification for Onesie

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddOnesieBtn())).click();
			softAssert.assertTrue(productPage.getRemoveOnesieBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Onesie Add button: Failed! Cannot able to add to cart!");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getRemoveOnesieBtn())).click();
			softAssert.assertTrue(productPage.getAddOnesieBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Onesie Remove button: Failed!");
		}

		// Add and Remove button verification for Red shirt

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddRedshirtBtn())).click();
			softAssert.assertTrue(productPage.getRemoveRedshirtBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("RedShirt Add button: Failed! Cannot able to add to cart!");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getRemoveRedshirtBtn())).click();
			softAssert.assertTrue(productPage.getAddRedshirtBtn().isDisplayed());
		} catch (Exception e) {
			softAssert.fail("Redshirt Remove button: Failed! ");
		}

		softAssert.assertAll();
		System.out.println(username + ": Tests for 'All Add/Remove Buttons' completed successfully!");

	}

	// Test All Items on Cart Badge

	@Test(priority = 2, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify All Items on Cart badge")
	public void testCartBadge(String username, String password) {
		int expectedCount = 6;
		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn().click();
		productPage.getAddBikelightBtn().click();
		productPage.getAddOnesieBtn().click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddJacketBtn())).click();
			productPage.getRemoveJacketBtn().isDisplayed();
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddShirtBtn())).click();
			productPage.getRemoveShirtBtn().isDisplayed();
			wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddRedshirtBtn())).click();
			productPage.getRemoveRedshirtBtn().isDisplayed();
		} catch (Exception e) {
			throw new SkipException(username
					+ " Skipping test: Jacket/Tshirt and Redshirt not found in the cart due to defect ProductPage Add/Remove Btns.");
		}

		String badgeText = productPage.getCartBadgeCount();
		System.out.println("Cart badge after adding item " + expectedCount + ": " + badgeText);

		Assert.assertEquals(badgeText, String.valueOf(expectedCount),
				"Cart badge should display '" + expectedCount + "' after adding item " + expectedCount);

	}
	// Validate Cart Coordinates
	// Only visual_user has the problem

	@Test(priority = 3, description = "Verify Cart coordinates")
	public void testCartCoordinatesForVisualUserOnly() {
		String username = "visual_user";
		String password = "secret_sauce";

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		int x = 1203;
		int y = 10;

		WebElement cart = productPage.getCartBtn();
		Point point = cart.getLocation();
		int actualX = point.getX();
		int actualY = point.getY();

		System.out.println(username + ": CartBtn X and Y coordinates: " + actualX + "," + actualY);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualX, x);
		softAssert.assertEquals(actualY, y);
		softAssert.assertAll();
		System.out.println(username + ": Test for 'CartBtn Coordinates' completed successfully!");
	}

	// Sort Test

	// Price Low to High
	@Test(priority = 6, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Low to High Price Sort")
	public void testSortLowToHigh(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		List<Double> sortedPrices = Arrays.asList(7.99, 9.99, 15.99, 15.99, 29.99, 49.99);

		Select dropdown = new Select(productPage.getSortDropdownBtn());
		dropdown.selectByVisibleText("Price (low to high)");

		List<WebElement> afterPrice = productPage.getPriceList();
		List<Double> afterPriceList = new ArrayList<>();

		for (WebElement price : afterPrice) {
			afterPriceList.add(Double.valueOf(price.getText().replace("$", "")));
		}
		System.out.println(username + "\nExpected:" + sortedPrices + "\nActual: " + afterPriceList);

		Assert.assertEquals(afterPriceList, sortedPrices, "Prices after sorting do not match!");
		System.out.println(username + ": Test for 'Low to High Sort' completed successfully!");
	}

	// Price High to Low
	@Test(priority = 7, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify High to Low Price Sort")
	public void testSortHighToLow(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		List<Double> sortedPrices = Arrays.asList(49.99, 29.99, 15.99, 15.99, 9.99, 7.99);

		Select dropdown = new Select(productPage.getSortDropdownBtn());
		dropdown.selectByVisibleText("Price (high to low)");

		List<WebElement> afterPrice = productPage.getPriceList();
		List<Double> afterPriceList = new ArrayList<>();

		for (WebElement price : afterPrice) {
			afterPriceList.add(Double.valueOf(price.getText().replace("$", "")));
		}
		System.out.println(username + "\nExpected:" + sortedPrices + "\nActual: " + afterPriceList);

		Assert.assertEquals(afterPriceList, sortedPrices, "Prices after sorting do not match!");
		System.out.println(username + ": Test for 'High to Low Sort' completed successfully!");
	}

	// A to Z
	@Test(priority = 4, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify A to Z Sort")
	public void testSortAtoZ(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		List<String> sortedNames = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
				"Test.allTheThings() T-Shirt (Red)");

		Select dropdown = new Select(productPage.getSortDropdownBtn());
		dropdown.selectByVisibleText("Name (A to Z)");

		List<WebElement> afterNames = productPage.getProductNamesList();
		List<String> afterNamesList = new ArrayList<>();

		for (WebElement name : afterNames) {
			afterNamesList.add(name.getText());
		}
		System.out.println(username + "\nExpected:" + sortedNames + "\nActual  :" + afterNamesList);

		Assert.assertEquals(afterNamesList, sortedNames, "Names after sorting do not match!");
		System.out.println(username + ": Test for 'Sort A to Z' completed successfully!");
	}

	// Z to A
	@Test(priority = 5, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Z to A Sort")
	public void testSortZtoA(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		List<String> sortedNames = Arrays.asList("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie",
				"Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light", "Sauce Labs Backpack");

		Select dropdown = new Select(productPage.getSortDropdownBtn());
		dropdown.selectByVisibleText("Name (Z to A)");

		List<WebElement> afterNames = productPage.getProductNamesList();
		List<String> afterNamesList = new ArrayList<>();

		for (WebElement name : afterNames) {
			afterNamesList.add(name.getText());
		}
		System.out.println(username + "\nExpected:" + sortedNames + "\nActual  :" + afterNamesList);

		Assert.assertEquals(afterNamesList, sortedNames, "Names after sorting do not match!");
		System.out.println(username + ": Test for 'Sort Z to A' completed successfully!");
	}

	@Test(priority = 10, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Glitch Z to A Sort")
	public void testGlitchSortZtoA(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		this.startTime = System.nanoTime();
		Select dropdown = new Select(productPage.getSortDropdownBtn());
		dropdown.selectByVisibleText("Name (Z to A)");
		this.endTime = System.nanoTime();

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (org.openqa.selenium.NoAlertPresentException | TimeoutException e) {
		}

		this.duration = (endTime - startTime) / 1_000_000_000.0;
		Assert.assertTrue(this.duration <= 1.0, "\nSort took " + this.duration + " seconds expecting <=1.0 seconds");
		System.out.println(
				username + ": Test for 'Sort ZtoA Response Time' completed successfully." + "\nTime: " + this.duration);
	}

	@Test(priority = 11, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Glitch High to Low Sort")
	public void testGlitchSortHighToLow(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		this.startTime = System.nanoTime();
		Select dropdown = new Select(productPage.getSortDropdownBtn());
		dropdown.selectByVisibleText("Price (high to low)");
		this.endTime = System.nanoTime();

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (org.openqa.selenium.NoAlertPresentException | TimeoutException e) {
		}

		this.duration = (endTime - startTime) / 1_000_000_000.0;
		Assert.assertTrue(this.duration <= 1.0, "\nSort took " + this.duration + " seconds expecting <=1.0 seconds");
		System.out.println(username + ": Test for 'Sort HightoLow Response Time' completed successfully." + "\nTime: "
				+ this.duration);
	}

	@Test(priority = 12, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Glitch Low to High Sort")
	public void testGlitchSortLowToHigh(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);

		this.startTime = System.nanoTime();
		Select dropdown = new Select(productPage.getSortDropdownBtn());
		dropdown.selectByVisibleText("Price (low to high)");
		this.endTime = System.nanoTime();

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			throw new SkipException("Test skipped due to sorting is broken!");
		} catch (org.openqa.selenium.NoAlertPresentException e) {
		}

		this.duration = (endTime - startTime) / 1_000_000_000.0;
		Assert.assertTrue(this.duration <= 1.0, "\nSort took " + this.duration + " seconds expecting <=1.0 seconds");
		System.out.println(username + ": Test for 'Sort LowToHigh Response Time' completed successfully." + "\nTime: "
				+ this.duration);
	}

	// Logout Test

	@Test(priority = 1, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify logout")
	public void testLogout(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		new ProductPage(driver).toLogout();
		boolean userLoggedOut = new LoginPage(driver).isLogoutSuccessful();
		Assert.assertTrue(userLoggedOut);
		System.out.println(username + ": Test for 'Logout' completed successfully!");
	}
}
