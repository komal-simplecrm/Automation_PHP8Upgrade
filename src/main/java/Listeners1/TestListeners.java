package Listeners1;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Library_Files.Base_Class;
import Library_Files.UtilityClass;

import com.aventstack.extentreports.markuputils.MarkupHelper;
public class TestListeners extends TestListenerAdapter
{
	public static WebDriver driver;
	 public ExtentSparkReporter spark;
	 public ExtentReports extent;
	 public ExtentTest test;
	public void onStart(ITestContext testContext)
	 {
		//Create object of ExtentReports
			extent =new ExtentReports();
			//Create object ofExtent Spark Reporter and Pass the path of extent report
			spark= new ExtentSparkReporter("target/Reports1.html");
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
		 
		 	/*//Create object of ExtentReports
			extent =new ExtentReports();
			//Create object ofExtent Spark Reporter and Pass the path of extent report
			spark= new ExtentSparkReporter("target/Reports.html");
			//Configure the Theme for extent report
			spark.config().setTheme(Theme.DARK);
			//Give the Name to the extent report
			spark.config().setDocumentTitle("MyReport");
			//Attached the Spark Report to the Spark Reporter
			extent.attachReporter(spark);*/
			System.out.println("test is started");
	 }
	  /*htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/myReport.html");//specify location of the report
	  htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
	  
	  extent=new ExtentReports();
	  
	  extent.attachReporter(htmlReporter);
	  extent.setSystemInfo("Host name","localhost");
	  extent.setSystemInfo("Environemnt","QA");
	  extent.setSystemInfo("user","pavan");
	  
	  htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
	  htmlReporter.config().setReportName("Functional Testing"); // name of the report
	  htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
	  htmlReporter.config().setTheme(Theme.STANDARD);
	 }*/
	 public void onTestSuccess(ITestResult tr)
	 {
		test=extent.createTest(tr.getName()); // create new entry in the report
		 test.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
		
		//test.pass(MethodName+ " TestCase is Passed");
		 test.pass(MarkupHelper.createLabel(tr.getName()+" Test case Passed", ExtentColor.GREEN));
		 //extent.flush();*/
		  System.out.println("TestCase is Passed");
		 // extent.flush();
		  //driver.quit();
	 }
	 
}
	 
	 /*public void onTestFailure(ITestResult tr) 
	 {
		test=extent.createTest(tr.getName()); // create new entry in the report
		 String MethodName = tr.getMethod().getMethodName();
		 System.out.println(MethodName);
		 //test.log(Status.FAIL,MarkupHelper.createLabel(" test is failed"+ tr.getName(),ExtentColor.RED));
		 test.fail(tr.getThrowable());
			String path=null;
		 try {
				
					path = Base_Class.ScreenShot(driver, MethodName);
					//test.addScreenCaptureFromPath(path);
					 test.log(Status.FAIL, " This test case is fail").addScreenCaptureFromPath(path);
				//test.log(Status.FAIL,MarkupHelper.createLabel(" test is failed"+ tr.getName(),ExtentColor.RED)).addScreenCaptureFromPath(path);
					 test.fail(MarkupHelper.createLabel(tr.getName()+" Test case Failed", ExtentColor.RED)).addScreenCaptureFromPath(path);
				 
			}catch (Exception e)
			
			{e.printStackTrace();
			
			//test.addScreenCaptureFromPath(path);
			}
			}
				/*String path;
				try {
					path = UtilityClass.ScreenShot(driver, MethodName);
					UtilityClass.ScreenShot(driver, MethodName);
					test.fail(tr.getThrowable()).addScreenCaptureFromPath(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
	 }
	  /*try {
	  String MethodName = tr.getMethod().getMethodName();
		
		String path = UtilityClass.ScreenShot(driver, MethodName);
		
		
		UtilityClass.ScreenShot(driver, MethodName);
	  test.log(Status.FAIL,MarkupHelper.createLabel(" test is failed"+ tr.getName(),ExtentColor.RED)).addScreenCaptureFromPath(path);
	  test.fail(tr.getThrowable());
	  }catch (Exception e) {
		    e.printStackTrace();
		} 
	  String screenshotPath=System.getProperty("user.dir")+"\\ScreenShot1\\"+tr.getName()+".png";
	  try {
		  String MethodName = tr.getMethod().getMethodName();
			
			//String path = UtilityClass.ScreenShot(driver, MethodName);
			
			
			UtilityClass.ScreenShot(driver, MethodName);
			
			System.out.println("TestCase is Failed Screen Shot Captured");
			
			//test.fail(MethodName+ " TestCase is Failed").addScreenCaptureFromPath(path);
			//test.fail(MarkupHelper.createLabel(tr.getName()+" Test case Failed", ExtentColor.RED)).addScreenCaptureFromPath(path);
			test.log(Status.FAIL,MarkupHelper.createLabel(" test is failed"+ tr.getName(),ExtentColor.RED)).addScreenCaptureFromPath(screenshotPath); // send the passed information to the report with GREEN color highlighted
			test.fail(tr.getThrowable());
			extent.flush();
			  driver.quit();
	   System.out.println(" test is failed");
	   extent.flush();
		  driver.quit();
	  }*/
	  //extent.flush();
		  
	  // catch (Exception e) {
	    //e.printStackTrace();
	  //} 
/*try {String filename =  String.format("Screenshot-%s.jpg",Calendar.getInstance().getTimeInMillis());
TakesScreenshot ts= (TakesScreenshot)driver;
File source= ts.getScreenshotAs(OutputType.FILE);
String dest="./screenshot"+filename;
File snapshotDest = new File(dest);
FileUtils.copyFile(source, snapshotDest);
Reporter.log("Screen Shot file" +dest);

}catch(Exception e)
{
	throw new RuntimeException("Failed to take Screenshot", e);
}*/
	 
	 /*public void onTestSkipped(ITestResult tr)
	 {
		 try {
	  test=extent.createTest(tr.getName()); // create new entry in th report
	  test.log(Status.SKIP,MarkupHelper.createLabel(" test is skipped"+ tr.getName(),ExtentColor.ORANGE));
	  System.out.println(" test is skipped");
	 // extent.flush();
	  //extent.flush();
	 // driver.quit();
	  
		 } catch (Exception e) {
			   e.printStackTrace();
			  } 
	 }

	 
	public void onFinish(ITestContext testContext)
	 {
	  extent.flush();
	  driver.quit();
	 }
}
	 
	/*public void onTestSkipped(ITestResult result) 
	{
		
		try {
	        test.skip(MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.YELLOW));
	        extent.flush();
	    }catch(NullPointerException e)
		{
	    	test.skip(MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.YELLOW));
	        extent.flush();
			System.out.println("NullPointerException thrown!");
			
		}*/
	
		/*try {
		test =extent.createTest(result.getName());
		//test .log(Status.SKIP, result.getThrowable());
		//test .log(Status.SKIP, result.getName());
		test .log(Status.SKIP,  MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));
		}catch(NullPointerException e)
		{
			System.out.println("NullPointerException thrown!");
			
		}
		*/
		
			/*if(result.getStatus()==ITestResult.SKIP)
		test.skip(MarkupHelper.createLabel(result.getName()+" Test case Skipped", ExtentColor.YELLOW));
		test.skip(result.getThrowable());
		
		}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		} 
	
	@Override
	public void onConfigurationSkip(ITestResult result) {
		test.info(String.format("Skipped Configuaration for: %s",result.getMethod().getMethodName()));*/
	
	
	

