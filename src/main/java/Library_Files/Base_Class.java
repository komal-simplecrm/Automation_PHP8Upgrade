package Library_Files;


import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Login_Page.Login_Page;


//@Listeners(Listeners1.TestListeners.class)
public class Base_Class 
{	
	//Globally declare WebDriver object so that we can call it any where
	public static WebDriver driver = null;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	public static WebDriver Tempdriver ;
	public static WebDriver  Firfoxdriver ;
	
	@Parameters("browserName")
	
	@BeforeMethod(alwaysRun = true)
	public void initializeBrowser(@Optional("Chrome") String browserName) throws IOException, InterruptedException
	{
		
		if(browserName.equals("Chrome"))
		{
			   // Step-I: Set path of chromedriver.exe file
			  //Parameter-I: Name of the browser
			String openBrowserPath=System.getProperty("user.dir")+"\\Browsers\\chromedriver.exe";
			  //Parameter-II: Path of chromedriver.exe file
			
			System.setProperty("webdriver.chrome.driver",openBrowserPath);
			//Create object of ChromeDriver class and provide referance of WebDriver interface
			//This below two lines are added because the chrome does not support direct https and pass the object of ChromeOptions
			//into the ChromeDriver.
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			//options.addArguments("--remote-allow-origins=*","--ignore-certificate-errors");
			//options.addArguments("--remote-allow-origins=*","-headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
			
			driver = new ChromeDriver(options);
			
		
			
		}else if (browserName.equals("Firefox"))
		{
			// Step-I: Set path of Firefoxdriver.exe file
			  //Parameter-I: Name of the browser
			String openBrowserPath=System.getProperty("user.dir")+"\\Browsers\\geckodriver.exe";
			//String openBrowserPath=System.getProperty("user.dir")+"\\Browsers\\msedgedriver.exe";
			
			  //Parameter-II: Path of Firefoxdriver.exe file
			System.setProperty("webdriver.gecko.driver",openBrowserPath);
			//Create object of FirefoxDriver class and provide reference of WebDriver interface
			 driver = new FirefoxDriver();
			
		}
		
		
		//Pass the URL on the Browser
		
		//driver.get("https://hackatonteam1.simplecrmdev.com/login");
		//driver.get("https://automatephp8.simplecrmdev.com/");
		//driver.get("https://automatephp8v267.simplecrmdev.com/");
		driver.get("https://automatephp82.simplecrmdev.com/login");
		//Maximize the Browser
		driver.manage().window().maximize();
		//implicit Wait 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Login_Page login= new Login_Page(driver);
		
		//Login as admin User
		//login.Login("UN","PSW");
		//Latest creds
		login.Login("un","psw");
		//login.LoginAdmin1("UN","PSW");
		
	}
	
	
	@BeforeSuite(alwaysRun = true)
	public void extentReport()
	{
		//Create object of ExtentReports
		extent =new ExtentReports();
		//Create object ofExtent Spark Reporter and Pass the path of extent report
		spark= new ExtentSparkReporter("target/Reports.html");
		//Configure the Theme for extent report
		spark.config().setTheme(Theme.DARK);
		//Give the Name to the extent report
		spark.config().setDocumentTitle("MyReport");
		//Attached the Spark Report to the Spark Reporter
		extent.attachReporter(spark);
		
		
	}
	
	
	@AfterMethod(alwaysRun = true) 
	public void getResult(ITestResult result) throws IOException
	{
		
		if(result.getStatus()==ITestResult.FAILURE) 
		{	String MethodName = result.getMethod().getMethodName();
		
			String path = UtilityClass.ScreenShot(driver, MethodName);
			
			
			UtilityClass.ScreenShot(driver, MethodName);
			
			System.out.println("TestCase is Failed Screen Shot Captured");
			
			//test.fail(MethodName+ " TestCase is Failed").addScreenCaptureFromPath(path);
			test.fail(MarkupHelper.createLabel(result.getName()+" Test Case Failed", ExtentColor.RED)).addScreenCaptureFromPath(path);
			test.fail(result.getThrowable());
			
		}
		
		else if(result.getStatus()==ITestResult.SUCCESS)
			{
				
						System.out.println("TestCase is Passed");
							//test.pass(MethodName+ " TestCase is Passed");
						test.pass(MarkupHelper.createLabel(result.getName()+" Test Case Passed", ExtentColor.GREEN));
			} 
		
		//Flush the report in the Extent Report
				extent.flush();
		//Flush the report
		tearDown();

	}
	
	public void tearDown() throws IOException
	{
		
		//Close the Browser
		//driver.close();
		driver.quit();
	}
	
	
	
	
	public static String ScreenShot(WebDriver driver, String testMethodName) throws IOException
	{
		
		//Type Cast WebDRiver object with TakesScreenshot interface and call getScreenshotAs method
		File Scrouce=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//create object of file class and pass the destination folder path in that constructor
		File Dest=new File(testMethodName+"_"+".png");
		
		//Copy that screen shot from one location to the another location so create object of file class first
		FileHandler.copy(Scrouce, Dest);
		return Dest.getAbsolutePath();
	}
	
	
	public void initializeBrowserForOpprotunitiesToFeedbackFlow(String browserName, String UN, String PSW) throws IOException, InterruptedException
	{
		if (browserName.equals("Firefox"))
		{
			// Step-I: Set path of Firefoxdriver.exe file
			  //Parameter-I: Name of the browser
			String openBrowserPath=System.getProperty("user.dir")+"\\Browsers\\geckodriver.exe";
			//String openBrowserPath=System.getProperty("user.dir")+"\\Browsers\\msedgedriver.exe";
			
			  //Parameter-II: Path of Firefoxdriver.exe file
			System.setProperty("webdriver.gecko.driver",openBrowserPath);
			//Create object of FirefoxDriver class and provide referance of WebDriver interface
			
			
			
			 Firfoxdriver = new FirefoxDriver();
			   
			 
			 Firfoxdriver.get("https://automatephp82.simplecrmdev.com/login");
			//Maximize the Browser
			 Firfoxdriver.manage().window().maximize();
			//implicit Wait 
			 Firfoxdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
			Login_Page login= new Login_Page(Firfoxdriver);
			//Login as admin User
			login.LoginAdmin(UN,PSW);
			//login.Login("un","psw");
			//Thread.sleep(2000);
			//Firfoxdriver.get(URL);
		}
		else if (browserName.equals("Edge"))
		{
			// Step-I: Set path of Edgedriver.exe file
			  //Parameter-I: Name of the browser
			
			String openBrowserPath = System.getProperty("user.dir")+"\\Browsers\\msedgedriver.exe";
			
			  //Parameter-II: Path of Edge.exe file
			System.setProperty("webdriver.edge.driver",openBrowserPath);
			
			//Create object of EdgeDriver class and provide reference of WebDriver interface
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("--remote-allow-origins=*");
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--remote-allow-origins=*");
			 Tempdriver=new EdgeDriver(edgeOptions);
			
			 Tempdriver.get("https://automatephp82.simplecrmdev.com/login");
			//Maximize the Browser
			 Tempdriver.manage().window().maximize();
			//implicit Wait 
			 Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			Login_Page login= new Login_Page(Tempdriver);
			//Login as admin User
			login.LoginAdmin(UN,PSW);
			//login.Login("un","psw");	
		}
	}
}
