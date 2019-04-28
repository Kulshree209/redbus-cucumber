package test.cucumber;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

public class App 
{
	
	public static final Logger log = Logger.getLogger(App.class);
	WebDriver driver;
	String url ="https://www.redbus.in";
	String srcIntialChars="Mu";
	String destination = "Nasik";
	String departuretime="//div[@id='filter-block']/div[1]/div[2]/ul[2]/li[4]/label[1]";
	String location = "Mumbai";
	String totalHotels="//div[@id='hotels_meta']/div[1]/span[1]";
	String hotelLocationxpath="//ul[@id='hotel_items']/li[1]/div[1]/div[1]/div[3]/div[3]/div[1]"; 
	String fareonBDxpath="//li[@id='4238412']/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/span[2]/span[2]";
	String boardingPointcss="div.radio-unchecked";
	String viewSeatscss="div.seat-container-wrapper";
	String departureTimexpath ="//div[@id='filter-block']/div[1]/div[2]/ul[2]/li[4]/label[1]";
	String bustypeNonACxpath="//div[@id='filter-block']/div[1]/div[2]/ul[3]/li[4]/label[1]";
	String passengerDetailscss="div.cusinfo-sub-components";
	String farePDxpath="//div[@id='root']/div[1]/div[4]/div[3]/div[1]/div[1]/div[2]/span[1]";
	
	@FindBy(xpath="//input[@id='src']")
	WebElement source;
	
	@FindBy(xpath="//section[@id='search']/div[1]/div[1]/div[1]/ul[1]/li[1]")
	WebElement selectSource;
	
	@FindBy(xpath="//input[@id='dest']")
	WebElement dest;
	
	@FindBy(xpath="//div[@id='rb-calendar_onward_cal']/table[1]/tbody[1]/tr[7]/td[2]")
	WebElement calenderDate;
	
	@FindBy(css="button.fl.button#search_btn")
	WebElement searchbtn;
	
	@FindBy(xpath="//div[@id='filter-block']/div[1]/div[2]/ul[2]/li[4]/label[1]")
	WebElement departure;
	
	@FindBy(xpath="//li[@id='4238412']/div[1]/div[2]/div[1]")
	WebElement busSelection;
	
	@FindBy(css="div.button.view-seats.fr")
	WebElement viewBusSeats;
	
	@FindBy(xpath="//div[@id='page_main_header']/nav[1]/ul[1]/li[2]/a[1]")
	WebElement hotelLink;
	
	@FindBy(xpath="//input[@id='search_key']")
	WebElement hotelLocation;
	
	@FindBy(xpath="//ul[@id='location']/li[6]/div[1]/label[1]")
	WebElement selectLocation;
	
	@FindBy(css="button.button.continue.inactive")
	WebElement proceedbtn;
	
	@FindBy(css="button.button.continue.inactive.text-trans-uc.w-h-cont")
	WebElement priorproceedbtn;
	
	public App(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setup() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addPreference("dom.disable_beforeunload", true);
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver=new FirefoxDriver(options);
		driver.manage().window().maximize();
	}
	
	public void implcitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void scrollDown() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,250)");
	}

	public void search_Hotel() {
		JavascriptExecutor je2 = (JavascriptExecutor) driver;
		je2.executeScript("document.getElementById('search_hotel').click();");
	}
	
	public void notification_Hotel() {
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		je1.executeScript("document.getElementsByTagName('button')[6].click();");	
	}
	
	public void suggestion() {
		JavascriptExecutor je3 = (JavascriptExecutor) driver;
		je3.executeScript("document.getElementsByClassName('suggestion-item')[5].click();");
	}
	
	public void wait_ex() throws Exception {
		Thread.sleep(2000);
	}
	
	public void hotel_Count() {
		String totalHotels=driver.findElement(By.xpath("//div[@id='hotels_meta']/div[1]/span[1]")).getText();
		String[] totalHotels1=totalHotels.split("\\ ");
		int totalHotelscount = Integer.parseInt(totalHotels1[0]);
		System.out.println("Total Hotels:	" + totalHotelscount);	
	}
	
	public void verifyLocation() {
		WebElement hotelLocation=driver.findElement(By.xpath("//ul[@id='hotel_items']/li[1]/div[1]/div[1]/div[3]/div[3]/div[1]"));
		String location=hotelLocation.getText();
		String expectedLocation="Andheri, Mumbai";
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public boolean enter_Source() throws InterruptedException {
		source.sendKeys(srcIntialChars);
		return false;	
	}
    
	public boolean select_Source() throws InterruptedException {
		selectSource.click();
		return false;
	}
	
	public boolean enter_select_Destination() throws InterruptedException {
		WebElement destination1=dest;
		destination1.sendKeys(destination);
		destination1.sendKeys(Keys.ENTER);
		destination1.sendKeys(Keys.TAB);
		log.info("Destination Selected " +destination);
		return false;
	}
	
	public boolean select_CalDate() throws InterruptedException {
		calenderDate.click();
		return false;
	}
	
	public boolean handlenotification() throws InterruptedException {
		Thread.sleep(5000);
		return false;
	}
	
	public boolean search_Click() throws InterruptedException {
		searchbtn.click();
		return false;
	}
    
	public boolean select_DepartureTime() throws InterruptedException {
		WebElement departureTime=departure;
		WebDriverWait wait=new WebDriverWait(driver, 40);
		departureTime= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(departuretime)));
		departureTime.click();
		return false;
	}
		
	public boolean select_Bus() throws InterruptedException {
		busSelection.click();
		return false;
	}
	
	public boolean view_BusSeats() throws InterruptedException {
		viewBusSeats.click();
		return false;
	}
	
	public boolean hotelLink() throws InterruptedException {
		hotelLink.click();
		return false;
	}
	
	public boolean hotel_Location() throws InterruptedException {
		hotelLocation.sendKeys(location);
		return false;	
	}
	
	
	public boolean select_Location() throws InterruptedException {
		selectLocation.click();
		return false;	
	}
	
	public boolean prior_Proceed() throws InterruptedException {
		priorproceedbtn.click();
		return false;	
	}
	
	public boolean proceed() throws InterruptedException {
		WebElement proceed=proceedbtn;
		proceed.click();
		proceed.sendKeys(Keys.TAB);
		return false;
	}
	
}
