package pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class SubscriptionPage extends PageBase{
	WebDriverWait wait;
	static WebDriver driver;
	By landedCountry = By.id("country");
	String selectedCountry_xpath = "//a[contains(@class,'country ')][contains(.,'%s')]";
	By planNames= By.xpath("//*[@class='plan-names']//strong");
	By prices= By.xpath("//*[@class='price']//b");
	By currency= By.xpath("//*[@class='price']//i");

	public SubscriptionPage(WebDriver driver) {
		SubscriptionPage.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}

	public void selectCountry(WebDriver driver,String country) {
		ClickElement(driver, landedCountry, "landedCountry");		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(selectedCountry_xpath , country ))));
		ClickElement(driver, By.xpath(String.format(selectedCountry_xpath , country )), "selectedCountry");
	}

	public ArrayList<String> GetPlanNames(){
		ArrayList<String> planNamesList= new ArrayList<String>();
		List<WebElement> plans = driver.findElements(planNames);
		for (WebElement plan : plans) {
			planNamesList.add(GetTextFromElement(driver,plan,"plan"));
		}
		return planNamesList;

	}
	public ArrayList<String> GetPrices(){
		ArrayList<String> pricesList= new ArrayList<String>();
		List<WebElement> pricesElements = driver.findElements(prices);
		for (WebElement price : pricesElements) {
			pricesList.add(GetTextFromElement(driver,price,"price"));
		}
		return pricesList;

	}
	public ArrayList<String> GetCurrency(){
		ArrayList<String> currencyList= new ArrayList<String>();
		List<WebElement> currencyElements = driver.findElements(currency);
		for (WebElement currency : currencyElements) {
			currencyList.add(GetTextFromElement(driver,currency,"currency").split("/")[0]);
		}
		return currencyList;

	}

}