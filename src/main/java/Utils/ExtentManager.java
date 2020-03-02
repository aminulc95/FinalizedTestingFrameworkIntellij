package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		if(extent == null)
		{
			CreateInstance("c:/frank1/selenium-extent.html");
		}
		return extent;
	}
	
	public static ExtentReports CreateInstance(String fileName){
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Selenium Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Selenium Automation Report");
		htmlReporter.setAppendExisting(true);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);		
		return extent;
	}
}











