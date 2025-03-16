package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.LoginPage;
import base.ProductPage;

public class LoginTest extends BaseTest {

	// To perform Login

	@Test(priority = 1, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify login")
	public void testToLogin(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.toLogin(username, password);
		
		
		ProductPage productPage = new ProductPage(driver);
		
		boolean isLoginSuccesful = productPage.isProductPageDisplayed();
		Assert.assertTrue(isLoginSuccesful);
	}

	// Login Response Time Test

	@Test(priority = 4, dataProvider = "validCredentials", dataProviderClass = BaseTest.class, description = "Verify Login response time")
	public void testResponseTime(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		this.startTime = System.nanoTime();
		loginPage.toLogin(username, password);
		this.endTime = System.nanoTime();
		this.duration = (endTime - startTime) / 1_000_000_000.0;
		Assert.assertTrue(this.duration <= 2.0, "\nLogin took " + this.duration + " seconds expecting <=2.0 seconds");
		System.out.println(
				username + ": Test for 'Login Response Time' completed successfully." + "\nTime: " + this.duration);
	}

	// Locked Out User Test

	@Test(priority = 3, description = "Verify locked out user error message")
	public void testLockedOutErrorMsg() {
		this.username = "locked_out_user";
		this.password = "secret_sauce";
		LoginPage loginPage = new LoginPage(driver);
		loginPage.toLogin(username, password);
		this.errorMessage = loginPage.getErrorMessage();
		Assert.assertTrue(this.errorMessage.contains("Epic sadface: Sorry, this user has been locked out"));
		System.out.println(
				username + ": Test for 'Locked Out User' completed successfully. \n" + "\"" + this.errorMessage + "\"");
	}

	// Invalid username Test

	@Test(priority = 2, description = "Verify invalid login error message")
	public void testInvalidLoginErrorMsg() {
		this.username = "Invalid_user";
		this.password = "Invalid_pass";
		LoginPage loginPage = new LoginPage(driver);
		loginPage.toLogin(username, password);
		this.errorMessage = loginPage.getErrorMessage();
		Assert.assertTrue(this.errorMessage
				.contains("Epic sadface: Username and password do not match any user in this service"));
		System.out.println(
				username + ": Test for 'Invalid user' completed successfully. \n" + "\"" + this.errorMessage + "\"");
	}
}