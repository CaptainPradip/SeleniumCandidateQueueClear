package com.citrix.elearning.candidatemerge.Pages;

import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Pradip.Nemane this class for all generic Method call on all pages
 */
public class BasePage {
	static Logger logger = Logger.getLogger(BasePage.class.getName());

	/**
	 * instance variable for WebDriver.
	 */
	protected WebDriver driver;

	/**
	 * Constructor to init WebDriver
	 *
	 * @param driver
	 *            {@link WebDriver }}
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;

	}

	/**
	 * Method to clear and type text
	 *
	 * @param element
	 *            {@link WebElement}}
	 * @param text
	 *            the element text to set
	 */
	public void clearAndType(WebElement element, String text) {
		if (isDisplyed(element)) {
			highLighterMethod(element);
			element.clear();
			element.sendKeys(text);

		}
	}

	/**
	 *
	 * @param element
	 */
	public void click(WebElement element) {
		if (isDisplyed(element)) {
			element.click();
		}
	}

	/**
	 * Click On Alert Cancel Button
	 */
	public void clickOnAlertCancel() {

		this.driver.switchTo().alert().dismiss();
	}

	/**
	 * Click on Alert OK Button
	 */
	public void clickOnAlertOk() {

		this.driver.switchTo().alert().accept();
	}

	/**
	 * to get WebElement Visible Text
	 *
	 * @param element
	 * @return {@link String}
	 */
	public String getText(WebElement element) {
		if (isDisplyed(element)) {
			return element.getText().trim();
		}
		return null;

	}

	/**
	 * method to high light WebElement
	 *
	 * @param driver
	 * @param webElement
	 */
	public void highLighterMethod(WebElement webElement) {

		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
				webElement);

	}

	/**
	 *
	 * @param element
	 * @return
	 */

	public boolean isDisplyed(By element) {
		try {

			this.driver.findElement(element);
			return true;

		} catch (Exception e) {

			logger.error("element is not visible : " + element.toString() + "->" + e.getMessage());

		}
		return false;

	}

	/**
	 *
	 * @param element
	 * @return
	 */
	public boolean isDisplyed(WebElement element) {
		try {

			return element.isDisplayed();

		} catch (Exception e) {
			logger.error("element is not visible : " + element.toString() + "->" + e.getMessage());
		}
		return false;

	}

	/**
	 * for send key to Web Element
	 *
	 * @param element
	 *            {@link WebElement}}
	 * @param Key
	 *            {@link Keys}}
	 */
	public void sendKey(WebElement element, Keys Key) {

		element.sendKeys(Key);
	}

	public void waitUntilElementToBeClickable(By webElement, long timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (TimeoutException e) {
			logger.error(
					"Timeout waiting for webelement to be present" + webElement.toString() + "->" + e.getMessage());
		} catch (Exception e) {
			logger.error("web element not be present " + webElement.toString() + "->" + e.getMessage());
		}
	}

	/**
	 *
	 * @param webElement
	 * @param timeoutInSeconds
	 * @throws Exception
	 */
	public void waitUntilElementToBeClickable(WebElement webElement, long timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (TimeoutException e) {
			logger.error(
					"Timeout waiting for webelement to be present " + webElement.toString() + "->" + e.getMessage());
		} catch (Exception e) {
			logger.error("web element not be present " + webElement.toString() + "->" + e.getMessage());
		}

	}

	/**
	 * method for wait page load
	 *
	 * @return
	 */
	public void waitUntilPageLoad() {
		final WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver t) {
				System.out.println("Waiting");
				return ((JavascriptExecutor) t).executeScript("return document.readyState").toString()
						.equals("complete");
			};
		});
	}

	/**
	 * method for wait for given time
	 *
	 * @param webElement
	 * @param timeoutInSeconds
	 * @throws Exception
	 */
	public void waitUntilVisibilityOfElementLocated(By webElement, long timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);

			wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));

		} catch (TimeoutException e) {
			logger.error(
					"Timeout waiting for webelement to be present" + webElement.toString() + "->" + e.getMessage());
		} catch (Exception e) {
			logger.error("web element not be present " + webElement.toString() + "->" + e.getMessage());
		}

	}

}
