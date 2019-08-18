package core;

import java.io.Writer;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.WebDriver;

public class Linear {
	
	static WebDriver driver;
	static String url;
	static Properties p = new Properties();
	static Writer report;
	static String browser;
      
   public static void main(String[] args) throws Exception {

   System.setProperty("webdriver.gecko.driver", "./resources/webdrivers/mac/geckodriver.sh");
   WebDriver driver = new SafariDriver();
   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
   driver.get("http://alex.academy/exe/signup/www/index.php");

   driver.findElement(By.id("id_fname")).sendKeys("John");
   driver.findElement(By.id("id_lname")).sendKeys("Smith");
   driver.findElement(By.id("id_email")).sendKeys("vis@gmail.com");
   driver.findElement(By.id("id_phone")).sendKeys("415 555-0002");
   driver.findElement(By.id("id_submit_button")).click();
   
   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
System.out.println(driver.getTitle().equals("Confirmation") ? "pass" : "fail");
 




//driver.quit();    
	
   }
	
}
	
