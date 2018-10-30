package com.citrix.elearning.candidatemerge.Pages.candidatematch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.Pages.BasePage;
import com.citrix.elearning.candidatemerge.Pages.candidateprofile.CandidateReconciliationPage;

/**
 * This class contains candidate matching page functionalities.
 *
 * @author Pradip.Nemane
 *
 */
public class CandidateMatchPage extends BasePage {

	/**
	 * Web Element For Candidate Id.
	 */
	@FindBy(xpath = "//TABLE[2]/TBODY[1]/TR[4]/TD[9]")
	WebElement matchCandidateIdLabel;

	/**
	 * Web Element for match email.
	 */
	@FindBy(xpath = "//TABLE[2]/TBODY[1]/TR[4]/TD[7]")
	WebElement matchEmailLabel;

	/**
	 * Web Element for first name link.
	 */
	@FindBy(xpath = "//TABLE[2]/TBODY[1]/TR[4]/TD[3]")
	WebElement matchFirstNameLink;

	/**
	 * Web element for last name link..
	 */
	@FindBy(xpath = "//TABLE[2]/TBODY[1]/TR[4]/TD[5]")
	WebElement matchLastNameLink;

	/**
	 * Constructor initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}}
	 */
	public CandidateMatchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Click on Match Last Name.
	 *
	 * @return {@link CandidateProfileMatchPage}
	 */
	public CandidateReconciliationPage clickOnMatchLastName() {
		highLighterMethod(this.matchLastNameLink);
		click(this.matchLastNameLink);
		return new CandidateReconciliationPage(this.driver);
	}

	/**
	 * Method to get candidate id.
	 *
	 * @return candidate id.
	 */
	public String getCandidateId() {
		return getText(this.matchCandidateIdLabel);
	}

	/**
	 * Method to get email.
	 *
	 * @return email .
	 */
	public String getEmail() {
		return getText(this.matchEmailLabel);
	}

	/**
	 * Method for get first name text.
	 *
	 * @return first name.
	 */
	public String getFirstName() {
		return getText(this.matchFirstNameLink);
	}

	/**
	 * Method to return Last name text.
	 *
	 * @return last name.
	 */
	public String getLastName() {
		return getText(this.matchLastNameLink);
	}

}
