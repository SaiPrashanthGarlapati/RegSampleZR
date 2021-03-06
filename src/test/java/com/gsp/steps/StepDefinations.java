package com.gsp.steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.gsp.utils.PropertyReader;
import com.gsp.utils.SeleniumDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinations {
	final static Logger logger = Logger.getLogger(StepDefinations.class);
	@Given("User navigates to login page$")
	public void userNavigatesToLoginPage() {
		System.out.println("Executing the Launching -Started");
		SeleniumDriver.getFirefoxdriver().navigate().to("https://app.iformbuilder.com/exzact/dataViews.php");
		// Assert.assertEquals("iFormBuilder Control Panel",
		// SeleniumDriver.getFirefoxdriver().getTitle());
		System.out.println("Executing the Launching -Ended");
	}

	@And("User enters the following credentials userName as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void userEntersTheFollowingCredentials(String userName, String password) {
		logger.info("Executing the input of Creds -started");
		SeleniumDriver.getWebDriverWait()
				.until(ExpectedConditions
						.visibilityOf(SeleniumDriver.getFirefoxdriver().findElement(By.name("USERNAME"))))
				.sendKeys(userName);
		SeleniumDriver.getWebDriverWait()
				.until(ExpectedConditions
						.visibilityOf(SeleniumDriver.getFirefoxdriver().findElement(By.name("PASSWORD"))))
				.sendKeys(password);
		logger.info("Executing the input of Creds -ended");
	}

	@And("User clicks on \"([^\"]*)\" button$")
	public void userClicksOnButton(String buttonText) {
		System.out.println("Executing the userClick on button -started: " + buttonText);
		SeleniumDriver.getWebDriverWait()
				.until(ExpectedConditions.visibilityOf(
						SeleniumDriver.getFirefoxdriver().findElement(By.xpath("//*[@alt='" + buttonText + "']"))))
				.click();
		logger.info("Executing the userClick on button -ends");
	}

	@Then("Validate Whether the User logged in \"([^\"]*)\"$")
	public void validateUserName(String user) {
		SeleniumDriver.getFirefoxdriver().manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		List<WebElement> elements = SeleniumDriver.getWebDriverWait().until(ExpectedConditions
				.visibilityOfAllElements(SeleniumDriver.getFirefoxdriver().findElements(By.className("loggedin"))));
		long count = elements.stream().filter(s -> s.getText().contains(user)).count();
		logger.debug("logged In:"+count);
		Assert.assertNotEquals(count, 0l);
	}

	@And("User clicks on \"([^\"]*)\"$")
	public void userClickon(String keyword) {
		logger.info("Executing the userClick-starts");
		SeleniumDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(
				SeleniumDriver.getFirefoxdriver().findElement(By.xpath("//*[contains(text(),'" + keyword + "')]"))))
				.click();
		logger.info("Executing the userClickBy -ends");
	}

	@And("^User clicks \"([^\"]*)\"$")
	public void userClicks(String keyword) {
		logger.info("Executing the userClickBy link Text-starts");
		SeleniumDriver.getWebDriverWait().until(
				ExpectedConditions.visibilityOf(SeleniumDriver.getFirefoxdriver().findElement(By.linkText(keyword))))
				.click();
		logger.info("Executing the userClickBy link Text-ends");
	}

	@And("User inputs \"([^\"]*)\" as \"([^\"]*)\"$")
	public void userinputs(String elementName, String content) {
		logger.info("Executing the input step - started");
		WebElement element = SeleniumDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(SeleniumDriver
				.getFirefoxdriver().findElement(By.xpath("//*[contains(text(),'" + elementName + "')]"))));
		element.findElement(By.xpath("./ancestor::tr/descendant::input")).sendKeys(content);
		logger.info("Executing the input step - Ends");
	}
	
	@Then("^Validate record with data in recordtable$")
	public void validateRecords(DataTable table) {
		logger.info("Verification started");
		WebElement element;
		List<List<String>> data = table.raw();
		element = SeleniumDriver.getWebDriverWait()
				.until(ExpectedConditions.visibilityOf(SeleniumDriver.getFirefoxdriver().findElement(By.id("flex1"))));

		String InsertedFirstName = element.findElement(By.xpath("./tbody/tr[1]/td[3]/div")).getText();
		String InsertedLastName = element.findElement(By.xpath("./tbody/tr[1]/td[4]/div")).getText();
		String Insertedphone = element.findElement(By.xpath("./tbody/tr[1]/td[5]/div")).getText();
		System.out.println(InsertedFirstName + " " + InsertedLastName + "  " + Insertedphone);
		Assert.assertEquals(InsertedFirstName, data.get(1).get(0));
		Assert.assertEquals(InsertedLastName, data.get(1).get(1));
		Assert.assertEquals(Insertedphone, data.get(1).get(2));
		logger.info("Verification Ended");

	}

	@And("^User click on checkbox of \"([^\"]*)\"$")
	public void clickonCheckBox(String keyword) {
		logger.info("Executing the checkbox click - started");
		WebElement element = SeleniumDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(
				SeleniumDriver.getFirefoxdriver().findElement(By.xpath("//*[contains(text(),'" + keyword + "')]"))));
		element.findElement(By.xpath("./ancestor::tr/descendant::input")).click();
		logger.info("Executing the checkbox click - Ended");
	}

	@Then("^validate the Inserted User data$")
	public void validateUserData(DataTable table) {
		logger.info("Verification Started");
		List<List<String>> data = table.raw();
		SeleniumDriver.getWebDriverWait()
				.until(ExpectedConditions.visibilityOf(SeleniumDriver.getFirefoxdriver().findElement(By.id("flex1"))));
		WebElement Inserteduser = SeleniumDriver.getWebDriverWait()
				.until(ExpectedConditions.visibilityOf(SeleniumDriver.getFirefoxdriver()
						.findElement(By.xpath("//*[contains(text(),'" + data.get(1).get(0) + "')]"))))
				.findElement(By.xpath("./ancestor::tr"));
		boolean validated = validateCreatedUser(Inserteduser, data);
		Assert.assertTrue(validated);
		logger.info("Verification Ended");

	}

	public boolean validateCreatedUser(WebElement InsertedElement, List<List<String>> data) {
		boolean correctUser = false;

		if (InsertedElement.findElement(By.xpath("./descendant::td[2]/descendant::div")).getText()
				.equals(data.get(1).get(1))) {

			if (InsertedElement.findElement(By.xpath("./descendant::td[3]/descendant::div")).getText()
					.equals(data.get(1).get(2))) {

				if (InsertedElement.findElement(By.xpath("./descendant::td[4]/descendant::div")).getText()
						.equals(data.get(1).get(3))) {

					if (InsertedElement.findElement(By.xpath("./descendant::td[5]/descendant::img")).getAttribute("alt")
							.equals(data.get(1).get(4))) {

						if (InsertedElement.findElement(By.xpath("./descendant::td[6]/descendant::img"))
								.getAttribute("alt").equals(data.get(1).get(5))) {

							if (InsertedElement.findElement(By.xpath("./descendant::td[7]/descendant::img"))
									.getAttribute("alt").equals(data.get(1).get(6))) {
								correctUser = true;
							}
						}
					}

				}
			}
		}

		return correctUser;
	}

}
