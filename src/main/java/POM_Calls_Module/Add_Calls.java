package POM_Calls_Module;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Library_Files.UtilityClass;


public class Add_Calls 
{
	//Data members should be declare globally with access level private by using @FindBy annotation
	@FindBy(xpath="//input[@id='name']") private WebElement Subject;
	@FindBy(xpath="//input[@id='status']") private WebElement Status;
	@FindBy(xpath="//input[@id='duration_hours']") private WebElement Duration_Hours;
	@FindBy(xpath="//input[@id='duration']") private WebElement Duration;
	@FindBy(xpath="//input[contains(@class,'MuiSwitch-input')]") private WebElement Popup;
	@FindBy(xpath="//div[@id='reminder_time']") private WebElement Reminder_Time;
	@FindBy(xpath="//div[@id='email_reminder_time']") private WebElement EmailReminder_Time;
	//@FindBy(xpath="//span[text()=' Popup']/..//input[contains(@class,'MuiSwitch-input')]") private WebElement PopUuRadioBtn;
	//@FindBy(xpath="//span[text()=' Email']/..//input[contains(@class,'MuiSwitch-input')]") private WebElement EmailRadioBtn;
	@FindBy(xpath="//span[text()='Add Reminder']") private WebElement Add_Reminder;
	@FindBy(xpath="//p[text()='Reminder Time:']/..//span[contains(text(),'Popup')]/..//input[contains(@class,'MuiSwitch-input')]") private WebElement PopUpRadioBtn;
	@FindBy(xpath="//p[text()='Reminder Time:']/..//span[contains(text(),'Email')]/..//input[contains(@class,'MuiSwitch-input')]") private WebElement EmailRadioBtn;
	@FindBy(xpath="//label[contains(text(),'Reminder')]/..//label[contains(text(),'Popup')]/..//input[contains(@class,'PrivateSwitchBase')]") private WebElement PopUpRadioBtnReminders;
	@FindBy(xpath="//label[contains(text(),'Reminder')]/..//label[contains(text(),'Email')]/..//input[contains(@class,'PrivateSwitchBase')]") private WebElement EmailRadioBtnReminders;
	@FindBy(xpath="//div[@id='timer_popup0']") private WebElement Time_popup;
	//@FindBy(xpath="//div[@id='reminders']") private WebElement Reminders;
	@FindBy(xpath="//input[@id='asterisk_caller_id_c']") private WebElement Caller_id;
	
	@FindBy(xpath="//span[text()='Users']//*[local-name()='svg']") private WebElement UsersInRemainder;
	@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement FirstNameOnWindow;
	@FindBy(xpath="//div[@role='dialog']//input[@id='last_name']")private WebElement LastNameOnWindow;
	@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow2;
	@FindBy(xpath="//span[text()='Contacts']//*[local-name()='svg']") private WebElement ContactsInRemainder;
	@FindBy(xpath="//span[text()='Leads']//*[local-name()='svg']") private WebElement LeadsInRemainder;
	@FindBy(xpath="//span[text()='Select']") private WebElement Select;
	@FindBy(xpath="//span[text()='Search']")private WebElement SearchBtnOnWindow1;
	@FindBy(xpath="(//button[@type='submit']//span[text()='Save'])[2]")private WebElement SaveBottomButton;
	
	//Calls Module Drop down
	@FindBy(xpath="//form[@id='filter-form']//input[@id='direction']")private WebElement Direction;
	@FindBy(xpath="//form[@id='filter-form']//input[@id='status']")private WebElement FilterStatus;
	@FindBy(xpath="//form[@id='filter-form']//div[@id='date_start_range_choice']")private WebElement StartDate;
	@FindBy(xpath="//form[@id='filter-form']//div[@id='date_end_range_choice']")private WebElement EndDate;
	@FindBy(xpath="//form[@id='filter-form']//button[@id='start_range_date_entered']")private WebElement StartDateCreated;
	@FindBy(xpath="//form[@id='filter-form']//button[@id='end_range_date_entered']")private WebElement EndDateCreated;
	@FindBy(xpath="//form[@id='filter-form']//button[@id='range_date_entered']")private WebElement DateCreatedDynamicField;	
	@FindBy(xpath="//label[contains(text(),'Reminder')]")private WebElement RemainderWord;
	@FindBy(xpath="//*[local-name()='svg' and @id='search-btn-assigned_user_name']")private WebElement AssignedToSearchIcon;
	//Initialize the constructor with access level public using PageFactory class
	public Add_Calls(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilize within the methods with access level public
	public String enterSubject(String subject)
	{	String s = Keys.chord(Keys.CONTROL, "a");
		Subject.sendKeys(s);
		Subject.sendKeys(Keys.DELETE);
		Subject.sendKeys(subject);
		String Sub=Subject.getAttribute("value");
		return Sub;
	
	}
	
	public String selectStatus(WebDriver driver, String status)
	{
		Status.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+status+"']")).click();
		String status1=Status.getText();
		
		return status1;
	
	}
	
	public String enterDuration_Hours(String hours)
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		Duration_Hours.sendKeys(s);
		Duration_Hours.sendKeys(Keys.DELETE);
		Duration_Hours.sendKeys(hours);
		String duration=Duration_Hours.getAttribute("value");
		return duration;
	
	}
	public String enterDuration(WebDriver driver, String hours)
	{
		Duration.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+hours+"']")).click();
		String duration = Duration.getAttribute("value");
		return duration;
	}
	
	public void selectPopUpRadioBtn(WebDriver driver, String timePrior)
	{
		PopUpRadioBtn.click();
		boolean result= PopUpRadioBtn.isSelected();
		if(result==true)
		{
			Reminder_Time.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+timePrior+"']")).click();
		}
		else 
		{
			System.out.println("Popup Radio Button is not selected");
		}
	}
	
	public void selectEmailRadioBtn(WebDriver driver, String timePrior)
	{
		EmailRadioBtn.click();
		boolean result= EmailRadioBtn.isSelected();
		if(result==true)
		{
			EmailReminder_Time.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+timePrior+"']")).click();
		}
		else 
		{
			System.out.println("Email Radio Button is not selected");
		}
	}
	
	public void clickOnAdd_Reminder()
	{
		Add_Reminder.click();
		
	}
	
	public void selectPopUpRadioBtnReminders(WebDriver driver, String timePrior, int i) throws InterruptedException
	{
		//PopUpRadioBtnReminders.click();
		boolean result= PopUpRadioBtnReminders.isSelected();
		if(result==true)
		{
			//Time_popup.click();
			driver.findElement(By.xpath("//input[@id='timer_popup"+i+"']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+timePrior+"']")).click();
		}
		else 
		{
			System.out.println("Popup Radio Button is not selected");
			PopUpRadioBtnReminders.click();
			//Time_popup.click();
			driver.findElement(By.xpath("//input[@id='timer_popup"+i+"']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+timePrior+"']")).click();
		}
	}
	
	public void selectEmailRadioBtnReminders(WebDriver driver, String timePrior, int i) throws InterruptedException
	{
		//EmailRadioBtnReminders.click();
		boolean result = EmailRadioBtnReminders.isSelected();
		if(result==true)
		{
			//Reminders.click();
			driver.findElement(By.xpath("//div[@id='reminder-card-"+i+"']//input[@id='timer_email"+i+"']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+timePrior+"']")).click();
		}
		else 
		{
			System.out.println("Email Radio Button is not selected");
			EmailRadioBtnReminders.click();
			//Reminders.click();
			driver.findElement(By.xpath("//div[@id='reminder-card-"+i+"']//input[@id='timer_email"+i+"']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+timePrior+"']")).click();
		}
	}
	
	public void enterCaller_id(String caller_id)
	{
		Caller_id.sendKeys(caller_id);
		
	}
	
	public void scrollpage(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Caller_id);
	}
	public void InRemainder(WebDriver driver, String name) throws InterruptedException
	{	
			SearchBtnOnWindow1.click();
			Thread.sleep(5000);
		
			driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+name+"')]/../../../..//input")).click();
		
	}
	
	

	public void entertimebefore(WebDriver driver, String timePrior, int i) throws InterruptedException
	{
		//i- Remainder Id id='timer_popup"+i+"' for POP up
		//i- Remainder ID id='reminder-div-"+i+"' for Email 
		selectPopUpRadioBtnReminders(driver, timePrior, i );
		selectEmailRadioBtnReminders(driver, timePrior, i );
	}
	public void addInviteesInRemainder(WebDriver driver, int i, String Invitees, String nameOfInvitees) throws InterruptedException
	{
		
		driver.findElement(By.xpath("//div[@id='reminder-card-"+i+"']//span[text()='"+Invitees+"']//*[local-name()='svg']")).click();
		String[] sapratename = nameOfInvitees.split(" ");
		String firstName = sapratename[0];
		String lastName = sapratename[1];
		
		//Clear the field
		String s = Keys.chord(Keys.CONTROL, "a");
		FirstNameOnWindow.sendKeys(s);
		FirstNameOnWindow.sendKeys(Keys.DELETE);
		//enter text in first name field
		FirstNameOnWindow.sendKeys(firstName);
		//Clear the field
		String s1 = Keys.chord(Keys.CONTROL, "a");
		LastNameOnWindow.sendKeys(s1);
		LastNameOnWindow.sendKeys(Keys.DELETE);
		//enter text in last name field
		LastNameOnWindow.sendKeys(lastName);
		SearchBtnOnWindow2.click();
		Thread.sleep(7000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",SearchBtnOnWindow2);
		//WebElement name = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+nameOfInvitees+"')]"));
		WebElement checkBox = driver.findElement(By.xpath("(//table[@role='grid']//tbody//td//a[contains(text(),'"+nameOfInvitees+"')]/../../../..//input)[1]"));
		//Thread.sleep(2000);
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",name);
		Thread.sleep(1000);
		checkBox.click();
		Thread.sleep(1000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Select);
		Thread.sleep(1000);
		Select.click();
		
	}
	
	public void addMoreUsers()
	{
		UsersInRemainder.click();
	}
	public void selectUsers()
	{
		Select.click();
	}
	
	public void addMultipleInviteesInRemainder(WebDriver driver,String Invitees, String nameOfInvitees) throws InterruptedException
	{
		
		driver.findElement(By.xpath("//div[@id='reminder-card-0']//span[text()='"+Invitees+"']//*[local-name()='svg']")).click();
		//SearchBtnOnWindow1.click();
		String[] sapratename = nameOfInvitees.split(",");
		String Name1 = sapratename[0];
		String Name2 = sapratename[1];
		
		String[] arr = {Name1,Name2};
		
		
		for(int i=0;i<2;i++)
		{
			
			String[] name1 = arr[i].split(" ");
			String firstname1 = name1[0];
			String lastname1 = name1[1];
			String s = Keys.chord(Keys.CONTROL, "a");
			FirstNameOnWindow.sendKeys(s);
			FirstNameOnWindow.sendKeys(Keys.DELETE);
			FirstNameOnWindow.sendKeys(firstname1);
			String s1 = Keys.chord(Keys.CONTROL, "a");
			LastNameOnWindow.sendKeys(s1);
			LastNameOnWindow.sendKeys(Keys.DELETE);
			LastNameOnWindow.sendKeys(lastname1);
			SearchBtnOnWindow2.click();
			Thread.sleep(1000);
			String Name = sapratename[i];
			driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+Name+"')]/../../../..//input")).click();
			
		}
	
			//driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+Name+"')]/../../../..//input")).click();
			//driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+Name2+"')]/../../../..//input")).click();
			Select.click();
	}
	public void scrollUpTotime(WebDriver driver, int i)
	{
		WebElement removeRemainder = driver.findElement(By.xpath("//div[@id='reminder-card-"+i+"']//input[@id='timer_popup"+i+"']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",removeRemainder);
	}
	
	public void scrollpageuptoDuration(WebDriver driver)
	{
		WebElement Remaindertext = driver.findElement(By.xpath("//label[contains(text(),'Duration')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Remaindertext);
	}
	public void scrollUpToAdd_Reminder(WebDriver driver)
	{
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Add_Reminder);
	}
	
	//Calls Module

	public void selectDirection(WebDriver driver, String direction) 
	{
		Direction.click();
			
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+direction+"')]")).click();
	}
	public void selectFilterStatus(WebDriver driver, String status) 
	{
		FilterStatus.click();
			
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+status+"')]")).click();
	}
	public void selectStartDate(WebDriver driver, String option, String date) throws Exception 
	{
		StartDate.click();
		
		WebElement element = driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+option+"')]"));
		element.click();
		Thread.sleep(2000);
		WebElement options = driver.findElement(By.xpath("//form[@id='filter-form']//div[@id='date_entered_range_choice']//span"));
		
		String Option = options.getText();
		System.out.println(Option);
		if(Option.equals("Is Between"))
		{
			String[] sapratename = date.split(",");
			String startdate = sapratename[0];
			String enddate = sapratename[1];
			
			StartDateCreated.click();
			UtilityClass.wait_until_element_found(driver, StartDateCreated);
			UtilityClass.selectDates(driver, startdate, "dd-MMM-yyyy");
			
			EndDateCreated.click();
			UtilityClass.wait_until_element_found(driver, EndDateCreated);
			UtilityClass.selectDates(driver, enddate, "dd-MMM-yyyy");
		}
		else if(Option.equals("Equals")||Option.equals("Not On")||Option.equals("After")||Option.equals("Before"))
		{
		DateCreatedDynamicField.click();
		UtilityClass.wait_until_element_found(driver, DateCreatedDynamicField);
		UtilityClass.selectDates(driver, date, "dd-MMM-yyyy");
		}
	
	}
	public void selectEndDate(WebDriver driver, String option, String Date) throws Exception 
	{
		EndDate.click();
		
		WebElement element = driver.findElement(By.xpath("//ul[@role='listbox']//li//span[contains(text(),'"+option+"')]"));
		element.click();
		Thread.sleep(2000);
		WebElement options = driver.findElement(By.xpath("//form[@id='filter-form']//div[@id='date_entered_range_choice']//span"));
		
		String Option = options.getText();
		System.out.println(Option);
		if(Option.equals("Is Between"))
		{
			String[] sapratename = Date.split(",");
			String startdate = sapratename[0];
			String enddate = sapratename[1];
			
			StartDateCreated.click();
			UtilityClass.wait_until_element_found(driver, StartDateCreated);
			UtilityClass.selectDates(driver, startdate, "dd-MMM-yyyy");
			
			EndDateCreated.click();
			UtilityClass.wait_until_element_found(driver, EndDateCreated);
			UtilityClass.selectDates(driver, enddate, "dd-MMM-yyyy");
		}
		else if(Option.equals("Equals")||Option.equals("Not On")||Option.equals("After")||Option.equals("Before"))
		{
		DateCreatedDynamicField.click();
		UtilityClass.wait_until_element_found(driver, DateCreatedDynamicField);
		UtilityClass.selectDates(driver, Date, "dd-MMM-yyyy");
		}
		
	}
	public void scrolluptoAssignedTo(WebDriver driver)
	{
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",AssignedToSearchIcon);
	}
	
	public void scrolluptoRemainderWord(WebDriver driver)
	{
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",RemainderWord);
	}
	
}
