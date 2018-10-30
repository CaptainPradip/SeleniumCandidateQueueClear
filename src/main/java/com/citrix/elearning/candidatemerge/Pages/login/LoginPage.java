package com.citrix.elearning.candidatemerge.Pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.Pages.BasePage;
import com.citrix.elearning.candidatemerge.Pages.candidatequeue.CandidateQueuePage;

/**
 * This class for login page.
 *
 * @author Pradip.Nemane
 *
 */
public class LoginPage extends BasePage {
	/**
	 * Web element for password textBox.
	 */
	@FindBy(name = "password")
	WebElement passwordTextBox;

	/**
	 * Web element for submit Button.
	 */
	@FindBy(name = "submit")
	WebElement submitButton;

	/**
	 * Web element for username textBox.
	 */
	@FindBy(name = "username")
	WebElement usernameTextBox;

	/**
	 * initialize Web Driver.
	 *
	 * @param driver
	 *            {@link WebDriver}}
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method for click on submit button.
	 */
	public void clickOnSubmitButton() {
		click(this.submitButton);
	}

	/**
	 * Method for login to application.
	 *
	 * @param username.
	 *            {@link username}
	 *
	 * @param password.
	 *            {@link password}}
	 * @return {@link CandidateQueuePage}
	 *
	 */
	public CandidateQueuePage login(String username, String password) {
		setUsernameTextBox(username);
		setPasswordTextBox(password);
		clickOnSubmitButton();
		waitUntilPageLoad();
		return new CandidateQueuePage(this.driver);
	}

	/**
	 * Method for set password.
	 *
	 * @param password.
	 */
	public void setPasswordTextBox(String password) {
		highLighterMethod(this.passwordTextBox);
		this.passwordTextBox.sendKeys(password);
	}

	/**
	 * Method for set user name.
	 *
	 * @param username
	 */
	public void setUsernameTextBox(String username) {
		clearAndType(this.usernameTextBox, username);
	}
}
