package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Function {
	static WebDriver driver;
	static String url;
	static Properties p = new Properties();
	static Writer report;
	static long start;
	static long finish;
	static String browser;
    
	//open 
	
	public static void open(String browser, String url)throws IOException {
		Logger.getLogger("").setLevel(Level.OFF);
		p.load(new FileInputStream("./input.properties"));
		Common.open(browser, p.getProperty("url"));
		
}
	//isElementPresent 
	
	static boolean isElementPresent(By by) {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty()) return true; else return false;}
	
	//getSize
	
	static String getSize(By by) {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			// return driver.findElement(by).getRect().getDimension()
			return driver.findElement(by).getSize().toString().replace(", ", "x"); else return "null";}
   //getLocation
	
	static String getLocation(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equals("Safari")) return "(0x0)";
		else {
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
		return driver.findElement(by).getRect().getPoint().toString().replace(", ", "x"); 
		else return "null";}
		}
 //setValue
	
	static void setValue(By by, String value) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			 driver.findElement(by).sendKeys(value);}
//getValue
	
	static String getValue(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && 
			 driver.findElement(by).isDisplayed() && driver.findElement(by).getTagName().equalsIgnoreCase("input"))
		return driver.findElement(by).getAttribute("value").toString().trim();

		else if (!driver.findElements(by).isEmpty() && 
				  driver.findElement(by).isDisplayed() && driver.findElement(by).getTagName().equalsIgnoreCase("span"))
			return driver.findElement(by).getText().trim();
		else return "null";}

	//submit
	
	static void submit(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			driver.findElement(by).submit();}

	//getOS 
	
	static String getOS() {
		return ((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().trim();
			}
	//getBrowser
	
	static String getBrowser() {
		String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString().trim();
		return browser.replaceFirst(String.valueOf(browser.charAt(0)), String.valueOf(browser.charAt(0)).toUpperCase());
		// return browser.substring(0,1).toUpperCase() + browser.substring(1).toLowerCase();
			}
	//getFileName
	
	static String getFileName() {
		String file = driver.getCurrentUrl().toString().trim();
		return file.substring(file.lastIndexOf('/') + 1);
			}
   //waitTitlePage
	
	static void waitTitlePage(String title){
		WebDriverWait wait = new WebDriverWait(driver, 15); 
		wait.until(ExpectedConditions.titleIs(title));
		}
	
	//writeReportHeader
	
	static void writeReportHeader(Writer report) throws IOException {
		report.write("#,Browser,Page,Field,isPresent,Value,Size,Location" + "\n");
		System.out.print("#,Browser,Page,Field,isPresent,Value,Size,Location" + "\n");
		}
   
	//writeReportLine
	
	static void writeReportLine(String index, String fieldName, By by, Writer report) throws IOException {
		
		report.write(
				index + "," + 
				Common.getBrowser() + "," + 
				Common.getFileName() + "," + 
				fieldName + "," + 
				Common.isElementPresent(by) + "," +
				Common.getValue(by) + "," +
				Common.getSize(by) + "," +
				Common.getLocation(by) + "\n");
		
	System.out.print(
				index + "," + 
				Common.getBrowser() + "," + 
				Common.getFileName() + "," + 
				fieldName + "," + 
				Common.isElementPresent(by) + "," +
				Common.getValue(by) + "," +
				Common.getSize(by) + "," +
				Common.getLocation(by) + "\n");
		}
	public static void pageSignUpValidation (String url) {
		
		Common.open(browser, p.getProperty("url"));
		System.out.println("Page URL: " + driver.getCurrentUrl());
		System.out.println("01. Element [id_fname]: " + (p.getProperty("id_fname") != null? "Pass": "Fail"));
	}
	public static void pageConfirmationValidation (String url) {
	
		System.out.println("Page URL: " + driver.getCurrentUrl());
		System.out.println("04. Element [id_phone]: " + (p.getProperty("id_phone") != null? "Pass": "Fail"));
		
		}
	
	
	public static void main(String[] args) throws Exception {
		// browser = "firefox";
		browser = System.getProperty("browser"); // -Dbrowser="firefox"
		if(browser == null) {System.err.println("Please provide browser: -Dbrowser=firefox"); System.exit(0);}
		else if(!browser.equalsIgnoreCase("chrome") &&
				!browser.equalsIgnoreCase("firefox") &&
				!browser.equalsIgnoreCase("safari") &&
				!browser.equalsIgnoreCase("edge")) 
				{System.err.println("Framework supports only: Chrome, Firefox, Safari or Edge"); System.exit(0);}
		
		SignUp.open(browser);
		SignUp.validate();
		Confirmation.validate();
		Common.quit();
		
	}
	}
	
//signup validation
	
	
	
	
	         
	
	
				    		