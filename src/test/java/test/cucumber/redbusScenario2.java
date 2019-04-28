package test.cucumber;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class redbusScenario2 {
	
	WebDriver driver;
	
	//********Browser Setup************
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
	
	//********Give the url in console and press ENTER key************
	@Given("^Enter the URL Sc2$")
	public void enter_the_URL_Sc2() throws Throwable {
		App app = new App(driver);
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the url with https	:");
		String url=sc.nextLine();
		//driver.get(app.url);
		driver.get(url);
		app.implcitWait(driver);
	}

	//********Click on hotel link from the redbus page************
	@When("^Hotels link is visible on the screen$")
	public void hotels_link_is_visible_on_the_screen() throws Throwable {
		App app = new App(driver);
		app.hotelLink();
	}

	//********Search hotels from mumbai************
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

	//********Select location -> Andheri************
	@Then("^Select location as Andheri$")
	public void select_location_as_Andheri() throws Throwable {
		JavascriptExecutor je3 = (JavascriptExecutor) driver;
		je3.executeScript("document.getElementsByClassName('suggestion-item')[5].click();");

		JavascriptExecutor je2 = (JavascriptExecutor) driver;
		je2.executeScript("document.getElementById('search_hotel').click();");
	}

	//********Result of the search with Andheri location************
	@Then("^Verify whether the results are getting displayed for Andheri, Mumbai Location$")
	public void verify_whether_the_results_are_getting_displayed_for_Andheri_Mumbai_Location() throws Throwable {
		App app = new App(driver);
		app.select_Location();
		String totalHotels = driver.findElement(By.xpath(app.totalHotels)).getText();
		String[] totalHotels1 = totalHotels.split("\\ ");
		int totalHotelscount = Integer.parseInt(totalHotels1[0]);
		System.out.println("Total Hotels:	" + totalHotelscount);
		app.wait_ex();		
		WebElement hotelLocation = driver.findElement(By.xpath(app.hotelLocationxpath));
		String location = hotelLocation.getText();
		String expectedLocation = "Andheri, Mumbai";
		verifyEqualsLocation(location, expectedLocation);
		System.out.println("Hotel Location is	" + location);
	}

	//********Verification of the result************
	private void verifyEqualsLocation(String location, String expectedLocation) {
		if (location.equals(expectedLocation))
			System.out.println("Hotel Location is	" + location);
	}
	
	//********Closing browser************
	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		driver.quit();
	}
	
}
