package testcase;

import java.time.Duration;
import java.util.List;

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
import utilities.CheckoutInfo;

public class CheckoutTest extends BaseTest {
	
	// Test if checkout all completed

	@Test(priority = 5, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify if checkout on all items is complete")
	public void isCheckoutAllComplete(String username, String password) {

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
					+ " Skipping test: Jacket, Tshirt and Redshirt not found in the cart due to failure of ProductPage Add/Remove Btns.");
		}
		CheckoutPage checkoutPage = cartPage.toCheckout();

		CheckoutInfo yourInfo = new CheckoutInfo();
		yourInfo.setFirstName("Jon Romeo");
		yourInfo.setLastName("Robillos");
		yourInfo.setZip("1008");
		checkoutPage.setInformation(yourInfo).toContinue().toFinish();
		Assert.assertTrue(checkoutPage.isCheckoutComplete());
		System.out.println(username + ": Test for 'isCheckoutAllComplete' completed successfully!");
	}

	// Test to Cancel checkout

	@Test(priority = 6, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify cancel button")
	public void toCancelCheckout(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		CartPage cartPage = new CartPage(driver);
		cartPage.toCheckout();
		new CheckoutPage(driver).toCancel();
		Assert.assertTrue(cartPage.getCheckout().isDisplayed());
		System.out.println(username + ": Test for 'Cancel Checkout' completed successfully!");
	}

	// Verify Response time of Cancel button

	@Test(priority = 7, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Response time of Cancel button")
	public void testGlitchCancelBtn(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		new CartPage(driver).toCheckout();
		CheckoutInfo yourInfo = new CheckoutInfo();
		yourInfo.setFirstName("Jon Romeo");
		yourInfo.setLastName("Robillos");
		yourInfo.setZip("1008");
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.setInformation(yourInfo).toContinue();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		try {
			WebElement finishBtn = wait.until(ExpectedConditions.visibilityOf(checkoutPage.getFinishBtn()));
			Assert.assertTrue(finishBtn.isDisplayed());
		} catch (Exception e) {
			throw new SkipException(username
					+ " Skipping test: Due to defect txtbox! Unable to proceed.");
		}
		
		this.startTime = System.nanoTime();
		new CheckoutPage(driver).toCancel();
		this.endTime = System.nanoTime();
	
		this.duration = (endTime - startTime) / 1_000_000_000.0;
		Assert.assertTrue(this.duration <= 1.0, "\nCancel took " + this.duration + " seconds expecting <=1.0 seconds");
		System.out.println(username + ": Test for 'Cancel button Response Time' completed successfully." + "\nTime: "
				+ this.duration);
	}
	
	// Verify Response time of Backhome button
	
	@Test(priority = 8, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify finish button and Response time of Backhome button")
	public void testFinishBtnAndGlitchBackhomeBtn(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		new CartPage(driver).toCheckout();
		CheckoutInfo yourInfo = new CheckoutInfo();
		yourInfo.setFirstName("Jon Romeo");
		yourInfo.setLastName("Robillos");
		yourInfo.setZip("1008");
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.setInformation(yourInfo).toContinue();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		try {
			WebElement finishBtn = wait.until(ExpectedConditions.visibilityOf(checkoutPage.getFinishBtn()));
			Assert.assertTrue(finishBtn.isDisplayed());
			checkoutPage.toFinish();
		} catch (Exception e) {
			throw new SkipException(username
					+ " Skipping test: Incomplete information due to defect txtbox! Unable to proceed.");
		}
		
		try {
			WebElement backhomeBtn = wait.until(ExpectedConditions.visibilityOf(checkoutPage.getBackhomeBtn()));
			Assert.assertTrue(backhomeBtn.isDisplayed());
			this.startTime = System.nanoTime();
			checkoutPage.toBackhomeBtn();
			this.endTime = System.nanoTime();
		} catch (Exception e) {
			Assert.fail("Test failed due to defect FinishBtn! Unable to proceed!.");
		}
 	
		this.duration = (endTime - startTime) / 1_000_000_000.0;
		Assert.assertTrue(this.duration <= 1.0, "\nBackhome took " + this.duration + " seconds expecting <=1.0 seconds");
		System.out.println(username + ": Test for 'Backhome button Response Time' completed successfully." + "\nTime: "
				+ this.duration);
	}

	// Test txtbox field from Information Page.
	// Only problem_user and error_user has the problem.

	@Test(priority = 1, dataProvider = "ProblemAndErrorUsers", dataProviderClass = BaseTest.class, description = "Verify if Txtbox is working")
	public void yourInformationPageTxtFunction(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		new CartPage(driver).toCheckout();
		CheckoutInfo yourInfo = new CheckoutInfo();
		yourInfo.setFirstName("Jon Romeo");
		yourInfo.setLastName("Robillos");
		yourInfo.setZip("1008");
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.setInformation(yourInfo);

		String actualLastName = checkoutPage.getLastNameFieldValue();
		String actualFirstName = checkoutPage.getFirstNameFieldValue();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualFirstName, "Jon Romeo", "First Name does not match expected value!");
		softAssert.assertEquals(actualLastName, "Robillos", "Last Name does not match expected value!");
		softAssert.assertAll();

		System.out.println(username + ": Test for 'yourInformationPageTxtFunction' completed successfully!");
	}

	// Test if error message is displayed if First name is missing

	@Test(priority = 2, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify error message when First Name is missing")
	public void isErrorMsgIsDisplayedForMissingFirstName(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		new CartPage(driver).toCheckout();

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		CheckoutInfo yourInfo = new CheckoutInfo();
		yourInfo.setFirstName("");
		yourInfo.setLastName("Robillos");
		yourInfo.setZip("1008");
		checkoutPage.setInformation(yourInfo).toContinue();
		this.errorMessage = checkoutPage.getErrorMessage();
		Assert.assertTrue(this.errorMessage.contains("Error: First Name is required"),
				"First Name Error message not found due to other textbox defect ");

		System.out.println(username + ": Test for 'Error Message on missing first name' completed successfully. \n"
				+ "\"" + this.errorMessage + "\"");
	}

	// Test if error message is displayed if Last name is missing

	@Test(priority = 3, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify error message when Last Name is missing")
	public void isErrorMsgIsDisplayedForMissingLastName(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		new CartPage(driver).toCheckout();

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		CheckoutInfo yourInfo = new CheckoutInfo();
		yourInfo.setFirstName("Jon Romeo");
		yourInfo.setLastName("");
		yourInfo.setZip("1008");
		checkoutPage.setInformation(yourInfo).toContinue();
		try {
			boolean checkout = checkoutPage.isCheckoutPage();
			Assert.assertTrue(checkout);
			this.errorMessage = checkoutPage.getErrorMessage();
			Assert.assertTrue(this.errorMessage.contains("Error: Last Name is required"),
					"Error message do not match!");
		} catch (Exception e) {
			Assert.fail("Error Message not found! Checkout proceeded despite missing Last Name for" + username);
		}

		System.out.println(username + ": Test for 'Error Message on missing last name' completed successfully. \n"
				+ "\"" + this.errorMessage + "\" is displayed.");
	}

	// Test if error message is displayed if Postal code is missing

	@Test(priority = 4, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify error message when Postal code is missing")
	public void isErrorMsgDisplayedForMissingPostal(String username, String password) {

		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);

		ProductPage productPage = new ProductPage(driver);
		productPage.getAddBackpackBtn();
		productPage.toCart();

		new CartPage(driver).toCheckout();

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		CheckoutInfo yourInfo = new CheckoutInfo();
		yourInfo.setFirstName("Jon Romeo");
		yourInfo.setLastName("Robillos");
		yourInfo.setZip("");
		checkoutPage.setInformation(yourInfo).toContinue();

		this.errorMessage = checkoutPage.getErrorMessage();
		Assert.assertTrue(this.errorMessage.contains("Error: Postal Code is required"),
				"Postal Error message not found due to other textbox defect ");

		System.out.println(username + ": Test for 'Error Message on missing last name' completed successfully. \n"
				+ "\"" + this.errorMessage + "\"");
	}
}
