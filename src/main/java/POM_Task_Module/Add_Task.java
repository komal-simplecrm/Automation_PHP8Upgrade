package POM_Task_Module;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.UtilityClass;

public class Add_Task 
{
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//input[@id='name']")private WebElement Subject;
	@FindBy(xpath="//input[@id='status']")private WebElement Status;
	@FindBy(xpath="//button[@id='date_start']")private WebElement Date_Start;
	@FindBy(xpath="//input[@id='parent_name']")private WebElement RelatedTo;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-parent_name']")private WebElement RelatedToDynamic;
	
	@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow1;
	@FindBy(xpath="//button[@id='date_due']")private WebElement Date_Due;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-contact_name']")private WebElement ContachNameSearchIcon;
	@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement FirstNameOnWindow;
	@FindBy(xpath="//div[@role='dialog']//input[@id='last_name']")private WebElement LastNameOnWindow;
	@FindBy(xpath="//span[text()='Search']")private WebElement SearchBtnOnWindow2;
	@FindBy(xpath="//input[@id='priority']")private WebElement Priority;
	
	
	//Xpath on windows
	@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
	@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement windowFirstName;
	@FindBy(xpath="//div[@role='dialog']//input[@id='bug_number']")private WebElement windowBugNumber;
	@FindBy(xpath="//div[@role='dialog']//input[@id='case_number']")private WebElement windowCaseNumber;
	
	//Calls Module
	@FindBy(xpath="//form[@id='filter-form']//input[@id='parent_name']")private WebElement FilterRelatedTo;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-parent_name']")private WebElement FilterRelatedToDynamic;
		
	//Initialize the constructor with access level public using PageFactory class
	public Add_Task(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilize within the methods with access level public
	public String enterSubject(String subject)
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		Subject.sendKeys(s);
		Subject.sendKeys(Keys.DELETE);
		Subject.sendKeys(subject);
		String S=Subject.getAttribute("value");
		return S;
	
	}
	
	public void clickOnStatus(WebDriver driver, String status)
	{
		Status.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+status+"']")).click();
	
	}
	
	public String selectStartDate(WebDriver driver, String date, String time) throws Exception
	{	String s = Keys.chord(Keys.CONTROL, "a");
		Date_Start.sendKeys(s);
		Date_Start.sendKeys(Keys.DELETE);
		Date_Start.click();
		UtilityClass.wait_until_element_found(driver, Date_Start);
		UtilityClass.selectDatesAndTime(driver, date, "dd-MMM-yyyy", time);
		String datestart = Date_Start.getAttribute("value");
		return datestart;
	}
	
	public String clickRelatedTo(WebDriver driver, String relatedTo, String name) throws InterruptedException
	{
		
		RelatedTo.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+relatedTo+"']")).click();
		
		if(relatedTo.equals("Account")||relatedTo.equals("Task")||relatedTo.equals("Opportunity")||relatedTo.equals("Project")||relatedTo.equals("Project Task")|| 
				relatedTo.equals("Meeting")|| relatedTo.equals("Call")||relatedTo.equals("Quote")||relatedTo.equals("Invoice"))
		{
			Thread.sleep(1000);
			RelatedToDynamic.click();
			windowName.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+name+"')]")).click();
		}
		else if(relatedTo.equals("Contact")|| relatedTo.equals("Lead")||relatedTo.equals("Target"))
		{
			RelatedToDynamic.click();
			String[] sapratename = name.split(" ");
			String firstName = sapratename[0];
			String lastName = sapratename[1];
			
			//enter text in first name field
			FirstNameOnWindow.sendKeys(firstName);
			//enter text in last name field
			LastNameOnWindow.sendKeys(lastName);
			
			
			
			//windowFirstName.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+name+"')]")).click();
		}
		else if(relatedTo.equals("Bug"))
		{
			RelatedToDynamic.click();
			windowBugNumber.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(4000);
			//driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a")).click();
			driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a")).click();
		}
		else if(relatedTo.equals("Ticket")||relatedTo.equals("Case"))
		{
			RelatedToDynamic.click();
			windowCaseNumber.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(4000);
			//driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a")).click();
			driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a")).click();
		}
		
		
		String related = RelatedTo.getAttribute("value");
		return related;
	}
	
	
	public void selectDueDate(WebDriver driver, String date, String time) throws Exception
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		Date_Due.sendKeys(s);
		Date_Due.sendKeys(Keys.DELETE);
		Date_Due.click();
		UtilityClass.wait_until_element_found(driver, Date_Due);
		UtilityClass.selectDatesAndTime(driver, date, "dd-MMM-yyyy", time);
	
	}
	
	public void enterContactName(WebDriver driver, String contactName) throws InterruptedException
	{
		ContachNameSearchIcon.click();
		String[] sapratename = contactName.split(" ");
		String firstName = sapratename[0];
		String lastName = sapratename[1];
		
		//enter text in first name field
		FirstNameOnWindow.sendKeys(firstName);
		//enter text in last name field
		LastNameOnWindow.sendKeys(lastName);
		
		SearchBtnOnWindow1.click();
		Thread.sleep(2000);
		WebElement Contact = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+contactName+"')]"));
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", Contact);
		Thread.sleep(2000);
		Contact.click();
	}
	
	public void selectPriority(WebDriver driver, String priority)
	{
		Priority.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+priority+"']")).click();
	
	}
	
	public void clickOnSubjectInListView(WebDriver driver, String subject)
	{
		
		driver.findElement(By.xpath("//a[contains(text(),'"+subject+"')]")).click();
	
	}
	
	//Filter RelatedTo field function
	public String clickOnFilterRelatedTo(WebDriver driver, String relatedTo, String name) throws InterruptedException
	{
		FilterRelatedTo.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+relatedTo+"']")).click();
		
		if(relatedTo.equals("Account")||relatedTo.equals("Task")||relatedTo.equals("Opportunity")||relatedTo.equals("Project")||relatedTo.equals("Project Task")|| 
				relatedTo.equals("Meeting")|| relatedTo.equals("Call")||relatedTo.equals("Quote")||relatedTo.equals("Invoice"))
		{
			Thread.sleep(1000);
			FilterRelatedToDynamic.click();
			windowName.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(2000);
			
			WebElement element = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+name+"')]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
			Thread.sleep(4000);
			element.click();
		}
		else if(relatedTo.equals("Contact")|| relatedTo.equals("Lead")||relatedTo.equals("Target"))
		{
			FilterRelatedToDynamic.click();
			windowFirstName.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(2000);
			WebElement element = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+name+"')]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
			element.click();
		}
		else if(relatedTo.equals("Bug"))
		{
			FilterRelatedToDynamic.click();
			windowBugNumber.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(3000);
			//driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a")).click();
			WebElement element = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
			element.click();
		}
		else if(relatedTo.equals("Ticket")||relatedTo.equals("Case"))
		{
			FilterRelatedToDynamic.click();
			windowCaseNumber.sendKeys(name);
			SearchBtnOnWindow1.click();
			Thread.sleep(3000);
			//driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a")).click();
			WebElement element = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+name+"']/../..//a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
			element.click();
		}
		
		
		String related = FilterRelatedTo.getAttribute("value");
		return related;
	}
	
	
}
