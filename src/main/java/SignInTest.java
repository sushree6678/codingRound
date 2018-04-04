import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest {
private	WebDriver driver = null;
	
	@BeforeClass
	public void setUpClass() {
		setDriverPath();
		 System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		 driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		waitFor(2000);
	}

	@BeforeMethod
	public void setUp() {

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click(); 
	}

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
 
        
    //Here we have to use frame handling concept for element clicking in frame window because here iframe is present
        driver.switchTo().frame("modal_window");
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        System.out.println(errors1);
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
        driver.switchTo().defaultContent();
             
       // waitFor(5000);
        
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
