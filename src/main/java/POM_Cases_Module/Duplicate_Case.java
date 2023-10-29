package POM_Cases_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Duplicate_Case {
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//input[@name='case_number']")private WebElement CaseNumber;
	@FindBy(xpath="//input[@id='priority']")private WebElement Priority;
	@FindBy(xpath="//input[@id='state']")private WebElement State;
	@FindBy(xpath="//input[@id='status']")private WebElement Status;
	@FindBy(xpath="//input[@id='type']")private WebElement Type;
	@FindBy(xpath="//input[@id='name']")private WebElement Subject;
	@FindBy(xpath="//input[@id='linkedin_account_c']")private WebElement Linkedin_Account;
	@FindBy(xpath="//body[@id='tinymce']//p")private WebElement Note;
	@FindBy(xpath="//textarea[@id='resolution']")private WebElement Resolution;
	
	//Detail view Xpaths for Sate and Status
	//@FindBy(xpath="//h6[text()='State']/../..//following-sibling::div//h6")private WebElement DetailViewState;
	@FindBy(xpath="//h6//div[@id='status']//span")private WebElement DetailViewStatus;
	//@FindBy(xpath="//h6[text()='Number']/../..//following-sibling::div//h6")private WebElement DetailViewCaseNumber;
	//@FindBy(xpath="//h6[text()='Subject']/../..//following-sibling::div//h6")private WebElement DetailViewSubject;
	

	
	//Initialize the constructor with access level public using PageFactory class
	public Duplicate_Case(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}

	//Utilize within the methods with access level public

	public String getCaseNumber()
	{
		String caseNumber=CaseNumber.getAttribute("value");
		return caseNumber;
	}
	
	public String getPriority()
	{
		String priority=Priority.getAttribute("value");
		return priority;
	}
	
	public String getState()
	{
		String state=State.getAttribute("value");
		
		return state;
	}
	
	public String getStatus()
	{
		String status=Status.getAttribute("value");
		return status;
	}
	
	public String getType()
	{
		String type=Type.getAttribute("value");
		return type;
	}
	
	public String getSubject()
	{
		String subject=Subject.getAttribute("value");
		return subject;
	}
	
	public String getLinkedin_Account()
	{
		String linkedin_account=Linkedin_Account.getAttribute("value");
		return linkedin_account;
	}
	
	public String getNote(WebDriver driver)
	{driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='tox-edit-area__iframe']")));
		String note=Note.getText();
		System.out.println(note);
		driver.switchTo().defaultContent();
		scrollpage(driver);
		return note;
	}
	public String getResolution()
	{
		
		String resolution=Resolution.getText();
		System.out.println(resolution);
		return resolution;
	}
	public void scrollpage(WebDriver driver)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].value ='';", Resolution);
	}

	/*public String getDetailViewState()
	{
		String state = DetailViewState.getText();
		return state;
	}*/
	
	public String getDetailViewStatus()
	{
		String status = DetailViewStatus.getText();
		return status;
	}
	
	/*public String getDetailViewCaseNumber()
	{
		String number = DetailViewCaseNumber.getText();
		return number;
	}
	
	public String getDetailViewSubject()
	{
		String subject = DetailViewSubject.getText();
		return subject;
	}*/
	
	public String getDetailViewText(WebDriver driver, String field)
	{
		String subject = driver.findElement(By.xpath("//h6[text()='"+field+"']/../..//following-sibling::div//h6")).getText();
		return subject;
	}
}
