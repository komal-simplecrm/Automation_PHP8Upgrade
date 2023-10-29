package POM_Task_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Duplicate_Task_Page 
{
		//Data members should be declare globally with access level private by using @findBy annotation
		@FindBy(xpath="//input[@id='name']")private WebElement Subject;
		@FindBy(xpath="//input[@id='status']")private WebElement Status;
		@FindBy(xpath="//input[@id='date_start']")private WebElement Date_Start;
		@FindBy(xpath="//input[@id='date_due']")private WebElement Date_Due;
		@FindBy(xpath="//input[@id='parent_name']")private WebElement RelatedTo;
		@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-parent_name']/../../../..//input[@id='parent_name']")private WebElement RelatedToDynamic;
		@FindBy(xpath="//input[@id='contact_name']")private WebElement Contact_Name;
		@FindBy(xpath="//input[@id='priority']")private WebElement Priority;
		
		//Initialize the constructor with access level public using PageFactory class
		public Duplicate_Task_Page(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilize within the methods with access level public
		public String getSubject()
		{
			
			String Sub=Subject.getAttribute("value");
			return Sub;
		
		}
		public String getStatus()
		{
			
			String status=Status.getAttribute("value");
			return status;
		
		}
		
		public String[] getDateStart()
		{
			
			String date_start=Date_Start.getAttribute("value");
			String[] date1=date_start.split(" ");
			return date1;
		
		}
		
		public String[] getDateDue()
		{
			
			String date_dueTime=Date_Due.getAttribute("value");
			String[] date=date_dueTime.split(" ");
		
			return date;
		
		}
		
		public String getRelatedTo()
		{
			
			String relatedTo=RelatedTo.getAttribute("value");
			return relatedTo;
		
		}
		
		public String getRelatedToDynamic(String lead)
		{
			String relatedTodynamic = null;
			relatedTodynamic = RelatedToDynamic.getAttribute("value");
			if(lead.equalsIgnoreCase("lead"))
			{
				String sapratename[] = relatedTodynamic.split("\\s", 2);
				String firstName = sapratename[0];
				relatedTodynamic = sapratename[1];
				System.out.println("firstName: "+firstName+ "and lastName: "+relatedTodynamic);
			}
			
			return relatedTodynamic;
		
		}
		
		public String getContact_Name()
		{
			
			String contact_name=Contact_Name.getAttribute("value");
			return contact_name;
		
		}
		
		public String getPriority()
		{
			
			String priority=Priority.getAttribute("value");
			return priority;
		
		}
}
