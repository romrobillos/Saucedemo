package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CheckoutInfo;

public class CheckoutPage extends BasePage {

	@FindBy(id = "first-name")
	WebElement txtFirstName;

	@FindBy(id = "last-name")
	WebElement txtLastName;

	@FindBy(id = "postal-code")
	WebElement txtZip;

	@FindBy(id = "cancel")
	WebElement cancelBtn;

	@FindBy(id = "continue")
	WebElement continueBtn;

	@FindBy(id = "finish")
	WebElement finishBtn;

	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	@FindBy(className = "title")
	WebElement checkoutHeader;

	@FindBy(id = "checkout_complete_container")
	WebElement checkoutComplete;

	@FindBy(xpath = "//h3[@data-test='error']")
	WebElement errorLabel;

	@FindBy(id = "back-to-products")
	WebElement backhomeBtn;

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	public boolean isCheckoutPage() {
		return checkoutHeader.isDisplayed();
	}

	public boolean isCheckoutComplete() {
		return checkoutComplete.isDisplayed();
	}

	public CheckoutPage setInformation(CheckoutInfo data) {
		txtFirstName.sendKeys(data.getFirstName());
		txtLastName.sendKeys(data.getLastName());
		txtZip.sendKeys(data.getZip());
		return this;
	}

	public CheckoutPage toContinue() {
		continueBtn.click();
		return this;
	}

	public String getFirstNameFieldValue() {
		return txtFirstName.getAttribute("value");
	}

	public String getLastNameFieldValue() {
		return txtLastName.getAttribute("value");
	}

	public CheckoutPage toFinish() {
		finishBtn.click();
		return this;
	}

	public CartPage toCancel() {
		cancelBtn.click();
		return new CartPage(driver);
	}

	public CheckoutPage toBackhomeBtn() {
		backhomeBtn.click();
		return this;
	}

	public WebElement getBackhomeBtn() {
		return backhomeBtn;
	}
	
	public WebElement getFinishBtn() {
		return finishBtn;
	}

	public WebElement getFirstName() {
		return txtFirstName;
	}

	public WebElement getLastName() {
		return txtLastName;
	}

	public String getFirstNameValue() {
		return txtFirstName.getText();
	}

	public String getLastNameValue() {
		return txtLastName.getText();
	}

	public String getErrorMessage() {
		return errorLabel.getText();
	}

}
