package com.citrix.elearning.candidatemerge.Pages.candidateprofile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.Pages.BasePage;

/**
 * This class for verifying candidate inbound and master record details.
 *
 * @author Pradip.Nemane
 *
 */
public class CandidateReconciliationPage extends BasePage {

	/**
	 * WebElement for Don't Apply Inbound Record to Master Record link .
	 */
	@FindBy(linkText = "Don't Apply Inbound Record to Master Record")
	WebElement dontApplyToMasterLink;

	/**
	 * WebElement for inBound Primary Email.
	 */
	@FindBy(xpath = "//table[@id='emailTableA']/tbody/tr/td/table/tbody/tr[2]/td[2]")
	WebElement inboundPrimaryEmail;

	/**
	 * WebElement for Apply Inbound Record to Master Record link.
	 */
	@FindBy(linkText = "Apply Inbound Record to Master Record")
	WebElement inBoundToMasterLink;

	/**
	 * WebElement for inBound Updated Date.
	 */
	@FindBy(xpath = "//TBODY[1]/TR[5]/TD[1]/TABLE[1]/TBODY[1]/TR[1]/TD[2]")
	WebElement inboundUpdateDate;

	/**
	 * WebElement for Master Primary.
	 */
	@FindBy(xpath = "//table[@id='emailTableB']/tbody/tr/td/table/tbody/tr[2]/td[2]")
	WebElement masterPrimaryEmail;

	/**
	 * WebElement for Master updated Date.
	 */
	@FindBy(xpath = "//TBODY[1]/TR[5]/TD[5]//TABLE[1]/TBODY[1]/TR[1]/TD[2]")
	WebElement masterUpdateDate;

	/**
	 * WebElement for Create a new Master Record link.
	 */
	@FindBy(linkText = "Create a new Master Record")
	WebElement newMasterLink;

	/**
	 * Constructor initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public CandidateReconciliationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method for click on dont apply to master Link.
	 */
	public void clickOnDontApplyToMasterLink() {
		highLighterMethod(this.dontApplyToMasterLink);
		click(this.dontApplyToMasterLink);
	}

	/**
	 * Method for click on inbound to master Link.
	 */
	public void clickOnInBoundToMasterLink() {
		highLighterMethod(this.inBoundToMasterLink);
		click(this.inBoundToMasterLink);
	}

	/**
	 * Method for click on apply to master Link.
	 */
	public void clickOnNewMasterLink() {
		highLighterMethod(this.newMasterLink);
		click(this.newMasterLink);
	}

	/**
	 * Method for get InBound primary Email text.
	 *
	 * @return inbound primary email
	 */
	public String getInboundPrimaryEmail() {

		return getText(this.inboundPrimaryEmail);

	}

	/**
	 * Method for get inBound Updated Date in text.
	 *
	 * @return inbound update date.
	 */
	public String getInboundUpdateDate() {
		return getText(this.inboundUpdateDate);
	}

	/**
	 * Method get master email in text.
	 *
	 * @return master primary email.
	 */
	public String getMasterPrimaryEmail() {
		return getText(this.masterPrimaryEmail);
	}

	/**
	 * Method for get master update date in text.
	 *
	 * @return master update Date.
	 */
	public String getMasterUpdateDate() {
		return getText(this.masterUpdateDate);
	}

}
