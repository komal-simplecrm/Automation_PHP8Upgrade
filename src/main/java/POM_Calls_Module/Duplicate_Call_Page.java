package POM_Calls_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.CommonFunctions;

public class Duplicate_Call_Page 
{
	//Data members should be declare globally with access level private by using @FindBy annotation
		@FindBy(xpath="//input[@id='name']") private WebElement Subject;
		@FindBy(xpath="//input[@id='status']") private WebElement Status;
		@FindBy(xpath="//input[@id='duration_hours']") private WebElement Duration_Hours;
		@FindBy(xpath="//input[@id='asterisk_caller_id_c']") private WebElement Caller_id;
		@FindBy(xpath="//div[@id='timer_popup0']") private WebElement Time_popup;
		@FindBy(xpath="//div[@id='reminders']") private WebElement Reminders;
		//Initialize the constructor with access level public using PageFactory class
			public Duplicate_Call_Page(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
			}
			//Utilize within the methods with access level public
			public String getSubject()
			{
				String subject = Subject.getAttribute("value");
				return subject;
			}
			public String getStatus()
			{
				String status = Status.getAttribute("value");
				return status;
			}

			public String getDuration()
			{
				String duration = Duration_Hours.getAttribute("value");
				return duration;
			}
			public String getPopUpRemainder()
			{
				String popupremainder = CommonFunctions.GetText(Time_popup);
				
				return popupremainder;
			}
			
			public String getEmailRemainder()
			{
				String emailremainder = CommonFunctions.GetText(Reminders);
				
			
				return emailremainder;
			}
			public String getCaller_id()
			{
				String callerid = Caller_id.getAttribute("value");
				return callerid;
			}
}
