package Listeners1;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter
{
	public static WebDriver driver;
	 public ExtentSparkReporter spark;
	 public ExtentReports extent;
	 public ExtentTest test;
	public void onStart(ITestContext testContext)
	 {
		//Create object of ExtentReports
			extent = new ExtentReports();
			//Create object ofExtent Spark Reporter and Pass the path of extent report
			spark = new ExtentSparkReporter("target/SkippedTestCases.html");
			//Configure the Theme for extent report
			spark.config().setTheme(Theme.DARK);
			//Give the Name to the extent report
			spark.config().setDocumentTitle("MyReport");
			//Attached the Spark Report to the Spark Reporter
			extent.attachReporter(spark);
		
		System.out.println("report is started");
	 }
	public void onTestStart(ITestResult result)
	 {
		 
		 	
			System.out.println("test is started");
	 }
	public void onTestSkipped(ITestResult tr)
	 {
		 try {
	  test=extent.createTest(tr.getName()); // create new entry in the report
	  
	  test.log(Status.SKIP, tr.getThrowable());
	  test.log(Status.SKIP,MarkupHelper.createLabel(" test is skipped "+ tr.getName(),ExtentColor.ORANGE));
	  System.out.println(" test is skipped");
	 // extent.flush();
	  //extent.flush();
	 // driver.quit();
	  
		 } catch (Exception e) 
		 	{
			   e.printStackTrace();
			 } 
		 
	 }
	public void onFinish(ITestContext testContext)
	 {
	  extent.flush();
	  driver.quit();
	 }
}
