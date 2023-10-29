package POM_Notes_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Add_Notes 
{
	//Data members should be declare globally with access level private by using @FindBy annotation
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-contact_name']")private WebElement Contacts;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-scrm_knight_notes_1_name']")private WebElement Knight;
	@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement FirstNameOnWindow;
	@FindBy(xpath="//div[@role='dialog']//input[@id='last_name']")private WebElement LastNameOnWindow;
	@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow;
	//xpath for duplicate functionality
	@FindBy(xpath="//input[@id='contact_name']") private WebElement ContactsName;
	@FindBy(xpath="//input[@id='scrm_knight_notes_1_name']") private WebElement KnightValue;
	@FindBy(xpath="//label[@id='filename']/span/span") private WebElement UploadedFile;
	
	//Initialize the constructor with access level public using PageFactory class
	public Add_Notes(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	//Utilize within the methods with access level public 
	public void selectContacts(WebDriver driver, String contacts) throws InterruptedException 
	{
		Contacts.click();
		String[] sapratename = contacts.split(" ");
		String firstName = sapratename[0];
		String lastName = sapratename[1];
		
		//enter text in first name field
		FirstNameOnWindow.sendKeys(firstName);
		//enter text in last name field
		LastNameOnWindow.sendKeys(lastName);
		//Click on search button		
		SearchBtnOnWindow.click();
		Thread.sleep(2000);
		WebElement CreatedBy = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+contacts+"')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", CreatedBy);
		//Thread.sleep(2000);
		CreatedBy.click();
	}
	
	public void selectKnight(WebDriver driver, String knight) throws InterruptedException 
	{
		Knight.click();
		String[] sapratename = knight.split(" ");
		String firstName = sapratename[0];
		String lastName = sapratename[1];
		
		//enter text in first name field
		FirstNameOnWindow.sendKeys(firstName);
		//enter text in last name field
		LastNameOnWindow.sendKeys(lastName);
		//Click on search button		
		SearchBtnOnWindow.click();
		Thread.sleep(2000);
		WebElement CreatedBy = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+knight+"')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", CreatedBy);
		//Thread.sleep(2000);
		CreatedBy.click();
	}
	
	
	
	//Function for get the Text for Duplicate functionality.
	public String getContactsName() 
	{
		String contactsname = ContactsName.getAttribute("value");
		return contactsname;
	}
	
	public String getKnightValue() 
	{
		String name = KnightValue.getAttribute("value");
		return name;
	}
	
	public String getUploadedFileName() 
	{
		String filename = UploadedFile.getText();
		return filename;
	}
}
