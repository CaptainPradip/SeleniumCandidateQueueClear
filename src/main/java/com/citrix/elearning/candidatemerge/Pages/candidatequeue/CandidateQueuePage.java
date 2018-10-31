package com.citrix.elearning.candidatemerge.Pages.candidatequeue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.Pages.BasePage;
import com.citrix.elearning.candidatemerge.Pages.candidatematch.CandidateMatchPage;

/**
 * This class for candidate queue page.
 *
 * @author Pradip.Nemane
 *
 */
public class CandidateQueuePage extends BasePage {

	/**
	 * Web Element for candidate queue link.
	 */
	@FindBy(xpath = "//A[contains(text(),'Candidate Queue')]")

	WebElement candidateQueueLink;

	/**
	 * Web Element for Name Search TextBox.
	 */
	@FindBy(xpath = "//TABLE[1]/THEAD[1]/TR[1]/th[6]/input")
	WebElement nameSearchTextBox;
	/**
	 * Web Element for Queue date of first Candidate.
	 */
	@FindBy(xpath = "//FORM[1]/TABLE[1]/TBODY[1]/TR[1]/td[1]")
	WebElement queuedDate;

	/**
	 * Web Element for Candidate table first element.
	 */
	@FindBy(xpath = "//TABLE[1]/TBODY[1]/TR[1]/TD[6]//A")
	WebElement tableFirstRow;
	/**
	 * Web element for total number of candidate queue records.
	 */
	@FindBy(xpath = "//DIV[1]/DIV[2]/span")
	WebElement totalNumberofCandidate;

	/**
	 * Constructor initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public CandidateQueuePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to Click on candidate Queue Link.
	 *
	 * @return {@link CandidateProfileMatchPage}
	 */
	public CandidateQueuePage clickOnCandidateLink() {
		highLighterMethod(this.candidateQueueLink);
		click(this.candidateQueueLink);
		waitUntilPageLoad();
		return new CandidateQueuePage(this.driver);
	}

	/**
	 * Method to Get First and Last Name.
	 *
	 * @return name of candidate.
	 */
	public String getName() {
		return getText(this.tableFirstRow);
	}

	/**
	 * Method for Get Queue Date.
	 *
	 * @return queue date.
	 */
	public String getQueuedDate() {
		return getText(this.queuedDate);
	}

	/**
	 * Method for get Total Candidate Queue Count.
	 *
	 * @return no of record after search.
	 */
	public int getTotalNumebrOfCandidateRecords() {
		waitUntilPageLoad();
		final String candidateWithResultText = getText(this.totalNumberofCandidate);
		final int totalCandidate = Integer.parseInt(candidateWithResultText.split(" ")[0].trim());
		return totalCandidate;
	}

	/**
	 * Verifying Candidate is removed from Queue.
	 *
	 * @param updatedDate
	 *            candidate profile update date.
	 * @param firstAndLastName
	 *            candidate first and last name with comma(,) separated.
	 * @return if candidate is present after merge then return false else true.
	 *
	 */
	public boolean isCandidateMerged(String updatedDate, String firstAndLastName) {

		while (true) {
			boolean searchElement = isDisplyed(
					By.xpath("//tr/td[text()='" + updatedDate + "']/../td/a[text()='" + firstAndLastName + "']"));

			this.totalNumberofCandidate = this.driver.findElement(By.xpath("//DIV[1]/DIV[2]/span"));
			int count = getTotalNumebrOfCandidateRecords();

			if ((count == 0) && (!searchElement)) {
				return true;
			}
			final Pattern p = Pattern.compile("([1-5])");
			final Matcher m = p.matcher((Integer.toString(count)));
			if (m.matches()) {
				return false;
			}

			this.totalNumberofCandidate = this.driver.findElement(By.xpath("//DIV[1]/DIV[2]/span"));
			count = getTotalNumebrOfCandidateRecords();

		}

	}

	/**
	 * Method for select and click on candidate Last Name.
	 *
	 * @return {@link CandidateProfileMatchPage}
	 */
	public CandidateMatchPage selectAndClickCandidateNameLink() {
		highLighterMethod(this.tableFirstRow);
		click(this.tableFirstRow);
		waitUntilPageLoad();
		return new CandidateMatchPage(this.driver);
	}

	/**
	 * Method for set candidate name in search box.
	 *
	 * @param firstAndLastName
	 *            candidate first and last name with comma(,) separated.
	 */
	public void setNameSearchTextBoxAndSearch(String firstAndLastName) {
		clearAndType(this.nameSearchTextBox, firstAndLastName);
		sendKey(this.nameSearchTextBox, Keys.ENTER);
		waitUntilPageLoad();
	}
}
