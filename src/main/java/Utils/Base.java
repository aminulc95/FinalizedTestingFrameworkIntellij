package Utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
	
	public static WebDriver driver;
	public static Library myLib;
	public static ObjectMap objmap;
	public static String workingDir = System.getProperty("user.dir");

	@BeforeMethod
	public void beforeEachTest() {
		myLib = new Library(driver);
		objmap = new ObjectMap(workingDir + "\\objectpropertiesfile\\objectmap.properties");
//		driver = myLib.startChromeBrowser();
		driver = myLib.startHeadlessChromeBrowser();
//		driver = myLib.startFirefoxBrowser();
//		driver = myLib.startIEBrowser();

	}

	@AfterMethod
	public void afterEachTest(ITestResult result) {
		myLib.customWait(5);
		if (driver != null) {
			driver.quit();
		}
	}

}
