package Library_Files;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class UtilityClass extends Base_Class
{	
	
	public static String getDataFromPF(String key) throws IOException 
	{
		//Create object FileInputStream class and pass path of the property file
		//Path:right click on package-->properties-->copy Location
		String PropertyFile = System.getProperty("user.dir")+"\\Prop.Properties";
		//To reach up to property file
		FileInputStream file = new FileInputStream(PropertyFile);
		
		//Create object of Properties class to call the load method which is present in Properties class
		Properties prop = new Properties();
		
		//To open Property file non-static method
		prop.load(file);
		
		//To fetch data from property file and stored in the object
		String value1 = prop.getProperty(key);
		
		return value1;
	}
	
	
			
	//Fetch data from excel sheet for Cases Module
		public static String fetchDataFromExcelSheet(String SheetName,int Rowindex, int Cellindex) throws EncryptedDocumentException, IOException
		{
			String ExcelSheetPath=System.getProperty("user.dir")+"\\ExcelData\\TestData1.xlsx";
			//create object of FileInputStream
			FileInputStream file= new FileInputStream(ExcelSheetPath);
			
			
			try
			{
				Sheet SH=WorkbookFactory.create(file).getSheet(SheetName);
				Row row = SH.getRow(Rowindex);
				 Cell cell = row.getCell(Cellindex);
			
				 if(cell.getCellType()==CellType.STRING)
					 return cell.getStringCellValue();
				 /*else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()== CellType.FORMULA)
				 {
					 String cellValue = String.valueOf(cell.getNumericCellValue());
					 if(DateUtil.isCellDateFormatted(cell))
					 {
						 DateFormat dt = new SimpleDateFormat("dd/MMM/yyyy");
						 Date date = cell.getDateCellValue();
						 cellValue = dt.format(date);
					 }
					 return cellValue;
					 
				 }*/
				 else if(cell.getCellType()== CellType.BLANK)
					 return "";
				 else return String.valueOf(cell.getBooleanCellValue());
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				return "";
			}
			
			
		}
		
	
	//Take the Screenshot of fail test case
	public static String ScreenShot(WebDriver driver, String testMethodName) throws IOException
	{
		String ScreenshotPath=System.getProperty("user.dir")+"\\ScreenShot1\\";
		//Type Cast WebDriver object with TakesScreenshot interface and call getScreenshotAs method
		File Scrouce=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//create object of file class and pass the destination folder path in that constructor
		//FileUtils.copyFile(Scrouce, new File("C:\\Users\\admin\\eclipse-workspace\\NewProject\\ScreenShot"+"\\Sample1\\.png"));
		File Dest=new File(ScreenshotPath+testMethodName+".png");
		//Copy that screen shot from one location to the another location so create object of file class first
		FileHandler.copy(Scrouce, Dest);
		return Dest.getAbsolutePath();
	}
	
	//Waiting until element found
	public static void wait_until_element_found(WebDriver driver,WebElement xpath)
	{
		String xpath1=xpath.getText();
		System.out.println(xpath1);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); 
		wait.until(ExpectedConditions.elementToBeClickable(xpath));
		
		
	}
	
	//Waiting until element visible
	public static void wait_until_element_invisible(WebDriver driver,WebElement xpath)
	{
		String xpath1 = xpath.getText();
		System.out.println(xpath1);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); 
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath1)));
	}
	
	//Waiting until element visible
		public static void wait_until_element_presence(WebDriver driver,WebElement xpath)
		{
			String xpath1 = xpath.getText();
			System.out.println(xpath1);
			WebDriverWait myDynamicElement = new WebDriverWait(driver, Duration.ofSeconds(50));
	        myDynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath1)));
		}
		
		public static void wait_until_color_changed(WebDriver driver,WebElement xpath, WebElement panel)
		{
			String xpath1 = xpath.getText();
			System.out.println(xpath1);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
			wait.until(ExpectedConditions.attributeToBe(panel, "style", "background-color: rgb(40, 167, 69);"));
		}
	
	//Select the date in calendar field
	
	public static void selectDates(WebDriver driver, String targetDate, String dateFormat) throws Exception{
	{
		System.out.println("Enter in Select date");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat targetDateFormat=new SimpleDateFormat(dateFormat);
		Date formattedTargetDate;
		try {
			System.out.println("Enter in try block");
			try{
				Thread.sleep(2000);
			targetDateFormat.setLenient(false);
			Thread.sleep(4000);
			formattedTargetDate = targetDateFormat.parse(targetDate);
			calendar.setTime(formattedTargetDate);
			}
			catch(ParseException e)
			{
				Thread.sleep(2000);
				targetDateFormat.setLenient(false);
				Thread.sleep(4000);
				formattedTargetDate = targetDateFormat.parse(targetDate);
				calendar.setTime(formattedTargetDate);
			}
			System.out.println("Parse the date");
			int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
			int targetMonth = calendar.get(Calendar.MONTH);
			int targetYear = calendar.get(Calendar.YEAR);
			System.out.println(targetDay);
			System.out.println(targetMonth);
			System.out.println(targetYear);
			String actualDate=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersSlideTransition-transitionContainer')]//p[contains(@class,'MuiTypography-alignCenter')]")).getText();
			Thread.sleep(2000);
			System.out.println("Parse the actual date");
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
			
			int actualMonth = calendar.get(Calendar.MONTH);
			int actualYear = calendar.get(Calendar.YEAR);
			Thread.sleep(2000);
			while(targetMonth < actualMonth || targetYear < actualYear)
			{
				//System.out.println("Enter to compare1");
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//div[contains(@class,'MuiPickersCalendarHeader-switchHeader')]//button)[1]")).click();
				
				actualDate=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersSlideTransition-transitionContainer')]//p[contains(@class,'MuiTypography-alignCenter')]")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				
				actualMonth= calendar.get(Calendar.MONTH);
				actualYear= calendar.get(Calendar.YEAR);
			
			}
			Thread.sleep(2000);
			while(targetMonth > actualMonth || targetYear > actualYear)
			{
				//System.out.println("Enter to compare2");
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//div[contains(@class,'MuiPickersCalendarHeader-switchHeader')]//button)[2]")).click();
				
				actualDate=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersSlideTransition-transitionContainer')]//p[contains(@class,'MuiTypography-alignCenter')]")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				
				actualMonth= calendar.get(Calendar.MONTH);
				actualYear= calendar.get(Calendar.YEAR);
			}
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@class,'MuiPickersCalendar-transitionContainer')]//button[not(contains(@class,'MuiPickersDay-hidden'))]//span/p[text()='"+targetDay+"']")).click();
		}catch(ParseException e)
		{
			throw new Exception("Invalid date is provided, please check input date");
		}
		
	}
		
}
	
	public static void selectDatesAndTime(WebDriver driver, String targetDate, String dateFormat,String time) throws Exception{
		{
			Calendar calendar= Calendar.getInstance();
			SimpleDateFormat targetDateFormat=new SimpleDateFormat(dateFormat);
			Date formattedTargetDate;
			try {
				targetDateFormat.setLenient(false);
				formattedTargetDate=targetDateFormat.parse(targetDate);
				calendar.setTime(formattedTargetDate);
				
				
				int targetDay=calendar.get(Calendar.DAY_OF_MONTH);
				int targetMonth=calendar.get(Calendar.MONTH);
				int targetYear=calendar.get(Calendar.YEAR);
				System.out.println(targetDay);
				System.out.println(targetMonth);
				System.out.println(targetYear);
				
				String splitTime[] = time.split(" ");
				 String TimeFrame=splitTime[0];
				 String AM_PM=splitTime[1];
				 
				String splittime[] = TimeFrame.split(":");
				//String hours=splittime[0];
				 //String minutes=splittime[1];
				 
				 int hours = Integer.parseInt(splittime[0]);
				 int minutes = Integer.parseInt(splittime[1]);
				 
				 
				 
				 
				 System.out.println(hours);
				 System.out.println(minutes);
				 System.out.println(AM_PM);
				 
				String actualDate=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersSlideTransition-transitionContainer')]//p[contains(@class,'MuiTypography-alignCenter')]")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				
				int actualMonth=calendar.get(Calendar.MONTH);
				int actualYear=calendar.get(Calendar.YEAR);
				Thread.sleep(2000);
				while(targetMonth < actualMonth || targetYear < actualYear)
				{
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//div[contains(@class,'MuiPickersCalendarHeader-switchHeader')]//button)[1]")).click();
					
					actualDate=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersSlideTransition-transitionContainer')]//p[contains(@class,'MuiTypography-alignCenter')]")).getText();
					calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
					
					actualMonth= calendar.get(Calendar.MONTH);
					actualYear= calendar.get(Calendar.YEAR);
				
				}
				Thread.sleep(2000);
				while(targetMonth > actualMonth || targetYear > actualYear)
				{
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//div[contains(@class,'MuiPickersCalendarHeader-switchHeader')]//button)[2]")).click();
					
					actualDate=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersSlideTransition-transitionContainer')]//p[contains(@class,'MuiTypography-alignCenter')]")).getText();
					calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
					
					actualMonth= calendar.get(Calendar.MONTH);
					actualYear= calendar.get(Calendar.YEAR);
				}
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[contains(@class,'MuiPickersCalendar-transitionContainer')]//button[not(contains(@class,'MuiPickersDay-hidden'))]//span/p[text()='"+targetDay+"']")).click();
			
				//TargetTime
				Actions act=new Actions(driver);
				
				//driver.findElement(By.xpath("//button[contains(@class,'MuiButtonBase')]//span//h3")).click();
				WebElement hour=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersBasePicker-container')]//span[text()='"+hours+"']"));
				act.moveToElement(hour).perform();
		    	act.click().perform();
				
				WebElement minute=driver.findElement(By.xpath("//div[contains(@class,'MuiPickersBasePicker-container')]//span[text()='"+minutes+"']"));
				act.moveToElement(minute).perform();
		    	act.click().perform();
		    	
		    	WebElement am_pm=driver.findElement(By.xpath("//button[@type='button']//span//h6[text()='"+AM_PM+"']"));
		    	act.moveToElement(am_pm).perform();
		    	act.click().perform();
		    	
				driver.findElement(By.xpath("//div[contains(@class,'MuiDialogActions')]//button[@type='button']//span[text()='OK']")).click();
			
			
			}catch(ParseException e)
			{
				throw new Exception("Invalid date is provided, please check input date");
			}
			
			
			
		}
		
	
	}
	//Date convert to moth name
	public static String convertMonth(int month)
	{
		String monthString;
    switch (month) {
    case 1:  monthString = "Jan";      break;
    case 2:  monthString = "Feb";      break;
    case 3:  monthString = "Mar";      break;
    case 4:  monthString = "Apr";      break;
    case 5:  monthString = "May";      break;
    case 6:  monthString = "Jun";      break;
    case 7:  monthString = "Jul";      break;
    case 8:  monthString = "Aug";      break;
    case 9:  monthString = "Sep";      break;
    case 10: monthString = "Oct";      break;
    case 11: monthString = "Nov";      break;
    case 12: monthString = "Dec";      break;
    default: monthString = "Invalid month"; break;
}
    return monthString;
		
	}
	
	
	//Page Zoom In
	public static void ZoomIn() throws AWTException
    {
         
		for(int i=0; i<3; i++){
	        Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_MINUS);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_MINUS);
	        }
    }
	
	//Page Zoom Out
	public static void ZoomOut() throws AWTException
    {
         
		for(int i=0; i<3; i++){
	        Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_EQUALS);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_EQUALS);
	        }
    }
	public static void CheckPageLoaded()
	{
	// JavaScript Executor type cast with driver object
    JavascriptExecutor j = (JavascriptExecutor)driver;
    
	//iterate 50 times after every one second to verify if in ready state
    for (int i=0; i<50; i++)
    {
       try {
    	   		Thread.sleep(1000);
       		}catch (InterruptedException ex) 
       			{
       				System.out.println("Page has not loaded yet ");
       			}
       			//again check page state
       if (j.executeScript("return document.readyState").toString().equals("complete"))
       {
    	   System.out.println("Page has loaded");
    	   break;
       }
    }
 }
}
