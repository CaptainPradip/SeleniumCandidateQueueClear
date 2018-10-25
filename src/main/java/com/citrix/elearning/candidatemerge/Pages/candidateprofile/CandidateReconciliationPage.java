package com.citrix.elearning.candidatemerge.Pages.candidateprofile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.Pages.BasePage;

/**
 *
 * @author Pradip.Nemane
 *
 */
public class CandidateReconciliationPage extends BasePage {

	/**
	 * Don't Apply Inbound Record to Master Record link WebElement
	 */
	@FindBy(linkText = "Don't Apply Inbound Record to Master Record")
	WebElement dontApplyToMasterLink;

	/**
	 * inBound Primary Email WebElement
	 */
	@FindBy(xpath = "//table[@id='emailTableA']/tbody/tr/td/table/tbody/tr[2]/td[2]")
	WebElement inboundPrimaryEmail;

	/**
	 * Apply Inbound Record to Master Record link WebElement
	 */
	@FindBy(linkText = "Apply Inbound Record to Master Record")
	WebElement inBoundToMasterLink;

	/**
	 * inBound Updated Date WebElement
	 */
	@FindBy(xpath = "//TBODY[1]/TR[5]/TD[1]/TABLE[1]/TBODY[1]/TR[1]/TD[2]")
	WebElement inboundUpdateDate;

	/**
	 * Master Primary WebElement
	 */
	@FindBy(xpath = "//table[@id='emailTableB']/tbody/tr/td/table/tbody/tr[2]/td[2]")
	WebElement masterPrimaryEmail;

	/**
	 * Master updated Date WebElement
	 */
	@FindBy(xpath = "//TBODY[1]/TR[5]/TD[5]//TABLE[1]/TBODY[1]/TR[1]/TD[2]")
	WebElement masterUpdateDate;

	/**
	 * Create a new Master Record link WebElement
	 */
	@FindBy(linkText = "Create a new Master Record")
	WebElement newMasterLink;

	/**
	 * Constructor initialization
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public CandidateReconciliationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * for click on Dont Apply to master Link
	 */
	public void clickOnDontApplyToMasterLink() {
		highLighterMethod(this.dontApplyToMasterLink);
		click(this.dontApplyToMasterLink);
	}

	/**
	 * for click on inbound to master Link
	 */
	public void clickOnInBoundToMasterLink() {
		highLighterMethod(this.inBoundToMasterLink);
		click(this.inBoundToMasterLink);
	}

	/**
	 * for click on Apply to master Link
	 */
	public void clickOnNewMasterLink() {
		highLighterMethod(this.newMasterLink);
		click(this.newMasterLink);
	}

	/**
	 * get InBound primary Email text
	 *
	 * @return {@link String}
	 */
	public String getInboundPrimaryEmail() {

		return getText(this.inboundPrimaryEmail);

	}

	/**
	 * get inBound Updated Date in text
	 *
	 * @return inbound update date.
	 */

	public String getInboundUpdateDate() {
		return getText(this.inboundUpdateDate);
	}

	/**
	 * get master email in text
	 *
	 * @return
	 */

	public String getMasterPrimaryEmail() {
		return getText(this.masterPrimaryEmail);
	}

	/**
	 * get master update date in text
	 *
	 * @return
	 */
	public String getMasterUpdateDate() {
		return getText(this.masterUpdateDate);
	}

}
