import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelBookingTest {
private WebDriver driver = null;
	
	@BeforeClass
	public void setUpClass() {
		setDriverPath();
		driver = new ChromeDriver();
	}
    
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
        driver.get("https://www.cleartrip.com/");   
        Thread.sleep(2000);
	}
	
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {

    	driver.findElement(By.xpath("//a[@title='Find hotels in destinations around the world']")).click();

        driver.findElement(By.id("Tags")).sendKeys("Indiranagar, Bangalore");
        
        // Ideally we should give dates, which is missing in the code
       
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
    
    @AfterClass
	 //close the browser 
	 public void tearDown() {
		 if(driver!=null) {
				System.out.println("Closing firefox browser");
				driver.quit();
			}
	 }

}
