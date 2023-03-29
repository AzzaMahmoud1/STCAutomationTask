package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class PageBase {
	static WebDriver driver;
	public static Logger logger = Logger.getLogger(PageBase.class);

	public Boolean ElementIsDisplayed(WebDriver driver, By element_locator, String element_log) {
		logger = Logger.getLogger("Check Element is displayed");

		try {
			driver.findElement(element_locator).isDisplayed();
			logger.info(element_log + " is Displayed successfully");
			return true;

		} catch (Exception e) {
			logger.error(element_log + " is not Displayed");

			return false;
		}
	}
	public Boolean ElementIsDisplayed(WebDriver driver, WebElement element, String element_log) {
		logger = Logger.getLogger("Check Element is displayed");

		try {
			element.isDisplayed();
			logger.info(element_log + " is Displayed successfully");
			return true;

		} catch (Exception e) {
			logger.error(element_log + " is not Displayed");

			return false;
		}
	}
	public void ClickElement(WebDriver driver, By element_locator, String element_log) {
		logger = Logger.getLogger("Click Element ");
		Boolean flag = ElementIsDisplayed(driver, element_locator, element_log);
		if (flag == true) {
			WebElement element = driver.findElement(element_locator);
			element.click();
			logger.info("Click on " + element_log);
		} else {
			logger.error("Cannot Click on " + element_log);
		}
	}
	public String GetTextFromElement(WebDriver driver ,WebElement element, String element_log)
	{
		logger = Logger.getLogger("Get Text From Element ");
		String text="";
		Boolean Flag_element = ElementIsDisplayed(driver, element, element_log);
		if (Flag_element == true) {
			text= element.getText();
			logger.info("Text of "+element_log+" = "+text);

		}else
		{
			logger.error("Cannot get text from element  " + element_log);
		}
		return text;
	}

}
