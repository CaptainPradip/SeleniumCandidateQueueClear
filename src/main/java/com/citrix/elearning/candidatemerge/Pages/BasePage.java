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
 * This class for all generic Methods.
 *
 * @author Pradip.Nemane
 */
public class BasePage {
	/**
	 * Logger object use for logging .
	 */
	static Logger logger = Logger.getLogger(BasePage.class);

	/**
	 * Instance variable for WebDriver.
	 */
	protected WebDriver driver;

	/**
	 * Constructor to init WebDriver.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 *
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Click on Alert OK Button.
	 */
	public void alertAccept() {
		this.driver.switchTo().alert().accept();
	}

	/**
	 * Click On Alert Cancel Button.
	 */
	public void alertDismiss() {
		this.driver.switchTo().alert().dismiss();
	}

	/**
	 * Method to clear and type text.
	 *
	 * @param element
	 *            {@link WebElement}
	 *
	 * @param text
	 *            the element text to set.
	 */
	public void clearAndType(WebElement element, String text) {
		highLighterMethod(element);
		element.clear();
		element.sendKeys(text);
		logger.info("send key to " + element.toString() + "---->" + text);
	}

	/**
	 * Method for click on web element.
	 *
	 * @param element
	 *            {@link WebElement}
	 */
	public void click(WebElement element) {
		element.click();
	}

	/**
	 * Method for get text from Alert.
	 *
	 * @return text from alert.
	 */
	public String getAlertText() {

		return this.driver.switchTo().alert().getText().trim();
	}

	/**
	 * Method to get WebElement Visible Text.
	 *
	 * @param element
	 *            {@link WebElement}
	 * @return text of web element.
	 */
	public String getText(WebElement element) {
		try {
			return element.getText().trim();
		} catch (final Exception e) {
			logger.error("element is not visible : " + element.toString() + "->" + e.getMessage());
			return null;
		}
	}

	/**
	 * Method to high light WebElement.
	 *
	 * @param webElement
	 *            {@link WebElement}
	 */
	public void highLighterMethod(WebElement webElement) {
		final JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
				webElement);
	}

	/**
	 * Method to check Alert is Present.
	 *
	 * @return true if alert is present else false.
	 */
	public boolean isAlertPresent() {
		try {
			final WebDriverWait wait = new WebDriverWait(this.driver, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (final TimeoutException e) {
			logger.error("Alert  is not visible : " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method for to check Web element is Present in DOM.
	 *
	 * @param element
	 *            {@link By}
	 * @return true if element in DOM present else false.
	 */
	public boolean isDisplyed(By element) {
		try {
			this.driver.findElement(element);
			return true;
		} catch (final Exception e) {

			logger.error("element is not visible : " + element.toString() + "->" + e.getMessage());
		}
		return false;
	}

	/**
	 * Method for check Web Element is present in DOM Or not.
	 *
	 * @param element
	 *            {@link WebElement}
	 * @return true if element in DOM present else false.
	 */
	public boolean isDisplyed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (final Exception e) {
			logger.error("element is not visible : " + element.toString() + "->" + e.getMessage());
		}
		return false;
	}

	/**
	 * Method for send key to Web Element.
	 *
	 * @param element
	 *            {@link WebElement}}
	 * @param Key
	 *            {@link Keys}}
	 */
	public void sendKey(WebElement element, Keys Key) {
		if (isDisplyed(element)) {
			element.sendKeys(Key);
		}
	}

	/**
	 * Method for check element is present in DOM or not.
	 *
	 * @param webElement
	 *            {@link WebElement}
	 * @param timeoutInSeconds
	 */
	public void waitUntilElementToBeClickable(By webElement, long timeoutInSeconds) {
		try {
			final WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (final TimeoutException e) {
			logger.error("Waiting Timeout finish for webelement" + webElement.toString() + "->" + e.getMessage());
		}
	}

	/**
	 * method for Wait until element to be clickable.
	 *
	 * @param webElement
	 *            {@link WebElement}
	 * @param timeoutInSeconds
	 */
	public void waitUntilElementToBeClickable(WebElement webElement, long timeoutInSeconds) {
		try {
			final WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (final TimeoutException e) {
			logger.error("Waiting Timeout finish for webelement" + webElement.toString() + "->" + e.getMessage());
		}
	}

	/**
	 * method for wait until page load.
	 */
	public void waitUntilPageLoad() {
		final WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver t) {
				final String status = ((JavascriptExecutor) t).executeScript("return document.readyState").toString();

				return status.equals("complete");
			};
		});
	}

	/**
	 * Method for Wait until element is visible.
	 *
	 * @param webElement
	 *            {@link WebElement}
	 * @param timeoutInSeconds
	 */
	public void waitUntilVisibilityOfElementLocated(By webElement, long timeoutInSeconds) {
		try {
			final WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
		} catch (final TimeoutException e) {
			logger.error("Waiting Timeout finish for webelement" + webElement.toString() + "->" + e.getMessage());
		}
	}
}
