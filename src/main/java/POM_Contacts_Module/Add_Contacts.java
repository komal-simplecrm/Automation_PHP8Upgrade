package POM_Contacts_Module;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Add_Contacts 
{
	//Data members should be declare globally with access level private by using @FindBy annotation
	
	@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-account_name']")private WebElement AccountNameSearchIcon;
	@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow;
	@FindBy(xpath="//input[@id='twitter_handle_c']")private WebElement TwitterHandle;
	@FindBy(xpath="//input[@id='user_name_c']")private WebElement UserName;
	@FindBy(xpath="//input[@id='password_c']")private WebElement Password;
	@FindBy(xpath="//input[@id='portal_active_user_c']")private WebElement Portal_active_userCheckBox;
	@FindBy(xpath="//input[@id='report_to_name']")private WebElement ReportsTo;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-report_to_name']")private WebElement ReportsToSearchIcon;
	@FindBy(xpath="//div[@role='dialog']//span[text()='Search']")private WebElement SearchOnWindow;
	@FindBy(xpath="//button[@aria-haspopup='menu'or @title='More Action']")private WebElement Menu;
	@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement windowFirstName;
	@FindBy(xpath="//div[@role='dialog']//input[@id='last_name']")private WebElement LastNameOnWindow;
	//Xpath of detail view for Sales flow
	@FindBy(xpath="//div[contains(@class,'MuiCardHeader-content')]//span[1]")private WebElement ContactNameInDetailView;
	//Initialize the constructor with access level public using PageFactory class
	public Add_Contacts(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//Utilize within the methods with access level public
	/*public void enterAccountName(WebDriver driver,String accountname)
	{
		AccountNameSearchIcon.click();
		windowName.sendKeys(accountname);
		SearchOnWindow.click();
		driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[text()='"+accountname+"']")).click();
		
	}*/
	
	public void enterTwitterHandle(String twitterhandle)
	{
		
		TwitterHandle.sendKeys(twitterhandle);
		
	}
	
	public void enterUserName(String username)
	{
		
		UserName.sendKeys(username);
		
	}
	
	public void enterPassword(String password)
	{
		
		Password.sendKeys(password);
		
	}
	
	public void enterReportsTo(WebDriver driver,String reportsTo) throws InterruptedException
	{
		ReportsToSearchIcon.click();
		//SearchOnWindow.click();
		String[] sapratename = reportsTo.split(" ");
		String firstName = sapratename[0];
		String lastName = sapratename[1];
		
		//Clear the field
		String s = Keys.chord(Keys.CONTROL, "a");
		windowFirstName.sendKeys(s);
		windowFirstName.sendKeys(Keys.DELETE);
		//enter text in first name field
		windowFirstName.sendKeys(firstName);
		//Clear the field
		String s1 = Keys.chord(Keys.CONTROL, "a");
		LastNameOnWindow.sendKeys(s1);
		LastNameOnWindow.sendKeys(Keys.DELETE);
		
		//enter text in last name field
		LastNameOnWindow.sendKeys(lastName);
		
		SearchOnWindow.click();
		Thread.sleep(5000);
		WebElement name= driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[text()='"+reportsTo+"']"));  
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", name);
		
		name.click();
		Thread.sleep(2000);
	}
	
	public void Portal_active_userCheckBox()
	{
		
		Portal_active_userCheckBox.click();
		
	}
	
	/*public void ScrollPageUptoRep(WebDriver driver)
	{
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Password);
		
	}*/
	public void clickOnSaveBtnOnLocationPopup(WebDriver driver) throws InterruptedException
	{
		
		//Actions act=new Actions(driver);
		//act.moveToElement(hour).perform();
    	//act.click().perform();
		 Alert alert = driver.switchTo().alert();
		// Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        Thread.sleep(5000);
        		
        // Accepting alert		
        alert.accept();	
	}
	
	//Utilize within the methods with access level public
	public String getTwitterHandle()
	{
		String twitterhandle = TwitterHandle.getAttribute("value");
		return twitterhandle;
	}
			
	public String getUserName()
	{
		String username = UserName.getAttribute("value");
		return username;
	}
	
	public String getPassword()
	{
		String password = Password.getAttribute("value");
		return password;
	}
	
	public String getReportsTo()
	{
		String reportsTo = ReportsTo.getAttribute("value");
		System.out.println(reportsTo);
		return reportsTo;
	}
	
	public String VerifyContactName()
	{
		String contactname = ContactNameInDetailView.getText();
		return contactname;
	}
	
}
