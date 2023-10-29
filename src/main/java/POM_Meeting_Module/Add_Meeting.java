package POM_Meeting_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.UtilityClass;

public class Add_Meeting 
{

		//Data members should be declare globally with access level private by using @FindBy annotation
		@FindBy(xpath="//button[@id='date_end']") private WebElement Date_end;
		@FindBy(xpath="//input[@id='location']") private WebElement Location;
		@FindBy(xpath="//div[@id='duration']") private WebElement Duration;
		
		
		//Xpath for Duplicate functionality
		@FindBy(xpath="//input[@id='date_end']") private WebElement dateend;
		public Add_Meeting(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilize within the methods with access level public        
		public String selectDateEnd(WebDriver driver, String date, String time) throws Exception
		{	String s = Keys.chord(Keys.CONTROL, "a");
			Date_end.sendKeys(s);
			Date_end.sendKeys(Keys.DELETE);
			Date_end.click();
			UtilityClass.wait_until_element_found(driver, Date_end);
			UtilityClass.selectDatesAndTime(driver, date, "dd-MMM-yyyy", time);
			String datestart = Date_end.getAttribute("value");
			return datestart;
		}
		
		public void enterLocation(String location)
		{
			Location.sendKeys(location);
		}

		public void selectDuration(WebDriver driver, String duration) throws InterruptedException
		{
			Duration.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@role='listbox']//li//span[text()='"+duration+"']")).click();
		}

		//Get the data for Duplicate Functionality not created separate class
		public String[] getDateEnd()
		{
			String EndDate = dateend.getAttribute("value");
			String[] date1=EndDate.split(" ");
			return date1;
			
		}
		
		public String getLocation()
		{
			String location = Location.getAttribute("value");
			
			return location;
		}

		public String getDuration()
		{
			String duration = Duration.getText();
			return duration;
		}
		//i = number of invites
		public String getInvitee(WebDriver driver, int i)
		{
			String Invitee = driver.findElement(By.xpath("(//div[@id='reminder-card-0']//span[contains(@class,'MuiChip-label')])["+i+"]")).getText();
			return Invitee;
		}






}
