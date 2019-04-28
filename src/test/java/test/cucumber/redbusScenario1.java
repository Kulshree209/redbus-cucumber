package test.cucumber;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class redbusScenario1 {
	WebDriver driver;
	String fareonBD;
	String farePD;

	//********Browser Setup************
	@Given("^Open the firefox browser Scenario1$")
	public void open_the_firefox_browser_Scenario1() throws Throwable {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addPreference("dom.disable_beforeunload", true);
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
	}

	//********Give the url in console and press ENTER key************
	@Given("^Enter the URL$")
	public void enter_the_URL() throws Throwable {
		App app = new App(driver);
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the url with https	:");
		String url=sc.nextLine();
		//driver.get(app.url);
		driver.get(url);
		app.implcitWait(driver);
	}

	//********Bus ticket searching************
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

	//********Select Departure time and Bus Type************
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

	//********Vie Seats and select seats************
	@Then("^Select the (\\d+) Available Seats,Boarding and Dropping Point$")
	public void select_the_Available_Seats_Boarding_and_Dropping_Point(int arg1) throws Throwable {
		App app = new App(driver);
		app.select_Bus();
		app.view_BusSeats();
		viewSeats();
	}

	//********Proceed with booking***********
	@Then("^Click on Proceed to Book$")
	public void click_on_Proceed_to_Book() throws Throwable {
		App app = new App(driver);
		app.proceed();
	}

	//********Opted out from insurance policy************
	@Then("^On Passenger Details, Select I don't want insurance$")
	public void on_Passenger_Details_Select_I_don_t_want_insurance() throws Throwable {
		App app = new App(driver);
		WebElement passengerDetails = driver.findElement(By.cssSelector(app.passengerDetailscss));

		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,250)");

		JavascriptExecutor noins = (JavascriptExecutor) driver;
		noins.executeScript("document.getElementById('insuranceNotOpted').click();");
	}

	//********Total amount verification************
	@Then("^Verify whether the Total Amount Displayed on Passenger Details is the same as displayed on Select the$")
	public void verify_whether_the_Total_Amount_Displayed_on_Passenger_Details_is_the_same_as_displayed_on_Select_the()
			throws Throwable {
		App app = new App(driver);
		farePD = driver.findElement(By.xpath(app.farePDxpath)).getText();
		System.out.println("Total Amount Displayed on Passenger Details	" + farePD);
		verifyEquals(fareonBD, farePD);
	}

	//********Closing browser************
	@Then("^close the browser Scenario1$")
	public void close_the_browser_Scenario1() throws Throwable {
		driver.quit();
	}

	//********Comparison of the amount************
	private void verifyEquals(String fareonBD, String farePD) {
		if (fareonBD.equals(farePD))
			System.out.println("fareonBD & farePD are same");
		else
			System.out.println("fareonBD & farePD are different");
	}
	
	//********Providing the Alert on the screen to select seats************
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
	
}
