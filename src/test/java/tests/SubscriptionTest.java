package tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import pages.SubscriptionPage;
import testData.TestData;

public class SubscriptionTest extends TestBase{
	SubscriptionPage SubscriptionObject;	
	@BeforeClass
	public void beforeMethod() throws InterruptedException {

		OpenBrowser();
		SubscriptionObject = new SubscriptionPage(driver);
	}


	@DataProvider(name = "ValidateSubcriptionPackage")
	public static Object[][] Validate_Subscription_Packages_dataProvider() throws Exception {

		return new Object[][] {			
			Arrays.stream(TestData.fetchData("BAH")).flatMap(Arrays::stream).toArray(),
			Arrays.stream(TestData.fetchData("KWT")).flatMap(Arrays::stream).toArray(),
			Arrays.stream(TestData.fetchData("KSA")).flatMap(Arrays::stream).toArray()
		};
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify packages,currency & plan for each country")
	@Test(priority = 1, alwaysRun = true, dataProvider = "ValidateSubcriptionPackage")
	public void validate_Subscription_Packages(String country, String types, String prices, String currency ){

		SubscriptionObject.selectCountry(driver,country);
		Assert.assertEquals(SubscriptionObject.GetPlanNames(),Arrays.asList(types.split(",")),"Plans are displayed successfully");
		Assert.assertEquals(SubscriptionObject.GetPrices(),Arrays.asList(prices.split(",")),"Prices are displayed successfully");
		Assert.assertEquals(SubscriptionObject.GetCurrency(),Arrays.asList(currency.split(",")),"Currencies are displayed successfully");

	}

	@AfterClass
	public void afterMethod() {
		CloseBrowser();
	}
}
