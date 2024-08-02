package POM_Opportunities_Module;

import java.text.ParseException;



import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;

public class Duplicate_Opp_Page 
{
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//input[@id='name']")private WebElement OpportunityName;
	@FindBy(xpath="//input[@id='account_name']")private WebElement AccountName;
	@FindBy(xpath="//input[@id='currency_id']")private WebElement Currency;
	@FindBy(xpath="//input[@id='date_closed']")private WebElement Date_closed;
	@FindBy(xpath="//input[@id='amount']")private WebElement OpportunityAmount;
	@FindBy(xpath="//input[@id='opportunity_type']")private WebElement Type;
	@FindBy(xpath="//input[@id='sales_stage']")private WebElement SalesStage;
	@FindBy(xpath="//input[@id='probability']")private WebElement Probability;
	@FindBy(xpath="//input[@id='next_step']")private WebElement Next_Step;
	@FindBy(xpath="//input[@id='actual_date_closed_c']")private WebElement Actual_date_closed;
	@FindBy(xpath="//input[@id='date_lost_c']")private WebElement Date_lost;
	@FindBy(xpath="//textarea[@id='description']")private WebElement Description;
	@FindBy(xpath="//textarea[@id='reason_for_lost_c']")private WebElement Reason_for_lost;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-account_name']")private WebElement CampaignSearchIcon;
	@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
	@FindBy(xpath="//span[@id='back-btn-link']")private WebElement OpportunityDetailView;
			
			
			//Initialize the constructor with access level public using PageFactory class
			public Duplicate_Opp_Page(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
				
			}

			//Utilize within the methods with access level public

			public String getOpportunityName()
			{
				String OppName=OpportunityName.getAttribute("value");
				return OppName;
			}
			
			public String getAccountName()
			{
				String C = AccountName.getAttribute("value");
				return C;
			}
			
			public String getCurrency()
			{
				String C = CommonFunctions.GetText(Currency);
				return C;
			}
			
			public String[] getDate_closed() throws ParseException
			{
				
				//Get Date_Closed from opportunity Module
				String date_closed = Date_closed.getAttribute("value");
				
				
				String[] date1 = date_closed.split(" ");
				return date1;
				
				
				
			}
			
			public String getOpportunityAmount()
			{
				String C=OpportunityAmount.getAttribute("value");
				System.out.println(C);
				String S = C.replaceAll(",", "");
			
				 String[] D = StringUtils.split(S,".");
				
				
				return D[0];
			}
			
			public String getType()
			{
				String C = Type.getAttribute("value");
				return C;
			}
			
			public String getSalesStage()
			{
				String salesStage = SalesStage.getAttribute("value");
				
				return salesStage;
			
				
			}
			
			public String getNext_Step()
			{
				String C=Next_Step.getAttribute("value");
				return C;
			}
			
			public String getProbability()
			{
				String C=Probability.getAttribute("value");
				return C;
			}
			
			public String[] getActual_date_closed()
			{
				String actual_date_closed = Actual_date_closed.getAttribute("value");
				String[] date1 = actual_date_closed.split(" ");
				return date1;
			}
			
			public String[] getDate_lost()
			{
				 
				String date_lost=Date_lost.getAttribute("value");
				String[] date1=date_lost.split(" ");
				return date1;
			}
			
			public String getDescription()
			{
				String C=Description.getAttribute("value");
				return C;
			}
			
			public String getReason_for_lost()
			{
				String C=Reason_for_lost.getAttribute("value");
				return C;
			}
			
			
			
			//In Duplicate test case compare Date in sheet and Date in record both are in same format DD-MMM-YYYY
			public String dateCompare(String[] FieldName) throws ParseException
			{
				//get Start Date 
				String[] date = FieldName; 
				String ClosedDate = date[0];
				//Split date by -
				String[] dateex=ClosedDate.split("-");
				String month=dateex[1];
				//convert date string to int
				int m=Integer.parseInt(month);  
				//get month name format MMM
				String MonthName=UtilityClass.convertMonth(m);
				//combine date dd-MMM-yyyy
				String ExactclosedDate=dateex[0]+"-"+MonthName+"-"+dateex[2];
				System.out.println(ExactclosedDate);
				return ExactclosedDate;
			}
}
