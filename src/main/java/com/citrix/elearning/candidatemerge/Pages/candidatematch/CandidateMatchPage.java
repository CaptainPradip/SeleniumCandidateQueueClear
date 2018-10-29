package com.citrix.elearning.candidatemerge.Pages.candidatematch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.Pages.BasePage;
import com.citrix.elearning.candidatemerge.Pages.candidateprofile.CandidateReconciliationPage;

/**
 * This class for get Matching page Candidate details.
 *
 * @author Pradip.Nemane
 *
 */
public class CandidateMatchPage extends BasePage {

	/**
	 * Web Element For CandidateId.
	 */
	@FindBy(xpath = "//TABLE[2]/TBODY[1]/TR[4]/TD[9]")
	WebElement matchCandidateIdLable;

	/**
	 * Web Element for match Email.
	 */
	@FindBy(xpath = "//TABLE[2]/TBODY[1]/TR[4]/TD[7]")
	WebElement matchEmailLable;

	/**
	 * Web Element for First Name match.
	 */
	@FindBy(xpath = "//TABLE[2]/TBODY[1]/TR[4]/TD[3]")
	WebElement matchFirstNameLink;

	/**
	 * Web element for Last Name Match.
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
	 * method to get candidate id.
	 *
	 * @return candidate id.
	 */
	public String getCandidateId() {
		return getText(this.matchCandidateIdLable);
	}

	/**
	 * method to get email.
	 *
	 * @return email .
	 */
	public String getEmail() {
		return getText(this.matchEmailLable);
	}

	/**
	 * method for get first name text.
	 *
	 * @return first name.
	 */
	public String getFirstName() {
		return getText(this.matchFirstNameLink);
	}

	/**
	 * method to return Last name text.
	 * 
	 * @return last name.
	 */
	public String getLastName() {
		return getText(this.matchLastNameLink);
	}

}
