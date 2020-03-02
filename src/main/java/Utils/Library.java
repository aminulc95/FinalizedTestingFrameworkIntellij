package Utils;

import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Library {
	final static Logger logger = Logger.getLogger(Library.class);
	public ObjectMap objmap;
	private WebDriver driver;

	public Library(WebDriver _driver) {
		driver = _driver;
	}
	
	//------------------------------------------------------------------------------------------------
	public WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			logger.info("Chrome browser started");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}
	//------------------------------------------------------------------------------------------------
	public WebDriver startHeadlessChromeBrowser() {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("enable-automation");
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			options.addArguments("--dns-prefetch-disable");
			options.addArguments("--disable-gpu");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver(options);
			logger.info("Chrome browser started");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}
	//------------------------------------------------------------------------------------------------
	public WebDriver startFirefoxBrowser() {
		try {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}
	//------------------------------------------------------------------------------------------------
	public WebDriver startIEBrowser() {
		try {
			System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}
	//------------------------------------------------------------------------------------------------
	public void GoToWebsite(String websiteURL) {
		try {
		driver.get(websiteURL);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
	}
	//------------------------------------------------------------------------------------------------
	public void enterTextField(By by, String userInputString) {
		try {
			WebElement element = driver.findElement(by);
			element.sendKeys(userInputString);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
	}
	//------------------------------------------------------------------------------------------------
	public void clickButton(By by) {
		try {
			WebElement button = driver.findElement(by);
			button.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
	}
	//------------------------------------------------------------------------------------------------
	public WebDriver waitUntilVisibleElement(By by) {
		try {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return driver;
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}
	//------------------------------------------------------------------------------------------------
	public WebDriver changeToNewTab(int tabDrop, int tabKeep) {
		try {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabDrop));
		driver.close();
		driver.switchTo().window(tabs.get(tabKeep));
		return driver;
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}
	//------------------------------------------------------------------------------------------------
	public WebDriver selectFromDropdown(WebElement by, String index) {
		try {
		Select counrty = new Select(by);
		counrty.selectByValue(index);
		return driver;
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
			//System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}
	//------------------------------------------------------------------------------------------------
	public String getCurrentTime() {
		String currentTime = null;
		try {
			Date date = new Date();
			String tempDate = new Timestamp(date.getTime()).toString();
			currentTime = tempDate.replace(" ", "_").replace(":", "_").replace(".", "_").replace("-", "_");
			// System.out.println("date string: '" +currentTime +"'");
		} catch (Exception e) {
			logger.error("Error: ", e);	
			assertTrue(false);			
		}
		return currentTime;
	}
	//------------------------------------------------------------------------------------------------
	public void checkDirectory(String inputPath) {
		File file = new File(inputPath);
		String abPath = file.getAbsolutePath();
		File file2 = new File(abPath);
		if (!file2.exists()) {
			if (file2.mkdirs()) {
				System.out.println("folders created...");
			} else {
				System.out.println("folders not created...");
			}
		}
	}
	//------------------------------------------------------------------------------------------------
	public String captureScreenshot(String screenshotFileName, String filePath) {

		String screenshotPath = null;
		String timestamp = getCurrentTime();
		try {
			if (!filePath.isEmpty()) {
				checkDirectory(filePath);
				screenshotPath = filePath + screenshotFileName + timestamp + ".png";
			} else {
				checkDirectory("target/screenshots/");
				screenshotPath = "target/screenshots/" + screenshotFileName + timestamp + ".png";
			}

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File(screenshotPath));
		} catch (Exception e) {
			logger.error("Error: ", e);	
			assertTrue(false);			
		}
		System.out.println("Screenshot Captured: " + screenshotPath);
		return screenshotPath;
	}
	//------------------------------------------------------------------------------------------------
	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);	
			assertTrue(false);			
		}
	}
	//------------------------------------------------------------------------------------------------
	public String captureScreenshotForExtent(String screenshotFileName, String filePath) {

		String screenshotPath = null;
		String timestamp = getCurrentTime();
		try {
			if (!filePath.isEmpty()) {
				checkDirectory(filePath);
				screenshotPath = filePath + screenshotFileName + ".png";
			} else {
				checkDirectory("target/screenshots/");
				screenshotPath = "target/screenshots/" + screenshotFileName + timestamp + ".png";
			}

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File(screenshotPath));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		System.out.println("Screenshot Captured: " + screenshotPath);
		return screenshotPath;
	}

}
