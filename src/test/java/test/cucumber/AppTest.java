package test.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.modules.thread.thread;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;

public class AppTest {
	WebDriver driver;
	String fareonBD;
	String farePD;

	/*
	@Given("^Open the firefox browser$")
	public void open_the_firefox_browser() throws Throwable {
		App app = new App(driver);
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addPreference("dom.disable_beforeunload", true);
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
	}

	@Given("^enter the URL$")
	public void enter_the_URL() throws Throwable {
		App app = new App(driver);
		driver.get(app.url);
		app.implcitWait(driver);

	}

	@Then("^Search for Bus Tickets from Mumbai to Nasik$")
	public void search_for_Bus_Tickets_from_Mumbai_to_Nasik() throws Throwable {
		App app = new App(driver);
		app.enter_Source();

		app.select_Source();

		app.enter_select_Destination();

		app.select_CalDate();

		app.handlenotification();

		Robot rob = new Robot();
		// rob.keyPress(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_ENTER);

		app.search_Click();
	}

	@Then("^Select Departure time as After (\\d+) PM and Select Bus Type as Non AC$")
	public void select_Departure_time_as_After_PM_and_Select_Bus_Type_as_Non_AC(int arg1) throws Throwable {
		App app = new App(driver);

		WebElement departureTime;
		WebDriverWait wait = new WebDriverWait(driver, 40);

		departureTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(app.departureTimexpath)));
		departureTime.click();
		driver.findElement(By.xpath(app.bustypeNonACxpath)).click();

		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,250)");

	}

	@Then("^Select the (\\d+) Available Seats,Boarding and Dropping Point$")
	public void select_the_Available_Seats_Boarding_and_Dropping_Point(int arg1) throws Throwable {
		App app = new App(driver);
		app.select_Bus();
		app.view_BusSeats();
		viewSeats();

	}

	@Then("^Click on Proceed to Book$")
	public void click_on_Proceed_to_Book() throws Throwable {
		App app = new App(driver);
		app.proceed();

	}

	@Then("^On Passenger Details, Select I don't want insurance$")
	public void on_Passenger_Details_Select_I_don_t_want_insurance() throws Throwable {
		App app = new App(driver);
		WebElement passengerDetails = driver.findElement(By.cssSelector(app.passengerDetailscss));

		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,250)");

		JavascriptExecutor noins = (JavascriptExecutor) driver;
		noins.executeScript("document.getElementById('insuranceNotOpted').click();");

	}

	@Then("^Verify whether the Total Amount Displayed on Passenger Details is the same as displayed on Select the$")
	public void verify_whether_the_Total_Amount_Displayed_on_Passenger_Details_is_the_same_as_displayed_on_Select_the()
			throws Throwable {
		App app = new App(driver);
		farePD = driver.findElement(By.xpath(app.farePDxpath)).getText();

		System.out.println("Total Amount Displayed on Passenger Details	" + farePD);
		verifyEquals(fareonBD, farePD);
	}

	private void verifyEquals(String fareonBD, String farePD) {
		if (fareonBD.equals(farePD))
			System.out.println("fareonBD & farePD are same");
		else
			System.out.println("fareonBD & farePD are different");

	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		driver.quit();
	}
	

	@When("^Hotels link is visible on the screen$")
	public void hotels_link_is_visible_on_the_screen() throws Throwable {
		App app = new App(driver);
		app.hotelLink();

	}

	@Then("^Search for Hotels in Mumbai$")
	public void search_for_Hotels_in_Mumbai() throws Exception {
		App app = new App(driver);
		app.hotel_Location();

		JavascriptExecutor je2 = (JavascriptExecutor) driver;
		je2.executeScript("document.getElementById('search_hotel').click();");

		app.wait_ex();

		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		je1.executeScript("document.getElementsByTagName('button')[6].click();");

	}

	@Then("^Select location as Andheri$")
	public void select_location_as_Andheri() throws Throwable {

		JavascriptExecutor je3 = (JavascriptExecutor) driver;
		je3.executeScript("document.getElementsByClassName('suggestion-item')[5].click();");

		JavascriptExecutor je2 = (JavascriptExecutor) driver;
		je2.executeScript("document.getElementById('search_hotel').click();");

	}

	@Then("^Verify whether the results are getting displayed for Andheri, Mumbai Location$")
	public void verify_whether_the_results_are_getting_displayed_for_Andheri_Mumbai_Location() throws Throwable {
		App app = new App(driver);
		app.select_Location();

		String totalHotels = driver.findElement(By.xpath(app.totalHotels)).getText();
		String[] totalHotels1 = totalHotels.split("\\ ");
		int totalHotelscount = Integer.parseInt(totalHotels1[0]);
		System.out.println("Total Hotels:	" + totalHotelscount);

		WebElement hotelLocation = driver.findElement(By.xpath(app.hotelLocationxpath));
		String location = hotelLocation.getText();
		String expectedLocation = "Andheri, Mumbai";
		verifyEqualsLocation(location, expectedLocation);
		System.out.println("Hotel Location is	" + location);
	}

	private void verifyEqualsLocation(String location, String expectedLocation) {
		if (location.equals(expectedLocation))
			System.out.println("Hotel Location is	" + location);
	}

	public void viewSeats() throws Exception {
		App app = new App(driver);
		WebElement viewSeats;
		WebDriverWait wait = new WebDriverWait(driver, 40);
		viewSeats = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(app.viewSeatscss)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("alert('Please select seat')");

		Thread.sleep(5000);

		WebElement boardingPoint;

		boardingPoint = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(app.boardingPointcss)));
		boardingPoint.click();

		app.prior_Proceed();

		fareonBD = driver.findElement(By.xpath(app.fareonBDxpath)).getText();
		System.out.println("Amount displayed on Boarding and Dropping Point page " + fareonBD);

	}
*/
}
