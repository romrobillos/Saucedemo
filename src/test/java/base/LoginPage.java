package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement btnLogin;

	@FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")
	WebElement errorLabel;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void toLogin(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		btnLogin.click();
	}

	public boolean isLogoutSuccessful() {
		return btnLogin.isDisplayed();
	}

	public String getErrorMessage() {
		return errorLabel.getText();
	}
}

